package ca.uwo.eng.se2205b.lab2.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Test the {@link Course} implementation
 */
public class RealCourseTest {
    
    /**
     * Test the name property
     */
    @Test
    public void name() {
        RealDepartment SE = new RealDepartment("SE");
        RealCourse se2205 = new RealCourse("Data Structures", "2205b", 10,SE);
        //making sure the id works
        assertEquals("2205b", se2205.getCourseCode());
        assertEquals(se2205.getName(), "Data Structures");
        se2205.setName("Data");
        assertEquals(se2205.getName(), "Data");
    }
    
    
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /**
     * Test that the maximum occupancy behaves properly
     */
    @Test
    public void maximumOccupancy() throws CourseMaxCapacityStoreException {
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealCourse data = new RealCourse("DataAndAlgorithms", "2205b", 10,SE);
        RealStudent[] jake = new RealStudent[11];
        for(int i = 0; i < jake.length; i++){
            jake[i] = new RealStudent("jake", "paul", SE, 34);
            try {
                data.enrollStudent(jake[i]);
                //Assert.assertEquals(i+1, data.getEnrolledStudents().size());
            } catch (CourseMaxCapacityStoreException e) {
                //assertEquals(i, jake[i].getStudentID());
                assertThat(e.getMessage(), is(data.getName() + " can not store " + jake[i].getFirstName() + ", maximum capacity reached."));
            }
        }
    }
    
    /**
     * Test that adding/removing students behaves
     */
    @Test
    public void students() {
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealCourse data = new RealCourse("DataAndAlgorithms", "2205b", 10,SE);
        RealStudent jake = new RealStudent("jake", "paul", SE,34);
        RealStudent ryan = new RealStudent("ryan", "steward", SE,12);
        RealStudent luke = new RealStudent("luke", "bottle",  SE,6);
        try {
            data.enrollStudent(jake);
            data.enrollStudent(ryan);
            data.enrollStudent(ryan);
            luke.addCourse(data);
            assertEquals(3, data.getEnrolledStudents().size());
            jake.removeCourse(data);
            assertEquals(2, data.getEnrolledStudents().size());
            data.removeStudent(ryan);
            assertEquals(1, data.getEnrolledStudents().size());
            data.removeStudent(luke);
            assertEquals(0, data.getEnrolledStudents().size());
        } catch (CourseMaxCapacityStoreException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test department interactions
     */
    @Test
    public void department() {
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealCourse data = new RealCourse("DataAndAlgorithms", "2205b",10, SE);
        assertEquals(data.getDepartment(), SE);
        RealDepartment QE = new RealDepartment("SoftwareEng");
        data.setDepartment(QE);
        assertEquals(data.getDepartment(), QE);
        data.setDepartment(null);
        assertEquals(data.getDepartment(), null);
    }
    
    @Test
    public void toStringTest(){
        RealDepartment SE= new RealDepartment("Software Engineering");
        RealCourse algorithms= new RealCourse("Data Structures And Algorithms", "2205",10, SE);
        RealStudent john= new RealStudent("john", "smith", SE,30);
        RealStudent nick= new RealStudent("nick", "bartlett", SE,22);
        try{
            algorithms.enrollStudent(nick);
            algorithms.enrollStudent(john);
        }
        catch(CourseMaxCapacityStoreException e){
            e.printStackTrace();
        }
        
        String test = algorithms.toString();
        
        assertEquals(test, "RealCourse{Course Name=Data Structures And Algorithms, Course Code=2205, Max Capacity=10, Current Size=2, Department=Software Engineering, Students=[nick bartlett, john smith]}");
    }
}