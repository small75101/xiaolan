package com.xiaolan.common.config.security;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by ThinkPad on 2017/8/26.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = (User) userService.findByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("用户或密码不正确");
        }
        //加密过程在这里体现
        if (!passwordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("用户或密码不正确");
        }
        if (!user.isEnabled()) {
            throw new BadCredentialsException("用户异常");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}