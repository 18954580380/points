package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.PointsExpenseStrategyGoodsRelation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StrategyGoodsMapper extends Mapper<PointsExpenseStrategyGoodsRelation> {
    void deleteByStrategyId(@Param("id") Integer id);
}
