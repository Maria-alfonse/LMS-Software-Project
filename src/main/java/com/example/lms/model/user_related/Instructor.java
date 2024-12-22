package com.example.lms.model.user_related;

import com.example.lms.model.course_related.Course;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Instructor")
public class Instructor extends User {

    //courses list?
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> course = new ArrayList<>();

    @JsonGetter("Courses")
    public Map<Integer, String> getCourses(){
        Map<Integer, String > courses = new HashMap<>();
        for(Course c : course){
            courses.put(c.getId(), c.getTitle());
        }
        return courses;
    }
}