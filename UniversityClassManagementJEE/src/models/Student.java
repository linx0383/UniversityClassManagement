package models;


public class Student{
	protected static int idCounter=100000; 
	protected  int id;
	protected String name;
	
	{
		idCounter++;
		id=idCounter;
	}
	
	public static int getIdCounter() {
		return idCounter;
	}
	
	public static void setIdCounter(int idCounter) {
		Student.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Student(String name) {
		this.name = name;
	}
	public void printStudent(){
		System.out.println("Student: "+name+" ID: "+id);
	}

}
