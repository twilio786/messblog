package com.mess.controller;

import com.mess.payload.CommentDto;
import com.mess.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentPostController {
    private CommentService commentService;

    public CommentPostController(CommentService commentService) {
        this.commentService = commentService;
    }

  @PostMapping
    public ResponseEntity<CommentDto>createCommentPost(@RequestParam("messPostId") long messPostId ,@RequestBody CommentDto commentDto){
        CommentDto commentPost = commentService.createCommentPost(messPostId, commentDto);
        return new ResponseEntity<>(commentPost, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentPostId}")
    public ResponseEntity<String>deleteCommentPost(@PathVariable long commentPostId){
        commentService.deleteCommentPost(commentPostId);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }
    @GetMapping("/{messPostId}")
    public List<CommentDto>getAllCommentsByMessPostId(@PathVariable long messPostId){
        List<CommentDto> commentDtos = commentService.getAllCommentsByMessPostId(messPostId);
        return  commentDtos;
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>>getAllComments(){
        List<CommentDto> allComments = commentService.getAllComments();
return new ResponseEntity<>(allComments,HttpStatus.OK);
    }
}
