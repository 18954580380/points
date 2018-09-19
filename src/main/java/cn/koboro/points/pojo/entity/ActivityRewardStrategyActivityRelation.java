package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动奖励策略管理活动表
 */
@Entity
@Data
@Table(name = "koboro_activity_reward_strategy_activity_relation")
public class ActivityRewardStrategyActivityRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 活动id
     */
    @Column(name = "activity_id")
    private Integer activityId;
    /**
     * 活动名称
     */
    @Column(name = "activity_name")
    private String activityName;
    /**
     *活动奖励策略Id
     */
    @Column(name = "activity_remard_strategy_id")
    private Integer activityRemardStrategyId;

}
