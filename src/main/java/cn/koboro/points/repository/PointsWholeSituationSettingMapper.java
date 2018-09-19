package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;
import tk.mybatis.mapper.common.Mapper;

public interface PointsWholeSituationSettingMapper extends Mapper<PointsWholeSituationSetting> {
    Integer findRatioPoints();

    void updateStatus();

    PointsWholeSituationSetting findPointsWholeSituationSetting();
}
