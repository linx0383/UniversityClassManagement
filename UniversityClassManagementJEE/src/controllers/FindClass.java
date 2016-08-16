package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UniversityClass;
import models.UniversityClassManagement;

/**
 * Servlet implementation class FindClass
 */
@WebServlet("/FindClass")
public class FindClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		dispatcher=request.getRequestDispatcher("admin/findclass.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String findClassErrorMessage=null;
		String foundMessage=null;
		String classNumber=request.getParameter("classNumber");
		boolean validFormData = true;
		if (classNumber.trim().equals("")) {
					validFormData=false;
					findClassErrorMessage="Class Number is mandatory";
			}
		RequestDispatcher dispatcher = null;
		if (validFormData) {
			int cId=Integer.parseInt(classNumber);
			UniversityClass uc=UniversityClassManagement.findUniversityClassbyNumber(cId);

			if(uc!=null){
				request.setAttribute("classByNumber", uc);
				foundMessage="Class found!";
			}
			else
				findClassErrorMessage="No such class with Class Number "+classNumber;	
		}
		request.setAttribute("findClassErrorMessage", findClassErrorMessage);
		request.setAttribute("foundMessage", foundMessage);
		dispatcher = request.getRequestDispatcher("admin/findclass.jsp");
		dispatcher.forward(request, response);
	}

}
