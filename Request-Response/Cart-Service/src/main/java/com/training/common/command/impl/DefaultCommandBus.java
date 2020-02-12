package com.training.common.command.impl;

import com.training.common.command.CommandBus;
import com.training.common.command.CommandHandler;
import com.training.common.exceptions.NoHandlerFoundForCommandException;
import com.training.common.util.Tuple;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DefaultCommandBus implements CommandBus,
        SmartLifecycle,
        ApplicationContextAware {

    private Map<Class, Tuple<Method,Object>>
            commandHandlers= new ConcurrentHashMap<>();
    private boolean started;
    private ApplicationContext applicationContext;

    @Override
    public <U> U execute(Object commandObject
            /*For example RegisterUserCommand*/) {
        Tuple<Method, Object
                /**example UserAggregate bean
                 or RegisterUserCommandHandler bean**/
                > methodObjectTuple = commandHandlers.get(commandObject.getClass());
        if(methodObjectTuple == null){
            throw new NoHandlerFoundForCommandException(
                    "No Handler for "
                            + commandObject.getClass()
                    ,commandObject.getClass());
        }

        //methodObjectTuple._1,methodObjectTuple._2
        //i,.e _2._1(commandObject)
        // userAggregate.updateUser(updateUserCommand)
        // registerUserCommandHandler.register(registerUserCommand);
        return (U)ReflectionUtils.invokeMethod(methodObjectTuple._1,methodObjectTuple._2,commandObject);
    }

    public Object registerCommandHandlerBean(Object bean, String beanName) throws BeansException {
        System.out.println("beanName = " + beanName);
        ReflectionUtils
                .doWithMethods(bean.getClass(),(method)->{

                    CommandHandler declaredAnnotation = method.getDeclaredAnnotation(CommandHandler.class);
                    if(declaredAnnotation==null){return;}
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if(parameterTypes==null||parameterTypes.length!=1){return;}

                    Class<?> commandClass = parameterTypes[0];
                    commandHandlers.put(commandClass,new Tuple<>(method,bean));

                });
        return bean;
    }

    @Override
    public void start() {
        Stream.of(applicationContext.getBeanDefinitionNames())
                .map(name->new Tuple<>( name,applicationContext.getBean(name)))
                .forEach(nameAndBean->registerCommandHandlerBean(nameAndBean._2,nameAndBean._1));

        started=true;
    }

    @Override
    public void stop() {
        started=false;
    }

    @Override
    public boolean isRunning() {
        return started;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
