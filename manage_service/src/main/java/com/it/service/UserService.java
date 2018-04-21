package com.it.service;

import com.it.model.Tb_User;

import java.util.List;

public interface UserService {
    /**
     * 添加学生
     * @param tb_User
     * @return
     */
    Boolean insertUser(Tb_User tb_User);

    /**
     * 根据ID删除学生
     * @param id
     * @return
     */
    Boolean deleteUserById(Integer id);

    /**
     * 根据ID修改学生信息
     * @param tb_User
     * @return
     */
    Boolean updateUserById(Tb_User tb_User);

    /**
     * 查询学生信息
     * @param id
     * @return
     */
    List<Tb_User> selectUser(Integer id);

    /**
     * 查询用户的角色
     * @param id user的id
     * @return
     */
    Tb_User selectUserRole(Integer id);

}
