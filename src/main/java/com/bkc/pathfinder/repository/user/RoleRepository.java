package com.bkc.pathfinder.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.user.Role;

/**
 * 
 * @author bumki
 *
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
