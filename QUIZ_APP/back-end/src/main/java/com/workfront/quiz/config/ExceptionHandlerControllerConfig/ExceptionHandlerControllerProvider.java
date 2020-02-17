package com.workfront.quiz.config.ExceptionHandlerControllerConfig;


import com.workfront.quiz.api.impl.ExceptionHandlerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerControllerProvider {

    @Bean
    public  ExceptionHandlerController provide(){
        return new ExceptionHandlerController();
    }
}
