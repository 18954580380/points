package cn.koboro.points.service.impl;

import cn.koboro.points.annotation.AutoPage;
import cn.koboro.points.pojo.entity.PointsPool;
import cn.koboro.points.pojo.entity.PointsPoolPutRecord;
import cn.koboro.points.repository.PointsPoolMapper;
import cn.koboro.points.repository.PointsPoolPutRecordMapper;
import cn.koboro.points.repository.UserPointsLoseEfficacyRecordMapper;
import cn.koboro.points.repository.UserPointsRecordMapper;
import cn.koboro.points.service.PointsPoolPutRecordService;
import cn.koboro.points.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PointsPoolPutRecordServiceImpl implements PointsPoolPutRecordService {
    @Resource
    private PointsPoolPutRecordMapper pointsPoolPutRecordMapper;
    @Resource
    private  UserPointsRecordMapper userPointsRecordMapper;
    @Resource
    private PointsPoolMapper pointsPoolMapper;
    @Resource
    private UserPointsLoseEfficacyRecordMapper userPointsLoseEfficacyRecordMapper;
    /**
     * 查询所有的投放记录列表
     * @return
     */
    @AutoPage
    @Override
    public List<PointsPoolPutRecord> selectAll() {
        return pointsPoolPutRecordMapper.selectAll();
    }

    /**
     * 投放积分
     * @param pointsPoolPutRecord
     * @return
     */
    @Override
    public int putPoint(PointsPoolPutRecord pointsPoolPutRecord) {
        //投放时间
        pointsPoolPutRecord.setCreateTime(new Date());
        //截止当次积分累计投放积分
        Integer putTotalPoints=pointsPoolPutRecordMapper.totalPutPoints();
        Integer totalPutPoints=(Validator.isEmpty(putTotalPoints)?0:putTotalPoints)+
                pointsPoolPutRecord.getPutPoints();
        pointsPoolPutRecord.setTotalPutPoints(totalPutPoints);
        //查询积分池累计赠送1 	用户已领取积分总数	用户已消费积分总数
        List<Map<String,Object>>userRecordDetail=userPointsRecordMapper.findPointsDetail();
        if(!Validator.isEmpty(userRecordDetail)){
           for(Map<String,Object>map:userRecordDetail){
               int source=Integer.valueOf(map.get("source").toString());
               int points=Integer.valueOf(map.get("points").toString());
               if(source==1){
                   pointsPoolPutRecord.setTotalPresentedPoints(points);
               }
               else{
                   if(source==2 || source==3){
                       pointsPoolPutRecord.setUserGetTotalPoints((
                               Validator.isEmpty(pointsPoolPutRecord.getUserGetTotalPoints())?
                                       0:pointsPoolPutRecord.getUserGetTotalPoints())+points);
                   }else{
                       pointsPoolPutRecord.setUserExpenseTotalPoints(points);
                   }
               }

           }
        }
        //积分池剩余积分总数
        PointsPool pointsPool=pointsPoolMapper.selectByPrimaryKey(1);
        //用户留存积分总数=系统赠送+用户领取-用户消费-用户失效
        //查询用户失效总积分
        Integer totalLosePoints= userPointsLoseEfficacyRecordMapper.findTotalLosePoints();
        if(Validator.isEmpty(totalLosePoints)){
            totalLosePoints=0;
        }
       int userKeepTotalPoints=pointsPoolPutRecord.getTotalPresentedPoints()+pointsPoolPutRecord.getUserGetTotalPoints()
               -pointsPoolPutRecord.getUserExpenseTotalPoints()-totalLosePoints;
        pointsPoolPutRecord.setUserKeepTotalPoints(userKeepTotalPoints);
        //更新积分池可用积分
        pointsPool.setPoints(pointsPool.getPoints()+pointsPoolPutRecord.getPutPoints());
        pointsPoolMapper.updateByPrimaryKey(pointsPool);
        //保存实体
        pointsPoolPutRecord.setResidueTotalPoints(pointsPool.getPoints());
        return pointsPoolPutRecordMapper.insertSelective(pointsPoolPutRecord);
    }
}
