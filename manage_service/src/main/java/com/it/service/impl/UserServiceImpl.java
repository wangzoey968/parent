package com.it.service.impl;

import com.it.dao.UserDao;
import com.it.model.Tb_User;
import com.it.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Boolean insertUser(Tb_User tb_User) {
        return userDao.insert(tb_User);
    }

    public Boolean deleteUserById(Integer id) {
        Tb_User tb_User = new Tb_User();
        tb_User.setId(id);
        return userDao.delete(tb_User);
    }

    public Boolean updateUserById(Tb_User tb_User) {
        return userDao.update(tb_User);
    }

    public List<Tb_User> selectUser(Integer id) {
        Tb_User tb_User = new Tb_User();
        tb_User.setId(id);
        return userDao.select(tb_User);
    }

    //supports是指有事物就使用事物,没有事物,就忽略
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.SUPPORTS)
    public Tb_User selectUserRole(Integer id) {
        return userDao.selectUserRole(id);
    }

    //**************************************************************************

}
