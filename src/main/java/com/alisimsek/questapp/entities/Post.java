package com.alisimsek.questapp.entities;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name="post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER) //lazy tanımlasaydık -> db den post çektiğimizde user objelerini çekmez
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinince ona ait tüm postların silinmesini sağlar
    User user;


    String title;
    @Lob // @Lob, metin, resim veya başka büyük veri türlerini veritabanında saklamak için kullanılır.
    @Column(columnDefinition = "text" )
    String text;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;
}
