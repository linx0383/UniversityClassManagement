package models; 
import data.DBManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import models.Student;

import models.InvalidInputNameException;
import models.UniversityClass;
import models.UniversityClassManagement;

import java.sql.Connection;
import java.sql.Statement;
// Developed by Tianshu Lin
//Main Menu 1. Add Class 2. List Classes (Prints class with student
//		 * info) 3. Add Student (Add Students to array) 4. Find a Class (Prompts
//		 * for major and returns all matching classes, ) 5. Enroll Student to
//		 * class (promts for class Id and student id and enrolls) 6. Exit

public class UniversityClassManagementApplication{
	private Scanner scanner;
	UniversityClass uc;
	Student stu;
	static Connection con=null;
	void DisplayAddClass() throws InvalidInputNameException{
		System.out.println("Please Enter the Name for the New Class: ");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		if(!line.isEmpty()){
			uc=UniversityClassManagement.createUniversityClass(line);
		}
		else{
			throw new InvalidInputNameException("Invalid Class Name");
			//return;
		}
		System.out.println("Please Select the Major for the newly created "+uc.getClassName()+" Class:");
		scanner = new Scanner(System.in);
		String line1 = scanner.nextLine();
		if(!line1.isEmpty()){
			uc.setMajor(line1);
		}
		else{
			throw new InvalidInputNameException("Invalid Major Name");
			//return;
		}
		System.out.println("Please Set the Classroom for the Newly Created "+uc.getClassName()+" Class:");
		scanner = new Scanner(System.in);
		String line2 = scanner.nextLine();
		if(!line2.isEmpty()){
			System.out.println("Please Set the Classroom Seating Capacity for the Newly Created "+uc.getClassName()+" Class:");
			scanner = new Scanner(System.in);
			int line3 = scanner.nextInt();
			if(line3>=0)
			{
				uc.setClassRoom(line2,line3);
			}
			else{
				throw new InvalidInputNameException("Invalid Number of Seating Capacity");
				//return;
			}
		}
		else{
			throw new InvalidInputNameException("Invalid Classroom Name");
			//return;
		}
		uc.setScheduleSet(false);
		uc.print();
		
        Statement stmt = null;
		try{
		
		con=DBManager.getConnection();
		String sql="insert into universityclass(cname,major) values('"+uc.getClassName()+"','"+uc.getMajor()+"')";
		stmt=con.createStatement();	
		stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		stmt.close();
		
		String sql2="insert into classroom(cid,crname, seat) values('"+uc.getClassNumber()+"','"+uc.getClassRoom().getClassRoomName()+"','"+uc.getClassRoom().getSeatingCapacity()+"')";
		stmt=con.createStatement();	
		stmt.executeUpdate(sql2,Statement.RETURN_GENERATED_KEYS);
		stmt.close();
		con.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	void DisplayListClasses(){
		UniversityClassManagement.printAllClasses();
	}
	
	void DisplayListStudents(){
		UniversityClassManagement.printAllStudents();
	}
	
	void DisplayAddStudent(){

		while(true){
		System.out.println("1: Add a new student to University");
		System.out.println("2: Return to previous menu");
		scanner = new Scanner(System.in);
		int int1 = scanner.nextInt();
			switch(int1){
			case 1: System.out.println("Please enter the name of student");
					scanner = new Scanner(System.in);
					String str1 = scanner.nextLine();
					UniversityClassManagement.addStudentIntoManagement(str1);
			        Statement stmt = null;
					try{
					
					con=DBManager.getConnection();
					String sql="insert into student(sname) values('"+str1+"')";
					stmt=con.createStatement();	
					stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					stmt.close();
					con.close();
					System.out.println("Student "+str1+" has been successfully added with Id "+Student.getIdCounter());
					}catch(Exception ex){
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
			case 2: return;
			}
		}
	}
	
	void DisplayFindClass(){
		System.out.println("Please Choose the option for Finding a Class");
		System.out.println("1: Find a Class by Class Number");
		System.out.println("2: Find a Class by Class Name");
		System.out.println("3: Return to Main Menu");
		scanner= new Scanner(System.in);
		int option=scanner.nextInt();
		switch(option){
		case 1: findClassbyNumber();
				break;
		case 2: findClassbyName();
				break;
		case 3: return;
		default:System.out.println("Invalid Input");
				return;
		}
	
	}
	
	boolean findClassbyNumber(){
		UniversityClass univerclass;
		System.out.println("Please enter the class number:");
		scanner= new Scanner(System.in);
		int num1=scanner.nextInt();
		univerclass=UniversityClassManagement.findUniversityClassbyNumber(num1);
		this.uc=univerclass;
		if(univerclass!=null){
			univerclass.print();
			return true;
			}
		else{
			System.out.println("The class with number "+ num1+" does not exist!");
			return false;
		}
	}
	
	void findClassbyName(){
		UniversityClass univerclass;
		System.out.println("Please enter the class name");
		scanner= new Scanner(System.in);
		String str=scanner.nextLine();
		univerclass=UniversityClassManagement.findUniversityClassbyName(str);
		if(univerclass!=null){
			univerclass.print();
			}
		else
			System.out.println("The class withname "+ univerclass+" does not exist!");
	}
	
	void DisplaySetSchedule(){
		System.out.println("Set the Schedule for a Class");
		boolean isfound=findClassbyNumber();
		if(isfound==false)
			return;
		System.out.println("1. Set Schedule for the above selected Class");
		System.out.println("2. Return to Previous Menu");
		scanner=new Scanner(System.in);
		int int2=scanner.nextInt();
		switch(int2){
		case 1:  setSchedule();
				 break;	
		case 2:  return;
		default: return;
		}
	}
	
	void setSchedule(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat time = new SimpleDateFormat("hh:mm:ss");
		System.out.println("Please Set the Schedule for the "+uc.getClassName()+" Class  Format");
		System.out.println("Please specify the start date of the class in format yyyy-MM-dd");
		scanner = new Scanner(System.in);
		String line4 = scanner.nextLine();
		if(line4!=null){
			try {
				Date convertedStartDate=df.parse(line4);
				System.out.println(convertedStartDate);
				uc.setStartDate(convertedStartDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("Please specify the start time of the class in format hh:mm:ss");
			scanner = new Scanner(System.in);
			String line5 = scanner.nextLine();
			if(line5!=null){
				Date convertedStartTime;
				try {
					convertedStartTime = time.parse(line5);
					System.out.println(convertedStartTime);
					uc.setStartTime(convertedStartTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Invalid start time format");
				return;
			}
			
			System.out.println("Please specify the end time of the class in format hh:mm:ss");
			scanner = new Scanner(System.in);
			String line6 = scanner.nextLine();
			if(line6!=null){
				Date convertedEndTime;
				try {
					convertedEndTime = time.parse(line6);
					uc.setEndTime(convertedEndTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Invalid end time format");
				return;
			}			
			System.out.println("Please specify the end date of the class in format yyyy-MM-dd");
			scanner = new Scanner(System.in);
			String line7 = scanner.nextLine();
			if(line7!=null)
			{
				Date convertedEndDate;
				try {
					convertedEndDate = df.parse(line7);
					uc.setEndDate(convertedEndDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Invalid end date format");
				return;
			}
			
			Statement stmt = null;
			try{
			
			con=DBManager.getConnection();
			String sql="insert into classroomschedule(scheduleid,startdate,enddate,starttime,endtime) values("+uc.getClassNumber()+",'"+line4+"','"+line7+"','"+line5+"','"+line6+"')";
			stmt=con.createStatement();	
			stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.close();
			con.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			uc.setScheduleSet(true);
			System.out.println("The Schedule has been successfully set for "+uc.getClassName() +" class");
			uc.print();
		}
		else{
			System.out.println("Invalid start date format");
			return;
		}
	}
	
	void DisplayEnrollStudent(){

		System.out.println("Please enter the student's Id");
		scanner=new Scanner(System.in);
		int ID=scanner.nextInt();
		stu=UniversityClassManagement.findStudentbyId(ID);
		if(stu!=null){
			UniversityClass usc;
			System.out.println("Student "+stu.getName()+" has been selected with Id "+ ID);
			System.out.println("Please enter the class number that you want to enroll the student in");
			scanner=new Scanner(System.in);
			int classnum=scanner.nextInt();
			usc=UniversityClassManagement.findUniversityClassbyNumber(classnum);
			if(usc!=null){
				usc.enrollStudent(stu);
		        Statement stmt = null;
					try{
					con=DBManager.getConnection();
					String sql="update universityclass set enrolledstudent='"+stu.getName()+"' where cid="+usc.getClassNumber();
					String sql2="update universityclass set enrolledstudent=concat(enrolledstudent,'"+", "+stu.getName()+"') where cid="+usc.getClassNumber();
					stmt=con.createStatement();
					if(usc.getEnrolledStudents().size()==1){
						stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
						}
					else{
						stmt.executeUpdate(sql2,Statement.RETURN_GENERATED_KEYS);
					}
					stmt.close();
					con.close();
					System.out.println("The student "+stu.getName()+" has been successfully enrolled in to class "+ usc.getClassName());
					}catch(Exception ex){
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
			}
			else{
				System.out.println("The class with number "+classnum+" does not exist!");
				return;
			}		
		}
		else{
			System.out.println("The student with ID "+ID+" does not exist!");
			return;
		}
	}
	
	void displayMainMenu() throws InvalidInputNameException{		
		while(true)
		{
			//print empty lines for separating from previous options
			System.out.println();
			System.out.println("Welcome to Use University Class Management Application");
			System.out.println("                   Main Menu                        ");
			System.out.println("1:Add Class");
			System.out.println("2:List Classes");
			System.out.println("3:Add Student");
			System.out.println("4:Find a Class");
			System.out.println("5:Set Schedule for a Class");
			System.out.println("6:Enroll Student to a Class");
			System.out.println("7:List Students");
			System.out.println("8:Exit");
			System.out.print("Please enter your option: ");
			scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				DisplayAddClass();
				break;
			case 2:
				DisplayListClasses();
				break;
			case 3:
				DisplayAddStudent();
				break;
			case 4:
				DisplayFindClass();
				break;
			case 5:
				DisplaySetSchedule();
				break;
			case 6:
				DisplayEnrollStudent();
				break;
			case 7:
				DisplayListStudents();
				break;				
			case 8:
				System.out.println("Exited the application");
				System.exit(0);
			default:
				System.out.println("Invalid input, please try again");
				break;
			}		
		}
	}

	public static void main(String[] args) throws Exception {
			

		UniversityClassManagementApplication app = new UniversityClassManagementApplication();
		app.displayMainMenu();

}
}
