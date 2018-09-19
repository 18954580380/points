package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.PointsPoolPutRecord;

import java.util.List;

public interface PointsPoolPutRecordService {
    List<PointsPoolPutRecord> selectAll();

    int putPoint(PointsPoolPutRecord pointsPoolPutRecord);
}
