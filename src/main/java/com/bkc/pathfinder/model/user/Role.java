package com.bkc.pathfinder.model.user;

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
@Table(name="app_role")
public class Role {
	
	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "role"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "role_id", unique = true, nullable = false, length = 20)
	private String roleId;
	
	@Column(name="role_name", unique=true, nullable=false, length=20 )
	private String roleName;
	
	@Column(name="description", nullable=false, length=200)
	private String description;

	/*
	 * @ManyToMany(mappedBy="roles") 
	 * private Set<User> users;
	 */
	
	@OneToMany(mappedBy="role")
	private Set<UserRole> userRoles;
}
