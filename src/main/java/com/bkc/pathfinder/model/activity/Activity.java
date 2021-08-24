package com.bkc.pathfinder.model.activity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bkc.pathfinder.model.contact.ContactActivity;

import lombok.Data;

/**
 * 
 * @author bumki
 *
 */

@Data
@Entity
@Table(name="activity")
public class Activity {
	
	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "activity"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "activity_id", unique = true, nullable = false, length = 20)
	private String activityId;
	
	@Column(name = "subject", nullable = false, length = 100)
	private String subject;
	
	@Column(name = "scheduled_start_dt")
	private LocalDateTime scheduledStartDt;
	
	@Column(name = "scheduled_end_dt")
	private LocalDateTime scheduledEndDt;
	
	@Column(name = "notes", nullable = false, length = 1000)
	private String notes;
	
	//TODO: learn more about FetchType and CascadeType
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="activity_type_id", nullable=false)
	private ActivityType activityType;
	
//	@OneToMany(mappedBy = "activity")
//	private Set<ContactActivity> contactActivities;

}
