package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.PointsExpenseStrategy;

import java.util.List;

public interface ConsumeRewardPointsService {

    void savePoints(PointsExpenseStrategy pointsExpenseStrategy);

    List<PointsExpenseStrategy> selectPoin();

    void delete(Integer id);

    PointsExpenseStrategy selectByID(PointsExpenseStrategy newp);

    void updatePoints(PointsExpenseStrategy pointsExpenseStrategy);
}
