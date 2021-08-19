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

	Optional<List<ContactActivity>> findAllByContact(Contact contact);

	Optional<List<ContactActivity>> findAllByActivity(Activity activity);

	Optional<List<Activity>> findActivitiesByType(ActivityType activityType);

}
