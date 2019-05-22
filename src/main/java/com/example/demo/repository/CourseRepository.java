package com.example.demo.repository;

import com.example.demo.modal.Course;
import com.example.demo.modal.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>();
    Long id = 0L;

    public CourseRepository() {
        Course javaOne = Course.builder()
                .id(id++)
                .className("Java I")
                .instructor(new Instructor("Steve", "Jobs", "Phd", "TownHall201"))
                .startDate(new Date("8/1/2018"))
                .endDate(new Date("12/24/2018"))
                .timeFrame("8am-10am")
                .build();

        courses.add(javaOne);
    }

    public Optional<Course> updateCourse(Course course, Long id) {
        if (id == null) {
            return Optional.empty();
        }

        Instructor instructor = course.getInstructor();
        for (Course existCourse : courses) {
            if (existCourse.getId().equals(id)) {
                existCourse.setClassName(course.getClassName());
                existCourse.setStartDate(course.getStartDate());
                existCourse.setEndDate(course.getEndDate());
                existCourse.setTimeFrame(course.getTimeFrame());
                existCourse.setInstructor(course.getInstructor());

                return Optional.of(existCourse);
            }
        }
        return Optional.empty();
    }

    public Optional<Course> deleteCourse(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                Course temp = courses.get(i);
                courses.remove(i);
                return Optional.of(temp);
            }
        }
        return Optional.empty();
    }

    public Course createCourse(Course course) {
        course.setId(id++);
        courses.add(course);
        return course;
    }

    public List<Course> findAllClasses(){
        //链接数据库
        //返回数据库的信息
        return  courses;
    }

    public List<Course> findAllCourse(String searchByCourseName){

        return new ArrayList<Course>();
    }

    public List<Course> findCourseByName(String courseName) {
        if(courseName.equals("Java_I")) {
            return courses;
        }

        return new ArrayList<Course>();
    }
}
