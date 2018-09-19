package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分消费策略
 */
@Data
@Entity
@Table(name = "koboro_points_expense_strategy")
public class PointsExpenseStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *积分消费策略名称
     */
    @Column(name = "expense_strategy_name")
    private String expenseStrategyName;
    /**
     *抵现金额(每100积分)
     */
    @Column(name = "points_to_now")
    private BigDecimal pointsToNow;
    /**
     *100:全额抵现,其它比例值均为上限比例
     */
    @Column(name = "discount_max")
    private Integer discountMax;
    /**
     *兑换范围 0:全部商品 1:部分商品
     */
    @Column(name = "convert_scope")
    private Integer convertScope;
    /**
     *0:永久有效 1:指定时段
     */
    @Column(name = "convert_scope_aging")
    private Integer convertScopeAging;
    /**
     *开始时间（永久有效此处为空）
     */
    @Column(name = "start_time")
    private Date startTime;
    /**
     *结束时间（永久有效此处为空）
     */
    @Column(name = "end_time")
    private Date endTime;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     *修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     *策略状态 0:未生效 1:已生效2:已失效
     */
    @Column(name = "expense_strategy_state")
    private Integer expenseStrategyState;
    /**
     * 商品数量
     */
    @Transient
    private Integer goodsCount;
}
