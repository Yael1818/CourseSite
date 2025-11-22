package com.example.demo.service;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long>{
}
