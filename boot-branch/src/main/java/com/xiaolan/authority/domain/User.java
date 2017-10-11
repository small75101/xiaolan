package com.xiaolan.authority.domain;

import com.xiaolan.authority.constant.UserCst;
import com.xiaolan.authority.domain.dto.MenuDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表
 *
 * @author dsir
 * @create 2017-09-21 14:01
 **/
@Entity
@Table(name = "auth_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//唯一标示
    //==================信息
    @Column(name = "username", nullable = false, unique = true, length = 36)
    private String username;//登录账号
    @Column(name = "nickname", nullable = false, length = 60)
    private String nickname;//昵称
    @Column(name = "password", nullable = false, length = 100)
    private String password;//密码
    @Column(name = "phone", length = 15)
    private String phone;
    @Column(name = "createDate", updatable = false)
    private Date createDate;// 创建时间
    //==================状态
    @Column(name = "status", nullable = false)
    private Integer status = UserCst.STATUS_NORMOL;//状态 0:禁用状态 1:正常运行 2:软删除
    @Column(name = "type", nullable = false)
    private Integer type = UserCst.TYPE_GENERAL;//0 超级管理员，1 一般管理员
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;//是否正常使用

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<Role>();
    @Transient
    private Collection<MenuDto> menuTree;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", type=" + type +
                ", enabled=" + enabled +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<MenuDto> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(Collection<MenuDto> menuTree) {
        this.menuTree = menuTree;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        for (Role role : this.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }
}
