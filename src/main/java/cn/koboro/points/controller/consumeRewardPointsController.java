package cn.koboro.points.controller;

import cn.koboro.points.pojo.entity.PointsExpenseStrategy;
import cn.koboro.points.service.ConsumeRewardPointsService;
import cn.koboro.points.service.StrategyGoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumeRewardPoints")
public class consumeRewardPointsController {
    @Resource
    private ConsumeRewardPointsService consumeRewardPointsService;
    @Resource
    private StrategyGoodsService strategyGoodsService;

    @GetMapping("/list")
    public String skipList(ModelMap modelMap) {
        List<PointsExpenseStrategy> p=consumeRewardPointsService.selectPoin();
        modelMap.put("p",p);
        return "consumeRewardPoints/list";
    }

    @GetMapping("/add")
    public String skipAdd(@Value("${koboroServer.http}") String s,ModelMap modelMap) {
        List<Map> map=strategyGoodsService.selectGoods(s);
        modelMap.put("goods",map);
        return "consumeRewardPoints/add";
    }
    @GetMapping("/delete")
    public String delete( @RequestParam(value = "id",required = false) Integer id) {
        strategyGoodsService.deleteByStrategyId(id);
        consumeRewardPointsService.delete(id);
        return "redirect:/consumeRewardPoints/list";
    }
    @GetMapping("/update")
    public String update( @Value("${koboroServer.http}") String s,@RequestParam(value = "id",required = false) Integer id,ModelMap modelMap) {
        PointsExpenseStrategy  newp =new PointsExpenseStrategy();
        newp.setId(id);
        newp=  consumeRewardPointsService.selectByID(newp);
        modelMap.put("p",newp);

        List<Map> map=strategyGoodsService.selectGoods(s);
        modelMap.put("goods",map);
        return "consumeRewardPoints/add";
    }

    @GetMapping("/save")
    public String save(PointsExpenseStrategy pointsExpenseStrategy,
                     @RequestParam(value = "goodsId",required = false) String[] goodsId,
                     @RequestParam(value = "goodsName",required = false) String[] goodsName,
                     @RequestParam(value = "goodsCategory",required = false) String[] goodsCategory){

       if(null != pointsExpenseStrategy.getId()){
           consumeRewardPointsService.updatePoints(pointsExpenseStrategy);
           if(pointsExpenseStrategy.getConvertScope()!=0){
               strategyGoodsService.updateGoods(pointsExpenseStrategy.getId(),goodsId,goodsName,goodsCategory);
           }
       }else{
           consumeRewardPointsService.savePoints(pointsExpenseStrategy);
           if(pointsExpenseStrategy.getConvertScope()!=0){
               strategyGoodsService.saveGoods(pointsExpenseStrategy.getId(),goodsId,goodsName,goodsCategory);
           }
       }



        return "redirect:/consumeRewardPoints/list";
    }

}
