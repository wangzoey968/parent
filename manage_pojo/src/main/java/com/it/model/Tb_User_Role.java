package com.it.model;

/**
 * Created by wangzy on 2018/5/3.
 * 此表为中间表,user和role的映射对应表
 */
public class Tb_User_Role {

    private Integer tb_user_id;

    private Integer tb_role_id;

    public Integer getTb_user_id() {
        return tb_user_id;
    }

    public void setTb_user_id(Integer tb_user_id) {
        this.tb_user_id = tb_user_id;
    }

    public Integer getTb_role_id() {
        return tb_role_id;
    }

    public void setTb_role_id(Integer tb_role_id) {
        this.tb_role_id = tb_role_id;
    }

}
