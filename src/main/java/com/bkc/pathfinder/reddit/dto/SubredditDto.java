package com.bkc.pathfinder.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author bumki
 *
 */

/*
 * Dto - better performance than projection. It needs mapper interface.
 * Projection - less code, easier to implement
 * 
 * Decided to go with projection
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
