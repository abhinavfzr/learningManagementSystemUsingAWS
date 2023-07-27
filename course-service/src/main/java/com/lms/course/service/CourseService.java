package com.lms.course.service;

import io.tej.SwaggerCodgen.model.Course;

import java.util.List;

public interface CourseService {

    public void addCourse(Course course);
    public void deleteCourse(String course);
    public List<Course> getAll();
    public List<Course>  getCoursesInfo(String courseName);
    public List<Course> getCoursesDurationandTechnology(String tech,Integer durationFromRange, Integer durationToRange);


}
