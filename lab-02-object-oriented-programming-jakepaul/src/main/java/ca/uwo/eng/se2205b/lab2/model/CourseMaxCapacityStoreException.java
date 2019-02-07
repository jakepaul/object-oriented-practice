package ca.uwo.eng.se2205b.lab2.model;

/**
 * Created by jacob on 2017-02-08.
 */
public class CourseMaxCapacityStoreException extends RuntimeException {
    private Student student;
    private Course course;
    
    public CourseMaxCapacityStoreException(Student student, Course course){
        super(course + " can not store " + student + ", maximum capacity reached.");
        this.student = student;
        this.course = course;
    }
    
    public Student getStudent(){
        return student;
    }
    
    public Course getCourse(){
        return course;
    }
}
