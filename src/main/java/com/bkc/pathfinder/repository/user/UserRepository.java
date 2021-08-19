package com.bkc.pathfinder.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;

/**
 * 
 * @author bumki
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserId(String userId);
	Optional<User> findByUserName(String userName);
	
	/*
	 * NOTE: @Query uses jpql meaning it uses Class name and Property name
	 */
	@Modifying
	@Query("update User set userName=:userName where userId=:userId")
	void updateUserName(@Param("userId") String userId, @Param("userName") String userName);
	
	Optional<List<UserRole>> findRoleByUserId(@Param("userId") String userId);

}
