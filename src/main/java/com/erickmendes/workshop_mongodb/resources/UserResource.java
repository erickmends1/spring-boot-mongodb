package com.erickmendes.workshop_mongodb.resources;

import com.erickmendes.workshop_mongodb.domain.Post;
import com.erickmendes.workshop_mongodb.domain.User;
import com.erickmendes.workshop_mongodb.dto.UserDTO;
import com.erickmendes.workshop_mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream()
                .map(x -> new UserDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
        User user = service.fromDTO(objDTO);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userDTO){
        User user = service.fromDTO(userDTO);
        service.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }
}