package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Student;

public class UniversityClass {

		
		protected String className;
		static int classNumberCounter=1000;  //Auto generated number for each class
		int classNumber;
		protected String major;
		protected ClassRoom classRoom;
		protected ClassRoomSchedule classRoomSchedule=new ClassRoomSchedule();;
		protected ArrayList<Student> enrolledStudents=new ArrayList<Student>();
		protected boolean isScheduleSet=false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy EEEE");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
		
		
		{
			classNumberCounter++;
			classNumber=classNumberCounter;
		}
		
		
		public static int getClassNumberCounter() {
			return classNumberCounter;
		}

		public static void setClassNumberCounter(int classNumberCounter) {
			UniversityClass.classNumberCounter = classNumberCounter;
		}

		public boolean isScheduleSet() {
			return isScheduleSet;
		}

		public void setScheduleSet(boolean isScheduleSet) {
			this.isScheduleSet = isScheduleSet;
		}

		public int getClassNumber() {
			return classNumber;
		}
		
		public ClassRoom setClassRoom(String classRoomName,int classRoomSeatingCapacity) {
			classRoom=new ClassRoom(classRoomName,classRoomSeatingCapacity);
			return classRoom;
		}
		
		
		public ClassRoom getClassRoom() {
			return classRoom;
		}

		public void setClassRoom(ClassRoom classRoom) {
			this.classRoom = classRoom;
		}

		public UniversityClass(String className){
			this.className=className;
		}	
		
		public String getClassName() {
			return className;
		}


		public void setClassName(String className) {
			this.className = className;
		}
		
		public ClassRoomSchedule setStartDate(Date date){
		   classRoomSchedule.setStartDate(date);
		   return classRoomSchedule;
		}
		
		public ClassRoomSchedule setStartTime(Date time){
			   classRoomSchedule.setStartTime(time);
			   return classRoomSchedule;
			}
		
		public ClassRoomSchedule setEndTime(Date time){
			   classRoomSchedule.setEndTime(time);
			   return classRoomSchedule;
			}
		
		public ClassRoomSchedule setEndDate(Date date){
			   classRoomSchedule.setEndDate(date);
			   return classRoomSchedule;
			}


		public String getMajor() {
			return major;
		}


		public void setMajor(String major) {
			this.major = major;
		}

	
		public ClassRoomSchedule getClassRoomSchedule() {
			return classRoomSchedule;
		}


		public void setClassRoomSchedule(ClassRoomSchedule classRoomSchedule) {
			this.classRoomSchedule = classRoomSchedule;
		}
		
		public void enrollStudent(Student stu){
			enrolledStudents.add(stu);
		}
		
		public ArrayList<Student> getEnrolledStudents() {
			return enrolledStudents;
		}

		public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
			this.enrolledStudents = enrolledStudents;
		}

		public void print(){
			System.out.println("ClassName: "+className);
			System.out.println("Classnumber: " + classNumber);
			System.out.println("Major: "+major);
			System.out.println("ClassroomName: " + classRoom.getClassRoomName()+" Capacity: "+classRoom.getSeatingCapacity());
			System.out.print("Enrolled Studens: ");
			for(Student stu:enrolledStudents){
			System.out.print(stu.getName()+"  ");
			}
			System.out.println();
			if(!isScheduleSet)
				System.out.println("The schedule has not been set for "+className+" class");
			else
				System.out.println("Schedule: "+ timeFormat.format(classRoomSchedule.getStartTime())+" to "+timeFormat.format(classRoomSchedule.getEndTime())+" from "+dateFormat.format(classRoomSchedule.getStartDate())+" to " +dateFormat.format(classRoomSchedule.getEndDate()));
			System.out.println();
		}
		
		
		
		
		

}
