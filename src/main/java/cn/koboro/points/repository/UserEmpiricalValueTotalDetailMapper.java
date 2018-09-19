package cn.koboro.points.repository;

import cn.koboro.points.pojo.entity.UserEmpiricalValueTotalDetail;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface UserEmpiricalValueTotalDetailMapper extends Mapper<UserEmpiricalValueTotalDetail> {
    Map<String,Object> findUserLeve(String archivalNumber);
}
