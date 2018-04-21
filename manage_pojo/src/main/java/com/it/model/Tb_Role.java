package com.it.model;

import org.apache.ibatis.type.Alias;

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
    private String role;
    /**
     * 多role对一user,引用tb_user的id
     */
    private Integer tb_user_id;

    @Override
    public String toString() {
        return "Tb_Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", tb_user_id=" + tb_user_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getTb_user_id() {
        return tb_user_id;
    }

    public void setTb_user_id(Integer tb_user_id) {
        this.tb_user_id = tb_user_id;
    }

}
