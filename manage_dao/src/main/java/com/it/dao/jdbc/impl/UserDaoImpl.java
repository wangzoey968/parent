package com.it.dao.jdbc.impl;

import com.it.dao.jdbc.DBUtil;
import com.it.dao.jdbc.inter.UserDaoInter;
import com.it.model.Tb_User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by wangzy on 2018/5/5.
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDaoInter {

    @Test
    public void select() throws SQLException {
       Tb_User user = this.selectUserById(1);
    }

    public Tb_User selectUserById(Integer id) {
        // JDBC模板依赖于连接池来获得数据的连接，所以必须先要构造连接池
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/user");
        dataSource.setUsername("root");
        dataSource.setPassword("zhundian66");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        String sql = "select * from tb_user WHERE id=" + id;
        Tb_User user = jdbcTemplate.queryForObject(sql, new UserMapper());
        System.out.println(user.toString());
        return null;
    }
    //定义内部类
    class UserMapper implements RowMapper<Tb_User> {
        public Tb_User mapRow(ResultSet resultSet, int i) throws SQLException {
            Tb_User user = new Tb_User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setPhoto(resultSet.getString(2));
            return user;
        }
    }

    @Test
    public void ts(){
        try {
            this.ss(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ss(Integer id) throws SQLException {
        String sql = "select * from tb_user WHERE id=?";
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,id);
        ResultSet set = ps.executeQuery();
        boolean b = set.wasNull();
        System.out.println(b);
    }

}
