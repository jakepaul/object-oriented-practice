package ca.uwo.eng.se2205b.lab2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Test fixture used to create a test model for test cases.
 */
public class ProvidedModelFactory {
    
    /**
     * Creates the model shown in
     * <a href="https://uwoece-se2205b-2017.github.io/labs/02-oop-serialization#question-0">Q1 Deliverable</a>.
     *
     * @return List of Department values
     */
    public static List<Department> createModel() throws CourseMaxCapacityStoreException {
        ArrayList<Department> school = new ArrayList<>();
        RealDepartment CEE = new RealDepartment("Civil and Environmental Engineering");
        school.add(CEE);
        RealDepartment ECE = new RealDepartment("Electrical And Computer Engineering");
        school.add(ECE);
        RealDepartment AM = new RealDepartment("Applied Math");
        school.add(AM);
        RealCourse AM1413 = new RealCourse("Calculus", "AM1413",AM,6);
        RealCourse ES1022 = new RealCourse("Statics", "ES1022", CEE, 6);
        RealCourse ES1036 = new RealCourse("Programming Fundamentals I", "ES1036",ECE, 5);
        RealCourse SE2205 = new RealCourse("Data Structures and Algorithms", "SE2205", ECE, 10);
        
        RealStudent student = new RealStudent("John", "Smith",1111, CEE);
        
        student.addCourse(AM1413);
        student.addCourse(ES1022);
        student.toString();
        
        RealStudent student2 = new RealStudent("Sarah", "McLachlan", 2222,ECE);
        
        student2.addCourse(AM1413);
        student2.addCourse(ES1036);
        student2.addCourse(SE2205);
        student2.toString();
        
        RealStudent student3 = new RealStudent("Gene", "Wilder",3333, ECE);
        
        student3.addCourse(AM1413);
        student3.addCourse(SE2205);
        student3.toString();
        
        RealStudent student4 = new RealStudent("Ron", "Weasley",4444, ECE);
        
        student4.addCourse(ES1022);
        student4.addCourse(SE2205);
        student4.toString();
        
        RealStudent student5 = new RealStudent("Minh", "Pham",5555,ECE);
        
        student5.addCourse(AM1413);
        student5.addCourse(ES1022);
        student5.toString();
        
        RealStudent student6 = new RealStudent("George", "Takei",6666, AM);
        
        student6.addCourse(AM1413);
        student6.addCourse(SE2205);
        student6.toString();
        
        RealStudent student7 = new RealStudent("Ralph", "Nader",7777,AM);
        
        student7.addCourse(AM1413);
        student7.addCourse(ES1022);
        student7.addCourse(ES1022);
        student7.addCourse(SE2205);
        student7.toString();
        
        RealStudent student8 = new RealStudent("Jane", "Tarzan", 8888,ECE);
        student8.toString();
        
        return null;
    }
}
