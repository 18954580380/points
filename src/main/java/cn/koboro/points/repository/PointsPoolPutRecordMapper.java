package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.PointsPoolPutRecord;
import tk.mybatis.mapper.common.Mapper;
public interface PointsPoolPutRecordMapper extends Mapper<PointsPoolPutRecord> {
     Integer totalPutPoints();
}
