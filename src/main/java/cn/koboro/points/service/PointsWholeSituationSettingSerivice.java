package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.PointsWholeSituationSetting;

import java.util.List;
import java.util.Map;

public interface PointsWholeSituationSettingSerivice {
    List<PointsWholeSituationSetting> selectAll();

    List<Map<String,Object>> findDetails();

    void savePointsWholeSituationSetting(PointsWholeSituationSetting pointsWholeSituationSetting);
}
