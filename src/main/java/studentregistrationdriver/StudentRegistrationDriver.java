package studentregistrationdriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.joda.time.LocalDate;

import studentregistration.*;

public class StudentRegistrationDriver {

	public static void main(String [] args) {
		String courseName = "Electronic & Computer Engineering";
		Student[] students1 = {new Student("John Keane", 1001, 20, "10/10/1995"), 
								new Student("Jack O'Brien", 1002, 20, "10/11/1995"), 
								new Student("Joe Bloggs", 1003, 21, "03/04/1995")};
		Student[] students2 = {new Student("Alan Doherty", 1401, 20, "10/10/1995"), 
								new Student("Jean Farrell", 1203, 20, "10/11/1995"), 
								new Student("Anna Hegarty", 1303, 21, "03/04/1995")};
		Module[] moduleList = { new Module("Software Engineering III", "CT417", students1), 
								new Module("BE Project", "CT450", students2), 
								new Module("Machine Learning & Data Mining", "CT444", students1) };
		LocalDate startDate = new LocalDate(2016, 9, 5);
		LocalDate endDate = new LocalDate(2017, 5, 10);
		CourseProgramme prog = new CourseProgramme(courseName, moduleList, startDate, endDate);
		
		
		Module[] mods = prog.getModuleList();
		HashMap <Student, ArrayList<Module>> smap = new HashMap<Student, ArrayList<Module>>();
		HashMap <Module, CourseProgramme>mmap = new HashMap<Module, CourseProgramme>();
		
		for(int i=0; i<mods.length; i++) {
			Student[] studentsEnrolled = mods[i].getStudentList();
			mmap.put(mods[i], prog);
			
			for(int j=0; j<studentsEnrolled.length; j++) {
				
				if (!smap.containsKey(studentsEnrolled[j])) {
					smap.put(studentsEnrolled[j], new ArrayList<Module>());
				}
				
				smap.get(studentsEnrolled[j]).add(mods[i]);
			}
		}
		
		Set<Student> keys = smap.keySet();
		Iterator<Student> i = keys.iterator();
		
		while(i.hasNext()){
			Student temp = i.next();
			System.out.print("Student: " + temp.getName() + "\t");
			ArrayList<Module> m = smap.get(temp);
			Iterator<Module> j = m.iterator();
			
			System.out.print("Modules: ");
			
			while(j.hasNext()) {
				Module tmp = j.next();
				System.out.print(tmp.getModuleName() + " ");
			}
			
			System.out.println("\tCourse: " + mmap.get(m.get(0)).getCourseName());
		}
	}
	
}
