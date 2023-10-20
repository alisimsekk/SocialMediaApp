package com.alisimsek.questapp.services;

import com.alisimsek.questapp.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
