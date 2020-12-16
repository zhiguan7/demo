package com.example.demo.controller;

import com.example.demo.mapper.HumanMapper;
import com.example.demo.pojo.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/human")
public class HumanCotroller {

    @Autowired
    public HumanMapper humanMapper;

    @GetMapping("/queryAll")
    public List<Human> queryAll(){
        return humanMapper.queryAll();
    }

}
