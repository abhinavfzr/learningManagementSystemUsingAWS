package com.lms.course.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "Course")
public class CourseDao {

    @Id
    private String id;

    private String courseName;

    private Integer courseDuration;

    private String courseDescription;

    private String courseTechnology;

    private String launchUrl;

    public CourseDao() {
    }

    public CourseDao(String id, String courseName, Integer courseDuration, String courseDescription, String courseTechnology, String launchUrl) {
        this.id = id;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDescription = courseDescription;
        this.courseTechnology = courseTechnology;
        this.launchUrl = launchUrl;
    }

    @Override
    public String toString() {
        return "CourseDao{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseDuration=" + courseDuration +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseTechnology='" + courseTechnology + '\'' +
                ", launchUrl='" + launchUrl + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(Integer courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseTechnology() {
        return courseTechnology;
    }

    public void setCourseTechnology(String courseTechnology) {
        this.courseTechnology = courseTechnology;
    }

    public String getLaunchUrl() {
        return launchUrl;
    }

    public void setLaunchUrl(String launchUrl) {
        this.launchUrl = launchUrl;
    }
}
