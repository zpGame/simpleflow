package com.hunk.simpleflow.spring;

import cn.hutool.core.util.ObjectUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.hunk.simpleflow.utils.CastUtils;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *         <p>
 *         基于代码形式的spring上下文工具类
 */
public class SpringAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public SpringAware() {
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        return CastUtils.cast(applicationContext.getBean(name));
    }

    public static  <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    private <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public <T> T registerBean(String beanName, Class<T> c) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(c.getName());
        beanFactory.setAllowBeanDefinitionOverriding(true);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        return getBean(beanName);
    }

    public <T> T registerBean(Class<T> c) {
        return registerBean(c.getName(), c);
    }

    public <T> T registerBean(String beanName, Object bean) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
                .getAutowireCapableBeanFactory();
        defaultListableBeanFactory.registerSingleton(beanName, bean);
        return CastUtils.cast(configurableApplicationContext.getBean(beanName));
    }

    public <T> T registerOrGet(String beanName, Class<T> clazz) {
        if (ObjectUtil.isNull(applicationContext)) {
            return null;
        }
        try {
            return getBean(beanName, clazz);
        } catch (Exception e) {
            return registerBean(beanName, clazz);
        }
    }

    public boolean hasBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }
}
