package com.training.session11.cartfullreqresp.command;

import lombok.Data;

@Data
public class OpenCartCommand extends AbstractCartCommand{

    private String userId;

}
