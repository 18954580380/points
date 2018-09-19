package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品奖励策略
 */
@Data
@Entity
@Table(name = "koboro_goods_reward_strategy")
public class GoodsRewardStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品奖品策略名称 默认：'商品买赠积分'
     */
    @Column(name = "reward_strategy_name")
    private String  rewardStrategyName;
    /**
     *商品奖励范围 1:全部商品适用 2:部分商品适用
     */
    @Column(name = "goods_reward_scope")
    private Integer  goodsRewardScope;
    /**
     *商品奖励积分比例
     */
    @Column(name = "goods_reward_ratio")
    private Integer  goodsRewardRatio;
    /**
     *商品奖励积策略是否删除
     */
    @Column(name = "is_delete")
    private Integer  isDelete;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     *修改时间
     */
    @Column(name = "update_time")
    private Date  updateTime;


}
