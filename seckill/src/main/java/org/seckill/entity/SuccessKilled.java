package org.seckill.entity;

import java.util.Date;

/**
 * 秒杀成功记录表
 * Created by OUC on 2016/12/9.
 */
public class SuccessKilled  {
    private long seckillId;

    private long userPhone;

    private short state;

    private Date createTime;

    //变通，successKilled和seckill是多对一的关系，一般多对一的关系都要加上所对应的那个“一”的实体类
    private Seckill seckill;
    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
