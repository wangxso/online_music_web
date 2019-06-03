package com.demo.mapper;

import com.demo.pojo.Administor;
import com.demo.pojo.AdministorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministorMapper {
    int countByExample(AdministorExample example);

    int deleteByExample(AdministorExample example);

    int deleteByPrimaryKey(String username);

    int insert(Administor record);

    int insertSelective(Administor record);

    List<Administor> selectByExample(AdministorExample example);

    Administor selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Administor record, @Param("example") AdministorExample example);

    int updateByExample(@Param("record") Administor record, @Param("example") AdministorExample example);

    int updateByPrimaryKeySelective(Administor record);

    int updateByPrimaryKey(Administor record);
}