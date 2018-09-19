package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsRewardStrategyMapper extends Mapper<GoodsRewardStrategy> {
    List<GoodsRewardStrategy> findGoodsRewardStrategy();
    int updateIsDeleteById(Integer id);
}
