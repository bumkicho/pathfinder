package com.bkc.pathfinder.repository.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.repository.activity.projection.ActivityProjection;

/**
 * 
 * @author bumki
 *
 */

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

	Optional<List<ActivityProjection>> findBySubject(String subject);

}
