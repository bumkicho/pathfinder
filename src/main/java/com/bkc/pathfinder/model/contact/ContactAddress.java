package com.bkc.pathfinder.model.contact;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="contact_address")
public class ContactAddress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long id;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="postal_code_1")
	private String postalCode1;
	
	@Column(name="postal_code_2")
	private String postalCode2;

}
