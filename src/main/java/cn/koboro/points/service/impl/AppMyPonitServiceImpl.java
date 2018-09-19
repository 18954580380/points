package cn.koboro.points.service.impl;
import cn.koboro.points.pojo.entity.*;
import cn.koboro.points.repository.*;
import cn.koboro.points.service.AppMyPonitService;
import cn.koboro.points.utils.PercentUtil;
import cn.koboro.points.utils.TimeUtil;
import cn.koboro.points.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@ResponseBody
public class AppMyPonitServiceImpl implements AppMyPonitService {
	@Resource
	private AppMyPonitMapper appMyPonitMapper;
	@Resource
	private UserPointsTotalDetailMapper userPointsTotalDetailMapper;
	@Resource
	private UserEmpiricalValueTotalDetailMapper userEmpiricalValueTotalDetailMapper;
	@Resource
	private EmpiricalValueLevelMapper empiricalValueLevelMapper;
	@Resource
	private PointsItemMapper pointsItemMapper;
	@Resource
	private EmpiricalValueItemMapper empiricalValueItemMapper;
	/**
	 * 添加个人积分来源
	 * caller 强豪
	 */
	@Override
	public Integer addIntegralSource(UserPointsRecord userPointsRecord) {
		userPointsRecord.setSource(5);
		Integer count=appMyPonitMapper.insertSelective(userPointsRecord);
		return count;
	}
    /**
     * App我的积分详情
     */
	@Override
	public Map<String, Object> findPointsDetailByArchivalNumber(String archivalNumber) {
		Map<String, Object> mapData = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		//获取的积分
		Integer getIntegral = 0;
		//消费的积分
		Integer expenseIntegral = 0;
		//剩余总积分
		Integer totleIntegral = 0;
		//今日新增积分
		Integer addIntegral = 0;
		//积分失效时间
		String lostTime = "";
		//失效积分
		Integer lostIntegral = 0;
		//积分来源
		List<Map<String, Object>> integralRecords = appMyPonitMapper.findMyTotleIntegral(archivalNumber);
		for (Map<String, Object> map : integralRecords) {
			String type = map.get("type").toString();
			String description = Validator.isEmpty(map.get("description")) ? "" : map.get("description").toString();
			if (map.get("type").toString().equals("1")) {
				getIntegral += Integer.valueOf(map.get("points").toString());
			} else {
				expenseIntegral += Integer.valueOf(map.get("points").toString());
			}
			//积分来源，积分
			Map<String, Object> dataMap = new HashMap<>();
			String record = "";
			String integral = "";
			String sign = "";
			if ("1".equals(type)) {
				sign = "0";
				record = "获取的积分(" + description + ")";
				integral = "+" + (Validator.isEmpty(map.get("points")) ? "" : map.get("points").toString());
			} else {
				sign = "1";
				record = "使用的的积分(" + description + ")";
				integral = "-" + (Validator.isEmpty(map.get("points")) ? "" : map.get("points").toString());
			}
			dataMap.put("sign", sign);
			dataMap.put("record", record);
			dataMap.put("integral", integral);
			dataMap.put("time", map.get("create_time").toString().substring(0, 10));
			list.add(dataMap);
		}
		totleIntegral = getIntegral - expenseIntegral;
		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		addIntegral = appMyPonitMapper.findAddIntegral(archivalNumber, nowTime);
		//查询
		List<Map<String, Object>> integralSettings = appMyPonitMapper.findIntegralSetting();
		lostTime =new SimpleDateFormat("yyyy-MM-dd").format(integralSettings.get(0).get("clearing_date")) ;
		//失效比率
		String liquidationRate = integralSettings.get(0).get("clearing_ratio").toString();
		//清算策略
		String clearingStrategy = integralSettings.get(0).get("clearing_strategy").toString();
		if ("1".equals(clearingStrategy)){
	    }
		else{
           if("2".equals(clearingStrategy)){
			   lostIntegral=totleIntegral;
		   }else{
			   lostIntegral=PercentUtil.mul(totleIntegral, Integer.valueOf(liquidationRate));
		   }

		}
		//总积分
		mapData.put("totleIntegral", Validator.isEmpty(totleIntegral)?0:totleIntegral);
		//今日新增积分
		mapData.put("addIntegral", Validator.isEmpty(addIntegral)?0:addIntegral);
		//失效积分
		mapData.put("lostIntegral", Validator.isEmpty(lostIntegral)?0:lostIntegral);
		//失效时间
		mapData.put("lostTime", lostTime);
		//积分消费获取记录
		mapData.put("record", list);
		//xp经验值查看
		findUserLeve(mapData,archivalNumber);
	    return mapData;
	}
	/**
	 * 查询最近几天的消费,获取记录
	 */
	@Override
	public Map<String, Integer> findIntegralByTime(String day, String archivalNumber ) {
		//获取当前日期前day日期
		Map<String,Integer>map=new HashMap<>();
		String time=TimeUtil.getDateBefore(new Date(), Integer.valueOf(day));
		Integer integral=appMyPonitMapper.findGetIntegral(time,archivalNumber,1);
		Integer expenseIntegral=appMyPonitMapper.findGetIntegral(time,archivalNumber,2);
		map.put("integral", Validator.isEmpty(integral)?0:integral);
		map.put("expenseIntegral", Validator.isEmpty(expenseIntegral)?0:expenseIntegral);
		return map;
	}
	/**
	 * 更新个人总积分
	 */
	@Override
	public int  updateUserPointsTotalDetail(UserPointsTotalDetail userPointsTotalDetail) {
		//查询个人积分表中是否存在该用户，不存在则是创建
		UserPointsTotalDetail u=appMyPonitMapper.findUserPointsTotalDetail(userPointsTotalDetail.getArchivalNumber());
		int number;
		if(Validator.isEmpty(u)){
		  number =userPointsTotalDetailMapper.insertSelective(userPointsTotalDetail);
		  }else{
			u.setUserKeepPoints(u.getUserKeepPoints()+userPointsTotalDetail.getUserKeepPoints());
			u.setUserGetPoints(u.getUserGetPoints()+userPointsTotalDetail.getUserGetPoints());
			u.setUpdateTime(new Date());
		    number =userPointsTotalDetailMapper.updateByPrimaryKey(u);
		  }
		  return number;
	}
	/**
	 * 更新积分池总积分
	 */
	@Override
	public void updatePointsPool(Integer points) {
		appMyPonitMapper.updatePointsPool(points);
	}

	/**
	 * 积分与经验值规则
	 * @return
	 */
	@Override
	public Map<String,Object> findRegulation() {
		Map<String,Object>map=new HashMap<>();
		//积分规则
		List<PointsItem>pointsItems =pointsItemMapper.selectAll();
		map.put("pointsItems",pointsItems);
		List<EmpiricalValueItem>empiricalValueItems =empiricalValueItemMapper.selectAll();
		map.put("empiricalValueItems",empiricalValueItems);
		//经验值规则
		return map;
	}

	/**
	 *查看用户经验值等级
	 */
	public Map<String,Object> findUserLeve(Map<String,Object>map,String archivalNumber){
		String level="";
		String levelName="";
		Map<String,Object>mapDate=userEmpiricalValueTotalDetailMapper.findUserLeve(archivalNumber);
		map.put("level",mapDate.get("user_level"));
		map.put("levelName",mapDate.get("user_title"));
		EmpiricalValueLevel empiricalValueLevel=empiricalValueLevelMapper.selectByPrimaryKey(Integer.valueOf(mapDate.get("id").toString())+1);
		if(Validator.isEmpty(empiricalValueLevel)){
			map.put("nextLevelName",mapDate.get("user_title"));
			map.put("xp",0);
		}else{
			map.put("nextLevelName",empiricalValueLevel.getUserTitle());
			Integer minXp=empiricalValueLevel.getXpMin();
			map.put("xp",minXp-Integer.valueOf(mapDate.get("total_empirical_value").toString()));
		}
        return map;
	}
}
