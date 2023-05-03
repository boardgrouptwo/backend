package com.khcare.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authenticationManager를 Bean에 등록
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //기본설정 사용안함
                .csrf().disable() // csrf 사용 안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함
                .and()
                .cors()
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/notice/insert").hasAuthority("admin")
                .antMatchers("/notice/delete").hasAuthority("admin")
                .antMatchers("/notice/update").hasAuthority("admin")
                .antMatchers("/shop/imageUpload").hasAuthority("admin")
                .antMatchers("/shop/productUpload").hasAuthority("admin")
                .antMatchers("/cart").hasAuthority("user")
                .antMatchers("/visit/visitmanager").hasAuthority("admin")
                .antMatchers("/visit/sign").hasAuthority("user")
                .antMatchers("/user/userInfo").hasAuthority("user")
                .antMatchers("/user/userUpdate").hasAuthority("user")
                .antMatchers("/service/userInfo").hasAuthority("user")
                .antMatchers("/visit/userInfo").hasAuthority("user")
                .antMatchers("/elder/elderUpdate").hasAuthority("user")
                .antMatchers("/elder/elderInsert").hasAuthority("user")
                .antMatchers("/elder/elderSelect").hasAuthority("user")
                .antMatchers("/payment/**/*").hasAuthority("user")
                .antMatchers("/payment/**/*").hasAuthority("user")
                .antMatchers("/spon/sponStatistic").hasAuthority("admin")
                .antMatchers("/spon/sponsorUserSum").hasAuthority("user")
                .antMatchers("/sponsor/**/*").hasAuthority("user")
                .antMatchers("/service/management").hasAuthority("admin")
                //.antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
    }
}
