package com.bkc.pathfinder.repository.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.contact.ContactAddress;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, String>{

}
