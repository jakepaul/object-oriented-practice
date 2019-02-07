package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jacob on 2017-02-08.
 */
public class RealCourse implements Course {
    
    private String courseName;
    private String courseID;
    private ArrayList<Student> students;
    private Department department;
    private int maxStudents;
    
    
    /**
     * Creates a new Course.
     * @param name Non-{@code null} or empty name
     * @param courseCode Non-{@code null} or empty courseCode
     * @param department Department that may be {@code null}
     * @param maxOccupancy The maximum number of students allowed in the course.
     *
     * @throws IllegalArgumentException if the name/course code is {@code null} or empty
     *                                  or the max occupancy is negative
     */
    public RealCourse(String name, String courseCode, @Nullable Department department, int maxOccupancy)
            throws IllegalArgumentException {
                if (name != null && name != "") {
                    this.courseName = name;
                }
                else{
                    throw new IllegalArgumentException("Course name cannot be null or empty");
                }
                if (courseCode != null && courseCode != "") {
                    this.courseID = courseCode;
                }
                else{
                    throw new IllegalArgumentException("Course code cannot be null or empty");
                }
                this.department = department;
                
                if (maxOccupancy > 0) {
                    this.maxStudents = maxOccupancy;
                }
                else{
                    throw new IllegalArgumentException("this number cannot be negative dummy");
                }
                this.students = new ArrayList<Student>();
    }
    
    
    /**
     * Get the unique course code for the Course.
     * @return Non-{@code null} or empty code for the course.
     */
    @Override
    public String getCourseCode(){
        return courseID;
    }
    
    /**
     * Set the name of the course
     * @param name Name of the course
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    @Override
    public void setName(@Nonnull String name){
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        else if (name == ""){
          throw new IllegalArgumentException("name cannot be empty");
        }
        else{
            courseName = name;
        }
        
    }
    
    /**
     * Get the current name of a course
     * @return Current name
     */
    @Override
    public String getName(){
        if(courseName != null){
            return courseName;
        }
        else{
            throw new NullPointerException("course name cannot be null");
        }
       
    }
    
    /**
     * Change the {@link Department} for a {@code Course}
     * @param newDepartment New department a course resides in.
     */
    @Override
    public void setDepartment(Department newDepartment){
        department = newDepartment;
        //department.addCourse(this);
    }
    
    /**
     * Get the current {@link Department}
     * @return Possibly {@code null} department.
     */
    @Override
    public Department getDepartment(){
        return department;
    }
    
    /**
     * Get the maximum occupancy of the course
     * @return Maximum number of students in the course.
     */
    @Override
    public int getMaximumOccupancy(){
        return maxStudents;
    }
    
    /**
     * Enroll a student in the course.
     * @param student Non-{@code null} student to add.
     *
     * @throws IllegalStateException if there is no room.
     */
    @Override
    public void enrollStudent(Student student) {
         if(students.size() >= maxStudents) {
            if(student == null){
                throw new NullPointerException("student cannot be null");
            }
             else if (!(students.contains(student))) {
                 students.add(student);
                 student.addCourse(this);
             }
         }
         else{
             throw new CourseMaxCapacityStoreException(student, this);
         }
        
    }
    
    /**
     * Remove a {@link Student} from a {@code Course}
     * @param student Removed student
     * @return The Student instance, if they were removed, otherwise {@code null}.
     */
    @Override
    public Student removeStudent(@Nonnull Student student){
        
        Student temp = student;
        if (students.contains(student)) {
            students.remove(student);
            student.removeCourse(this);
            return temp;
        }
        return null;
    }
    
    /**
     * Get all of the currently enrolled students
     * @return Non-{@code null} {@code List} of {@link Student}s, it may be empty.
     */
    @Override
    public List<Student> getEnrolledStudents() {
        
        return Collections.unmodifiableList(students);
    }
    @Override
    public List<String> getEnrolledStudentsString(){
        ArrayList<String> s= new ArrayList<>();
        for(Student i : students){
            s.add(i.getFirstName()+" "+i.getLastName());
        }
        return s;
    }
    
    
    public String toString(){
        return toStringHelper().toString();
    }
    
    private MoreObjects.ToStringHelper toStringHelper(){
        return MoreObjects.toStringHelper(this.getClass()).add("Course Name", courseName)
                .add("Course Code", courseID)
                .add("Max Capacity", maxStudents)
                .add("Current Size", students.size())
                .add("Department", department.getName())
                .add("Students", this.getEnrolledStudentsString());
    }
    
    public boolean equals(Object o){
        if(o == null) return false;
        // Check if the type of `o` is a List, if not, return false, they can't be equal
        if (!(o instanceof RealCourse)) return false;
        // Cast o to a List of Objects
        RealCourse that = (RealCourse) o;
        //check if the objects are equal
        return that.getName() == this.courseName
                && that.getEnrolledStudents().equals(this.students)
                && that.getCourseCode() == this.courseName
                && that.getDepartment() == this.department;
    }
    
    
}


