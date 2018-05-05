package com.it.model;

import org.apache.ibatis.type.Alias;

/**
 * Created by wangzy on 2018/5/4.
 */
@Alias("Tb_Order")
public class Tb_Order {
    /**
     * id
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 引用tb_user中的id
     */
    private Long tb_user_id;

    @Override
    public String toString() {
        return "Tb_Order{" +
                "id=" + id +
                ", orderNum='" + orderNum + '\'' +
                ", createTime=" + createTime +
                ", tb_user_id=" + tb_user_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getTb_user_id() {
        return tb_user_id;
    }

    public void setTb_user_id(Long tb_user_id) {
        this.tb_user_id = tb_user_id;
    }
}
