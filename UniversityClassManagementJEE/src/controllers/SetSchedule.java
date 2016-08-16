package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DBManager;
import data.DataException;
import models.InvalidUseridException;
import models.UniversityClass;
import models.UniversityClassManagement;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SetSchedule")
public class SetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UniversityClass uc=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMessage=null;
		String classnumber=request.getParameter("classNumber");
		if(classnumber!=null&&classnumber.trim().equals("")==false){
			int cNo=Integer.parseInt(classnumber);
			uc=UniversityClassManagement.findUniversityClassbyNumber(cNo);
			if(uc!=null){
				request.setAttribute("class", uc);
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/setschedule.jsp");
				dispatcher.forward(request, response);
		}
		}
		else{
			errorMessage="No such class "+classnumber+" exist!";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/findclasstosetschedule.jsp");
			dispatcher.forward(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMessage = null;
		String message=null;
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String endDate = request.getParameter("endDate");


		boolean validFormData = true;
		if (startDate.trim().equals("")) {
			errorMessage = "Start date is mandatory";
			validFormData = false;
		}
		if (startTime.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Start time is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Start time is mandatory";
		}
		if (endTime.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "End time is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "End time is mandatory";
		}
		if (endDate.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "End date is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "End date is mandatory";
		}

		if (validFormData) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat time = new SimpleDateFormat("hh:mm:ss");
			try {
				uc.setStartDate(df.parse(startDate));
				uc.setStartTime(time.parse(startTime));
				uc.setEndTime(time.parse(endTime));
				uc.setEndDate(df.parse(endDate));
				Connection con=null;
				con=DBManager.getConnection();
				String sql="insert into classroomschedule(scheduleid,startdate,enddate,starttime,endtime) values("+uc.getClassNumber()+",'"+startDate+"','"+endDate+"','"+startTime+"','"+endTime+"')";
				Statement stmt=null;
				stmt=con.createStatement();	
				stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.close();
				con.close();
				message="The Schedule has been successfully set for "+uc.getClassName()+"("+uc.getClassNumber()+") class!";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			errorMessage="Invalid date or time format!";
		}
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("Message", message);
			request.getRequestDispatcher("admin/schedulesuccessfullyset.jsp").forward(request,response);

	}

}
