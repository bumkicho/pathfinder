package com.bkc.pathfinder.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;

/**
 * 
 * @author bumki
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
	
	List<UserRole> findAllByUser(@Param("user") User user);

}
