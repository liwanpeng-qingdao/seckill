package org.seckill.sevice;

import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.ExcuteSeckill;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatSeckill;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 1.方法的粒度，不要过于关注方法的实现
 * 2.参数尽量简洁明了
 * 3.返回值类型
 * Created by OUC on 2016/12/10.
 */
public interface SeckillService {
    /**
     * 获取所有的秒杀
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 输出秒杀地址
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    ExcuteSeckill excuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatSeckill, SeckillCloseException;

}
