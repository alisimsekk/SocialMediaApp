package com.alisimsek.questapp.services;

import com.alisimsek.questapp.entities.Comment;
import com.alisimsek.questapp.entities.Like;
import com.alisimsek.questapp.entities.Post;
import com.alisimsek.questapp.entities.User;
import com.alisimsek.questapp.repositories.LikeRepository;
import com.alisimsek.questapp.requests.LikeCreateRequest;
import com.alisimsek.questapp.requests.LikeUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }else
            return likeRepository.findAll();
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;

    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }


    /*
    public Like updateOneLikeById(Long likeId, LikeUpdateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isPresent()){
            Like likeToUpdate = like.get();
            likeToUpdate.setUser(user);
            likeToUpdate.setPost(post);
            return likeRepository.save(likeToUpdate);

        }else
            return null;
    }
     */

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}








