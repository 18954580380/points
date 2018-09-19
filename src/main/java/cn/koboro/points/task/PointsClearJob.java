package cn.koboro.points.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;
import cn.koboro.points.pojo.entity.UserPointsLoseEfficacyRecord;
import cn.koboro.points.pojo.entity.UserPointsTotalDetail;
import cn.koboro.points.repository.PointsWholeSituationSettingMapper;
import cn.koboro.points.repository.UserPointsLoseEfficacyRecordMapper;
import cn.koboro.points.repository.UserPointsTotalDetailMapper;
import cn.koboro.points.utils.Validator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 指定日期积分比例清算任务
 * @author admin
 *
 */
@Component("PointsClearJob")
public class PointsClearJob {
	@Resource
	private PointsWholeSituationSettingMapper pointsWholeSituationSettingMapper;
	@Resource
	private UserPointsTotalDetailMapper userPointsTotalDetailMapper;
	@Resource
	private UserPointsLoseEfficacyRecordMapper userPointsLoseEfficacyRecordMapper;

    //每天2点0分0秒时执行
	 @Scheduled(cron = "0 0 2 * * ?")
	//@Scheduled(fixedRate=20000)
	@Transactional
    public void pointsClearJob() throws Exception{
		 PointsWholeSituationSetting pointsWholeSituationSetting=pointsWholeSituationSettingMapper.findPointsWholeSituationSetting();
    	if(Validator.isEmpty(pointsWholeSituationSetting)){
    		//等于空不做任何事情，这时尚未指定清算日期
    	}else{
			//积分清算时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    		String processTime=sdf.format(pointsWholeSituationSetting.getClearingDate());
    		//获取当前时间
    		String nowTime=sdf.format(new Date());
    		//如果当前时间等于清算时间，将用户获得积分按照清算比例清算
    		if(nowTime.equals(processTime)){
    			//获取清算比例
				//清算策略 1：用户积分不变 2：完全归零 3:按照比例清算
				int clearingStrategy=pointsWholeSituationSetting.getClearingStrategy();
				if(clearingStrategy==1){
					//用户积分不变
				}else{
					//查询所有的用户积分表
					List<UserPointsTotalDetail> userPointsTotalDetails=userPointsTotalDetailMapper.selectAll();
					//按照比例清算
					if(clearingStrategy==3){
						 int clearingRatio=pointsWholeSituationSetting.getClearingRatio();
						//按照比例清算值扣除积分
						BigDecimal ratio =new BigDecimal(clearingRatio).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
						for(UserPointsTotalDetail userPointsTotalDetail :userPointsTotalDetails){
							//用户留存积分
							Integer userKeepPoints=userPointsTotalDetail.getUserKeepPoints();
							Integer clearingPoints=new BigDecimal(userKeepPoints).multiply(ratio). setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
							userPointsTotalDetail.setUserKeepPoints(userKeepPoints-clearingPoints);
							userPointsTotalDetail.setUserDisabledPoints(userPointsTotalDetail.getUserDisabledPoints()+clearingPoints);
							userPointsTotalDetail.setUpdateTime(new Date());
							//更新用户积分总体详情
							userPointsTotalDetailMapper.updateByPrimaryKey(userPointsTotalDetail);
							//插入一条用户清算记录
							UserPointsLoseEfficacyRecord userPointsLoseEfficacyRecord=new UserPointsLoseEfficacyRecord();
							userPointsLoseEfficacyRecord.setArchivalNumber(userPointsTotalDetail.getArchivalNumber());
							userPointsLoseEfficacyRecord.setPoints(clearingPoints);
							userPointsLoseEfficacyRecord.setCreateTime(new Date());
							userPointsLoseEfficacyRecordMapper.insertSelective(userPointsLoseEfficacyRecord);
						}
					}else{
						//积分留存清零
						for(UserPointsTotalDetail userPointsTotalDetail :userPointsTotalDetails){
							//用户留存积分
							Integer userKeepPoints=userPointsTotalDetail.getUserKeepPoints();
							userPointsTotalDetail.setUserKeepPoints(0);
							userPointsTotalDetail.setUserDisabledPoints(userPointsTotalDetail.getUserDisabledPoints()+userKeepPoints);
							userPointsTotalDetail.setUpdateTime(new Date());
							//更新用户积分总体详情
							userPointsTotalDetailMapper.updateByPrimaryKey(userPointsTotalDetail);
							//插入一条用户清算记录
							UserPointsLoseEfficacyRecord userPointsLoseEfficacyRecord=new UserPointsLoseEfficacyRecord();
							userPointsLoseEfficacyRecord.setArchivalNumber(userPointsTotalDetail.getArchivalNumber());
							userPointsLoseEfficacyRecord.setPoints(userKeepPoints);
							userPointsLoseEfficacyRecord.setCreateTime(new Date());
							userPointsLoseEfficacyRecordMapper.insertSelective(userPointsLoseEfficacyRecord);
						}
					}
				}
    		}
    	}
    	
  }
}
