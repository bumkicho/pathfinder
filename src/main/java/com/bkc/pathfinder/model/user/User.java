package com.bkc.pathfinder.model.user;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

/**
 * 
 * @author bumki
 *
 */

@Data
@Entity
@Table(name="bkc_user") //user is reserved in postgresql
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(name="user_name", unique=true, nullable=false, length=20 )
	private String userName;
	
	@Column(name="user_password", nullable=false, length=50)
	private String userPassword;
	
	@Column(name="user_full_name", nullable=false, length=150)
	private String userFullName;
	
	@Column(name="created_dt", nullable=false)
	private LocalDateTime createdDt;
	/*
	 * @Enumerated(EnumType.STRING)
	 * 
	 * @Column(name="role", nullable=false) private Role role;
	 */
}