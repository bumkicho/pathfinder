package com.bkc.pathfinder.model.user;

import java.time.LocalDateTime;

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
@Table(name = "app_user_role")
public class UserRole {
	/*
	 * below is composite key approach not sure if I like it
	 * 
	 * @SuppressWarnings("serial") class UserRoleKey implements Serializable {
	 * 
	 * @Column(name="user_id", nullable=false ) private Long userId;
	 * 
	 * @Column(name="role_id", nullable=false) private Long roleId; }
	 * 
	 * @EmbeddedId private UserRoleKey id;
	 */

	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "userrole"), strategy = "com.bkc.pathfinder.model.common.SimpleIDGenerator")
	@Column(name = "user_role_id", length = 20)
	private String userRoleId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	Role role;

	@Column(name = "created_dt", nullable = false)
	private LocalDateTime createdDt;

}
