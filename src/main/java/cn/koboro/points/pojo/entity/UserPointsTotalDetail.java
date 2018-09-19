package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户积分总体详情
 */
@Data
@Entity
@Table(name = "koboro_user_points_total_detail")
public class UserPointsTotalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 档案号
     */
    @Column(name = "archival_number")
    private String archivalNumber;
    /**
     *用户已领取积分
     */
    @Column(name = "user_get_points")
    private Integer userGetPoints;
    /**
     *用户被系统赠送积分
     */
    @Column(name = "user_presented_points")
    private Integer userPresentedPoints;
    /**
     *用户消费积分
     */
    @Column(name = "user_expense_points")
    private Integer userExpensePoints;
    /**
     *用户留存积分
     */
    @Column(name = "user_keep_points")
    private Integer userKeepPoints;
    /**
     *用户失效积分
     */
    @Column(name = "user_disabled_points")
    private Integer userDisabledPoints;
    /**
     *更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
