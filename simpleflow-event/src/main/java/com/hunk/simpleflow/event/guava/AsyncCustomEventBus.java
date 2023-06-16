package com.hunk.simpleflow.event.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hunk.simpleflow.event.error.CustomEventException;

import java.util.concurrent.*;

/**
 * Created on 2023/5/19.
 *
 * @author norbit
 *     <p>异步事件
 */
public class AsyncCustomEventBus implements CustomEventBus {

    static AsyncEventBus asyncEventBus;

    public AsyncCustomEventBus createThread(
            String nameFormat,
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            int capacity) {
        ThreadFactory namedThreadFactory =
                new ThreadFactoryBuilder().setNameFormat(nameFormat).build();
        ExecutorService executorService =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(capacity),
                        namedThreadFactory,
                        new ThreadPoolExecutor.CallerRunsPolicy());
        asyncEventBus = new AsyncEventBus(executorService);
        return this;
    }

    @Override
    public void asyncPublish(CustomEvent customEvent) throws CustomEventException {
        try {
            asyncEventBus.post(customEvent);
        } catch (Exception e) {
            throw new CustomEventException("publish event error", e);
        }
    }

    @Override
    public void register(CustomEventListen<?> eventListener) {
        asyncEventBus.register(eventListener);
    }

    @Override
    public void unRegister(CustomEventListen<?> eventListener) {
        asyncEventBus.unregister(eventListener);
    }
}
