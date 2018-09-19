package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.UserPointsRecord;
import cn.koboro.points.pojo.entity.UserPointsTotalDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AppMyPonitMapper extends Mapper<UserPointsRecord> {
	//查询是否存在用户积分表
	 UserPointsTotalDetail findUserPointsTotalDetail(@Param("archivalNumber") String archivalNumber);

	 List<Map<String,Object>> findMyTotleIntegral(@Param("archivalNumber") String archivalNumber);
	 //今日新增积分
	 Integer findAddIntegral(@Param("archivalNumber") String archivalNumber, @Param("nowTime") String nowTime);
	 //查询积分失效时间和比例
	 List<Map<String,Object>>  findIntegralSetting();
	 //查询最近获取的积分
	 Integer findGetIntegral(@Param("time") String time, @Param("archivalNumber") String archivalNumber, @Param("type") Integer type);
	  //积分池积分更换
	 void updatePointsPool(@Param("points") Integer points);
}
