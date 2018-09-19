package cn.koboro.points.controller;

import cn.koboro.points.pojo.entity.PointsPoolPutRecord;
import cn.koboro.points.pojo.vo.ResultVO;
import cn.koboro.points.service.PointsPoolPutRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static cn.koboro.points.pojo.vo.ResultVO.success;

/**
 *积分池投放记录控制器
 */
@Controller
@RequestMapping("/pointsPoolPutRecord")
public class PointsPoolPutRecordController {
    @Resource
    private PointsPoolPutRecordService pointsPoolPutRecordService;
    /**
     * 投放记录列表
     * @return
     */
    @GetMapping("/listView")
    public String listView(){
        return "pointsPoolPutRecord/list";
    }
    /**
     *查询投放记录
     * @return
     */
    @ResponseBody
    @GetMapping("/selectAll")
    public ResultVO selectAll( ){
        List<PointsPoolPutRecord>pointsPoolPutRecords  = pointsPoolPutRecordService.selectAll();
        return success(new PageInfo<>(pointsPoolPutRecords));
    }

    /**
     * 投放积分
     * @param PointsPoolPutRecord
     * @return
     */
    @ResponseBody
    @PostMapping("/putPoints")
    public  ResultVO putPoints(PointsPoolPutRecord PointsPoolPutRecord){
      int count=pointsPoolPutRecordService.putPoint(PointsPoolPutRecord);
      if(count==1){
          return ResultVO.success();
      }
        return ResultVO.error(0,"投放积分失败");
    }

}
