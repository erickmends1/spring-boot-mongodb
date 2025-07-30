package com.erickmendes.workshop_mongodb.resources;

import com.erickmendes.workshop_mongodb.domain.Post;
import com.erickmendes.workshop_mongodb.dto.PostDTO;
import com.erickmendes.workshop_mongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id){
        Post obj = service.findById(id);
        PostDTO objDTO = new PostDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        List<Post> list = service.findByTitle(text);
        List<PostDTO> listDTO = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}