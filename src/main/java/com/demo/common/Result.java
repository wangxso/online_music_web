package com.demo.common;

import lombok.Data;

@Data
public class Result {
    private String message;
    private int status;

    public Result(String message, int status) {
        this.message=message;
        this.status=status;
    }

    public Result() {
    }
}
