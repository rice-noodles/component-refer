package com.noodles.refer.auth.config;

import com.noodles.refer.auth.constant.SecurityConstant;
import com.noodles.refer.auth.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Noodles
 * @since 2022-11-17 16:34
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // 禁用 CSRF
                .csrf().disable()
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // 指定的接口放行
                .antMatchers(SecurityConstant.SWAGGER_APIS).permitAll()
                .antMatchers(SecurityConstant.SYSTEM_APIS).permitAll()
                // 其他的接口都需要认证后才放行
                .anyRequest().authenticated()
                // 添加自定义Filter
                .and().addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 解决不允许显示在iframe的问题、禁用缓存
                .headers().frameOptions().disable().cacheControl();
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("*");
        cors.addAllowedMethod("*");
        cors.addAllowedHeader("*");
        cors.setAllowCredentials(true);
        cors.addExposedHeader(SecurityConstant.TOKEN_HEADER);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

