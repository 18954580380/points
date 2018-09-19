package cn.koboro.points.service.impl;

import cn.koboro.points.repository.ActivityRewardStrategyActivityRelationMapper;
import cn.koboro.points.service.ActivityRewardStrategyActivityRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityRewardStrategyActivityRelationServiceImpl implements ActivityRewardStrategyActivityRelationService {
    @Resource
    private ActivityRewardStrategyActivityRelationMapper activityRewardStrategyActivityRelationMapper;
    @Override
    public List<Map<String, Object>> findActivityRewardStrategyActivityRelation(Integer id) {
        return activityRewardStrategyActivityRelationMapper.findActivityRewardStrategyActivityRelation(id);
    }
}
