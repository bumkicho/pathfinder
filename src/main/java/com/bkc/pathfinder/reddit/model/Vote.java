package com.bkc.pathfinder.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.bkc.pathfinder.model.user.User;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    
    private VoteType voteType;
    
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId")
    Post post;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    User user;
}