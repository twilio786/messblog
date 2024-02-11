package com.mess.service.impl;

import com.mess.entity.CommentPost;
import com.mess.entity.MessPost;
import com.mess.exception.ResourceNotFound;
import com.mess.payload.CommentDto;
import com.mess.repository.CommentRepository;
import com.mess.repository.MessRepository;
import com.mess.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private MessRepository messRepo;

    public CommentServiceImpl(CommentRepository commentRepo, MessRepository messRepo) {
        this.commentRepo = commentRepo;
        this.messRepo = messRepo;
    }

    @Override
    public CommentDto createCommentPost(long messPostId, CommentDto commentDto) {
        MessPost messPost = messRepo.findById(messPostId).orElseThrow(
                () -> new ResourceNotFound("Post id not found" + messPostId)
        );
        CommentPost commentPost= new CommentPost();
        commentPost.setName(commentDto.getName());
        commentPost.setEmail(commentDto.getEmail());
        commentPost.setBody(commentDto.getBody());

        commentPost.setMessPost(messPost);

        CommentPost saved = commentRepo.save(commentPost);

        CommentDto commentDto1=new CommentDto();
        commentDto1.setName(saved.getName());
        commentDto1.setEmail(saved.getEmail());
        commentDto1.setBody(saved.getBody());

        return commentDto1;
    }

//    @Override
//    public void deleteCommentPost(long messPostId) {
//        commentRepo.deleteById(messPostId);
//    }
@Override
public void deleteCommentPost(long commentPostId) {
    commentRepo.findById(commentPostId).orElseThrow(
            () -> new ResourceNotFound("comment not found" + commentPostId)
        );
    commentRepo.deleteById(commentPostId);
}

    @Override
    public List<CommentDto> getAllCommentsByMessPostId(long messPostId) {
        List<CommentPost> comments = commentRepo.findByMessPostId(messPostId);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<CommentPost> all = commentRepo.findAll();
        List<CommentDto> dto = all.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dto;
    }

    CommentDto mapToDto(CommentPost commentPost) {
        CommentDto dto= new CommentDto();
        dto.setId(commentPost.getId());
        dto.setName(commentPost.getName());
        dto.setEmail(commentPost.getEmail());
        dto.setBody(commentPost.getBody());
        return dto;

    }


}
