package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.UserPointsRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserPointsRecordMapper extends Mapper<UserPointsRecord> {
    List<Map<String,Object>> findPointsDetail();
}
