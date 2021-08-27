package com.bkc.pathfinder.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.projection.SubredditProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Optional<Subreddit> findByName(String subredditName);
    
    @Query("select " +
            "max(a.name) as name, max(a.description) as description, max(a.createdDate) as createdDate,  " +
            "count(b.postId) as numberOfPosts " +
            "from Subreddit a left join Post b on a.id=b.id " +
            "where a.id=:subredditId group by a.id")
    SubredditProjection findWithPostCountById(@Param("subredditId") Long id);
    
    @Query("select " +
            "max(a.name) as name, max(a.description) as description, max(a.createdDate) as createdDate,  " +
            "count(b.postId) as numberOfPosts " +
            "from Subreddit a left join Post b on a.id=b.id group by a.id")
    List<SubredditProjection> findWithPostCount();

}