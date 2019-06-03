package com.demo.mapper;

import com.demo.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListenListMapper {
    int countByExample(ListenListExample example);

    int deleteByExample(ListenListExample example);

    int deleteByPrimaryKey(Integer listenListId);

    int insert(ListenList record);

    int insertSelective(ListenList record);

    List<ListenList> selectByExample(ListenListExample example);

    ListenList selectByPrimaryKey(Integer listenListId);

    int updateByExampleSelective(@Param("record") ListenList record, @Param("example") ListenListExample example);

    int updateByExample(@Param("record") ListenList record, @Param("example") ListenListExample example);

    int updateByPrimaryKeySelective(ListenList record);

    int updateByPrimaryKey(ListenList record);
}