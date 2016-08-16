package models;

/*
 * Each class room has certain characteristics
 * like seating capcatity
 * Projector availability etc.
 */
public class ClassRoom{
	protected String classroomName;
	protected int seatingCapacity;
	
	public String getClassRoomName() {
		return classroomName;
	}
	public void setClassRoomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public ClassRoom(String classroomName, int seatingCapacity) {
		this.classroomName = classroomName;
		this.seatingCapacity = seatingCapacity;
	}
	

}
