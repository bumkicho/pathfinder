package com.bkc.pathfinder.model.contact;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bkc.pathfinder.model.activity.Activity;

import lombok.Data;

/**
 * 
 * @author bumki
 *
 */

@Data
@Entity
@Table(name="contact_activity")
public class ContactActivity {
	
	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "cntact"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "contact_activity_id", length = 20)
	private String contactActivityId;
	
	@ManyToOne
	@JoinColumn(name="contact_id")
	Contact contact;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	Activity activity;
	
	@Column(name = "created_dt", nullable = false)
	private LocalDateTime createdDt;

}
