package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;
import org.springframework.stereotype.Repository;

/**
 * Created by OUC on 2016/12/9.
 */
@Repository
public interface SuccessKilledDao {
    /**
     * 记录用户秒杀成功的记录，由于在建表的时候使用了联合主键，故数据库会自动过滤重复
     * @param seckillId 秒杀的商品id
     * @param userPhone 用户的手机号
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据商品id，查询successSeckilled并携带seckill的信息
     * @param seckill
     * @return
     */
    SuccessKilled queryByidWithSeckill(@Param("seckill") long seckill,@Param("userPhone") long userPhone);

}
