package com.example.lms.model.course_related;

import com.example.lms.controller.CourseData;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.model.user_related.User;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "Course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Course(CourseData courseData, Instructor instructor) {
        this.title = courseData.getTitle();
        this.description = courseData.getDescription();
        this.duration = courseData.getDuration();
        this.instructor = instructor;
    }

//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Student> enrolledStudent = new ArrayList<>();
//
//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Quiz> quizzes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Map<Integer, Assignment> assignments = new HashMap<>();
//
//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Map<Integer, Lesson> lessons = new HashMap<>();
//
//    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
//    private QuestionsBank questionsBank;
}

