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
import models.Student;
import models.UniversityClass;
import models.UniversityClassManagement;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/EnrollClassServlet")
public class EnrollClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("enrollaclass.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String enrollMessage = null;
		String classNumber = request.getParameter("classNumber");
		boolean validFormData = true;
		if (classNumber.trim().equals("")) {
			enrollMessage = "Userid is mandatory";
			validFormData = false;
		}
//		if(UniversityClassManagement.ucs.containsKey(1001)==true){
//			System.out.println(UniversityClassManagement.ucs.get(1001).getClassName());
//		}
//		else{
//			System.out.println(UniversityClassManagement.ucs.get(1001).getClassName());
//		}
		if(validFormData==true){
			if(UniversityClassManagement.getUcs().containsKey(Integer.parseInt(classNumber))==true){
				UniversityClass uc=UniversityClassManagement.getUcs().get(Integer.parseInt(classNumber));
				String userid=(String)request.getSession().getAttribute("userid");
				Student stu=UniversityClassManagement.getStudents().get(Integer.parseInt(userid));
					uc.enrollStudent(stu);
			        Statement stmt = null;
						try{
						Connection con=DBManager.getConnection();
						String sql="update universityclass set enrolledstudent='"+stu.getName()+"' where cid="+uc.getClassNumber();
						String sql2="update universityclass set enrolledstudent=concat(enrolledstudent,'"+", "+stu.getName()+"') where cid="+uc.getClassNumber();
						stmt=con.createStatement();
						if(uc.getEnrolledStudents().size()==1){
							stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
							}
						else{
							stmt.executeUpdate(sql2,Statement.RETURN_GENERATED_KEYS);
						}
						stmt.close();
						con.close();
						enrollMessage="The student "+stu.getName()+" has been successfully enrolled into class "+uc.getClassName();
						}catch(Exception ex){
							System.out.println(ex.getMessage());
							ex.printStackTrace();
						}
				
			
			}
			else
				enrollMessage="The class with number "+classNumber+" does not exist!";
			
		}
		request.setAttribute("enrollMessage", enrollMessage);
		request.getRequestDispatcher("enrollaclass.jsp").forward(request, response);
	}
	
}
