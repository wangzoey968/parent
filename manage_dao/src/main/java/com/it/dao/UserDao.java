package com.it.dao;

import com.it.dao.base.BaseDao;
import com.it.model.Tb_User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao extends BaseDao<Tb_User> {

    public List<Tb_User> selectUserByAddress(String address);

    public Tb_User selectUserByName(String username);

}
