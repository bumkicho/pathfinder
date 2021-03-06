package com.bkc.pathfinder.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.bkc.pathfinder.model.user.User;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotEmpty
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "postId")
    Post post;
    
    private Instant createdDate;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}