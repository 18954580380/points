package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户经验值整体情况
 */
@Data
@Entity
@Table(name = "koboro_user_empirical_value_total_detail")
public class UserEmpiricalValueTotalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 档案号
     */
    @Column(name="archival_number")
    private String archivalNumber;
    /**
     *用户总经验值
     */
    @Column(name="total_empirical_value")
    private Integer totalEmpiricalValue;
    /**
     *经验值等级
     */
    @Column(name="empirical_value_level_id")
    private Integer empiricalValueLevelId;
}
