package com.bkc.pathfinder.model.contact;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

/**
 * 
 * @author bumki cho
 *
 */

@Data
@Entity
@Table(name="contact")
public class Contact {
	
	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "contact"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "contact_id", unique = true, nullable = false, length = 20)
	private String contactId;
	
	@Column(name="first_name", nullable=false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=100)
	private String lastName;
	
	@Column(name="full_name", length=200)
	private String fullName;
	
	@Column(name="email_address", length=100)
	private String emailAddress;
	
	@Column(name="created_dt")
	private LocalDateTime createdDt;
	
	@OneToMany(mappedBy = "contact")
	private Set<ContactActivity> contactActivities;
	
	@OneToMany(mappedBy = "contact")
	private Set<ContactAddress> contactAddress;
}
