package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by OUC on 2016/12/9.
 */
@Component
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId 秒杀的id
     * @param killTime 秒杀的时间（判断秒杀是否开始，以决定是否执行update）
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime")Date killTime);

    /**
     * 根据秒杀的id查询秒杀信息
     * @param seckillId 秒杀id
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询所有的秒杀信息（连个参数是偏移量查询的参数，用于分页查询较好）
     * @param offset 查询开始位置
     * @param limit 偏移量
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int  limit);

}
