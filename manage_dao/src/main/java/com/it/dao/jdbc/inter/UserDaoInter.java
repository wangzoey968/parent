package com.it.dao.jdbc.inter;

import com.it.model.Tb_User;

/**
 * Created by wangzy on 2018/5/5.
 */
public interface UserDaoInter {

    public Tb_User selectUserById(Integer id);

}
