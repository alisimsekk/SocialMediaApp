package com.alisimsek.questapp.controllers;

import com.alisimsek.questapp.services.LikeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;


}
