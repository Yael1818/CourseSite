package com.example.demo.service;

import com.example.demo.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
