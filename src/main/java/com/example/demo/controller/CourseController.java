package com.example.demo.controller;

import com.example.demo.modal.Course;
import com.example.demo.modal.dto.CourseDto;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// single function interface
@RestController
@RequestMapping
public class CourseController {
    @Autowired // IOC
            CourseService courseService; // Singleton

    @GetMapping(path = "/", produces = "application/json")
    public HttpEntity findAllCourses() {
        List<Course> allCourses = courseService.findAllCourses();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

//    @GetMapping(path = "/api/course/findAllCourses", produces = "application/json")
//    public HttpEntity<List<CourseDto>> findAllCourses(){
//        List<CourseDto> allCourses = courseService.findAllCourses();
//
//        return new ResponseEntity<>(allCourses, HttpStatus.OK);
//    }

    @GetMapping(path = "/look-up/{inputString}", produces = "application/json")
    public HttpEntity<Course> searchCourse(@PathVariable("inputString") String inputString) {

        List<Course> findedCourse = courseService.searchByCourseName(inputString);

        return new ResponseEntity(findedCourse, HttpStatus.OK);
    }

    @PostMapping(path = "/course", produces = "application/json")
    public HttpEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.OK);
    }

    @PutMapping(path = "/course/{id}", produces = "application/json")
    public HttpEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> updatedCourse = courseService.updateCourse(course, id);
        if (updatedCourse.isPresent()) {
            return new ResponseEntity<>(updatedCourse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Course.builder().build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/course/{id}", produces = "application/json")
    public HttpEntity<Course> updateCourse(@PathVariable Long id) {
        Optional<Course> deletedCourse = courseService.deleteCourse(id);
        if (deletedCourse.isPresent()) {
            return new ResponseEntity<>(deletedCourse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Course.builder().build(), HttpStatus.NOT_FOUND);
        }
    }
}
