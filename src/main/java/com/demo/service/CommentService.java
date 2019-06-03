package com.demo.service;

import com.demo.common.Result;
import com.demo.pojo.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findOneMusicComment(String mid);

    public Result addComment(Comment comment);
}
