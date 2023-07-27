package com.lms.course.controller;


import com.lms.course.rest.AuthService;
import com.lms.course.service.CourseService;
import io.tej.SwaggerCodgen.api.ApiApi;
import io.tej.SwaggerCodgen.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1.0/lms")
public class CoursesController implements ApiApi {
    @Autowired
    CourseService courseService;

    @Autowired
    AuthService authService;

    @Override
    public ResponseEntity<String> addCourse(String authToken, String coursename, Course body) {
        String user = authService.isCheckRole(authToken);
        if(user.equals("admin")) {
            courseService.addCourse(body);
            return ResponseEntity.ok(coursename + " is added");
        }else {
            return ResponseEntity.ok("user is not Authorised");
        }
    }

    @Override
    public ResponseEntity<String> deleteCourse(String authToken, String coursename) {
        try{
            String user = authService.isCheckRole(authToken);
            if(user.equals("admin")) {
                courseService.deleteCourse(coursename);
            }
            else {
                return ResponseEntity.ok("user is not Authorised");
            }
            return new ResponseEntity<>("course deleted successfully" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occurred while delete "+coursename,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Course>> getAllCourses(String authToken) {
        String user = authService.isCheckRole(authToken);
        return ResponseEntity.ok(courseService.getAll());
    }

    @Override
    public ResponseEntity<List<Course>> getCoursesDurationandTechnology(String authToken, String technology, Integer durationFromRange, Integer durationToRange) {
        String user = authService.isCheckRole(authToken);
        return ResponseEntity.ok(courseService.getCoursesDurationandTechnology(technology,durationFromRange,durationToRange));
    }

    @Override
    public ResponseEntity<List<Course>> getCoursesInfo(String authToken, String technology) {
        String user = authService.isCheckRole(authToken);
        return ResponseEntity.ok(courseService.getCoursesInfo(technology));
    }
}