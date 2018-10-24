/*

package com.springbootstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
   */
/* @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }
*//*

   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/AdminPage.html")
                .allowedOrigins("http://localhost:8082")
                .allowedMethods("HEAD","OPTIONS","GET","PUT","DELETE","POST")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
   }
}
*/
