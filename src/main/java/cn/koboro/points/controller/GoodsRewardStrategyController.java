package cn.koboro.points.controller;
import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import cn.koboro.points.pojo.entity.GoodsRewardStrategyGoodsRelation;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.GoodsRewardStrategyGoodsRelationService;
import cn.koboro.points.service.GoodsRewardStrategyService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static cn.koboro.points.pojo.vo.ResultVO.success;

/**
 * 商品奖励策略控制器
 */
@Controller
@RequestMapping("/goodsRewardStrategy")
public class GoodsRewardStrategyController {
    @Resource
    private GoodsRewardStrategyService goodsRewardStrategyService;
    @Resource
    private GoodsRewardStrategyGoodsRelationService  goodsRewardStrategyGoodsRelationService;
    /**
     * 商品奖励策略活动奖励策略列表View
     * @return
     */
    @GetMapping("/listView")
    public String listView(){
        return "goodsRewardStrategy/list";
    }

    /**
     * 商品奖励策略列表
     * @return
     */
    @ResponseBody
    @GetMapping("/selectAll")
    public ResultVO selectAll(){
        List<GoodsRewardStrategy> goodsRewardStrategies = goodsRewardStrategyService.selectAll();
        return success(new PageInfo<>(goodsRewardStrategies));
    }
    /**
     * 进入新建商品奖励页面
     * @return
     */
    @GetMapping("/addView")
    public String addView(){
        return "goodsRewardStrategy/add";
    }
    /**
     * 商品列表
     * @param modelMap
     * @return
     */
    @GetMapping("/goodsView")
    public  String goodsView(ModelMap modelMap,
                                @RequestParam("goodsIds")  String goodsIds){
        modelMap.put("goodsIds",goodsIds);
        modelMap.put("goodsData",getGoosForService());
        if(Validator.isEmpty(goodsIds)){
            modelMap.put("goodsIds",new ArrayList<Integer>());
        }else{
            modelMap.put("goodsIds",Arrays.asList(goodsIds.split(",")));
        }
        System.out.println(goodsIds);
        return "goodsRewardStrategy/goodsList";
    }

    /**
     *
     * @param goodsRewardStrategy
     * @param goodsIds
     * @param names
     * @param topcatids
     * @param topcatnames
     * @return
     */
    @PostMapping("/save")
    public String  save(GoodsRewardStrategy goodsRewardStrategy,
                        @RequestParam(defaultValue = "")String goodsIds,
                        @RequestParam(defaultValue = "")String names,
                        @RequestParam(defaultValue = "")String topcatids,
                        @RequestParam(defaultValue = "")String topcatnames){
        List<Map<String,Object>>goodsDate=new ArrayList<>();
        //全部商品适用
        if(goodsRewardStrategy.getGoodsRewardScope()==1){
            goodsDate=getGoosForService();
        }
        goodsRewardStrategyService.saveGoodsRewardStrategy(goodsRewardStrategy,goodsDate,goodsIds,names,topcatids,topcatnames);
        return "redirect:/goodsRewardStrategy/listView";
    }
    /**
     * 获取商品从接口
     * @return
     */
    public  List<Map<String,Object>> getGoosForService(){
        List<Map<String,Object>>distinctGoodsData= new ArrayList<Map<String,Object>>();
        String url= PropertiesUtil.getServiceUrl("goodsInterfaceUrl");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> data = Connection.newGET().send(url, headers, Map.class);
        if("0000".equals(data.get("res_code"))){
            List<Map<String,Object>> goodsData= (ArrayList<Map<String,Object>>) data.get("result");
             //查询正在关联商品策略商品
             List<GoodsRewardStrategyGoodsRelation>goodsRewardStrategyGoodsRelations=goodsRewardStrategyGoodsRelationService.selectAllGoodsRewardStrategyGoodsRelation();
             //商品列表中移除已经关联商品奖励策略的商品
            for (Map<String,Object>map:goodsData) {
                Integer goodId=Integer.valueOf(map.get("goods_id").toString());
                boolean flag=true;
                for (GoodsRewardStrategyGoodsRelation goodsRewardStrategyGoodsRelation:goodsRewardStrategyGoodsRelations) {
                      Integer existGoodId=goodsRewardStrategyGoodsRelation.getGoodsId();
                      if(goodId.equals(existGoodId)){
                          flag=false;
                      }
                }
                if(flag){
                    //未存在其他商品奖励策略中
                    distinctGoodsData.add(map);
                }
            }
        }
        return distinctGoodsData;
    }

    /**
     * 删除商品奖励策略 暂使用逻辑删除 is_delete=1
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteById")
    public ResultVO deleteById(@RequestParam("id") Integer id) {
        int i = goodsRewardStrategyService.deleteById(id);
        if (i == 1) {
            return ResultVO.success();
        }
        return ResultVO.error(0,"删除失败");
    }
    /**
     * 进入商品奖励修改页面
     * @param modelMap
     * @param id
     * @return
     */
    @GetMapping("/updateView")
    public  String updateView(ModelMap modelMap,@RequestParam("id") Integer id){
        GoodsRewardStrategy goodsRewardStrategy=goodsRewardStrategyService.findGoodsRewardStrategyById(id);
        String ids = "";//商品id
        String names="";//商品名称
        String topcatids="";//分类Id
        String topcatnames="";//分类名称
        //查询商品奖励策略关联的商品信息
        List<GoodsRewardStrategyGoodsRelation>goodsRewardStrategyGoodsRelations=goodsRewardStrategyGoodsRelationService.findGoodsRewardStrategyGoodsRelation(id);
        //拼接字符串
        for (GoodsRewardStrategyGoodsRelation g:goodsRewardStrategyGoodsRelations) {
            ids+=g.getGoodsId()+",";
            names+=g.getGoodsName()+",";
            topcatids+=g.getGoodsCategoryId()+",";
            topcatnames+=g.getGoodsCategory()+",";
        }
        //截取字符串最后一位
        if(!Validator.isEmpty(ids)){
            ids = ids.substring(0,ids.length() - 1);
            names = names.substring(0,names.length() - 1);
            topcatids = topcatids.substring(0,topcatids.length() - 1);
            topcatnames = topcatnames.substring(0,topcatnames.length() - 1);
        }
        //绑定值
        modelMap.put("goodsRewardStrategy",goodsRewardStrategy);
        modelMap.put("goodsRewardStrategyGoodsRelations",goodsRewardStrategyGoodsRelations);
        modelMap.put("ids",ids);
        modelMap.put("names",names);
        modelMap.put("topcatids",topcatids);
        modelMap.put("topcatnames",topcatnames);
        return "goodsRewardStrategy/update";
    }
    /**
     *
     * @param goodsRewardStrategy
     * @param goodsIds
     * @param names
     * @param topcatids
     * @param topcatnames
     * @return
     */
    @PostMapping("/update")
    public String  update(GoodsRewardStrategy goodsRewardStrategy,
                        @RequestParam(defaultValue = "")String goodsIds,
                        @RequestParam(defaultValue = "")String names,
                        @RequestParam(defaultValue = "")String topcatids,
                        @RequestParam(defaultValue = "")String topcatnames){
        List<Map<String,Object>>goodsDate=new ArrayList<>();
        //全部商品适用
        if(goodsRewardStrategy.getGoodsRewardScope()==1){
            goodsDate=getGoosForService();
        }
        goodsRewardStrategyService.updateGoodsRewardStrategy(goodsRewardStrategy,goodsDate,goodsIds,names,topcatids,topcatnames);
        return "redirect:/goodsRewardStrategy/listView";
    }
}
