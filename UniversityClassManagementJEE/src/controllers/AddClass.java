package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DAO;
import data.DBManager;
import data.DataException;
import models.InvalidInputNameException;
import models.InvalidUseridException;
import models.UniversityClass;
import models.UniversityClassManagement;
import models.User;

/**
 * Servlet implementation class AddClass
 */
@WebServlet("/AddClass")
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int step=0;
		//step=(Integer)request.getAttribute("addClass");
		//request.setAttribute("addClass",1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/addclass.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage=null;
		String className = request.getParameter("className");
		String major = request.getParameter("major");
		String classroom = request.getParameter("classroom");
		String seats=request.getParameter("seats");

		boolean validFormData = true;
		if (className.trim().equals("")) {
			errorMessage = "Class Name is mandatory";
			validFormData = false;
		}
		if (major.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Major is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Major is mandatory";
		}
		if (classroom.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Classroom is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Classroom is mandatory";
		}
		if (seats.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Number of Student is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Number of Student is mandatory";
		}


		RequestDispatcher dispatcher = null;
		if (validFormData) {
			UniversityClass uc=UniversityClassManagement.createUniversityClass(className);
			uc.setMajor(major);
			uc.setClassRoom(classroom, Integer.parseInt(seats));
			uc.setScheduleSet(false);
	        Statement stmt = null;
			try{
			Connection con=null;
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
			dispatcher = request.getRequestDispatcher("admin/addclass.jsp");

		} else {
			request.setAttribute("Message", "The class has been successfully added into the list");
			dispatcher = request.getRequestDispatcher("admin/addclass.jsp");
		}
		dispatcher.forward(request, response);

	}

}
