package cn.koboro.points.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.koboro.points.pojo.entity.PointsItem;
import cn.koboro.points.repository.PointsItemMapper;
import cn.koboro.points.service.PointsItemService;
@Service
@Transactional
public class PointsItemServiceImpl implements PointsItemService {
	@Resource
	private PointsItemMapper pointsItemMapper;
    /**
     * 查询所有的积分项目
     */
	@Override
	public List<PointsItem> selectAll() {
		return pointsItemMapper.selectAll();
	}
	/**
	 * 根据id查询实体
	 */
	@Override
	public PointsItem findPointsItemById(Integer id) {
		return pointsItemMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据外键更新实体
	 */
	@Override
	public int updatePointsItem(PointsItem pointsItem) {
		return pointsItemMapper.updateByPrimaryKey(pointsItem);
	}

}
