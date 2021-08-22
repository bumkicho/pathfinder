package com.bkc.pathfinder.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bkc.pathfinder.model.user.User;

/**
 * 
 * @author bumki
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserId(String userId);
	User findByUserName(String userName);
	
	@Query("select a from User a where userId=:userId and userPassword=:password")
	User findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
	
	/*
	 * NOTE: @Query uses jpql meaning it uses Class name and Property name
	 */
	@Modifying
	@Query("update User set userName=:userName where userId=:userId")
	void updateUserName(@Param("userId") String userId, @Param("userName") String userName);

}
