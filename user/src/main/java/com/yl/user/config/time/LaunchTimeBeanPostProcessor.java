package com.yl.user.config.time;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author simple-zhang
 * @date 3/1/2023 4:04 PM
 */
@Component
public class LaunchTimeBeanPostProcessor implements BeanPostProcessor, InstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor {

    @Autowired
    private LaunchTimeManager launchTimeManager;

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {

    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        launchTimeManager.beanStart(beanName, System.currentTimeMillis());
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        launchTimeManager.beanEnd(beanName, System.currentTimeMillis());
        return bean;
    }
}
