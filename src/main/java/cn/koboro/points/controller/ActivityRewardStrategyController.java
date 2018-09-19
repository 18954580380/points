package cn.koboro.points.controller;

import cn.koboro.points.pojo.entity.ActivityRewardStrategy;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.ActivityRewardStrategyActivityRelationService;
import cn.koboro.points.service.ActivityRewardStrategyService;
import cn.koboro.points.utils.Connection;
import cn.koboro.points.utils.PropertiesUtil;
import cn.koboro.points.utils.Validator;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static cn.koboro.points.pojo.vo.ResultVO.success;

/**
 * 活动奖励策略控制器
 */
@Controller
@RequestMapping("/activityRewardStrategy")
public class ActivityRewardStrategyController {
    @Resource
    private ActivityRewardStrategyService activityRewardStrategyService;
    @Resource
    private ActivityRewardStrategyActivityRelationService activityRewardStrategyActivityRelationService;
    /**
     * 奖励策略活动奖励策略列表View
     * @return
     */
    @GetMapping("/listView")
     public String listView(){
     return "activityRewardStrategy/list";
    }
    /**
     *查询活动奖励列表
     * @param request
     * @param modelMap
     * @param status 失效状态
     * @return
     */
    @ResponseBody
    @GetMapping("/selectAll")
    public ResultVO selectAll(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "status",defaultValue = "")Integer status){
        List<ActivityRewardStrategy> activityRewardStrategies = activityRewardStrategyService.selectAll(status);
        return success(new PageInfo<>(activityRewardStrategies));
    }

    /**
     * 进入新建活动奖励页面
     * @return
     */
    @GetMapping("/addView")
    public String addView(){
        return "activityRewardStrategy/add";
    }

    /**
     * 活动列表
     * @param modelMap
     * @return
     */
    @GetMapping("/activityView")
    public  String activityView(ModelMap modelMap,
                            @RequestParam("activityIds")  String activityIds,
                              @RequestParam("rewardGrantStartTime") String rewardGrantStartTime,
                               @RequestParam("rewardGrantEndTime")   String rewardGrantEndTime){
        modelMap.put("activityIds",activityIds);
        String url= PropertiesUtil.getServiceUrl("activityInterfaceUrl");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> data = Connection.newGET()
                .setParam("startTime", rewardGrantStartTime.replaceAll("-","/"))
                .setParam("endTime", rewardGrantEndTime.replaceAll("-","/"))
                .send(url, headers, Map.class);
        if(new Integer(0).equals(data.get("code"))){
            ArrayList<Map<String,Object>> activityData = (ArrayList<Map<String,Object>>) data.get("data");
            modelMap.put("activityData",activityData);
        }else{
            modelMap.put("activityData",new ArrayList<Map<String,Object>>());
        }
        if(Validator.isEmpty(activityIds)){
            modelMap.put("activityIds",new ArrayList<Integer>());
        }else{
            modelMap.put("activityIds",Arrays.asList(activityIds.split(",")));
        }
        System.out.println(activityIds);
        return "activityRewardStrategy/activityList";
    }

    /**
     * 保存活动奖励
     * @param activityRewardStrategy
     * @param activityIds
     * @param activityName
     * @return
     */
    @PostMapping("/save")
    public String  save(ActivityRewardStrategy activityRewardStrategy,
                          @RequestParam(defaultValue = "")String activityIds,
                          @RequestParam(defaultValue = "")String activityName){
        int id= activityRewardStrategyService.saveActivityRewardStrategy(activityRewardStrategy,activityIds,activityName);
        return "redirect:/activityRewardStrategy/listView";
    }

    /**
     * 根据id删除活动奖励策略
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteById")
     public ResultVO deleteById(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
        int i = activityRewardStrategyService.deleteById(id, status);
        if (i == 1) {
          return ResultVO.success();
        }
        return ResultVO.error(0,"删除失败");
    }

    /**
     * 进入修改页面
     * @param modelMap
     * @param id
     * @return
     */
    @GetMapping("/updateView")
    public  String updateView(ModelMap modelMap,@RequestParam("id") Integer id){
        ActivityRewardStrategy activityRewardStrategy=activityRewardStrategyService.findActivityRewardStrategyById(id);
        String activityIds="";
        String names="";
        //查询活动奖励策略关联的活动信息
        if(activityRewardStrategy.getRewardWay()==2){
           List<Map<String,Object>>activityInfo=activityRewardStrategyActivityRelationService.findActivityRewardStrategyActivityRelation(id);
           if(!Validator.isEmpty(activityInfo)){
               activityIds=activityInfo.get(0).get("activityIds").toString();
               names=activityInfo.get(0).get("names").toString();
           }
        }
        modelMap.put("activityRewardStrategy",activityRewardStrategy);
        modelMap.put("names",names);
        modelMap.put("activityIds",activityIds);
        return "activityRewardStrategy/update";
    }
    /**
     * 保存活动奖励
     * @param activityRewardStrategy
     * @param activityIds
     * @param activityName
     * @return
     */
    @PostMapping("/update")
    public String  update(ActivityRewardStrategy activityRewardStrategy,
                        @RequestParam(defaultValue = "")String activityIds,
                        @RequestParam(defaultValue = "")String activityName){
        int id= activityRewardStrategyService.UpdateActivityRewardStrategy(activityRewardStrategy,activityIds,activityName);
        return "redirect:/activityRewardStrategy/listView";
    }
}
