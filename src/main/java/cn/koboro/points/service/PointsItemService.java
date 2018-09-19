package cn.koboro.points.service;

import java.util.List;

import cn.koboro.points.pojo.entity.PointsItem;

public interface PointsItemService {
	 List<PointsItem> selectAll();
	 PointsItem findPointsItemById(Integer id);
	 int updatePointsItem(PointsItem pointsItem);
}
