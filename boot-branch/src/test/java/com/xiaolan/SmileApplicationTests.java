package com.xiaolan;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmileApplicationTests {
    Log log = LogFactory.getLog(getClass());
    @Autowired
    IUserService userService;

    @Test
    public void name() throws Exception {

    }

    @Test
    public void user() {
        System.out.println(userService);
        User user = new User();
        user.setUsername("username");
        user.setNickname("nickname");
        user.setPassword("password");
        user.setPhone("13208110000");

        User user1 = new User();
        user1.setUsername("username1");
        user1.setNickname("nickname1");
        user1.setPassword("password1");
        user1.setPhone("13208110001");
        //新增
        log.debug("新增");
        User userBD = userService.save(user);
        User userBD1 = userService.save(user1);
        log.debug(userBD);
        log.debug(userBD1);
        //删除
        log.debug("移除");
        userService.removeById(userBD.getId());
        //更新
        log.debug("更新");
        userBD1.setNickname("更新");
        userService.update(userBD1);


        //查询
        log.debug("查询");
        User user2 = userService.findOne(userBD.getId());
        User user22 = userService.findOne(userBD1.getId());
        log.debug(user2);
        log.debug(user22);
        User user3 = userService.findByUsername(userBD.getUsername());
        log.debug(user3);
        List<User> users = userService.findAll(null);
        log.debug(users);


        //查询
        log.debug("查询");
        User user222 = userService.findOne(userBD.getId());
        User user2222 = userService.findOne(userBD1.getId());
        log.debug(user222);
        log.debug(user2222);
    }

}
