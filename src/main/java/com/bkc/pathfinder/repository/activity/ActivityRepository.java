package com.bkc.pathfinder.repository.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.activity.ActivityType;
import com.bkc.pathfinder.projection.ActivityProjection;

/**
 * 
 * @author bumki
 *
 */

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

	List<ActivityProjection> findBySubject(String subject);

	List<Activity> findAllByActivityType(ActivityType activityType);

}
