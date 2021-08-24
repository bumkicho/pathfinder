package com.bkc.pathfinder.model.contact;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
@Table(name="contact_address")
public class ContactAddress {
	
	@Id
	@GeneratedValue(generator = "simple-generator")
	@GenericGenerator(name = "simple-generator", parameters = @Parameter(name = "prefix", value = "address"), strategy = "com.bkc.pathfinder.common.SimpleIDGenerator")
	@Column(name = "address_id", unique = true, nullable = false, length = 20)
	private String addressId;
	
	@ManyToOne
	@JoinColumn(name="contact_id")
	Contact contact;
	
	@Column(name="address_line_1", length=100)
	private String addressLine1;
	
	@Column(name="address_line_2", length=100)
	private String addressLine2;
	
	@Column(name="city", nullable=false, length=100)
	private String city;
	
	@Column(name="state", nullable=false, length=20)
	private String state;
	
	@Column(name="postal_code_1", length=10)
	private String postalCode1;
	
	@Column(name="postal_code_2", length=10)
	private String postalCode2;

}
