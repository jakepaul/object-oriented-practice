package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jacob on 2017-02-08.
 */
public class RealDepartment implements Department {
    
    private String deptname;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    
    
    /**
     * Creates a new department.
     * @param name Non-{@code null} or empty name
     *
     * @throws IllegalArgumentException if the name is {@code null} or empty
     */
    
    RealDepartment(String name) throws IllegalArgumentException {
        if (name != null && name != "") {
            this.deptname = name;
        }
        else{
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.students = new ArrayList<Student>();
        this.courses = new ArrayList<Course>();
    }
    
    
    /**
     * Get the unique name of the Department
     * @return Non-{@code null} name
     */
    @Override
    public String getName(){return deptname;}
    
    /**
     * Return the unique name of the department.
     * @param name Non-{@code null} name
     *
     * @throws IllegalArgumentException if {@code name} is empty
     */
    @Override
    public void setName(@Nonnull String name){
        if (name != ""){
            deptname = name;
        }
        else{
            throw new IllegalArgumentException("name cannot be empty");
        }
    }
    
    /**
     * Enroll a student in the Department.
     * @param student Non-{@code null} student to add.
     */
    @Override
    public void enrollStudent(@Nonnull Student student){
        if (! students.contains(student)){
            students.add(student);
            student.setDepartment(this);
        }
    }
    
    /**
     * Remove a {@link Student} from a {@code Department}
     * @param student Removed student
     * @return The Student instance, if they were removed, otherwise {@code null}.
     */
    @Override
    public Student removeStudent(@Nonnull Student student){
       Student temp = student;
        if (students.contains(student)){
            students.remove(student);
            student.setDepartment(null);
            
            return temp;
        }
        return null;
    }
    
    /**
     * Get all of the currently enrolled students
     * @return Non-{@code null} {@code List} of {@link Student}s, it may be empty.
     */
    @Override
    public List<Student> getEnrolledStudents(){
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
    /**
     * Adds a course to a department
     * @param course Course to add
     */
    @Override
    public void addCourse(@Nonnull Course course){
        if (!courses.contains(course)){
            courses.add(course);
            course.setDepartment(this);
        }
    }
    
    /**
     * Removes a course from the {@code Department}
     * @param course Course to remove
     * @return Instance removed, {@code null} if none are removed
     */
    @Override
    public Course removeCourse(@Nonnull Course course){
        Course temp = course;
        if(courses.contains(course)){
            courses.remove(course);
            course.setDepartment(null);
            
            return temp;
        }
        
        return null;
    }
    
    /**
     * Get all of the courses in the Department
     * @return List of all courses, may be empty, but never {@code null}
     */
    @Override
    public List<Course> getCourses(){
        return Collections.unmodifiableList(courses);
    }
    
    @Override
    public List<String> getCoursesString(){
        ArrayList<String> c= new ArrayList<>();
        for(Course i : courses){
            c.add(i.getName());
        }
        return c;
    }
    
    @Override
    public String toString(){
        return toStringHelper().toString();
    }
    
    public MoreObjects.ToStringHelper toStringHelper(){
        return MoreObjects.toStringHelper(this.getClass()).add("Department Name", deptname)
                .add("Current Course Amount", courses.size())
                .add("Current Student Amount", students.size())
                .add("Courses", this.getCoursesString())
                .add("Students", this.getEnrolledStudentsString());
        
        
    }
    
    public boolean equals(Object o){
        if(o == null) return false;
        // Check if the type of `o` is a List, if not, return false, they can't be equal
        if (!(o instanceof RealCourse)) return false;
        // Cast o to a List of Objects
        Department that = (RealDepartment) o;
        //check if the objects are equal
        return that.getName() == this.deptname
                && that.getEnrolledStudents().equals(this.students)
                && that.getCourses().equals(this.courses);
        
    }
   
    
    
}
