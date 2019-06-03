package com.demo.controller;


import com.demo.mapper.MusicMapper;
import com.demo.pojo.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/boot")
public class TestController {
    @Autowired
    private MusicMapper musicMapper;
    @RequestMapping("/music")
    public Music findMusic(){
        Music music = new Music();
        music.setAuthor("ws");
        music = musicMapper.selectByPrimaryKey("100000");
        return music;
    }
    @RequestMapping("/insert")
    public int insertMusic(){
        Music music = new Music();
        music.setAuthor("wxs");
        music.setPublisher("wxs");
        music.setName("hahaha");
        music.setLyricSrc("sdasd");
        music.setSrc("dasdasd");
        music.setMusicId("10000001");
        return musicMapper.insert(music);
    }

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        String name = "jiangbei";
        model.addAttribute("name", name);
        return "hello";
    }
}
