package com.it.model;

import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created by wangzy on 2018/4/19.
 */
@Alias("Tb_Role")
public class Tb_Role {
    /**
     * id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 一个角色可以对应多个用户,不存入数据库,@transient
     */
    private List<Tb_User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Tb_User> getUsers() {
        return users;
    }

    public void setUsers(List<Tb_User> users) {
        this.users = users;
    }
}
