package com.mess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String email;
private String name;
private  String body;

@ManyToOne
@JoinColumn(name = "mess_post_id")
private MessPost messPost;
}
