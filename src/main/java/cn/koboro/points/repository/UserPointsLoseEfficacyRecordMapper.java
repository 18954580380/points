package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.UserPointsLoseEfficacyRecord;
import tk.mybatis.mapper.common.Mapper;

public interface UserPointsLoseEfficacyRecordMapper extends Mapper<UserPointsLoseEfficacyRecord> {
    Integer findTotalLosePoints();
}
