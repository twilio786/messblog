package com.mess.service;

import com.mess.payload.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentService {
   public CommentDto createCommentPost(long messPostId,CommentDto commentDto);
   void deleteCommentPost(long commentPostId);
   List<CommentDto>getAllCommentsByMessPostId(long messPostId);
   List<CommentDto>getAllComments();
}
