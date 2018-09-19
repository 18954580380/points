package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.ActivityRewardStrategyActivityRelation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ActivityRewardStrategyActivityRelationMapper extends Mapper<ActivityRewardStrategyActivityRelation> {
    void deleteByActivityRewardStrategyId(Integer id);

    List<Map<String,Object>> findActivityRewardStrategyActivityRelation(Integer id);
}
