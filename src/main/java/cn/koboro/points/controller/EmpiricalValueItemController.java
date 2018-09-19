package cn.koboro.points.controller;
import cn.koboro.points.pojo.entity.EmpiricalValueItem;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.EmpiricalValueItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 经验值项目控制器
 */
@Controller
@RequestMapping("/empiricalValueItem")
public class EmpiricalValueItemController {
    @Resource
    private EmpiricalValueItemService empiricalValueItemService;
    /**
     * 查询所有的经验值项目
     * @param request
     * @param modelMap
     * @return
     */
    @GetMapping("/selectAll")
    public String selectAll(HttpServletRequest request,ModelMap modelMap){
        List<EmpiricalValueItem> empiricalValueItems=empiricalValueItemService.selectAll();
        modelMap.put("empiricalValueItems",empiricalValueItems);
        return "empiricalValueItem/list";
    }
    /**
     * 更新所有的经验值项目
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/updateEmpiricalValueItem")
    public ResultVO updatePointsItem(HttpServletRequest request,
                                     @RequestParam(value = "ids") Integer[] ids,
                                     @RequestParam(value = "rewardPeriods") Integer[] rewardPeriods,
                                     @RequestParam(value = "rewardNumbers") Integer[] rewardNumbers,
                                     @RequestParam(value = "empiricalValues")  Integer[] empiricalValues,
                                     @RequestParam(value = "itemDescriptions")  String[] itemDescriptions){
        System.out.println(ids.length+"//////////////////");
        Integer count=ids.length;
        if(rewardPeriods.length!=count || rewardNumbers.length!=count || empiricalValues.length!=count || itemDescriptions.length!=count)
            return ResultVO.error(0, "参数个数不对应,操作失败!");
        else{
            for (int i = 0; i < ids.length; i++) {
                //根据id查询实体
                EmpiricalValueItem empiricalValueItem=empiricalValueItemService.findEmpiricalValueItemById(ids[i]);
                empiricalValueItem.setRewardPeriod(rewardPeriods[i]);
                empiricalValueItem.setRewardNumber(rewardNumbers[i]);
                empiricalValueItem.setEmpiricalValue(empiricalValues[i]);
                empiricalValueItem.setItemDescription(itemDescriptions[i]);
                empiricalValueItem.setSetDate(new Date());
                //更新实体
               empiricalValueItemService.updateEmpiricalValueItem(empiricalValueItem);
            }
            return ResultVO.success();
        }
    }
}
