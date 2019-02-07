package ca.uwo.eng.se2205b.lab2.model;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by jacob on 2017-02-08.
 */
public class RealStudentTest {
    
    @Test
    public void CheckingStudentValues(){
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealStudent jake = new RealStudent("jake", "paul", SE,66);
        assertEquals("jake", jake.getFirstName());
        assertEquals("paul", jake.getLastName());
        assertEquals(SE, jake.getDepartment());
    }
    
    //other test
    @Test
    public void AddingStudents(){
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealCourse data = new RealCourse("DataAndAlgorithms", "2205b", 10,SE);
        RealStudent[] jake = new RealStudent[11];
        for(int i = 0; i < jake.length; i++){
            jake[i] = new RealStudent("jake", "paul", SE,66);
            try {
                jake[i].addCourse(data);
                // assertEquals(i+1, data.getEnrolledStudents().size());
            } catch (CourseMaxCapacityStoreException e) {
                //assertEquals(i, jake[i].getStudentID());
                assertThat(e.getMessage(), is(data.getName() + " can not store , maximum capacity reached."));
            }
        }
      
        
    }
    
    @Test
    public void RemovingStudents(){
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealCourse data = new RealCourse("DataAndAlgorithms", "2205b", 10,SE);
        RealStudent jake = new RealStudent("jake", "paul", SE, 66);
        RealStudent ryan = new RealStudent("ryan", "steward", SE, 78);
        try {
            jake.addCourse(data);
            assertEquals(jake.getCourse(0).getName(), "DataAndAlgorithms");
            ryan.addCourse(data);
            assertEquals(jake.removeCourse(data), data);
            //assertEquals(jake.getCourse(0).getName(), "index out of bounds");
            assertEquals(data.getEnrolledStudents().get(0).getFirstName(), "ryan");
        } catch (CourseMaxCapacityStoreException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void StudentNull(){
        RealDepartment SE = new RealDepartment("SoftwareEng");
        RealStudent student = new RealStudent("Charlie", "Brown", SE,34);
        try{
            student.setFirstName(null);
        }catch(IllegalArgumentException e){
            assertThat(e.getMessage() , is("string value cannot be null"));
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nameNoNull() {
        RealDepartment SE = new RealDepartment("SoftwareEng");
        //illegalargumentexception for the null first name
        RealStudent student = new RealStudent(null, "Brown", SE,22);
        //throw new NullPointerException("Sudent name cant be null");
        RealStudent student1 = new RealStudent(null, null, SE,23);
    }
    
    @Test
    public void testingEquals(){
        RealDepartment SE = new RealDepartment("SoftwareEng");
        //illegalargumentexception for the null first name
        RealStudent student = new RealStudent("Charlie", "Brown", SE,1);
        RealStudent student1 = new RealStudent("Charlie", "Brown", SE,1);
        
        if(student.equals(student1)){
            System.out.print(student.toString());
        }
        else fail("the equal objects were not detected equal");
    }
    
    
    @Test
    public void toStringTest(){
        RealDepartment SE= new RealDepartment("Software Engineering");
        RealDepartment AM= new RealDepartment("Applied Mathematics");
        RealCourse algorithms= new RealCourse("Data Structures And Algorithms", "se2205", 10,SE);
        RealCourse calc= new RealCourse("Calculus", "am2270", 10,AM);
        RealStudent nick= new RealStudent("nick", "bartlett",SE,11);
        try{
            nick.addCourse(algorithms);
            nick.addCourse(calc);
        }
        catch(CourseMaxCapacityStoreException e){
            e.printStackTrace();
        }
        
        String test = nick.toString();
        
        assertEquals(test, "RealStudent{First Name=nick, Last Name=bartlett, Department=Software Engineering, Courses=[Data Structures And Algorithms]}");
    }
    
}
