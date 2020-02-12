package com.training.common.exceptions;

import com.training.common.Lang;
import com.training.common.Lang;
import lombok.Data;

import java.util.Map;

@Data
public class ErrorInfo {

    private int code;
    private Map<Lang,String> message;
    private int restErrorCode= 400;

}
