package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by OUC on 2016/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)//单元测试注解
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})//要加载的spring文件

public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        int insert = successKilledDao.insertSuccessKilled(1000, 18906390283L);
        System.out.println("insertCount=" + insert);
    }

    @Test
    public void queryByidWithSeckill() throws Exception {
        SuccessKilled successKilleds = successKilledDao.queryByidWithSeckill(1000,18906390283L);
        System.out.println(successKilleds.toString());
        System.out.println(successKilleds.getSeckill().toString());
    }

}