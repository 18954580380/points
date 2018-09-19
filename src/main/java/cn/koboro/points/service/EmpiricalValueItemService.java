package cn.koboro.points.service;
import cn.koboro.points.pojo.entity.EmpiricalValueItem;
import java.util.List;

public interface EmpiricalValueItemService {
    List<EmpiricalValueItem>  selectAll();
    EmpiricalValueItem  findEmpiricalValueItemById(Integer id);
    int updateEmpiricalValueItem(EmpiricalValueItem empiricalValueItem);
}
