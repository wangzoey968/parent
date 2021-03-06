package com.it.service.mybatis.inter;

import com.it.model.Tb_User;

import java.util.List;

public interface UserService {
    /**
     * 添加学生
     * @param tb_User
     * @return
     */
    Tb_User insertUser(Tb_User tb_User);

    /**
     * 根据ID删除学生
     * @param id
     * @return
     */
    Tb_User deleteUserById(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Tb_User selectUserById(Integer id);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Tb_User selectUserByName(String username);

    /**
     * 根据地址查询用户
     * @param address
     * @return
     */
    List<Tb_User> selectUserByAddress(String address);

    /**
     * 查询user和其下的role
     * @param id
     */
    // TODO
    public List<Tb_User> selectUserRole(Integer id);

    /**
     * 查询user和下面的Order
     * @param id
     */
    public Tb_User selectUserOrder(Integer id);

}
