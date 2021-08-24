package com.bkc.pathfinder.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.activity.ActivityType;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;
import com.bkc.pathfinder.projection.ActivityProjection;
import com.bkc.pathfinder.projection.ContactActivityProjection;
import com.bkc.pathfinder.repository.activity.ActivityRepository;
import com.bkc.pathfinder.repository.activity.ActivityTypeRepository;
import com.bkc.pathfinder.repository.contact.ContactActivityRepository;

@Service
public class ActivityService implements ActivityServiceInterface {
	
	@Autowired
	ContactActivityRepository contactActivityRepository;
	
	@Autowired
	ActivityTypeRepository activityTypeRepository;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Override
	public Optional<List<ContactActivity>> findAllByContact(Contact contact) {
		return contactActivityRepository.findAllByContact(contact);
	}
	
	@Override
	public Optional<List<ContactActivity>> findAllByActivity(Activity activity) {
		return contactActivityRepository.findAllByActivity(activity);
	}
	
	@Override
	public Optional<List<Activity>> findActivitiesByType(ActivityType activityType) {
		return activityRepository.findAllByActivityType(activityType);
		//return activityTypeRepository.findActivitiesByType(activityType);
	}

	@Override
	public ActivityType saveActivityType(ActivityType activityType) {
		return activityTypeRepository.save(activityType);
	}

	@Override
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}

}
