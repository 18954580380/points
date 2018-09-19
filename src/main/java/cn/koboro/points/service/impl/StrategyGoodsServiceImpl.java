package cn.koboro.points.service.impl;

import cn.koboro.points.pojo.entity.PointsExpenseStrategyGoodsRelation;
import cn.koboro.points.repository.StrategyGoodsMapper;
import cn.koboro.points.service.StrategyGoodsService;
import cn.koboro.points.utils.Connection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 姜涛
 */
@Service
@Transactional
public class StrategyGoodsServiceImpl implements StrategyGoodsService {
	@Resource
	private StrategyGoodsMapper strategyGoodsMapper;
	private static String GOODS_URL = "/api/goods.do";

	/**
	 * 储存策略关联商品 详见 insertGoods方法
	 */
	@Override
	public void saveGoods(Integer id, String[] goodsId, String[] goodsName, String[] goodsCategory) {
		insertGoods(id, goodsId, goodsName, goodsCategory);

	}

	/**
	 * 此方法为去除已设定策略的商品
	 * @param cas
	 * @return
	 */
	@Override
	public List<Map> selectGoods(String cas) {
		Map result= (Map) getAllGoods(cas).get("result");
		List<Map> goods = (List<Map>) result.get("result");
		List<PointsExpenseStrategyGoodsRelation> alreadyExistGoods=strategyGoodsMapper.selectAll();
		Iterator<Map> it = goods.iterator();
		while(it.hasNext()){
			Map x = it.next();
			alreadyExistGoods.forEach(g -> {
				if(x.get("goods_id").equals(g.getGoodsId())){
					it.remove();
				}
			});
		}
		return goods;
	}

	/**
	 * 此为删除策略之后对应删除关联的商品
	 * @param id 策略ID
	 */
	@Override
	public void deleteByStrategyId(Integer id) {
		strategyGoodsMapper.deleteByStrategyId(id);
	}

	/**
	 * 修改消费策略所关联的商品信息   先删除 然后新增
	 * @param id 策略ID
	 * @param goodsId 商品ID
	 * @param goodsName 商品名称
	 * @param goodsCategory 商品类型
	 */
	@Override
	public void updateGoods(Integer id, String[] goodsId, String[] goodsName, String[] goodsCategory) {
		strategyGoodsMapper.deleteByStrategyId(id);
		insertGoods(id, goodsId, goodsName, goodsCategory);
	}

	/**
	 * 插入消费策略关联的商品表
	 * @param id 策略ID
	 * @param goodsId 商品ID
	 * @param goodsName 商品名称
	 * @param goodsCategory 商品类型
	 */
	private void insertGoods(Integer id, String[] goodsId, String[] goodsName, String[] goodsCategory) {
		for (int i=0;i<goodsId.length;i++) {
			PointsExpenseStrategyGoodsRelation good=new PointsExpenseStrategyGoodsRelation();
			good.setGoodsId(Integer.valueOf(goodsId[i]));
			good.setGoodsName(goodsName[i]);
			good.setGoodsCategory(goodsCategory[i]);
			good.setPointsExpenseStrategyId(id);
			strategyGoodsMapper.insertSelective(good);
		}
	}

	/**
	 * 获取商城商品接口
	 * @param cas IP地址
	 * @return 商品信息
	 */
	public static Map getAllGoods( String cas) {
		String url = cas + GOODS_URL;
		Map map = Connection.newPOST()
				.send(url, HashMap.class);
		return map;
	}
}
