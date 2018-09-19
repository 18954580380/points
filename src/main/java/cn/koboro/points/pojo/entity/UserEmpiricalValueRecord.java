package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户获取经验值记录表
 */
@Data
@Entity
@Table(name = "koboro_user_empirical_value_record")
public class UserEmpiricalValueRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *档案号
     */
    @Column(name="archival_number")
    private String archivalNumber;
    /**
     *获取经验值获取惩罚经验值数量
     */
    @Column(name="empirical_value")
    private Integer empiricalValue;
    /**
     *1:任务模块 2:圈子模块
     */
    @Column(name="source")
    private Integer source;
    /**
     *1: 获取 2:惩罚
     */
    @Column(name="type")
    private Integer type;
    /**
     *描述
     */
    @Column(name="description")
    private String description;
    /**
     *创建时间
     */
    @Column(name="create_time")
    private Date createTime;

}
