package com.demo.controller;

import com.demo.common.PageBean;
import com.demo.common.Result;
import com.demo.pojo.Music;
import com.demo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @RequestMapping("/findAllMusic")
    public List<Music> findAllMusic(){
        return musicService.findAllMusic();
    }

    @RequestMapping(value = "/addMusic",method = RequestMethod.POST)
    public Result addMusic(@RequestBody Music music){
        try {
            musicService.addMusic(music);
            return new Result("faild",500);
        }catch (Exception e){
            e.printStackTrace();
            return new Result("success",200);
        }
    }

    @RequestMapping("/findOneMusic")
    public Music findOneMusic(String mid){
        System.out.println(mid);
        return musicService.findOneMusic(mid);
    }

    @RequestMapping("/findMusicByPage")
    public PageBean<Music> findMusicByPage(int currentPage, int pageSize){
        return musicService.findMusicByPage(currentPage,pageSize);
    }
    @RequestMapping("/deleteSingle")
    public Result deleteSingle(String musicId){
        try {
            musicService.deleteSingle(musicId);
            return new Result("成功",200);
        }catch (Exception e){
            return new Result("失败",500);
        }
    }
}
