package com.xiaolan.authority.service;

import com.xiaolan.authority.domain.User;
import com.xiaolan.common.service.IBaseService;


public interface IUserService extends IBaseService<User, Long> {
    /**
     * 删除用户
     * 软删除
     *
     * @param id
     * @return
     */
    void removeById(Long id);


    /**
     * 查询一个具体用户 根据username
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


}
