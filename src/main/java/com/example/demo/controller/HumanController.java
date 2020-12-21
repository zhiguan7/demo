package com.example.demo.controller;

import com.example.demo.mapper.HumanMapper;
import com.example.demo.pojo.Human;
import com.example.demo.service.HumanService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ApiOperation("人类的接口")
@RestController
@RequestMapping("/human")
public class HumanController {

    @Autowired
    private HumanService humanService;

    @GetMapping("/queryAll")
    public List<Human> queryAll(){
        return humanService.queryAll();
    }

    @GetMapping("/queryByName/{name}")
    public Human queryByName(@PathVariable("name")@ApiParam("需要一个参数name") String name){
        return humanService.queryByName(name);
    }

}
