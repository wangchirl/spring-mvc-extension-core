package com.shadow.application.listener;

import com.shadow.utils.ConsolePrinter;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
public class RequestEndListener {

    @EventListener
    public void requested(RequestHandledEvent event) {
        ConsolePrinter.printlnCyan("请求处理完成监听器 SessionId ：" + event.getSessionId());
    }

    @EventListener
    public void myEvent(MyEvent event) {
        ConsolePrinter.printlnCyan("收到发布的 MyEvent 事件...");
    }
}
