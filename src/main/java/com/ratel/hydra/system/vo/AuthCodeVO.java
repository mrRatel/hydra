package com.ratel.hydra.system.vo;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ratel
 * @date 2020-05-29
 */
public class AuthCodeVO implements Delayed {
    private long time;
    private String number;

    private AuthCodeVO() {
    }

    public AuthCodeVO(String number, long delay) {
        this.number = number;
        time = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(time - System.currentTimeMillis(),unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long l = o.getDelay(TimeUnit.MILLISECONDS) - this.getDelay(TimeUnit.MILLISECONDS);
        return l > 0 ? -1 : 1;
    }
}
