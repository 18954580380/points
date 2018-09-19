package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 经验值等级表
 */
@Data
@Entity
@Table(name = "koboro_empirical_value_level")
public class EmpiricalValueLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户等级
     */
    @Column(name = "user_level")
    private String userLevel;
    /**
     *用户头衔
     */
    @Column(name = "user_title")
    private String userTitle;
    /**
     *经验xp下限
     */
    @Column(name = "xp_min")
    private Integer xpMin;
    /**
     *经验值xp上限
     */
    @Column(name = "xp_max")
    private Integer xpMax;
    /**
     *设置时间
     */
    @Column(name = "set_date")
    private Date setDate;



}
