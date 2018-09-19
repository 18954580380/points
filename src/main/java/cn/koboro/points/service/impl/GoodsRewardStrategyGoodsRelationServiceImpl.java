package cn.koboro.points.service.impl;

import cn.koboro.points.pojo.entity.GoodsRewardStrategy;
import cn.koboro.points.pojo.entity.GoodsRewardStrategyGoodsRelation;
import cn.koboro.points.repository.GoodsRewardStrategyGoodsRelationMapper;
import cn.koboro.points.service.GoodsRewardStrategyGoodsRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class GoodsRewardStrategyGoodsRelationServiceImpl implements GoodsRewardStrategyGoodsRelationService {
    @Resource
    private GoodsRewardStrategyGoodsRelationMapper goodsRewardStrategyGoodsRelationMapper;
    @Override
    public List<GoodsRewardStrategyGoodsRelation> findGoodsRewardStrategyGoodsRelation(Integer id) {
         return  goodsRewardStrategyGoodsRelationMapper.findGoodsRewardStrategyGoodsRelation(id);
    }

    /**
     * 查询所有正在被商品策略关联的商品
     * @return
     */
    @Override
    public List<GoodsRewardStrategyGoodsRelation> selectAllGoodsRewardStrategyGoodsRelation() {
        return goodsRewardStrategyGoodsRelationMapper.selectAllGoodsRewardStrategyGoodsRelation();
    }
}
