package ca.uwo.eng.se2205b.lab2.model;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by jacob on 2017-02-06.
 */
public interface Student {

    
    String getFirstName();
    
    void setFirstName(@Nonnull String first);
    
    String getLastName();
    
    void setLastName(@Nonnull String last);
    
    Department getDepartment();
    
    void setDepartment(Department dept);
    
    void displayCourses();
    
    void addCourse(Course course);
    
    Course getCourse(int index);
    
    List<Course> getAllCourses();
    
    List<String> getAllCoursesString();
    
    Course removeCourse(Course course);
    
    
    
    
}
