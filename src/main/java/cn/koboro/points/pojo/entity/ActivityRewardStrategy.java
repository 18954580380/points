package cn.koboro.points.pojo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动奖励策略
 */
@Data
@Entity
@Table(name = "koboro_activity_reward_strategy")
public class ActivityRewardStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 奖励策略名称
     */
    @Column(name = "reward_strategy_name")
    private String rewardStrategyName;
    /**
     * 奖励对象 1:不限人群
     */
    @Column(name = "reward_object")
    private Integer rewardObject;
    /**
     * 奖励条件 1:特定周期
     */
    @Column(name = "reward_condition")
    private Integer rewardCondition;
    /**
     * 奖励发放起始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "reward_grant_start_time")
    private Date rewardGrantStartTime;
    /**
     * 奖励发放终止时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "reward_grant_end_time")
    private Date rewardGrantEndTime;
    /**
     *奖励发放方式 1:用户登录既领取 2:用户通过活动领取
     */
    @Column(name = "reward_way")
    private Integer rewardWay;
    /**
     *用户奖励积分
     */
    @Column(name = "reward_points")
    private int rewardPoints;
    /**
     *策略说明
     */
    @Column(name = "strategy_on")
    private String strategyOn;
    /**
     *活动图片，奖励方式为1的时候，会上传图片
     */
    @Column(name = "activity_img_url")
    private String activityImgUrl;

     /**
      *
     *针对已过期策略 逻辑删除字段 1 删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;
    /**
     *策略创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     *策略修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     *  未开始  已发放   已过期
     *  mybatis映射忽略字段
     */
    @Transient
    private Integer status;
    /**
     *  序号
     *  mybatis映射忽略字段
     */
    @Transient
    private Integer index;

}
