package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.UserPointsRecord;
import cn.koboro.points.pojo.entity.UserPointsTotalDetail;
import java.util.Map;
public interface AppMyPonitService {
	int updateUserPointsTotalDetail(UserPointsTotalDetail userPointsTotalDetail);
	Map<String,Object>findPointsDetailByArchivalNumber(String archivalNumber);
	Map<String,Integer>findIntegralByTime(String day, String archivalNumber);
	Integer addIntegralSource( UserPointsRecord userPointsRecord);
	void updatePointsPool(Integer points);
	Map<String,Object> findRegulation();
}
