package cn.koboro.points.pojo.entity;

import lombok.Data;

import javax.persistence.*;
/**
 * 积分池
 */
@Data
@Entity
@Table(name = "koboro_points_pool")
public class PointsPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 积分池当前可用积分
     */
    @Column(name = "points")
    private Integer  points;
}