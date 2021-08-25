package com.bkc.pathfinder.model.activity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name="activity_type")
public class ActivityType {
	
	@Id
	@GeneratedValue(generator="simple-generator")
	@GenericGenerator(name="simple-generator", parameters=@Parameter(name="prefix", value="activityType"), strategy="com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name="activity_type_id", unique=true, nullable=false, length=20)
	private String activityTypeId;

	@Column(name="type_code", nullable=false, length=20)
	private String typeCode;
	
	@Column(name="description", nullable=false, length=100)
	private String description;

}
