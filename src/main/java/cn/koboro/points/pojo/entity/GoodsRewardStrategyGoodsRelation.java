package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
/**
 * 商品奖励策略商品中间表
 */
@Data
@Entity
@Table(name = "koboro_goods_reward_strategy_goods_relation")
public class GoodsRewardStrategyGoodsRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;
    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;
    /**
     *商品所属分类Id
     */
    @Column(name = "goods_category_id")
    private Integer goodsCategoryId;

    /**
     *商品所属分类
     */
    @Column(name = "goods_category")
    private String goodsCategory;

    /**
     *商品奖励策略Id
     */
    @Column(name = "goods_reward_strategy_id")
    private Integer goodsRewardStrategyId;






}
