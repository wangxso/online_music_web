package com.demo.service;

import com.demo.common.Result;
import com.demo.pojo.Comment;

import java.util.List;

public interface CommentService {
     List<Comment> findOneMusicComment(String mid);

     Result addComment(Comment comment);
}
