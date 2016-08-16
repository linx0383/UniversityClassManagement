package models;

import java.util.*;
import models.Student;

/*
 * Used for managing classes and students
 */
public class UniversityClassManagement {
	
	// HashMap declarations for storing accounts
	public static Hashtable<Integer,UniversityClass> ucs;
	public static Hashtable<Integer,Student> students;


	public static Hashtable<Integer, UniversityClass> getUcs() {
		return ucs;
	}

	public static void setUcs(Hashtable<Integer, UniversityClass> ucs) {
		UniversityClassManagement.ucs = ucs;
	}

	public static Hashtable<Integer, Student> getStudents() {
		return students;
	}

	public static void setStudents(Hashtable<Integer, Student> students) {
		UniversityClassManagement.students = students;
	}

	//protected access to restrict other classes from creating instance of the bank
	protected UniversityClassManagement(){
	}

	//static block is used for initialization
	static{
		 // Creating instances of HashMaps
		 ucs = new Hashtable<Integer,UniversityClass>();
		 students= new Hashtable<Integer,Student>();
	}
	
	/**
	 * Returns the name of the Bank
	 * @return
	 */
	//creates new SavingsAccount with zero balance
	public static UniversityClass createUniversityClass(String className){
		UniversityClass uc=new UniversityClass(className);
		ucs.put(uc.getClassNumber(), uc);
		return uc;
	}
	
	public static UniversityClass findUniversityClassbyNumber(int number){
			
		if(ucs.containsKey(number)) //returns true of false
			return ucs.get(number); //No cast required as generic HashMap returns correct type.
		else
			return null;
	}
	
	public static Student findStudentbyId(int Id){
		
		if(students.containsKey(Id)) //returns true of false
			return students.get(Id); //No cast required as generic HashMap returns correct type.
		else
			return null;
	}
	
	public static Student findStudentbyName(String name){
		
		 Collection<Student> collection = students.values();
		 Iterator<Student> iterator = collection.iterator();
		 while(iterator.hasNext()) {
			 Student student =  iterator.next();
			if(student.getName().equals(name))//returns true of false
				return student; //No cast required as generic HashMap returns correct type.
		}
			return null;
	}
	
	public static UniversityClass findUniversityClassbyName(String name){
		
		 Collection<UniversityClass> collection = ucs.values();
		 Iterator<UniversityClass> iterator = collection.iterator();
		 while(iterator.hasNext()) {
			 UniversityClass uclass =  iterator.next();
			if(uclass.getClassName().equals(name))//returns true of false
				return uclass; //No cast required as generic HashMap returns correct type.
		}
			return null;
	}
	
	public static Student addStudentIntoManagement(String name){
		Student student=new Student(name);
		students.put(student.getId(),student);
		return student;
	}
	
	public static void printAllClasses(){
		//System.out.println("\n............" + name +"............"  );
		System.out.println("All Classes Information");
	
		if(ucs.isEmpty()) //check whether there are any elements
			System.out.println("There are no available classes");
		else{
			 Collection<UniversityClass> collection = ucs.values();
			 System.out.println(ucs.values());
			 Iterator<UniversityClass> iterator = collection.iterator();
			// Iterator<UniversityClass> iterator1 = ucs.values().iterator();

			 while(iterator.hasNext()) {
				 UniversityClass uclass =  iterator.next();
				 uclass.print();
			}
			 System.out.println("Total no of Classes : " + collection.size());
		}
	}
	
	public static void printAllStudents(){
		System.out.println("All Students Information");
	
		if(students.isEmpty()){ //check whether there are any elements
			System.out.println("There are no available students added");
			
		}
		else{
			 Collection<Student> collection = students.values();
			 Iterator<Student> iterator = collection.iterator();
			// Iterator<UniversityClass> iterator1 = ucs.values().iterator();
			
			 while(iterator.hasNext()) {
				 Student stu =  iterator.next();
				 stu.printStudent();
			}
			 System.out.println("Total no of Students : " + collection.size());
		}
	}
	
}
