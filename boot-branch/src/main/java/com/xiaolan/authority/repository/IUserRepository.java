package com.xiaolan.authority.repository;


import com.xiaolan.authority.constant.UserCst;
import com.xiaolan.authority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户账号登录
     *
     * @param username
     */
    User findByUsername(String username);

    /**
     * 软删除
     *
     * @param id
     */
    @Modifying
    @Query(value = "update User u set u.status= " + UserCst.STATUS_REMOVR + " where u.id=?1")
    void remove(Long id);


}