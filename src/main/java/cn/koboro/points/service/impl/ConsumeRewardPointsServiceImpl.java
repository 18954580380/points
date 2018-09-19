package cn.koboro.points.service.impl;

import cn.koboro.points.annotation.AutoPage;
import cn.koboro.points.pojo.entity.PointsExpenseStrategy;
import cn.koboro.points.repository.ConsumeRewardPointsMapper;
import cn.koboro.points.service.ConsumeRewardPointsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ConsumeRewardPointsServiceImpl implements ConsumeRewardPointsService {
	@Resource
	private ConsumeRewardPointsMapper consumeRewardPointsMapper;

    @Override
    public void savePoints(PointsExpenseStrategy pointsExpenseStrategy) {
        pointsExpenseStrategy.setCreateTime(new Date());
        consumeRewardPointsMapper.insertSelective(pointsExpenseStrategy);
    }

    /**]
     * 查询所有策略  包括对应的关联商品表中商品的数量
     * @return
     */
    @AutoPage
    @Override
    public List<PointsExpenseStrategy> selectPoin() {
        return consumeRewardPointsMapper.selectAllAndGood();
    }

    @Override
    public void delete(Integer id) {
        consumeRewardPointsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PointsExpenseStrategy selectByID(PointsExpenseStrategy newp) {
        return consumeRewardPointsMapper.selectByPrimaryKey(newp);
    }

    @Override
    public void updatePoints(PointsExpenseStrategy pointsExpenseStrategy) {
        consumeRewardPointsMapper.updateByPrimaryKey(pointsExpenseStrategy);
    }


}
