package com.demo.service.impl;

import com.demo.common.Result;
import com.demo.mapper.CommentMapper;
import com.demo.pojo.Comment;
import com.demo.pojo.CommentExample;
import com.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> findOneMusicComment(String mid) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andMusicIdEqualTo(mid);
        return commentMapper.selectByExample(example);
    }

    /**
     * 评论添加
     * @param comment
     * @return
     */
    @Override
    public Result addComment(Comment comment) {
        try {
            commentMapper.insert(comment);
        }catch (Exception e){
            e.printStackTrace();
            return new Result("失败",500);
        }
        return new Result("成功",200);
    }
}
