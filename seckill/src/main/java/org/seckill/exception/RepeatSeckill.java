package org.seckill.exception;

/**
 *
 * 重复秒杀异常，运行时异常，需要继承RuntimeException,一般异常只需要下面的两个异常
 * Created by OUC on 2016/12/10.
 */
public class RepeatSeckill extends RuntimeException {
    public RepeatSeckill(String message) {
        super(message);
    }

    public RepeatSeckill(String message, Throwable cause) {
        super(message, cause);
    }
}
