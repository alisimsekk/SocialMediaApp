package com.alisimsek.questapp.repositories;

import com.alisimsek.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
