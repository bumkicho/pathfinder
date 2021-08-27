package com.bkc.pathfinder.projection;

/**
 * 
 * @author bumki
 *
 */

/*
 * This projection is used with @Query in ContactAddressRepository
 */
public interface ContactAddressProjection {
	
	//TODO: Create a projection that include contact activity fields
	String getFirstName();
	String getLastName();
	String getEmailAddress();
	String getAddressLine1();
	String getAddressLine2();
	String getCity();
	String getState();
	String getPostalCode1();
	String getPostalCode2();	

}
