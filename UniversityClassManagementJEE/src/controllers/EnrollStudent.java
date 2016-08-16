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
@WebServlet("/EnrollStudent")
public class EnrollStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollStudent() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/enrollstudent.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=null;
		String errorMessage=null;
		String classNumber = request.getParameter("classNumber");
		String studentId = request.getParameter("studentId");

		boolean validFormData = true;
		if (studentId.trim().equals("")) {
			errorMessage = "Student Id is mandatory";
			validFormData = false;
		}
		if (classNumber.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Class Number is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Class Number is mandatory";
		}

		RequestDispatcher dispatcher = null;
		if (validFormData) {
			int sId=Integer.parseInt(studentId);
			int cNo=Integer.parseInt(classNumber);
			Student stu=UniversityClassManagement.findStudentbyId(sId);
			if(stu!=null){
				UniversityClass usc;
				usc=UniversityClassManagement.findUniversityClassbyNumber(cNo);
				if(usc!=null){
					usc.enrollStudent(stu);
			        Statement stmt = null;
						try{
						Connection con=null;
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
						message="The student "+stu.getName()+" has been successfully enrolled in to class "+ usc.getClassName();
						}catch(Exception ex){
							System.out.println(ex.getMessage());
							ex.printStackTrace();
						}
				}
				else{
					errorMessage="The class with number "+cNo+" does not exist!";
				}		
			}
			else{
				errorMessage="The student with ID "+sId+" does not exist!";
			}
		}
		request.setAttribute("Message", message);
		request.setAttribute("errorMessage", errorMessage);
		dispatcher = request.getRequestDispatcher("admin/enrollstudent.jsp");
		dispatcher.forward(request, response);

	}
	}

