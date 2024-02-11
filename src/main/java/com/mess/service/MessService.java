package com.mess.service;

import com.mess.payload.MessDto;

import java.util.List;

public interface MessService {
    void createMessPost(MessDto messDto);
    void deleteMessPost(long id);
    List<MessDto> getAllMessPost(int pageNo, int pageSize, String sortBy, String sortDir);
    MessDto UpdateMessPost(long id, MessDto messDto);
}
