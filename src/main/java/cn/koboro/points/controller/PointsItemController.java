package cn.koboro.points.controller;
import cn.koboro.points.pojo.entity.PointsItem;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.PointsItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pointsItem")
public class PointsItemController {
  @Resource
  private PointsItemService pointsItemService;
  @GetMapping("/selectAll")
  public String selectAll(HttpServletRequest request,ModelMap modelMap){
	  List<PointsItem>pointsItems=pointsItemService.selectAll();
	  modelMap.put("pointsItems", pointsItems);
	  return "pointsItem/list";
  }

  /**
   * 更新所有的积分项目
   * @param request
   * @return
   */
  @ResponseBody
  @PostMapping("/updatePointsItem")
  public ResultVO updatePointsItem(HttpServletRequest request,
		  @RequestParam(value = "ids") Integer[] ids,
          @RequestParam(value = "rewardPeriods") Integer[] rewardPeriods,
          @RequestParam(value = "rewardNumbers") Integer[] rewardNumbers,
          @RequestParam(value = "pointsValues")  Integer[] pointsValues){
	      System.out.println(ids.length+"//////////////////");
		  Integer count=ids.length;
		  if(rewardPeriods.length!=count || rewardNumbers.length!=count || pointsValues.length!=count ){
				return ResultVO.error(0, "参数个数不对应,操作失败!"); 
		  }else{
			  for (int i = 0; i < ids.length; i++) {  
				  //根据id查询实体
				  PointsItem pointsItem=pointsItemService.findPointsItemById(ids[i]);
				  pointsItem.setRewardPeriod(rewardPeriods[i]);
				  pointsItem.setRewardNumber(rewardNumbers[i]);
				  pointsItem.setPointsValue(pointsValues[i]);
				  pointsItem.setSetDate(new Date());
				  //更新实体
				  int row = pointsItemService.updatePointsItem(pointsItem);
				} 
			    return ResultVO.success();
		  }
        }
}