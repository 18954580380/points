package cn.koboro.points.service;

import java.util.List;
import java.util.Map;

public interface StrategyGoodsService {

    void saveGoods(Integer id, String[] goodsId, String[] goodsName, String[] goodsCategory);

    List<Map> selectGoods(String s);

    void deleteByStrategyId(Integer id);

    void updateGoods(Integer id, String[] goodsId, String[] goodsName, String[] goodsCategory);
}
