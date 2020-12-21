package com.example.demo.service;

import com.example.demo.mapper.HumanMapper;
import com.example.demo.pojo.Human;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanService{

    @Autowired
    private HumanMapper humanMapper;

    public List<Human> queryAll() {
        return humanMapper.queryAll();
    }

    public Human queryByName(String name){
        return humanMapper.queryByName(name);
    }

    public void create(String name,String password){

        humanMapper.create(name,password);

    }
}
