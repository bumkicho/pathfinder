package com.bkc.pathfinder.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.activity.Activity;
import com.bkc.pathfinder.model.activity.ActivityType;
import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactActivity;

@Service
public interface ActivityServiceInterface {

	List<ContactActivity> findAllByContact(Contact contact);

	List<ContactActivity> findAllByActivity(Activity activity);

	List<Activity> findActivitiesByType(ActivityType activityType);

	Activity saveActivity(Activity activity);

	ActivityType saveActivityType(ActivityType activityType);

	Optional<ActivityType> findActivityTypeByTypeCode(String activityTypeCode);

}
