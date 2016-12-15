package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by OUC on 2016/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)//junit4启动时加载springioc容器
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})//告诉junit需要加载的文件
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
        Date date=new Date();
        int update= seckillDao.reduceNumber(1000,date);
        System.out.print("update="+update);
    }

    @Test
    public void queryById() throws Exception {
        long seckillId=1000;
        Seckill seckill=seckillDao.queryById(seckillId);
        System.out.print(seckill.getName());
        System.out.print(seckill.toString());
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills=seckillDao.queryAll(0,10);
        for (Seckill seckill:seckills){
            System.out.print(seckill+"\n");
        }
    }

}