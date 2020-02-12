package com.training.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLoader implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static <U> U loadBean(Class<U> u){

        return applicationContext.getBeansOfType(u).values().stream().findFirst().get();
    }




    public static <U> U loadBean(String name){


        return (U)applicationContext.getBean(name);
    }


}
