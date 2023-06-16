package com.hunk.simpleflow;

import com.hunk.simpleflow.config.SimpleFlowEventProperty;
import com.hunk.simpleflow.config.SimpleFlowProperty;
import com.hunk.simpleflow.event.EventBusInit;
import com.hunk.simpleflow.event.guava.CustomEventBus;
import com.hunk.simpleflow.flow.SimpleFlowExecutor;
import com.hunk.simpleflow.parse.SimpleFlowInitExecutor;
import com.hunk.simpleflow.property.SimpleFlowConfig;
import com.hunk.simpleflow.spring.SpringAware;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>
 */
@Configuration
@EnableConfigurationProperties({SimpleFlowProperty.class, SimpleFlowEventProperty.class})
@Import(SpringAware.class)
public class SimpleFlowPropertyAutoConfiguration {

    @Bean
    public SimpleFlowConfig simpleFlowConfig(
            SimpleFlowProperty property, SimpleFlowEventProperty eventProperty) {
        SimpleFlowConfig simpleFlowConfig = new SimpleFlowConfig();
        simpleFlowConfig.setRuleSource(property.getRuleSource());
        simpleFlowConfig.setStartInit(property.isStartInit());
        simpleFlowConfig.setFileType(property.getFileType());
        simpleFlowConfig.setEventEnum(eventProperty.getEventEnum());
        simpleFlowConfig.setNameFormat(eventProperty.getNameFormat());
        simpleFlowConfig.setCorePoolSize(eventProperty.getCorePoolSize());
        simpleFlowConfig.setMaximumPoolSize(eventProperty.getMaximumPoolSize());
        simpleFlowConfig.setKeepAliveTime(eventProperty.getKeepAliveTime());
        simpleFlowConfig.setCapacity(eventProperty.getCapacity());

        return simpleFlowConfig;
    }

    @Bean
    public SimpleFlowInitExecutor simpleFlowApp(SimpleFlowConfig simpleFlowConfig) {
        SimpleFlowInitExecutor flowInitExecutor = new SimpleFlowInitExecutor();
        flowInitExecutor.setFlowConfig(simpleFlowConfig);
        return flowInitExecutor;
    }

    @Bean
    public SimpleFlowInit simpleFlowInit(SimpleFlowInitExecutor flowInitExecutor) {
        return new SimpleFlowInit(flowInitExecutor);
    }

    @Bean
    public SimpleFlowExecutor simpleFlowExecutorInit() {
        return new SimpleFlowExecutor();
    }

    @Bean
    @ConditionalOnExpression(
            "'guava_sync'.equals('${simple.flow.event.event-enum}') || 'guava_async'.equals('${simple.flow.event.event-enum}')")
    public CustomEventBus defaultCustomEventBus(SimpleFlowConfig property) {
        return EventBusInit.of(property);
    }
}
