package cn.koboro.points.service.impl;

import cn.koboro.points.annotation.AutoPage;
import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import cn.koboro.points.pojo.entity.GoodsRewardStrategyGoodsRelation;
import cn.koboro.points.repository.GoodsRewardStrategyGoodsRelationMapper;
import cn.koboro.points.repository.GoodsRewardStrategyMapper;
import cn.koboro.points.service.GoodsRewardStrategyService;
import cn.koboro.points.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品奖励策略实现
 */
@Service
@Transactional
public class GoodsRewardStrategyServiceImpl implements GoodsRewardStrategyService {
    @Resource
    private GoodsRewardStrategyMapper goodsRewardStrategyMapper;
    @Resource
    private GoodsRewardStrategyGoodsRelationMapper goodsRewardStrategyGoodsRelationMapper;

    /**
     * 查询商品奖励策略列表 is_delete为0的策略
     *
     * @return
     */
    @AutoPage
    @Override
    public List<GoodsRewardStrategy> selectAll() {
        return goodsRewardStrategyMapper.findGoodsRewardStrategy();
    }

    /**
     * 保存商品奖励策略
     *
     * @param goodsRewardStrategy
     * @param goodsIds
     * @param names
     * @param topcatids
     * @param topcatnames
     * @return
     */
    @Override
    public int saveGoodsRewardStrategy(GoodsRewardStrategy goodsRewardStrategy, List<Map<String, Object>> goodsDate, String goodsIds, String names, String topcatids, String topcatnames) {
        //保存主表
        goodsRewardStrategy.setCreateTime(new Date());
        goodsRewardStrategyMapper.insertSelective(goodsRewardStrategy);
        int id = goodsRewardStrategy.getId();
        //保存关联的商品信息
        saveGoods(goodsRewardStrategy, goodsDate, goodsIds, names, topcatids, topcatnames);
        return id;
    }

    /**
     * 删除商品奖励策略 暂使用逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return goodsRewardStrategyMapper.updateIsDeleteById(id);
    }

    /**
     * 根据id查询商品活动策略
     *
     * @param id
     * @return
     */
    @Override
    public GoodsRewardStrategy findGoodsRewardStrategyById(Integer id) {
        return goodsRewardStrategyMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改商品奖励策略
     *
     * @param goodsRewardStrategy
     * @param goodsDate
     * @param goodsIds
     * @param names
     * @param topcatids
     * @param topcatnames
     */
    @Override
    public void updateGoodsRewardStrategy(GoodsRewardStrategy goodsRewardStrategy, List<Map<String, Object>> goodsDate, String goodsIds, String names, String topcatids, String topcatnames) {
        //修改主表
        goodsRewardStrategy.setUpdateTime(new Date());
        goodsRewardStrategyMapper.updateByPrimaryKeySelective(goodsRewardStrategy);
        int id = goodsRewardStrategy.getId();
        //删除商品奖励策略关联的商品
        goodsRewardStrategyGoodsRelationMapper.deleteByGoodsRewardStrategyId(id);
        //保存关联的商品信息
        saveGoods(goodsRewardStrategy, goodsDate, goodsIds, names, topcatids, topcatnames);
    }

    /**
     * 保存选中的商品
     *
     * @param goodsRewardStrategy
     * @param goodsDate
     * @param goodsIds
     * @param names
     * @param topcatids
     * @param topcatnames
     * @return
     */
    public void saveGoods(GoodsRewardStrategy goodsRewardStrategy, List<Map<String, Object>> goodsDate, String goodsIds, String names, String topcatids, String topcatnames) {
        //保存当前选中所有商品
        int id = goodsRewardStrategy.getId();
        if (goodsRewardStrategy.getGoodsRewardScope() == 1) {
            //保存商品
            for (Map<String, Object> map : goodsDate) {
                GoodsRewardStrategyGoodsRelation goodsRewardStrategyGoodsRelation = new GoodsRewardStrategyGoodsRelation();
                goodsRewardStrategyGoodsRelation.setGoodsId(Integer.valueOf(map.get("goods_id").toString()));
                goodsRewardStrategyGoodsRelation.setGoodsCategoryId(Integer.valueOf(map.get("topcatid").toString()));
                goodsRewardStrategyGoodsRelation.setGoodsName(map.get("name").toString());
                goodsRewardStrategyGoodsRelation.setGoodsCategory(map.get("topcatname").toString());
                goodsRewardStrategyGoodsRelation.setGoodsRewardStrategyId(id);
                //保存商品奖励商品关联表
                goodsRewardStrategyGoodsRelationMapper.insertSelective(goodsRewardStrategyGoodsRelation);
            }
        } else {
            //部分商品
            if (!Validator.isEmpty(goodsIds)) {
                //商品Id
                List<String> gids = Arrays.asList(goodsIds.split(","));
                //商品名称
                List<String> nms = Arrays.asList(names.split(","));
                //商品分类Id
                List<String> tids = Arrays.asList(topcatids.split(","));
                //商品分类名称
                List<String> tns = Arrays.asList(topcatnames.split(","));
                int i = 0;
                for (String gid : gids) {
                    GoodsRewardStrategyGoodsRelation goodsRewardStrategyGoodsRelation = new GoodsRewardStrategyGoodsRelation();
                    goodsRewardStrategyGoodsRelation.setGoodsId(Integer.valueOf(gid));
                    goodsRewardStrategyGoodsRelation.setGoodsCategoryId(Integer.valueOf(tids.get(i)));
                    goodsRewardStrategyGoodsRelation.setGoodsName(nms.get(i));
                    goodsRewardStrategyGoodsRelation.setGoodsCategory(tns.get(i));
                    goodsRewardStrategyGoodsRelation.setGoodsRewardStrategyId(id);
                    //保存商品奖励商品关联表
                    goodsRewardStrategyGoodsRelationMapper.insertSelective(goodsRewardStrategyGoodsRelation);
                    i++;
                }
            }
        }
    }
}
