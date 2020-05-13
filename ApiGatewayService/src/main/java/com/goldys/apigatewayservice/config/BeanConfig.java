package com.goldys.apigatewayservice.config;

import com.goldys.apigatewayservice.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * Annotate the class with @Configuration
 */
@Configuration
public class BeanConfig {

    /*
     *  Create a bean for FilterRegistrationBean.
     *  1. Register the JwtFilter
     *  2. add URL pattern for following so that any request for
     *     that URL pattern will be intercepted by the filter:
     *      - '/gymservice/api/v1/gymservice/*'
     *      - '/gymservice/api/v2/gymservice/*'
     *      - '/enquiryservice/api/v1/enquiryservice/admin/*'
     *      - '/ticketservice/api/v1/ticketservice/*'
     */

    @Bean
    public FilterRegistrationBean jwtFilter() {

        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
        filter.addUrlPatterns("/gymservice/api/v1/gymservice/*");
        filter.addUrlPatterns("/gymservice/api/v2/gymservice/*");
        filter.addUrlPatterns("/enquiryservice/api/v1/enquiryservice/admin/*");
        filter.addUrlPatterns("/ticketservice/api/v1/ticketservice/*");

        return filter;

    }

    /*
     *  Bean to be created for CorsFilter so that requests from any origin
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }

}
