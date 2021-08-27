package com.bkc.pathfinder.reddit.mapper;

import java.util.List;

//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;

import com.bkc.pathfinder.reddit.dto.SubredditDto;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;

/*
 * mapstruct has issue with eclipse and lombok
 * scratch this to use projection
 */
//@Mapper(componentModel = "spring")
@Deprecated
public interface SubredditMapper {
	
//	expression is basically calling mapPosts method with all posts returned by subreddit.getPosts
//	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
//    SubredditDto mapSubredditToDto(Subreddit subreddit);
//
//    default Integer mapPosts(List<Post> numberOfPosts) {
//        return numberOfPosts.size();
//    }
//
//    @InheritInverseConfiguration
//    @Mapping(target = "posts", ignore = true)
//    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);

}
