package cn.koboro.points.service.impl;
import cn.koboro.points.pojo.entity.EmpiricalValueItem;
import cn.koboro.points.repository.EmpiricalValueItemMapper;
import cn.koboro.points.service.EmpiricalValueItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class EmpiricalValueItemServiceImpl implements EmpiricalValueItemService{
    @Resource
    private EmpiricalValueItemMapper empiricalValueItemMapper;
    /**
     * 查询所有的经验值项目
     * @return
     */
    @Override
    public List<EmpiricalValueItem> selectAll() {
        return empiricalValueItemMapper.selectAll();
    }

    /**
     *根据id查询经验值
     * @return
     */
    @Override
    public EmpiricalValueItem findEmpiricalValueItemById(Integer id) {
        return empiricalValueItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新实体根据外键ID
     * @param empiricalValueItem
     * @return
     */
    @Override
    public int updateEmpiricalValueItem(EmpiricalValueItem empiricalValueItem) {
        return empiricalValueItemMapper.updateByPrimaryKey(empiricalValueItem);
    }
}
