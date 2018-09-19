package cn.koboro.points.service.impl;

import cn.koboro.points.annotation.AutoPage;
import cn.koboro.points.pojo.entity.PointsPool;
import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;
import cn.koboro.points.repository.*;
import cn.koboro.points.service.PointsWholeSituationSettingSerivice;
import cn.koboro.points.utils.Validator;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PointsWholeSituationSettingServiceImpl implements PointsWholeSituationSettingSerivice {
    @Resource
    private PointsWholeSituationSettingMapper pointsWholeSituationSettingMapper;
    @Resource
    private PointsPoolMapper pointsPoolMapper;
    @Resource
    private PointsPoolPutRecordMapper pointsPoolPutRecordMapper;
    @Resource
    private UserPointsRecordMapper userPointsRecordMapper;
    @Resource
    private UserPointsLoseEfficacyRecordMapper userPointsLoseEfficacyRecordMapper;
    /**
     * 查询所有全局设置
     * @return
     */
    @AutoPage
    @Override
    public List<PointsWholeSituationSetting> selectAll() {
        return pointsWholeSituationSettingMapper.selectAll();
    }

    /**
     * 详情
     * @return
     */
    @Override
    public List<Map<String, Object>> findDetails() {
        List<Map<String,Object>>listData=new ArrayList<>();
        Map<String,Object>mapData=new HashedMap();
        //积分池当前剩余积分
        PointsPool pointsPool=pointsPoolMapper.selectByPrimaryKey(1);
        mapData.put("points",pointsPool.getPoints());
        //积分池累计投放积分
        Integer putTotalPoints=pointsPoolPutRecordMapper.totalPutPoints();
        mapData.put("putTotalPoints",Validator.isEmpty(putTotalPoints)?0:putTotalPoints);
        //积分池累计赠送积分总数   用户已领取积分总数  用户已消费积分总数
        List<Map<String,Object>>userRecordDetail=userPointsRecordMapper.findPointsDetail();
        if(!Validator.isEmpty(userRecordDetail)){
            for(Map<String,Object>map:userRecordDetail){
                int source=Integer.valueOf(map.get("source").toString());
                int points=Integer.valueOf(map.get("points").toString());
                if(source==1){
                    mapData.put("totalPresentedPoints",points);
                }
                else{
                    if(source==2 || source==3){
                        mapData.put("userGetTotalPoints",Validator.isEmpty(
                                mapData.get("userGetTotalPoints"))?
                                         0:(Integer)mapData.get("userGetTotalPoints")+points);
                    }else{
                        mapData.put("userExpenseTotalPoints",points);
                    }
                }

            }
        }
        else{
            mapData.put("totalPresentedPoints",0);
            mapData.put("userGetTotalPoints",0);
            mapData.put("userExpenseTotalPoints",0);
        }
        //用户留存积分总数
        //用户留存积分总数=系统赠送+用户领取-用户消费-用户失效
        //查询用户失效总积分
        Integer totalLosePoints= userPointsLoseEfficacyRecordMapper.findTotalLosePoints();
        if(Validator.isEmpty(totalLosePoints)){
            totalLosePoints=0;
        }
        int userKeepTotalPoints=(Integer) mapData.get("totalPresentedPoints")+(Integer) mapData.get("userGetTotalPoints")
                               -(Integer) mapData.get("userExpenseTotalPoints")-totalLosePoints;
        mapData.put("userKeepTotalPoints",userKeepTotalPoints);
        //用户积分回收总数
        mapData.put("totalLosePoints",totalLosePoints);
        //当前积分货币清算比
        Integer ratioPoints=pointsWholeSituationSettingMapper.findRatioPoints();
        if(Validator.isEmpty(ratioPoints)){
            mapData.put("ratioPoints","");
        }else{
            mapData.put("ratioPoints","1:"+ratioPoints);
        }
        listData.add(mapData);
        return listData;
    }

    /**
     * 保存积分池策略
     * @param pointsWholeSituationSetting
     */
    @Override
    public void savePointsWholeSituationSetting(PointsWholeSituationSetting pointsWholeSituationSetting) {
        //保存之前更新其它策略为失效状态
        pointsWholeSituationSettingMapper.updateStatus();
        pointsWholeSituationSettingMapper.insertSelective(pointsWholeSituationSetting);
    }
}
