package com.hunk.simpleflow;

import com.hunk.simpleflow.event.guava.CustomEventBus;
import com.hunk.simpleflow.event.guava.DefaultCustomEventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2023/5/19.
 *
 * @author norbit
 *     <p>
 */
@Configuration
public class EventAutoConfiguration {

    @Bean
    public CustomEventBus defaultCustomEventBus() {
        return new DefaultCustomEventBus();
    }
}
