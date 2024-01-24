package com.example.axios.controller;

import com.example.axios.model.Post;
import com.example.axios.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/axioss")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping
    public ResponseEntity findAll(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(postRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity save(@RequestBody Post post){
        return new ResponseEntity(postRepository.save(post), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity save(@RequestBody Post post,@PathVariable Long id){
        post.setId(id);
        return new ResponseEntity(postRepository.save(post), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        postRepository.deleteById(id);
        return new ResponseEntity("Delete Done",HttpStatus.OK);
    }
    @GetMapping("/newest")
    public ResponseEntity<List<Post>> getNewestPosts() {
        List<Post> newestPosts = postRepository.findAllByOrderByCreateAtDesc();
        return new ResponseEntity<>(newestPosts, HttpStatus.OK);
    }
    @GetMapping("/public")
    public ResponseEntity<List<Post>> getPublicPosts() {
        List<Post> publicPosts = postRepository.findByStatus("Public");
        return new ResponseEntity<>(publicPosts, HttpStatus.OK);
    }
    @GetMapping("/public-onlyme")
    public ResponseEntity<List<Post>> getPublicAndOnlyMePosts() {
        List<Post> publicAndOnlyMePosts = postRepository.findByStatusIn(Arrays.asList("Public","OnlyMe"));
        return new ResponseEntity<>(publicAndOnlyMePosts, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> searchResult = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }






}
