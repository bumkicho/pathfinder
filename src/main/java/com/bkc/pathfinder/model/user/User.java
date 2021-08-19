package com.bkc.pathfinder.model.user;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

/**
 * 
 * @author bumki
 *
 */

@Data
@Entity
@Table(name = "app_user") // user is reserved in postgresql
public class User {

	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "user"), strategy = "com.bkc.pathfinder.model.common.SimpleIDGenerator")
	@Column(name = "user_id", unique = true, nullable = false, length = 20)
	private String userId;

	@Column(name = "user_name", nullable = false, length = 100)
	private String userName;

	@Column(name = "user_password", nullable = false, length = 100)
	private String userPassword;

	@Column(name = "created_dt", nullable = false)
	private LocalDateTime createdDt;

	/*
	 * 
	 * no reason for many to many with introduction of UserRole class
	 * 
	 * @ManyToMany
	 * 
	 * @JoinTable(name="app_user_role", joinColumns=@JoinColumn(name="user_id"),
	 * inverseJoinColumns=@JoinColumn(name="role_id") ) private Set<Role> roles;
	 */

	@OneToMany(mappedBy = "user")
	private Set<UserRole> userRoles;
}