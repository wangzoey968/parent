package com.it.dao.mybatis.inter;

import com.it.dao.mybatis.BaseDao;
import com.it.model.Tb_User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao extends BaseDao<Tb_User> {

    public Tb_User selectUserById(Integer id);

    public Tb_User selectUserByName(String name);

    public List<Tb_User> selectUserByAddress(String address);

    public List<Tb_User> selectUserRole(Integer id);

    public Tb_User selectUserOrder(Integer id);

}
