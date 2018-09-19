package cn.koboro.points.service;

import cn.koboro.points.pojo.entity.EmpiricalValueLevel;

import java.util.List;

public interface EmpiricalValueLevelService {
    List<EmpiricalValueLevel> selectAll();

    EmpiricalValueLevel findEmpiricalValueLevelById(Integer id);

    void updateEmpiricalValueLevel(EmpiricalValueLevel empiricalValueLevel);
}
