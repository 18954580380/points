package cn.koboro.points.controller;

import cn.koboro.points.pojo.entity.EmpiricalValueLevel;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.EmpiricalValueLevelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 经验值等级设置控制器
 */
@Controller
@RequestMapping("/empiricalValueLevel")
public class EmpiricalValueLevelController {
    @Resource
    private EmpiricalValueLevelService empiricalValueLevelService;
    /**
     * 查询所有的等级
     * @param request
     * @param modelMap
     * @return
     */
    @GetMapping("/selectAll")
    public String selectALL(HttpServletRequest request,ModelMap modelMap){
        List<EmpiricalValueLevel>empiricalValueLevels=empiricalValueLevelService.selectAll();
        modelMap.put("empiricalValueLevels",empiricalValueLevels);
        return "empiricalValueLevel/list";
    }

    /**
     * 更新所有的经验值等级
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/updateEmpiricalValueLevel")
    public ResultVO updatePointsItem(HttpServletRequest request,
                                     @RequestParam(value = "ids") Integer[] ids,
                                     @RequestParam(value = "userLevels") String[] userLevels,
                                     @RequestParam(value = "userTitles") String[] userTitles,
                                     @RequestParam(value = "xpMins")  Integer[] xpMins,
                                     @RequestParam(value = "xpMaxs")  Integer[] xpMaxs){
        System.out.println(ids.length+"//////////////////");
        Integer count=ids.length;
        if(userLevels.length!=count || userTitles.length!=count || xpMins.length!=count || xpMaxs.length!=count)
            return ResultVO.error(0, "参数个数不对应,操作失败!");
        else{
            for (int i = 0; i < ids.length; i++) {
                //根据id查询实体
                EmpiricalValueLevel empiricalValueLevel=empiricalValueLevelService.findEmpiricalValueLevelById(ids[i]);
                empiricalValueLevel.setUserLevel(userLevels[i]);
                empiricalValueLevel.setUserTitle(userTitles[i]);
                empiricalValueLevel.setXpMin(xpMins[i]);
                empiricalValueLevel.setXpMax(xpMaxs[i]);
                empiricalValueLevel.setSetDate(new Date());
                //更新实体
                empiricalValueLevelService.updateEmpiricalValueLevel(empiricalValueLevel);
            }
            return ResultVO.success();
        }
    }
}
