package cn.koboro.points.pojo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 全局设置表
 */
@Data
@Entity
@Table(name = "koboro_points_whole_situation_setting")
public class PointsWholeSituationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *积分/货币折算比 例如1元=100积分
     */
    @Column(name = "ratio_points")
    private Integer ratioPoints;
    /**
     *新用户初始积分
     */
    @Column(name = "new_user_init_points")
    private Integer newUserInitPoints;
    /**
     *报警积分
     */
    @Column(name = "alarm_points")
    private Integer alarmPoints;

    /**
     *报警处理方式
     */
    @Column(name = "processing_mode")
    private Integer processingMode;
    /**
     *报警处理方式，自动增加积分积分
     */
    @Column(name = "add_points")
    private Integer addPoints;
    /**
     *报警处理方式,邮箱警报
     */
    @Column(name = "email")
    private String email;
    /**
     *清算日期
     */
    @Column(name = "clearing_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date clearingDate;
    /**
     *清算策略 1：用户积分不变 2：完全归零 3:按照比例清算
     */
    @Column(name = "clearing_strategy")
    private Integer clearingStrategy;
    /**
     *清算比例 当清算策略为3 此字段不为空
     */
    @Column(name = "clearing_ratio")
    private Integer clearingRatio;
    /**
     * 状态 0 使用中 1已失效  每次创建将之前的置为失效状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
