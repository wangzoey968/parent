package com.it.model;

import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Alias("Tb_User")
public class Tb_User {
    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 相片
     */
    private String photo;
    /**
     * 密码
     */
    private String password;
    /**
     * user拥有的role,一user对多role,不存入数据库,@transient
     */
    private ArrayList<Tb_Role> roles;
    /**
     * user拥有多个订单,一对多,不存入数据库,@transient
     */
    private ArrayList<Tb_Order> orders;

    @Override
    public String toString() {
        return "Tb_User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", orders=" + orders +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Tb_Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Tb_Role> roles) {
        this.roles = roles;
    }

    public ArrayList<Tb_Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Tb_Order> orders) {
        this.orders = orders;
    }
}
