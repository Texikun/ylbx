package com.neuedu.ylbx.config;

import com.neuedu.ylbx.interceptor.CheckTokenInterceptor;
import com.neuedu.ylbx.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author: xianzhen
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
    /**
     * 拦截器校验Token
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义排除swagger访问的路径、登录接口
        String[] swaggerExcludes = new String[]{"/swagger-ui.html","/doc.html", "/swagger-resources/**", "/webjars/**", "/login/**"};
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/**") //拦截所有的 url
                .excludePathPatterns("/user/login")//排除url: /user/login (登录)
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**","/");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //当该项目被打包成jar包后，可以在jar包所在文件夹中生成一个upload文件夹用于文件/图片上传
//        //获取jar包所在目录
////        ApplicationHome home = new ApplicationHome(getClass());
////        File jarF = home.getSource();
//        //在jar包所在目录下生成一个images文件夹用来存储上传的图片
////        String dirPath = jarF.getParentFile().toString()+"/images/";
//
//        String path = System.getProperty("user.dir");
//        String dirPath = path+"/src/main/resources/static/images/";
//        System.out.println(dirPath);
//        registry.addResourceHandler("/images/**").addResourceLocations("file:/"+dirPath);
//
//    }
}
