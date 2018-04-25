package com.it.dao;

import com.it.dao.base.BaseDao;
import com.it.model.Tb_User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends BaseDao<Tb_User> {

    public Tb_User selectUserRole(Integer userId);

    public Tb_User selectUserByName(String username);

}
