package com.mess.controller;

import com.mess.payload.MessDto;
import com.mess.service.MessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mess")
public class MessController {
    private MessService messService;

    public MessController(MessService messService) {
        this.messService = messService;
    }

//    @PostMapping
//    public ResponseEntity<String> createMessPost(@RequestBody MessDto messDto) {
//        messService.createMessPost(messDto);
//        return new ResponseEntity<>("Post is created", HttpStatus.CREATED);
//
//    }
@PostMapping
public ResponseEntity<?> createMessPost(@Valid @RequestBody MessDto messDto, BindingResult bindingResult) {
if (bindingResult.hasErrors()){
    return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}
        messService.createMessPost(messDto);
    return new ResponseEntity<>("Post is created", HttpStatus.CREATED);

}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessPost(@PathVariable long id) {
        messService.deleteMessPost(id);
        return new ResponseEntity<>("Post is Deleted", HttpStatus.OK);
    }
//    @GetMapping
//    public ResponseEntity<List<MessDto>>getAllMessPost(){
//        List<MessDto> messPost = messService.getAllMessPost();
//        return new ResponseEntity<>(messPost,HttpStatus.OK);
//    }
    @PutMapping
    public ResponseEntity<MessDto>UpdateMessPost(
          @RequestParam("id")  long id,
            @RequestBody MessDto messDto){
        MessDto messDto1 = messService.UpdateMessPost(id, messDto);
        return new ResponseEntity<>(messDto1,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<MessDto>>getAllMessPost(
          @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
          @RequestParam(name = "pageSize",defaultValue = "3",required = false)int pageSize,
          @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
          @RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        List<MessDto> messPost = messService.getAllMessPost(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(messPost,HttpStatus.OK);
    }
}
