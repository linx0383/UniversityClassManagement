package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DAO;
import data.DataException;
import models.InvalidUseridException;
import models.UniversityClassManagement;
import models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMessage = null;
		String strUserid = request.getParameter("userid");
		String strPassword = request.getParameter("password");

		boolean validFormData = true;
		boolean validUser = false;
		if (strUserid.trim().equals("")) {
			errorMessage = "Userid is mandatory";
			validFormData = false;
		}
		if (strPassword.trim().equals("")) {
			validFormData = false;
			if (errorMessage == null)
				errorMessage = "Password is mandatory";
			else
				errorMessage = errorMessage + "<br/>" + "Password is mandatory";
		}

		User user = null;
		if (validFormData) {
			// You can read userdetails from database...
			try {
				user = DAO.getUser(strUserid);
				if(user.getPassword().equals(strPassword)){
					validUser = true;
					request.getSession().setAttribute("loggedUser", user);
				}
					
			} catch (Exception e) {
				errorMessage = e.getMessage();
				System.out.println(errorMessage);
			} 
		}

		RequestDispatcher dispatcher = null;
		if (validUser) {
			try {
				DAO.getInformation();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidUseridException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("students", UniversityClassManagement.students);
			request.getSession().setAttribute("classes", UniversityClassManagement.ucs);
			if(user.getUserid().equals("admin")){
				request.getSession().setAttribute("admin", true);
				dispatcher = request.getRequestDispatcher("admin/AdminHome");
			}
			else{
				request.getSession().setAttribute("userid",strUserid);
				request.getSession().setAttribute(strUserid, true);
				dispatcher = request.getRequestDispatcher("userhome.jsp");
			}


		} else {
			request.setAttribute("loginErrorMessage", errorMessage);
			dispatcher = request.getRequestDispatcher("login.jsp");
		}
		dispatcher.forward(request, response);

	}

}
