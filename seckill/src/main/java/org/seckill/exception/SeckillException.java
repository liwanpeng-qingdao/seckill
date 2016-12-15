package org.seckill.exception;

/**
 * 秒杀通用异常，其他的秒杀异常需要继承异常
 * Created by OUC on 2016/12/10.
 */
public class SeckillException extends SeckillCloseException {


    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
