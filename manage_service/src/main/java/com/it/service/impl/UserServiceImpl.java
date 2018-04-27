package com.it.service.impl;

import com.it.dao.UserDao;
import com.it.model.Tb_User;
import com.it.service.UserService;
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

    public Tb_User updateUserById(Tb_User tb_User) {
        return userDao.update(tb_User);
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

    //**************************************************************************
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
