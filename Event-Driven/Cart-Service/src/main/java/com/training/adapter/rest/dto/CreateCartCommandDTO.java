package com.training.adapter.rest.dto;

import lombok.Data;

@Data
public class CreateCartCommandDTO {
    private final String userId;
    private final String cartNo;
}
