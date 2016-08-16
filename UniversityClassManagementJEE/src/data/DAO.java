package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.InvalidUseridException;
import models.Student;
import models.UniversityClass;
import models.UniversityClassManagement;
import models.User;

public class DAO {
	
	
	public static User getUser(String userid) throws DataException, InvalidUseridException{
		Connection con = null;
		User user = null;
		try {
			con = DBManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select cast(stid as char(50)) as stid, spassword from student where stid='"+userid+"' union select stid,spassword from administrator where stid='"+userid+"'");
			if(rs.next()) {
				user = new User(userid,rs.getString(2));
				return user;
			}
			else
				throw new InvalidUseridException();
		} catch (SQLException ex) {
			throw new DataException(ex.getMessage());
		} catch (ClassNotFoundException e) {
			throw new DataException("The driver class is not available");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void getInformation() throws DataException, InvalidUseridException{
		UniversityClass readuc;
		Connection con = null;
		try{
		con=DBManager.getConnection();
		
		Statement stmt2=con.createStatement();
		ResultSet rs2=stmt2.executeQuery("SELECT * FROM student");
		while(rs2.next()){
			UniversityClassManagement.addStudentIntoManagement(rs2.getString("sname"));
		}
		
		
		Statement stmt=con.createStatement();		
		ResultSet rs=stmt.executeQuery("SELECT * FROM universityclass inner join classroom on universityclass.cid=classroom.cid");
		while(rs.next()){
			readuc=UniversityClassManagement.createUniversityClass(rs.getString("cname"));
			readuc.setMajor(rs.getString("major"));
			readuc.setClassRoom(rs.getString("crname"), rs.getInt("seat"));
			String estring=rs.getString("enrolledstudent");
			if(!rs.wasNull()){
				String delimiter=", ";
				String[] temp=estring.split(delimiter);
				for(int i=0;i<temp.length;i++){
					Student student=UniversityClassManagement.findStudentbyName(temp[i]);
					readuc.enrollStudent(student);
				}
			}
			else
				continue;	
		}
		
		Statement stmt3=con.createStatement();
		ResultSet rs3=stmt3.executeQuery("SELECT * FROM classroomschedule");
		while(rs3.next()){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat time = new SimpleDateFormat("hh:mm:ss");

			UniversityClass uc=UniversityClassManagement.findUniversityClassbyNumber(rs3.getInt("scheduleid"));
			
			Date convertedStartDate=df.parse(rs3.getString("startdate"));
			Date convertedEndDate=df.parse(rs3.getString("enddate"));
			Date convertedStartTime=time.parse(rs3.getString("starttime"));
			Date convertedEndTime=time.parse(rs3.getString("endtime"));
			uc.setStartDate(convertedStartDate);
			uc.setEndDate(convertedEndDate);
			uc.setStartTime(convertedStartTime);
			uc.setEndTime(convertedEndTime);
			uc.setScheduleSet(true);
		}
		con.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
