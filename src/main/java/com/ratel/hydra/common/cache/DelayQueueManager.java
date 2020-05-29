package com.ratel.hydra.common.cache;

import com.ratel.hydra.system.vo.AuthCodeVO;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 *
 * 延迟队列对于处理 验证码等 需要过期的数据
 * @author ratel
 * @date 2020-05-29
 */
public class DelayQueueManager {
    private static DelayQueue<AuthCodeVO> delayQueue = new DelayQueue<>();
    private static volatile boolean cleanData = true;
    /**
     * @Description 往阻塞队列中存入验证码
     * @Author      ratel
     * @Date        2020-05-29
     * @param       authCodeVO
     * @return      void
     **/
    public static void push(AuthCodeVO authCodeVO){
        delayQueue.offer(authCodeVO);
    }

    /**
     * @Description 清理过期验证码
     * @Author      ratel
     * @Date        2020-05-29
     * @return      void
     **/
    public static void cleanOverdueData(){
        while (cleanData){
            delayQueue.poll();
        }
    }

    /**
     * @Description 停止清理验证码
     * @Author      ratel
     * @Date        2020-05-29
     * @return      void
     **/
    public static void stopCleanData(){
        cleanData = false;
    }
}
