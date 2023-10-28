package com.alisimsek.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //lazy tanımladık -> db den post çektiğimizde post objelerini çekmez
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //bir post silinince ona ait tüm commentlerin silinmesini sağlar
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY) //lazy tanımladık -> db den post çektiğimizde user objelerini çekmez
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinince ona ait tüm postların silinmesini sağlar
    @JsonIgnore
    User user;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}
