package com.bkc.pathfinder.repository.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.repository.activity.projection.ActivityProjection;

/**
 * 
 * @author bumki
 *
 */

public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	Optional<Activity> findBySubject(String subject);
	
	@Query("select " +
			"act.subject as subject, act.scheduled_dt as scheduledDt, act.start_dt as startDt, " +
			"act.end_dt as endDt, act.note as note " +
			"from activity act inner join activity_contact ac on act.activity_id=ac.activity_id " +
			"inner join contact c on ac.contact_id=c.contact_id " +
			"where c.contact_id =:contactId"	
			)
	Optional<List<ActivityProjection>> findAllByContactId(@Param("contactId") Long contactId);

}
