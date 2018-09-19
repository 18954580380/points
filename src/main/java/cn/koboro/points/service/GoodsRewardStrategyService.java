package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.GoodsRewardStrategy;

import java.util.List;
import java.util.Map;

public interface GoodsRewardStrategyService {
    List<GoodsRewardStrategy> selectAll();

    int saveGoodsRewardStrategy(GoodsRewardStrategy goodsRewardStrategy,List<Map<String,Object>>goodsDate, String goodsIds, String names, String topcatids, String topcatnames);

    int deleteById(Integer id);

    GoodsRewardStrategy findGoodsRewardStrategyById(Integer id);

    void updateGoodsRewardStrategy(GoodsRewardStrategy goodsRewardStrategy, List<Map<String,Object>> goodsDate, String goodsIds, String names, String topcatids, String topcatnames);
}
