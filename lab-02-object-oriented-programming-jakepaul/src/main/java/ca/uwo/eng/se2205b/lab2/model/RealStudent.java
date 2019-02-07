package ca.uwo.eng.se2205b.lab2.model;

import com.google.common.base.MoreObjects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.lang.IllegalArgumentException;

/**
 * Created by jacob on 2017-02-06.
 */
public class RealStudent implements Student{
   private String firstName ;
   private String lastName ;
   private Department department;
   private ArrayList<Course> courses;
   private long id;
    
    /**
     * Creates a new Student.
     * @param firstName Non-{@code null} or empty name
     * @param lastName Non-{@code null} or empty name
     * @param studentId Positive student ID
     * @param department Department that may be {@code null}
     *
     * @throws IllegalArgumentException if the name/course code is {@code null} or empty
     *                                  or the max occupancy is negative
     */
    public RealStudent(String firstName, String lastName, long studentId, @Nullable Department department)
            throws IllegalArgumentException {
        
                if (firstName != null && firstName != "") {
                    this.firstName = firstName;
                }
                else {
                    throw new IllegalArgumentException("Name cannot be null or blank fool");
                }
                
                if (lastName != null && lastName != "") {
                    this.lastName = lastName;
                }
                else {
                    throw new IllegalArgumentException("Name cannot be null or blank fool");
                }
        
                this.department = department;
                this.courses = new ArrayList<Course>();
                
                if (studentId < 0){
                    throw new IllegalArgumentException("id must be positive");
                }
                else {
                    this.id = studentId;
                }
        }
    
   @Override
    public String getFirstName(){
       if (firstName != null && firstName != ""){
            return firstName;
        }
        else {
           throw new NullPointerException("Name is null");
       }
    }
    @Override
    public void setFirstName(@Nonnull String first){
        if (first != null && lastName != "") {
            firstName = first;
        }
        else{
            throw new NullPointerException("First name cannot be null");
        }
    }
    @Override
    public String getLastName(){
        if (lastName != null && lastName != "") {
            return lastName;
        }
        else{
            throw new NullPointerException("Name is null");
        }
    }
    @Override
    public void setLastName(String last){
        
        if(last != null && lastName.equals("")) {
            lastName = last;
        }
        else{
            throw new NullPointerException("Last name cannot be null");
        }
    }
    @Override
    public Department getDepartment(){
        return department;
    }
    @Override
    public void setDepartment(Department dept){
        department = dept;
        department.enrollStudent(this);
    }
    @Override
    public List<Course> getAllCourses(){
        return Collections.unmodifiableList(courses);
    }
    
    @Override
    public void displayCourses(){
//        //in this i want to make an iterator of the courses and print out the courses while the list hasnext
//        System.out.println("The courses you are enrolled in are: ");
//
//        for (int i = 0; i < courses.size() - 1; i++ ) {
//            System.out.print(courses.get(i));
//            if(i < courses.size() -2){
//                System.out.print(", ");
//            }
//        }
        
        Iterator<Course> itr = courses.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }
        
    }
    
    @Override
    public void addCourse(Course course)throws CourseMaxCapacityStoreException{
        if(course.getEnrolledStudents().size() != course.getMaximumOccupancy()+1) {
            if (course == null) {
                throw new NullPointerException("course cannot be null");
            } else {
                if (!courses.contains(course)) {
                    courses.add(course);
                    course.enrollStudent(this);
                }
            }
        }
        else{
            throw new CourseMaxCapacityStoreException(this, course);
        }
    }
    @Override
    public Course removeCourse(Course course){
        boolean success = courses.remove(course);
        course.removeStudent(this);
        if (success) {
            return course;
        }
        else{
            throw new IllegalArgumentException("course does not exist");
        }
    }
    @Override
    public Course getCourse(int index) {
        if (index>=0 && index<courses.size()){
            return courses.get(index);
        }
        else{
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        
    }
    @Override
    public String toString(){
        if (this.firstName!=null && this.lastName!=null){
            
            return toStringHelper().toString();
        }
        else{
            throw new NullPointerException("The student has not properly been created. Make sure there is a name and a department");
        }
    }
    
    @Override
    public List<String> getAllCoursesString(){
        ArrayList<String> c= new ArrayList<>();
        for(Course i : courses){
            c.add(i.getName());
        }
        return c;
    }
    
    public boolean equals(Object o){
        if(o == null) return false;
        // Check if the type of `o` is a List, if not, return false, they can't be equal
        if (!(o instanceof RealStudent)) return false;
        // Cast o to a List of Objects
        RealStudent that = (RealStudent) o;
        //check if the objects are equal
        return that.getFirstName() == this.getFirstName()
                && that.getLastName() == this.getLastName()
                && that.getDepartment() == this.getDepartment()
                && courses.equals(that.getAllCourses());
    }
    
    private MoreObjects.ToStringHelper toStringHelper(){
        return MoreObjects.toStringHelper(this.getClass()).add("First Name",firstName)
                .add("Last Name", lastName)
                .add("Department", department.getName())
                .add("Courses", this.getAllCoursesString());
    }
}
