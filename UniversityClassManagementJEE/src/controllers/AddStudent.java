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
import models.Student;
import models.UniversityClass;
import models.UniversityClassManagement;


/**
 * Servlet implementation class AddClass
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/addstudent.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage=null;
		String message=null;
		String studentName = request.getParameter("studentName");

		boolean validFormData = true;
		if (studentName.trim().equals("")) {
			errorMessage = "Class Name is mandatory";
			request.setAttribute("errorMessage", errorMessage);
			validFormData = false;
		}


		RequestDispatcher dispatcher = null;
		if (validFormData) {
			UniversityClassManagement.addStudentIntoManagement(studentName);
	        Statement stmt = null;
			try{
			Connection con=null;
			con=DBManager.getConnection();
			String sql="insert into student(sname) values('"+studentName+"')";
			stmt=con.createStatement();	
			stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.close();
			con.close();
			System.out.println("Student "+studentName+" has been successfully added with Id "+Student.getIdCounter());
			}catch(Exception ex){
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			message="Student "+studentName+" has been successfully added with Id "+Student.getIdCounter();

		}
		request.setAttribute("Message", message);
		dispatcher = request.getRequestDispatcher("admin/addstudent.jsp");
		dispatcher.forward(request, response);

	}

}
