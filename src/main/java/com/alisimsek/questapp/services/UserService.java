package com.alisimsek.questapp.services;

import com.alisimsek.questapp.entities.Like;
import com.alisimsek.questapp.entities.User;
import com.alisimsek.questapp.repositories.CommentRepository;
import com.alisimsek.questapp.repositories.LikeRepository;
import com.alisimsek.questapp.repositories.PostRepository;
import com.alisimsek.questapp.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.alisimsek.questapp.entities.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository,
                       PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            if (newUser.getUserName() != null && newUser.getPassword() != null){
                foundUser.setUserName(newUser.getUserName());
                foundUser.setPassword(newUser.getPassword());
            }else {
                foundUser.setUserName(foundUser.getUserName());
                foundUser.setPassword(foundUser.getPassword());
            }

            foundUser.setAvatar(newUser.getAvatar());
            userRepository.save(foundUser);
            return  foundUser;
        }
        else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByUsername(String usuerName) {
        return userRepository.findByUserName(usuerName);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if (postIds.isEmpty()){
            return null;
        }else {
            List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
            List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
            List<Object> result = new ArrayList<>();
            result.addAll(comments);
            result.addAll(likes);
            return result;
        }
    }
}
