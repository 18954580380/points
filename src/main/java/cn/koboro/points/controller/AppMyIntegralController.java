package cn.koboro.points.controller;
import cn.koboro.points.pojo.entity.UserPointsRecord;
import cn.koboro.points.pojo.entity.UserPointsTotalDetail;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.AppMyPonitService;
import cn.koboro.points.utils.JsonUtil;
import cn.koboro.points.utils.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/myIntegral")
public class AppMyIntegralController {
	@Resource
	private AppMyPonitService appMyPonitService;
	
	/**
	 * 添加我的积分来源
	 * @param  request
	 * @return ResultVO
	 * @caller 强豪
	 */
	@RequestMapping("/addIntegralSource")
	@ResponseBody
    public ResultVO addIntegralSource(HttpServletRequest request){
			try {
				 JSONObject jsonObject=new JSONObject();
				 Enumeration enu=request.getParameterNames();  
				 String paraName="";
				 while(enu.hasMoreElements()){  
				  paraName=(String)enu.nextElement();
				  //不需要根据参数名和获取参数值,参数名解析后就是需要得json串
				 }
				  UserPointsRecord userPointsRecord =JsonUtil.jsonToObject(UserPointsRecord.class, paraName);
				 if(Validator.isEmpty(userPointsRecord.getArchivalNumber())){
		        	return  ResultVO.error(0, "用户档案号为空");
		        }else{
					 userPointsRecord.setCreateTime(new Date());
		        	 Integer count=appMyPonitService.addIntegralSource(userPointsRecord);
		        	if(count==1){
		        		//存入用户总积分记录表中
						UserPointsTotalDetail userPointsTotalDetail=new UserPointsTotalDetail();
						userPointsTotalDetail.setUpdateTime(new Date());
						userPointsTotalDetail.setArchivalNumber(userPointsRecord.getArchivalNumber());
						userPointsTotalDetail.setUserGetPoints(userPointsRecord.getPoints());
						userPointsTotalDetail.setUserKeepPoints(userPointsRecord.getPoints());
		        		int num=appMyPonitService.updateUserPointsTotalDetail(userPointsTotalDetail);
		        		//更新积分池总积分记录
		        		appMyPonitService.updatePointsPool(userPointsRecord.getPoints());
		        		return  ResultVO.error(1, "保存成功");
		        	}else{
		        		return  ResultVO.error(0, "用户档案号为空");
		        	}
		        }
			} catch (Exception e) {
				System.out.println(e);
				return  ResultVO.error(0, "请求失败");
			}
			


    }
	/**
	 * 我的积分基础信息查询
	 * @param request
	 * @param archivalNumber
	 * @return
	 */
	@RequestMapping("/findIntegralDetail")
	@ResponseBody
    public ResultVO findIntegralDetail(HttpServletRequest request, @RequestParam(defaultValue="0") String archivalNumber){
    	Map<String,Object>map=new HashMap<>();
        if("0".equals(archivalNumber)){
        	return  ResultVO.error(0, "用户档案号为空为空");
        }else{
        	map=appMyPonitService.findPointsDetailByArchivalNumber(archivalNumber);
        	return ResultVO.success(map);
        }
    }
	/**
	 * 我的积分最近获取积分，按照时间查询
	 * @param request
	 * @param archivalNumber
	 * @return
	 */
	@RequestMapping("/findIntegralByTime")
	@ResponseBody
	public ResultVO findIntegralByTime(HttpServletRequest request, @RequestParam(defaultValue="3")String day, @RequestParam(defaultValue="0")String archivalNumber){
		Map<String,Integer>map=new HashMap<>();
		if("0".equals(archivalNumber)){
			return  ResultVO.error(0, "用户档案号为空");
		}else{
			map=appMyPonitService.findIntegralByTime(day,archivalNumber);
			return ResultVO.success(map);
		}
	}

	/**
	 * 积分与经验值规则
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRegulation")
	@ResponseBody
	public ResultVO findRegulation(HttpServletRequest request){
		    Map<String,Object>map=appMyPonitService.findRegulation();
			return ResultVO.success(map);

	}

}
