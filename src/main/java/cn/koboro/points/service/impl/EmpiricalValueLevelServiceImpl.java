package cn.koboro.points.service.impl;

import cn.koboro.points.pojo.entity.EmpiricalValueLevel;
import cn.koboro.points.repository.EmpiricalValueLevelMapper;
import cn.koboro.points.service.EmpiricalValueLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * 经验值等级设置等级实现类
 */
@Service
@Transactional
public class EmpiricalValueLevelServiceImpl implements EmpiricalValueLevelService {
    @Resource
    private EmpiricalValueLevelMapper empiricalValueLevelMapper;

    /**
     * 查询等级列表
     * @return
     */
    @Override
    @OrderBy
    public List<EmpiricalValueLevel> selectAll() {
        //通用mapper排序
        Example example=new Example(EmpiricalValueLevel.class);
        example.setOrderByClause("xp_min ASC,xp_max ASC");
        return empiricalValueLevelMapper.selectByExample(example);
    }

    /**
     * 根据id查询等级实体
     * @param id
     * @return
     */
    @Override
    public EmpiricalValueLevel findEmpiricalValueLevelById(Integer id) {
        return empiricalValueLevelMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新等级实体
     * @param empiricalValueLevel
     */
    @Override
    public void updateEmpiricalValueLevel(EmpiricalValueLevel empiricalValueLevel) {
         empiricalValueLevelMapper.updateByPrimaryKey(empiricalValueLevel);
    }
}
