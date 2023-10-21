package com.alisimsek.questapp.services;

import com.alisimsek.questapp.entities.Post;
import com.alisimsek.questapp.entities.User;
import com.alisimsek.questapp.repositories.PostRepository;
import com.alisimsek.questapp.requests.PostCreateRequest;
import com.alisimsek.questapp.requests.PostUpdateRequest;
import com.alisimsek.questapp.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // Id olmak zorunda değil. Resquest'de varsa ona göre dönüş yapar.
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;

        if (userId.isPresent()){
            list= postRepository.findByUserId(userId);
        }
        else{
            list= postRepository.findAll();
        }
        return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null){
            return null;
        }
        else{
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setText(newPostRequest.getText());
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            return postRepository.save(toSave);
        }

    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        else
            return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
