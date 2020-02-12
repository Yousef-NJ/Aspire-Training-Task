package com.training.common.exceptions;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "com.training")
@Data
@Component
public class AllExceptionsMessages {

    Map<String,ErrorInfo> exceptions;

}
