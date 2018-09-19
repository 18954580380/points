package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户积分记录表
 */
@Data
@Entity
@Table(name = "koboro_user_points_record")
public class UserPointsRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 档案号
     */
    @Column(name = "archival_number")
    private String archivalNumber;
    /**
     *获取积分或者消费积分的数量
     */
    @Column(name = "points")
    private Integer points;
    /**
     *1 系统赠送 2 活动奖励 3 商品奖励  4 商品兑换
     */
    @Column(name = "source")
    private Integer source;

    /**
     *来源信息Id 为source字段对应分类表Id   如source=1  source_info_id取积分项目表id
     */
    @Column(name = "source_info_id")
    private Integer sourceInfoId;
    /**
     *1: 获取 2:消耗
     */
    @Column(name = "type")
    private Integer type;
    /**
     *描述
     */
    @Column(name = "description")
    private String description;
    /**
     *创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
