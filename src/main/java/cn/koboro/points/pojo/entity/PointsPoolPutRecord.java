package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分池投放积分记录表
 */
@Data
@Entity
@Table(name = "koboro_points_pool_put_record")
public class PointsPoolPutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *本次新投放积分数量
     */
    @Column(name = "put_points")
    private Integer putPoints;
    /**
     *积分池累计投放积分
     */
    @Column(name = "total_put_points")
    private Integer totalPutPoints;
    /**
     *积分池累计赠送
     */
    @Column(name = "total_presented_points")
    private Integer totalPresentedPoints=0;
    /**
     *用户已领取积分总数
     */
    @Column(name = "user_get_total_points")
    private Integer userGetTotalPoints=0;
    /**
     *用户已消费积分总数
     */
    @Column(name = "user_expense_total_points")
    private Integer userExpenseTotalPoints=0;
    /**
     *用户留存积分总数
     */
    @Column(name = "user_keep_total_points")
    private Integer userKeepTotalPoints;
    /**
     *积分池剩余积分总数
     */
    @Column(name = "residue_total_points")
    private Integer residueTotalPoints;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     *创建人
     */
    @Column(name = "creator")
    private String creator;
}
