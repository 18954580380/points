package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import cn.koboro.points.pojo.entity.GoodsRewardStrategyGoodsRelation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsRewardStrategyGoodsRelationMapper extends Mapper<GoodsRewardStrategyGoodsRelation> {
    List<GoodsRewardStrategyGoodsRelation> findGoodsRewardStrategyGoodsRelation(Integer id);

    void deleteByGoodsRewardStrategyId(int id);

    List<GoodsRewardStrategyGoodsRelation> selectAllGoodsRewardStrategyGoodsRelation();
}
