
package com.springbootstarter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private VerifyAccessInterceptor verifyAccessInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifyAccessInterceptor).addPathPatterns("/switchtouser");
    }
 /*@Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }


   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/AdminPage.html")
                .allowedOrigins("http://localhost:8082")
                .allowedMethods("HEAD","OPTIONS","GET","PUT","DELETE","POST")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
   }*/
}
