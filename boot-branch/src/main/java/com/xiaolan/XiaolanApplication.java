package com.xiaolan;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class XiaolanApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(XiaolanApplication.class, args);
        initPassword(run);
    }

    public static void initPassword(ApplicationContext ctx) {
        IUserService suserService = (IUserService) ctx.getBean("userServiceImpl");
        User su = suserService.findByUsername("admin");
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);// 将密码加密
        String pass = "111111";
        if (su == null) {
            su = new User();
            su.setNickname("超级管理员");
            su.setStatus(1);
            su.setUsername("admin");
            su.setPhone("12345678910");
            su.setCreateDate(new Date());
            su.setPassword(bc.encode(pass));
            su.setEnabled(true);
            su.setType(0);
            suserService.save(su);
        } else {
            System.out.println("修改前密码:" + su.getPassword());//
            if (!bc.matches(pass, su.getPassword())) {//密码不正确
                su.setPassword(bc.encode(pass));// 可以先设置初始密码：000000
                System.out.println("修改后密码:" + su.getPassword());//
                // 然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
                suserService.update(su);// 运行一次后记得注释这段重复加密会无法匹配
            }
        }
    }
}
