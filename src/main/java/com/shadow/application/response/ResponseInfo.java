package com.shadow.application.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
@Data
@Accessors(chain = true)
public class ResponseInfo implements Serializable {

    private int code;

    private String message;

    private Object data;

    public ResponseInfo() {
    }

    public ResponseInfo(int code, String message) {
        this(code,message,null);
    }

    public ResponseInfo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
