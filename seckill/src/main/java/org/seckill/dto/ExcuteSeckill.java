package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillState;

/**
 * Created by OUC on 2016/12/10.
 */
public class ExcuteSeckill {
    private long seckillId;
    //秒杀状态(是否成功等类似信息，使用int)
    private int state;
    //秒杀状态翻译
    private String stateInfo;
    //秒杀成功的对象
    private SuccessKilled successKilled;
    //秒杀成功的构造方法

    public ExcuteSeckill(long seckillId, SeckillState seckillState, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
        this.successKilled = successKilled;
    }

    //秒杀失败的构造方法
    public ExcuteSeckill(long seckillId, SeckillState seckillState) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
