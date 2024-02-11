package com.mess.service.impl;

import com.mess.entity.MessPost;
import com.mess.exception.ResourceNotFound;
import com.mess.payload.MessDto;
import com.mess.repository.MessRepository;
import com.mess.service.MessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessServiceImpl implements MessService {
    private MessRepository messRepo;

    public MessServiceImpl(MessRepository messRepo) {
        this.messRepo = messRepo;
    }

    @Override
    public void createMessPost(MessDto messDto) {
        MessPost messPost = new MessPost();
        messPost.setName(messDto.getName());
        messPost.setEmail(messDto.getEmail());
        messRepo.save(messPost);

    }

    //    @Override
//    public void deleteMessPost(long id) {
//        messRepo.deleteById(id);
//    }
    @Override
    public void deleteMessPost(long id) {
        Optional<MessPost> byId = messRepo.findById(id);
        if (byId.isPresent()) {
            messRepo.deleteById(id);
        } else {
            throw new ResourceNotFound("Post not Found" + id);
        }
    }

//    @Override
//    public List<MessDto> getAllMessPost(int pageNo, int pageSize, String sortBy, String sortDir) {
//        List<MessPost> all = messRepo.findAll();
//        List<MessDto> collect = all.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
//
//        return collect;
//    }
@Override
public List<MessDto> getAllMessPost(int pageNo, int pageSize, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize,sort);

    Page<MessPost> all = messRepo.findAll(pageable);
    List<MessPost> content = all.getContent();
    List<MessDto> collect = content.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

    return collect;
}

    @Override
    public MessDto UpdateMessPost(long id, MessDto messDto) {
        MessPost messPost = messRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Id not Found" + id)
        );
        messPost.setName(messDto.getName());
        messPost.setEmail(messDto.getEmail());
        MessPost savedMessPost = messRepo.save(messPost);
        MessDto dto = mapToDto(savedMessPost);
        return dto;
    }

    MessDto mapToDto(MessPost messPost) {
        MessDto messDto=new MessDto();
        messDto.setId(messPost.getId());
        messDto.setName(messPost.getName());
        messDto.setEmail(messPost.getEmail());
        return messDto;
    }
}
