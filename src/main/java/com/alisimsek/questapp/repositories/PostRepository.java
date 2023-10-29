package com.alisimsek.questapp.repositories;

import com.alisimsek.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Optional<Long> userId);

    @Query(value ="select id from post where user_id = :userId order by create_date desc limit 5 " , nativeQuery = true)
    List<Long> findTopByUserId (@Param("userId") Long userId);
}
