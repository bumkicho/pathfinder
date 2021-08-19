package com.bkc.pathfinder.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.user.UserRole;

/**
 * 
 * @author bumki
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}
