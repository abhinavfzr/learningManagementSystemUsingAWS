package com.lms.course.service;

import com.lms.course.domain.CourseDao;
import com.lms.course.repository.CourseRepository;
import io.tej.SwaggerCodgen.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        CourseDao courseDao = new CourseDao();
        courseDao.setCourseName(course.getCourseName());
        courseDao.setCourseDescription(course.getCourseDescription());
        courseDao.setCourseDuration(course.getCourseDuration());
        courseDao.setCourseTechnology(course.getCourseTechnology());
        courseDao.setLaunchUrl(course.getLaunchUrl());
        courseRepository.save(courseDao);
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();

        List<CourseDao> courseDao = courseRepository.findAll();
        for (CourseDao courseD : courseDao) {
            Course course = new Course();
            course.setId(courseD.getId());
            course.setCourseName(courseD.getCourseName());
            course.setCourseDescription(courseD.getCourseDescription());
            course.setCourseDuration(courseD.getCourseDuration());
            course.setCourseTechnology(courseD.getCourseTechnology());
            course.setLaunchUrl(courseD.getLaunchUrl());
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesInfo(String courseName) {

        List<Course> courses = new ArrayList<>();

        List<CourseDao> courseDao = courseRepository.findByCourseTechnology(courseName);
        for (CourseDao courseD : courseDao) {
            Course course = new Course();
            course.setId(courseD.getId());
            course.setCourseName(courseD.getCourseName());
            course.setCourseDescription(courseD.getCourseDescription());
            course.setCourseDuration(courseD.getCourseDuration());
            course.setCourseTechnology(courseD.getCourseTechnology());
            course.setLaunchUrl(courseD.getLaunchUrl());
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesDurationandTechnology(String technology, Integer durationFromRange, Integer durationToRange) {
        List<Course> courses = new ArrayList<>();
        List<CourseDao> courseDao = courseRepository.findByCourseTechnology(technology);
        for (CourseDao courseD : courseDao) {
            Course course = new Course();
            course.setId(courseD.getId());
            course.setCourseName(courseD.getCourseName());
            course.setCourseDescription(courseD.getCourseDescription());
            course.setCourseDuration(courseD.getCourseDuration());
            course.setCourseTechnology(courseD.getCourseTechnology());
            course.setLaunchUrl(courseD.getLaunchUrl());
            courses.add(course);
        }
        return courses.stream().filter(c-> c.getCourseTechnology().equals(technology))
                .filter(c-> c.getCourseDuration() > durationFromRange && c.getCourseDuration() < durationToRange)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(String course) {
          CourseDao courseDao = courseRepository.findByCourseName(course);
          courseRepository.deleteById(courseDao.getId());
    }

}
