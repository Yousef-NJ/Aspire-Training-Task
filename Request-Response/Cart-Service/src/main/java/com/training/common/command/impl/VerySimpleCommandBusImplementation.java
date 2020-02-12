package com.training.common.command.impl;//package com.trainning.common.command.impl;
//
//import com.trainning.common.command.CommandBus;
//import com.trainning.session2.command.RegisterUserCommand;
//import com.trainning.session2.model.UserAggregator;
//import org.springframework.stereotype.Component;
//
//
//public class VerySimpleCommandBusImplementation implements CommandBus {
//
//    private final UserAggregator userAggregator;
//
//    public VerySimpleCommandBusImplementation(UserAggregator userAggregator) {
//        this.userAggregator = userAggregator;
//    }
//
//    @Override
//    public <U> U execute(Object commandObject) {
//        System.out.println("commandObject: "+commandObject);
//        if( commandObject instanceof RegisterUserCommand){
//             return (U)userAggregator
//                     .registerUser((RegisterUserCommand)commandObject);
//        }
//        return null;
//    }
//}
