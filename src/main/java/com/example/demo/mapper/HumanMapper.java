package com.example.demo.mapper;

import com.example.demo.pojo.Human;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//实际上就是DAO层

@Mapper
@Repository
public interface HumanMapper {

    List<Human> queryAll();

    Human queryByName(String name);

    void create(String name,String password);
}
