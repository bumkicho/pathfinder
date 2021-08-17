package com.bkc.pathfinder.model.contact;

import java.time.LocalDateTime;

import javax.persistence.*;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contact_id")
	private Long id;
	
	@Column(name="first_name", nullable=false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=100)
	private String lastName;
	
	@Column(name="full_name", length=200)
	private String fullName;
	
	@Column(name="email_address", length=100)
	private String emailAddress;
	
	@Column(name="address_id")
	private Long addressId;
	
	@Column(name="created_dt")
	private LocalDateTime createdDt;
}
