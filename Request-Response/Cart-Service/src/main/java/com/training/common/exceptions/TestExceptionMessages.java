package com.training.common.exceptions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestExceptionMessages implements CommandLineRunner {

    private final AllExceptionsMessages allExceptionsMessages;

    public TestExceptionMessages(AllExceptionsMessages allExceptionsMessages) {
        this.allExceptionsMessages = allExceptionsMessages;
    }

    @Override
    public void run(String... args) throws Exception {
        allExceptionsMessages
                .getExceptions()
                .forEach((exceptionName,errorInfo)->{
                    System.out.println("exceptionName = " + exceptionName);
                    System.out.println("========================");
                    System.out.println("errorInfo = " + errorInfo);
                    System.out.println();
                });
    }
}
