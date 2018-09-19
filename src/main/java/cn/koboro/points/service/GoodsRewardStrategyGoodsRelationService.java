package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import cn.koboro.points.pojo.entity.GoodsRewardStrategyGoodsRelation;

import java.util.List;

public interface GoodsRewardStrategyGoodsRelationService {
    List<GoodsRewardStrategyGoodsRelation> findGoodsRewardStrategyGoodsRelation(Integer id);

    List<GoodsRewardStrategyGoodsRelation> selectAllGoodsRewardStrategyGoodsRelation();
}
