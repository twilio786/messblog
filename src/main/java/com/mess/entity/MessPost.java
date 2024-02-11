package com.mess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name= "messPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessPost {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "messPost",cascade = CascadeType.ALL,orphanRemoval = true)
    List<CommentPost> commentPost;
}
