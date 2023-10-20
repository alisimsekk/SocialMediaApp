package com.alisimsek.questapp.requests;

import lombok.Data;

@Data
public class LikeUpdateRequest {
    Long userId;
    Long postId;
}
