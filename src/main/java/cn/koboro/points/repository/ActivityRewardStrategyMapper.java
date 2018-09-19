package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.ActivityRewardStrategy;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ActivityRewardStrategyMapper extends Mapper<ActivityRewardStrategy> {
    List<ActivityRewardStrategy> findActivityRewardStrategyByStatus(@Param("status") Integer status);
    int updateIsDeleteById(Integer id);
}
