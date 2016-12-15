package org.seckill.sevice.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.ExcuteSeckill;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillState;
import org.seckill.exception.RepeatSeckill;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.sevice.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by OUC on 2016/12/10.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());//注意日志
    @Resource
    private SeckillDao seckillDao;
    @Resource
    private SuccessKilledDao successKilledDao;
    private final String slat = "sjjflfdj$#(&sfjlsj><>?";

    @Override

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
            //秒杀未开启
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public ExcuteSeckill excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatSeckill, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        //执行秒杀：减库存+记录秒杀信息
        Date nowTime = new Date();

        try {
            int reduce = seckillDao.reduceNumber(seckillId, nowTime);

            if (reduce <= 0) {
                //没有更新记录，秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录秒杀信息
                int insertSeckill = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertSeckill <= 0) {
                    //已经执行到插入阶段，如若出现问题，可以预见的是因为重复秒杀
                    throw new RepeatSeckill("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByidWithSeckill(seckillId, userPhone);
                    return new ExcuteSeckill(seckillId, SeckillState.SUCCESS, successKilled);

                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatSeckill e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner erro");
        }
    }
}
