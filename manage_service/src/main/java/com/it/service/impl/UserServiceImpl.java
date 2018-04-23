package com.it.service.impl;

import com.it.dao.UserDao;
import com.it.model.Tb_User;
import com.it.service.CacheManager;
import com.it.service.UserService;
import org.apache.ibatis.annotations.CacheNamespace;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.config.CacheNamespaceHandler;
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

@Transactional
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
    @Cacheable(value = "user")
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.SUPPORTS)
    public Tb_User selectUserRole(Integer id) {
        return userDao.selectUserRole(id);
    }

    //**************************************************************************
    //清除缓存@CacheEvict(value = "users")
    //在方法执行前,清除所有的缓存@CacheEvict(value = "users",allEntries = true,beforeInvocation = true)
    //添加缓存@CachePut(value = "user")
    public Tb_User deleteUser(Integer id){
        Tb_User tb_user = new Tb_User();
        tb_user.setId(id);
        userDao.delete(tb_user);
        return tb_user;
    }

}
