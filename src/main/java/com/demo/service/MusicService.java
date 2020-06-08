package com.demo.service;

import com.demo.common.PageBean;
import com.demo.pojo.ListenList;
import com.demo.pojo.Music;

import java.util.List;

public interface MusicService {
    /**
     * 查找全部音乐
     * @return
     */
     List<Music> findAllMusic();

    /**
     * 添加音乐
     * @return
     */
     void addMusic(Music music);

    /**
     * 查找一首歌
     * @param musicId 歌曲id
     * @return
     */
     Music findOneMusic(String musicId);

    /**
     * 通过用户id查找用户歌单
     * @param userId
     * @return
     */
     List<ListenList> findMusicLists(String userId);

    /**
     * 分页查询歌曲
     * @param currentPage
     * @param pageSize
     * @return
     */
     PageBean<Music> findMusicByPage(int currentPage,int pageSize);

    /**
     *根据id删除单个音乐
     * @param musicId
     * @return
     */
     void deleteSingle(String musicId);
}
