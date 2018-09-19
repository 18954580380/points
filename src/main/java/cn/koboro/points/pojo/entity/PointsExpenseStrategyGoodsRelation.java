package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *  积分消费策略关联商品表
 */
@Data
@Entity
@Table(name = "koboro_points_expense_strategy_goods_relation")
public class PointsExpenseStrategyGoodsRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;
    /**
     *商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;
    /**
     *商品分类
     */
    @Column(name = "goods_category")
    private String goodsCategory;
    /**
     *积分消费策略Id
     */
    @Column(name = "points_expense_strategy_id")
    private Integer pointsExpenseStrategyId;




}
