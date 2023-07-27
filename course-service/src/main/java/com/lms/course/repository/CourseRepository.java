package com.lms.course.repository;

import com.lms.course.domain.CourseDao;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<CourseDao, String> {

    List<CourseDao> findByCourseTechnology(String courseTechnology);

    CourseDao findByCourseName(String courseName);

//@Query(value ="SELECT * FROM COURSE WHERE COURSE_DURATION BETWEEN :fromRange AND :toRange AND COURSE_TECHNOLOGY =:tech",nativeQuery = true)
   @Query("{'courseTechnology' :  ?0, 'courseDuration' : { $gt : ?1, $lt : ?2}}")
   List<CourseDao> findBy( String tech,  String fromRange,  String toRange);

}
