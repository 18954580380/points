package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.ActivityRewardStrategy;

import java.util.List;

public interface ActivityRewardStrategyService {
    List<ActivityRewardStrategy> selectAll(Integer status);

    int saveActivityRewardStrategy(ActivityRewardStrategy activityRewardStrategy, String activityIds, String activeName);

    int deleteById(Integer id, Integer status);

    ActivityRewardStrategy findActivityRewardStrategyById(Integer id);

    int UpdateActivityRewardStrategy(ActivityRewardStrategy activityRewardStrategy, String activityIds, String activityName);
}
