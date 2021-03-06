package com.it.service.mybatis.impl;

import com.it.dao.mybatis.inter.UserDao;
import com.it.model.Tb_User;
import com.it.service.mybatis.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Tb_User insertUser(Tb_User tb_User) {
        return userDao.insert(tb_User);
    }

    public Tb_User deleteUserById(Integer id) {
        Tb_User tb_User = new Tb_User();
        tb_User.setId(id);
        return userDao.delete(tb_User);
    }

    public Tb_User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    public Tb_User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    public List<Tb_User> selectUserByAddress(String address) {
        return userDao.selectUserByAddress(address);
    }

    /*查询用户以及下面的角色*/
    public List<Tb_User> selectUserRole(Integer id){
        return userDao.selectUserRole(id);
    }

    /*查询user和其下面的order*/
    public Tb_User selectUserOrder(Integer id){
        Tb_User us = userDao.selectUserOrder(id);
        System.out.println(us.toString());
        return us;
    }

    //***********************************************************************
    //supports是指有事物就使用事物,没有事物,就忽略
    //@Cacheable(value = "user")
    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS)
    //清除缓存@CacheEvict(value = "users")
    //在方法执行前,清除所有的缓存@CacheEvict(value = "users",allEntries = true,beforeInvocation = true)
    //添加缓存@CachePut(value = "user")
    public Tb_User deleteUser(Integer id) {
        Tb_User tb_user = new Tb_User();
        tb_user.setId(id);
        userDao.delete(tb_user);
        return tb_user;
    }

}
