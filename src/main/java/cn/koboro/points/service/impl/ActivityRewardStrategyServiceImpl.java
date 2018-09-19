package cn.koboro.points.service.impl;

import cn.koboro.points.annotation.AutoPage;
import cn.koboro.points.pojo.entity.ActivityRewardStrategy;
import cn.koboro.points.pojo.entity.ActivityRewardStrategyActivityRelation;
import cn.koboro.points.repository.ActivityRewardStrategyActivityRelationMapper;
import cn.koboro.points.repository.ActivityRewardStrategyMapper;
import cn.koboro.points.service.ActivityRewardStrategyService;
import cn.koboro.points.utils.DateUtil;
import cn.koboro.points.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ActivityRewardStrategyServiceImpl implements ActivityRewardStrategyService {
    @Resource
    private ActivityRewardStrategyMapper activityRewardStrategyMapper;
    @Resource
    private ActivityRewardStrategyActivityRelationMapper activityRewardStrategyActivityRelationMapper;
    @AutoPage
    @Override
    public List<ActivityRewardStrategy> selectAll(Integer status) {
        List<ActivityRewardStrategy> activityRewardStrategies=activityRewardStrategyMapper.findActivityRewardStrategyByStatus(status);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i=0;
        for ( ActivityRewardStrategy activityRewardStrategy:activityRewardStrategies){
              i++;
              String startTime=simpleDateFormat.format(activityRewardStrategy.getRewardGrantStartTime());
              String endTime=simpleDateFormat.format(activityRewardStrategy.getRewardGrantEndTime());
              activityRewardStrategy.setIndex(i);
              if(!DateUtil.dataCompare(startTime)){
                  //未开始
                   activityRewardStrategy.setStatus(1);
              }else{
                  if(!DateUtil.dataCompare(endTime)){
                      //已发布
                      activityRewardStrategy.setStatus(2);
                  }else{
                      //已过期
                      activityRewardStrategy.setStatus(3);
                  }
              }
        }
        return  activityRewardStrategies;
    }

    /**
     * 保存活动奖励
     * @param activityRewardStrategy
     * @param activityIds
     * @param activeName
     * @return
     */
    @Override
    public int saveActivityRewardStrategy(ActivityRewardStrategy activityRewardStrategy, String activityIds, String activeName) {
        //保存活动奖励主表
         activityRewardStrategy.setCreateTime(new Date());
         activityRewardStrategyMapper.insertSelective(activityRewardStrategy);
         int id=activityRewardStrategy.getId();
        //插入活动奖励关联活动表
        if(activityRewardStrategy.getRewardWay()==2){
            if(!Validator.isEmpty(activityIds)){
                List<String>aids=Arrays.asList(activityIds.split(","));
                List<String>names=Arrays.asList(activeName.split(","));
                for (int i=0;i<aids.size();i++){
                    //插入奖励策略关联活动表
                    ActivityRewardStrategyActivityRelation activityRewardStrategyActivityRelation=new ActivityRewardStrategyActivityRelation();
                    activityRewardStrategyActivityRelation.setActivityRemardStrategyId(id);
                    activityRewardStrategyActivityRelation.setActivityId(Integer.valueOf(aids.get(i)));
                    activityRewardStrategyActivityRelation.setActivityName(names.get(i));
                    activityRewardStrategyActivityRelationMapper.insertSelective(activityRewardStrategyActivityRelation);
                }
            }
        }
        return id;
    }
    /**
     * 删除活动奖励策略
     * @param id
     * @param status
     * @return
     */
    @Override
    public int deleteById(Integer id, Integer status) {
        int count=0;
        //已发放不删除
        if(status==2){
            return count;
        }
        //未开始 直接删除
        else if (status==1){
            //删除活动奖励策略关联活动的表
            activityRewardStrategyActivityRelationMapper.deleteByActivityRewardStrategyId(id);
            //删除活动策略
             count=activityRewardStrategyMapper.deleteByPrimaryKey(id);
        }else{
            //已过期 使用逻辑删除
            count=activityRewardStrategyMapper.updateIsDeleteById(id);
        }
        return count;
    }

    /**
     * 根据id查询奖励策略
     * @param id
     * @return
     */
    @Override
    public ActivityRewardStrategy findActivityRewardStrategyById(Integer id) {
        return activityRewardStrategyMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新活動奖励策略
     * @param activityRewardStrategy
     * @param activityIds
     * @param activityName
     * @return
     */
    @Override
    public int UpdateActivityRewardStrategy(ActivityRewardStrategy activityRewardStrategy, String activityIds, String activityName) {
       //跟新活动奖励策略主表
        activityRewardStrategy.setUpdateTime(new Date());
        int count=activityRewardStrategyMapper.updateByPrimaryKeySelective(activityRewardStrategy);
        //删除关联的活动
        activityRewardStrategyActivityRelationMapper.deleteByActivityRewardStrategyId(activityRewardStrategy.getId());
        if(activityRewardStrategy.getRewardWay()==2){
            if(!Validator.isEmpty(activityIds)){
                List<String>aids=Arrays.asList(activityIds.split(","));
                List<String>names=Arrays.asList(activityName.split(","));
                for (int i=0;i<aids.size();i++){
                    //插入奖励策略关联活动表
                    ActivityRewardStrategyActivityRelation activityRewardStrategyActivityRelation=new ActivityRewardStrategyActivityRelation();
                    activityRewardStrategyActivityRelation.setActivityRemardStrategyId(activityRewardStrategy.getId());
                    activityRewardStrategyActivityRelation.setActivityId(Integer.valueOf(aids.get(i)));
                    activityRewardStrategyActivityRelation.setActivityName(names.get(i));
                    activityRewardStrategyActivityRelationMapper.insertSelective(activityRewardStrategyActivityRelation);
                }
            }

        }
        return count;
    }
}
