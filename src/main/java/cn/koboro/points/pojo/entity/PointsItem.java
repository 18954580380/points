package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分项目表
 */
@Data
@Entity
@Table(name = "koboro_points_item")
public class PointsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 积分项目名称
     */
    @Column(name = "points_item_name")
    private String pointsItemName;
    /**
     *积分项目id,如果是任务同步到积分后台，可存储任务ID
     */
    @Column(name = "item_id")
    private Integer itemId;
    /**
     *奖惩周期 1：每天 2：每次 3：每小时
     */
    @Column(name = "reward_period")
    private Integer rewardPeriod;
    /**
     *奖惩次数
     */
    @Column(name = "reward_number")
    private Integer rewardNumber;
    /**
     *积分值
     */
    @Column(name = "points_value")
    private Integer pointsValue;
    /**
     *积分项目来源 1:任务模块 2：圈子模块
     */
    @Column(name = "points_item_source")
    private Integer pointsItemSource;
    /**
     *设定日期
     */
    @Column(name = "set_date")
    private Date setDate;
}
