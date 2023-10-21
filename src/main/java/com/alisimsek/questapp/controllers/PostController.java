package com.alisimsek.questapp.controllers;

import com.alisimsek.questapp.entities.Post;
import com.alisimsek.questapp.requests.PostCreateRequest;
import com.alisimsek.questapp.requests.PostUpdateRequest;
import com.alisimsek.questapp.responses.PostResponse;
import com.alisimsek.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // userId opsiyoneldir. Request'de id varsa user'ın postları gelir, id yoksa tüm postlar gelir
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){ //RequestParam yazarak userId yi opsiyonel tutarız. userId olmazsa tüm postları getirir
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId,updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }
}
