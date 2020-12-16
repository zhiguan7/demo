package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 查询全部
     */
    @GetMapping("/queryAll")
    public List<Map<String, Object>> userList(){
        String sql = "select * from test";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @GetMapping("/add")
    public int addUser(){
        String sql = "insert into test(name) value('小小')";
        return jdbcTemplate.update(sql);
    }

    @GetMapping("/update/{name}")
    public int updateUser(@PathVariable("name") String name){
        String sql = "update test set name=? where name='" + name +"'";
        Object[] objects = new Object[1];
        objects[0] = "晓晓";
        return jdbcTemplate.update(sql,objects);
    }

    @GetMapping("/delete/{id}")
    public int delUser(@PathVariable("id") int id){
        String sql = "delete from test where pk=?";
        return jdbcTemplate.update(sql,id);
    }
}
