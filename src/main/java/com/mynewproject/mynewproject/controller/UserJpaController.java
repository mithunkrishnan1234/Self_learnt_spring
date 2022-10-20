package com.mynewproject.mynewproject.controller;

import com.mynewproject.mynewproject.exception.UserNotFoundException;
import com.mynewproject.mynewproject.models.Post;
import com.mynewproject.mynewproject.models.User;
import com.mynewproject.mynewproject.repositories.PostRepository;
import com.mynewproject.mynewproject.repositories.UserRepository;
import com.mynewproject.mynewproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
   private UserRepository userRepository;
   private PostRepository postRepository;

    public UserJpaController(UserRepository userRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository=postRepository;
    }

    @GetMapping("/all-users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveuser(@PathVariable int id){
        Optional<User> user=userRepository.findById(id);

        if(user==null)
            throw new UserNotFoundException("id "+id+" is not found in the register");

        EntityModel<User>   entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
  @GetMapping("/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id: "+id+" is not having an account.Kindly check the credentials.");
        return user.get().getPosts();

    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
        @PostMapping("/users/{id}/posts")
        public ResponseEntity<Object> createPostsForUser(@PathVariable int id,@Valid @RequestBody Post post){
            Optional<User> user=userRepository.findById(id);
            if(user.isEmpty())
                throw new UserNotFoundException("id: "+id+" is not having an account.Kindly check the credentials.");
            post.setUser(user.get());
            Post savedPost = postRepository.save(post);
            URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
            return  ResponseEntity.created(location).build();

        }


}
