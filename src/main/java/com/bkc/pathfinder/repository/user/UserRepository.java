package com.bkc.pathfinder.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkc.pathfinder.model.user.User;

/**
 * 
 * @author bumki
 *
 */

public interface UserRepository extends JpaRepository {
	
	Optional<User> findByUserId(String userId);
	
	@Modifying
	@Query("update bkc_user set user_name=:userName where user_id=:userId")
	void updateUserName(@Param("userId") String userId, @Param("userName") String userName);

}
