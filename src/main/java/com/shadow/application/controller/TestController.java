package com.shadow.application.controller;

import com.shadow.application.entity.User;
import com.shadow.application.listener.MyEvent;
import com.shadow.application.response.R;
import com.shadow.application.service.TestService;
import com.shadow.utils.ConsolePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
@Controller
@RequestMapping
@ResponseBody
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestService testService;

    @GetMapping("/save")
    @ResponseBody
    public R save() {
        ConsolePrinter.printlnCyan("controller 所属容器：" + this.applicationContext.hashCode());
        ConsolePrinter.printlnCyan("controller 所属容器父容器：" + this.applicationContext.getParent().hashCode());
        User user = new User();
        user.setName("王钦")
            .setPassword("1111")
            .setEmail("438111969@qq.com")
            .setPhone("110")
            .setAddress("湖南省-张家界市");
        testService.save(user);
        // 发布事件
        applicationContext.publishEvent(new MyEvent(null));
        return R.ok().put("data","保存成功");
    }


    /**
     * 路径传参
     * @PathVariable 注解
     * /path/variable/shadow/18
     * name = shadow
     * age = 18
     */
    @GetMapping("/path/variable/{name}/{age}")
    public R pathVariable(@PathVariable("name") String name,@PathVariable("age")int age) {
        ConsolePrinter.printlnRed("@PathVariable params : " + name + " " + age);
        return R.ok().put("data",name + " " + age);
    }

    /**
     * 路径传参
     * @MatrixVariable 注解
     * /matrix/variable/shadow;q=18;email=438111969@qq.com
     * name = shadow
     * age = 18
     * email = 438111969@qq.com
     */
    @GetMapping("/matrix/variable/{name}")
    public R matrixVariable(@PathVariable("name")String name
            , @MatrixVariable("age")int age
            ,@MatrixVariable("email")String email) {
        ConsolePrinter.printlnYellow("@MatrixVariable params : " + name + " " + age + " " + email);
        return R.ok().put("data",name + " " + age + " " + email);
    }


    /**
     * 获取请求参数
     * @RequestParam
     * /request/param?name=shadow&age=18
     * name = shadow
     * age = 18
     */
    @GetMapping("/request/param")
    public R requestParam(@RequestParam("name")String name,@RequestParam("age")int age) {
        ConsolePrinter.printlnPurple("@RequestParam params : " + name + " " + age);
        return R.ok().put("data",name + " " + age);
    }


    /**
     * 获取请求头信息
     * @RequestHeader
     * /request/header
     * encoding = gzip, deflate, br
     * connection = keep-alive
     */
    @GetMapping("/request/header")
    public R requestHeader(@RequestHeader("Accept-Encoding")String encoding
                    ,@RequestHeader("Connection")String connection) {
        ConsolePrinter.printlnCyan("@RequestHeader params : " + encoding + " " + connection);
        return R.ok().put("data", encoding + " " + connection);
    }


    /**
     * 获取 Cookie 信息
     * @CookieValue
     * /cookie/value
     * jsessionId = xxxx
     */
    @GetMapping("/cookie/value")
    public R cookieValue(@CookieValue("JSESSIONID")String jsessionId) {
        ConsolePrinter.printlnRed("@CookieValue params : " + jsessionId);
        return R.ok().put("data",jsessionId);
    }


    /**
     * 获取前端传递的信息映射为对象
     * @ModelAttribute
     * /model/attribute?name=shadow&age=18&email=438111969@qq.com
     * User(name=shadow, age=18, password=null, email=438111969@qq, phone=null, address=null)
     */
    @GetMapping("/model/attribute")
    public R modelAttributeGet(@ModelAttribute User user) {
        ConsolePrinter.printlnYellow("@ModelAttribute get params : " + user);
        return R.ok().put("data", user);
    }
    /**
     * 获取前端传递的信息映射为对象
     * @ModelAttribute
     * /model/attribute
     * {
     *     "name":"shadow",
     *     "age":18,
     *     "email":"438111969@qq.com"
     * }
     * User(name=shadow, age=18, password=null, email=438111969@qq, phone=null, address=null)
     */
    @PostMapping("/model/attribute")
    public R modelAttributePost(@ModelAttribute User user) {
        ConsolePrinter.printlnPurple("@ModelAttribute post params : " + user);
        return R.ok().put("data", user);
    }

    /**
     * 获取 session.setAttribute 设置的属性值
     * @SessionAttribute
     * /session/attribute
     * request.getSession().setAttribute("login_user",new User()) 拦截器设置
     * @see com.shadow.application.interceptor.LoginInterceptor
     */
    @GetMapping("/session/attribute")
    public R sessionAttribute(@SessionAttribute("login_user") User user) {
        ConsolePrinter.printlnCyan("@SessionAttribute params : " + user);
        return R.ok().put("data",user);
    }

    /**
     * 获取 request.setAttribute 设置的属性值
     * @RequestAttribute
     * /request/attribute
     * request.setAttribute("user",new User()) 拦截器设置
     * @see com.shadow.application.interceptor.LoginInterceptor
     */
    @GetMapping("/request/attribute")
    public R requestAttribute(@RequestAttribute("user") User user) {
        ConsolePrinter.printlnRed("@RequestAttribute params : " + user);
        return R.ok().put("data",user);
    }


    // ================== POST 请求 ======================

    /**
     * 文件上传请求
     * /multipart/upload
     * MultipartFile
     * <dependency>
     * 		<groupId>commons-fileupload</groupId>
     * 		<artifactId>commons-fileupload</artifactId>
     * 	</dependency>
     *
     * 	<bean id="multipartResolver"
     * 	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
     * 	<!--上传文件的最大大小，单位为字节 -->
     * 	<property name="maxUploadSize" value="17367648787"></property>
     *
     * 	<!-- 上传文件的编码 -->
     * 	<property name="defaultEncoding" value="UTF-8"></property>
     * </bean>
     */
    @PostMapping("/multipart/upload")
    public R multipart(@RequestParam("file")MultipartFile file) {
        ConsolePrinter.printlnYellow("multipart file upload params : " + file.getOriginalFilename());
        return R.ok().put("data",file.getOriginalFilename());
    }


    /**
     * 请求体映射对象
     * @RequestBody
     * /request/body
     * {
     *     "name":"shadow",
     *     "age":18,
     *     "email":"438111969@qq.com"
     * }
     * User(name=shadow, age=18, password=null, email=438111969@qq, phone=null, address=null)
     */
    @PostMapping("/request/body")
    public R requestBody(@RequestBody User user) {
        ConsolePrinter.printlnPurple("@RequestBody params : " + user);
        return R.ok().put("data",user);
    }

    /**
     * 请求体映射对象
     * HttpEntity<T>
     * /http/entity
     * {
     *     "name":"shadow",
     *     "age":18,
     *     "email":"438111969@qq.com"
     * }
     * User(name=shadow, age=18, password=null, email=438111969@qq, phone=null, address=null)
     */
    @PostMapping("/http/entity")
    public R httpEntity(HttpEntity<User> userHttpEntity) {
        ConsolePrinter.printlnCyan("HttpEntity params : " + userHttpEntity.getBody());
        return R.ok().put("data",userHttpEntity.getBody());
    }

}
