package cn.koboro.points.controller;

import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.PointsWholeSituationSettingSerivice;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.koboro.points.pojo.vo.ResultVO.success;

/**
 * 全局设置控制器
 */
@Controller
@RequestMapping("/pointsWholeSituationSetting")
public class PointsWholeSituationSettingController {
    @Resource
    private PointsWholeSituationSettingSerivice pointsWholeSituationSettingSerivice;
    /**
     * 全局设置列表
     * @return
     */
    @GetMapping("/listView")
    public String listView(){
        return "pointsWholeSituationSetting/list";
    }
    /**
     *查询全局设置记录
     * @return
     */
    @ResponseBody
    @GetMapping("/selectAll")
    public ResultVO selectAll( ){
        List<PointsWholeSituationSetting> pointsWholeSituationSettings = pointsWholeSituationSettingSerivice.selectAll();
        return success(new PageInfo<>(pointsWholeSituationSettings));
    }
    /**
     * 进入新建积分策略
     * @return
     */
    @GetMapping("/addView")
    public String addView(ModelMap modelMap){
         List<Map<String,Object>>listData=pointsWholeSituationSettingSerivice.findDetails();
         modelMap.put("listData",listData);
         return "pointsWholeSituationSetting/add";
    }

    /**
     * 保存策略
     * @param pointsWholeSituationSetting
     * @return
     */
    @PostMapping("/save")
    public String  save(PointsWholeSituationSetting pointsWholeSituationSetting){
        pointsWholeSituationSetting.setCreateTime(new Date());
        pointsWholeSituationSettingSerivice.savePointsWholeSituationSetting(pointsWholeSituationSetting);
        return "redirect:/pointsWholeSituationSetting/listView";
    }
}
