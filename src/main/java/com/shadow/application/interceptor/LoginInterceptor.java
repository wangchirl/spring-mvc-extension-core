package com.shadow.application.interceptor;

import com.shadow.application.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = new User();
        user.setName("shadow").setAge(18).setAddress("湖南省-张家界市");
        request.getSession().setAttribute("login_user", user);
        request.setAttribute("user", user);
        request.getCookies()[0].setMaxAge(1800);
        return true;
    }
}
