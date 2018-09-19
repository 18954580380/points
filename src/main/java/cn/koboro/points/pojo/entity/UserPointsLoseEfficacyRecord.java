package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户积分失效记录
 */
@Data
@Entity
@Table(name = "koboro_user_points_lose_efficacy_record")
public class UserPointsLoseEfficacyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 档案号
     */
    @Column(name = "archival_number")
    private String archivalNumber;
    /**
     *失效积分
     */
    @Column(name = "points")
    private Integer points;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
