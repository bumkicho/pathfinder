package com.bkc.pathfinder.model.user;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author bumki
 *
 */

@Data
@Entity
@Table(name = "app_user") // user is reserved in postgresql. we need a different user table name.
public class User {

	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "user"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "user_id", unique = true, nullable = false, length = 20)
	private String userId;

	@Column(name = "user_name", nullable = false, length = 100)
	private String userName;

	@Column(name = "user_password", nullable = false, length = 100)
	private String userPassword;
	
	@Column(name = "user_email", nullable = false, length = 200)
	private String userEmail;

	@Column(name = "created_dt", nullable = false)
	private LocalDateTime createdDt;
	
	@Column(name = "is_active", nullable = false)
	private Boolean active;
	
	public boolean isActive() {
		return this.active;
	}

	// This is to hold JwtToken with User instance.
	// Transient will not be added to DB
	@Transient
	private String token;
}