package com.shadow.application.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author shadow
 * @create 2021-04-10
 * @description
 */
public class MyEvent  extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
