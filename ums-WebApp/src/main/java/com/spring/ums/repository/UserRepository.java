package com.spring.ums.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.ums.entity.User;
public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmail(String email);
List<User> findByFirstNameContainingOrLastNameContainingOrSkillsContainingOrCourseOfStudyContaining(
        String firstName, String lastName, String skills, String courseOfStudy);
}