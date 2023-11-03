package com.alisimsek.questapp.controllers;

import com.alisimsek.questapp.entities.Comment;
import com.alisimsek.questapp.requests.CommentCreateRequest;
import com.alisimsek.questapp.requests.CommentUpdateRequest;
import com.alisimsek.questapp.responses.CommentResponse;
import com.alisimsek.questapp.services.CommentService;
import javax.persistence.Lob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @PostMapping
    public Comment createOneComment (@RequestBody CommentCreateRequest request){
        return commentService.createOneComment(request);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request){
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping ("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }
}
