package com.bkc.pathfinder.repository.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.contact.Contact;
import com.bkc.pathfinder.model.contact.ContactAddress;
import com.bkc.pathfinder.projection.ContactAddressProjection;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, String>{

	ContactAddress findByContact(Contact contact);
	
	@Query("select " +
            "c.firstName as firstName, c.lastName as lastName, c.emailAddress as emailAddress,  " +
            "ca.addressLine1 as addressLine1, ca.addressLine2 as addressLine2, ca.city as city, ca.state as state, ca.postalCode1 as postalCode1, ca.postalCode2 as postalCode2  " +
            "from Contact c inner join ContactAddress ca on c.contactId=ca.contact")
	List<ContactAddressProjection> findAllContactAddress();

}
