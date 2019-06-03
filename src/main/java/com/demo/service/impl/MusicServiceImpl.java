package com.demo.service.impl;

import com.demo.common.PageBean;
import com.demo.mapper.ListenListMapper;
import com.demo.mapper.MusicMapper;
import com.demo.pojo.ListenList;
import com.demo.pojo.ListenListExample;
import com.demo.pojo.Music;
import com.demo.service.MusicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private ListenListMapper listenListMapper;

    /**
     * 查找所有的音乐
     * @return
     */
    @Override
    public List<Music> findAllMusic() {
        List<Music> musicList = musicMapper.selectByExample(null);
        return musicList;
    }

    /**
     * 添加音乐
     * @param music
     */
    @Override
    public void addMusic(Music music) {
        musicMapper.insert(music);
    }

    @Override
    public Music findOneMusic(String musicId) {
        Music music = musicMapper.selectByPrimaryKey(musicId);
        return music;
    }

    @Override
    public List<ListenList> findMusicLists(String userId) {
        ListenListExample example = new ListenListExample();
        ListenListExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<ListenList> listList = listenListMapper.selectByExample(example);
        return listList;
    }

    @Override
    public PageBean<Music> findMusicByPage(int currentPage, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数
        PageHelper.startPage(currentPage,pageSize);

        List<Music> musicList = musicMapper.selectByExample(null);
        int countNums = musicMapper.countByExample(null);
        PageBean<Music> pageData=new PageBean<>(currentPage,pageSize,countNums);
        pageData.setItems(musicList);
        return pageData;
    }

    @Override
    public void deleteSingle(String musicId) {
        musicMapper.deleteByPrimaryKey(musicId);
    }
}
