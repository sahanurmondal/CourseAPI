
package com.springbootstarter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@EnableWebMvc
@Configuration
//@ComponentScan("com.springbootstarter")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private VerifyAccessInterceptor verifyAccessInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifyAccessInterceptor).addPathPatterns("/switchtouser");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

@Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }


 /*  @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/AdminPage.html")
                .allowedOrigins("http://localhost:8082")
                .allowedMethods("HEAD","OPTIONS","GET","PUT","DELETE","POST")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
   }*/

}
