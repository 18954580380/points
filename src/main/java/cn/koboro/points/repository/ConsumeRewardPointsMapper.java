package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.PointsExpenseStrategy;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConsumeRewardPointsMapper extends Mapper<PointsExpenseStrategy> {
    List<PointsExpenseStrategy> selectAllAndGood();
}
