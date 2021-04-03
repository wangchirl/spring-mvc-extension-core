package com.shadow.application.initializer;

import com.shadow.application.config.MvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // spring javaConfig 这里默认使用 web.xml 中的配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // spring mvc javaConfig
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
