package org.seckill.sevice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.ExcuteSeckill;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by OUC on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"" +
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeckillService seckillServicel;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckills = seckillServicel.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill=seckillServicel.getById(1000);
        logger.info("seckill={}",seckill);

    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer=seckillServicel.exportSeckillUrl(1000);
        logger.info("exposer=" +exposer.toString() );

    }

    @Test
    public void excuteSeckill() throws Exception {
        long id=1000;
        long userPhone=18906390287L;
        String md5="dbeaeb45c5f4739f30ca9f82cfa960fb";
        ExcuteSeckill excuteSeckill= seckillServicel.excuteSeckill(id,userPhone,md5);
        logger.info("result={}",excuteSeckill);

    }

}