package cn.koboro.points.task;
import cn.koboro.points.componen.Email;
import cn.koboro.points.pojo.entity.PointsPool;
import cn.koboro.points.pojo.entity.PointsPoolPutRecord;
import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;
import cn.koboro.points.repository.PointsPoolMapper;
import cn.koboro.points.repository.PointsWholeSituationSettingMapper;
import cn.koboro.points.service.PointsPoolPutRecordService;
import cn.koboro.points.utils.EmailUtil;
import cn.koboro.points.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
@Component("EmailJob")
public class EmailJob {
	@Autowired
	private Email email;
	@Resource
	private PointsPoolMapper pointsPoolMapper;
	@Resource
	private PointsWholeSituationSettingMapper pointsWholeSituationSettingMapper;
	@Resource
	private PointsPoolPutRecordService pointsPoolPutRecordService;
	     //每天12点12分12秒时执行
         @Scheduled(cron = "12 12 12 * * ?")
	     //@Scheduled(fixedRate=20000)
	     public void emailJob() throws Exception{
         	//查询当前策略积分报警处理方式
			 PointsWholeSituationSetting pointsWholeSituationSetting=pointsWholeSituationSettingMapper.findPointsWholeSituationSetting();
			 if(Validator.isEmpty(pointsWholeSituationSetting)){
			 	return ;
			 }else{
			 	int processingMode=pointsWholeSituationSetting.getProcessingMode();
			 	int alarmPoints=pointsWholeSituationSetting.getAlarmPoints();
				 //积分池当前剩余积分
				 PointsPool pointsPool=pointsPoolMapper.selectByPrimaryKey(1);
				 int  points=pointsPool.getPoints();
				 //剩余积分小于报警积分
				 if(points<alarmPoints){
					 if(processingMode==1){
					 //发送邮件
						 String title="积分池剩余积分提示";
						 String message="积分池当前积分剩余"+points+"！";
						 //收件人邮箱
						 String toUserName=pointsWholeSituationSetting.getEmail();
						 EmailUtil.sendEmail(email,toUserName,title,message);
					 }else{
						//自动投放积分
						 int addPoints=pointsWholeSituationSetting.getAddPoints();
						 PointsPoolPutRecord pointsPoolPutRecord=new PointsPoolPutRecord();
						 pointsPoolPutRecord.setPutPoints(addPoints);
						 //插入一条投放积分记录
						 pointsPoolPutRecordService.putPoint(pointsPoolPutRecord);
					 }
				 }

			 }
	   }
}

