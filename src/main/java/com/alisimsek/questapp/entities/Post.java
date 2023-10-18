package com.alisimsek.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data
public class Post {
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //lazy tanımladık -> db den post çektiğimizde user objelerini çekmez
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinince ona ait tüm postların silinmesini sağlar
    @JsonIgnore
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "text" )
    String text;
}
