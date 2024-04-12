package com.example.webframeworkhw1.repository;

import com.example.webframeworkhw1.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findAllByOrderByYear();
    List<Course> findAllByYearAndSemester(int year, int semester);

    boolean existsBySubjectName(String name);

}
