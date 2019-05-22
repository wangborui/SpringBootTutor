package com.example.demo.service;


import com.example.demo.modal.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){

        return courseRepository.findAllClasses();
    }

    public List<Course> searchByCourseName(String input){

        return courseRepository.findCourseByName(input);
    }

    public Course createCourse(Course course) {
        return courseRepository.createCourse(course);
    }

    public Optional<Course> updateCourse(Course course, Long id) {
        return courseRepository.updateCourse(course, id);
    }

    public Optional<Course> deleteCourse(Long id) {
        return courseRepository.deleteCourse(id);
    }
}
