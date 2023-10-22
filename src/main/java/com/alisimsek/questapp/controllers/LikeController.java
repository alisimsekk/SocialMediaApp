package com.alisimsek.questapp.controllers;

import com.alisimsek.questapp.entities.Like;
import com.alisimsek.questapp.requests.LikeCreateRequest;
import com.alisimsek.questapp.requests.LikeUpdateRequest;
import com.alisimsek.questapp.responses.LikeResponse;
import com.alisimsek.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping()
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @PostMapping
    public Like createOneLike (@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    /*
    @PutMapping("/{likeId}")
    public Like updateOneLike (@PathVariable Long likeId, @RequestBody LikeUpdateRequest request){
        return likeService.updateOneLikeById(likeId,request);
    }
     */

    @DeleteMapping("/likeId")
    public void deleteOneLike (@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}














