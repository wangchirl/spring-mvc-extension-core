package com.shadow.application.entity;

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
public class User implements Serializable {

    private String name;

    private int age;

    private String password;

    private String email;

    private String phone;

    private String address;

}
