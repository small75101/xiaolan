package com.xiaolan.authority.service.impl;

import com.xiaolan.authority.domain.User;
import com.xiaolan.authority.repository.IUserRepository;
import com.xiaolan.authority.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 实现类
 *
 * @author dsir
 * @create 2017-09-21 14:39
 **/
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User save(User user) {
        user.setCreateDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void removeById(Long id) {
        userRepository.remove(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll(User user) {
        if (user == null)
            user = new User();
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("nickname", GenericPropertyMatchers.contains())// like包含description属性
                ;
        return userRepository.findAll(Example.of(user, matcher));
    }
}
