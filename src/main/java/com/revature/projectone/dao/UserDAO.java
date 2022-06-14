package com.revature.projectone.dao;

import com.revature.projectone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    @Query("select u from User u where email = ?1 AND userId = ?2")
    public List<User> findByEmailAndID(String email, int userId);


    //public void insertToUsers(String email, String password);


    @Query("select u from User u where email = ?1")
    public User findByEmail(String email);


    @Query("select u from User u where u.email = ?1 AND u.password =?2")
    public User findByEmailAndPassword(String email, String password);


}
