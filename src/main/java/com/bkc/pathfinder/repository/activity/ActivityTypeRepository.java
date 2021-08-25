package com.bkc.pathfinder.repository.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.activity.ActivityType;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, String> {

	Optional<List<ActivityType>> findAllByDescription(String description);
	
	Optional<ActivityType> findByTypeCode(String typeCode);

}
