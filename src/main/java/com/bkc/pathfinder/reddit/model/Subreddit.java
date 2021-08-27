package com.bkc.pathfinder.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.bkc.pathfinder.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotBlank(message = "Community name is required")
    private String name;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @OneToMany
    private List<Post> posts;
    
    private Instant createdDate;
    
//    @ManyToOne(fetch = LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    private User user;
}
