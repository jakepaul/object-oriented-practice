package ca.uwo.eng.se2205b.lab2.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link Department} implementation.
 */
public class RealDepartmentTest {
    
    /**
     * Test the name property
     */
    @Test
    public void name() {
        RealDepartment name = new RealDepartment("SE");
        assertEquals(name.getName(), "SE");
        name.setName("BIO");
        assertEquals(name.getName(), "BIO");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nameNoNull() {
        RealDepartment name = new RealDepartment(null);
    }
    
    /**
     * Test course changes
     */
    @Test
    public void courses() {
        RealDepartment SE= new RealDepartment("Software Engineering");
        RealCourse algorithms= new RealCourse("Data Structures And Algorithms", "2205", 10,SE);
        RealCourse construction= new RealCourse("Software Construction", "2250",10, SE);
        assertEquals(SE.getCourses().size(), 2);
        
        RealDepartment ECE= new RealDepartment("Electrical");
        RealCourse circuits = new RealCourse("Data Structures And Algorithms", "2238",10, ECE);
        circuits.setDepartment(SE);
        assertEquals(ECE.getCourses().size(), 0);
        assertEquals(SE.getCourses().size(), 3);
    }
    
    /**
     * Test student changes
     */
    @Test
    public void students() {
        RealDepartment SE= new RealDepartment("Software Engineering");
        RealDepartment CS = new RealDepartment("Computer Science");
        RealStudent john= new RealStudent("john", "smith", SE,3);
        RealStudent ben= new RealStudent("ben", "gibson", CS,9999);
        assertEquals(CS.getEnrolledStudents().size(), 1);
        assertEquals(SE.getEnrolledStudents().size(), 1);
        john.setDepartment(CS);
        assertEquals(CS.getEnrolledStudents().size(), 2);
        assertEquals(SE.getEnrolledStudents().size(), 0);
        CS.removeStudent(john);
        assertEquals(CS.getEnrolledStudents().size(), 1);
        assertEquals(SE.getEnrolledStudents().size(), 0);
    }
    
    @Test
    public void toStringTest(){
        RealDepartment SE= new RealDepartment("Software Engineering");
        RealCourse algorithms= new RealCourse("Data Structures And Algorithms", "2205",10, SE);
        RealCourse construction= new RealCourse("Software Construction", "2250",10 ,SE);
        RealStudent john= new RealStudent("john", "smith", SE,3);
        RealStudent ben= new RealStudent("ben", "gibson", SE,9999);
        
        SE.enrollStudent(ben);
        SE.enrollStudent(john);
        SE.addCourse(algorithms);
        SE.addCourse(construction);
        
        String test = SE.toString();
        
        assertEquals(test, "RealDepartment{Department Name=Software Engineering, Current Course Amount=2, Current Student Amount=2, Courses=[Data Structures And Algorithms, Software Construction], Students=[ben gibson, john smith]}");
    }
}