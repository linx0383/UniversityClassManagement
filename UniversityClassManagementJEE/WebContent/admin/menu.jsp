<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%


models.User user = (models.User)session.getAttribute("loggedUser");
boolean validUser = false;
if(user!= null && user.getUserid().equals("admin")){
	validUser = true;
}
%>

<h3>Menu</h3>
<a href="index.jsp"> Home </a> <br/>

<%
 if(validUser){
%>
<a href="AddClass">1. Add Class </a> <br/>
<a href="ListClasses">2. List Classes</a> <br/>
<a href="AddStudent">3. Add Student </a> <br/>
<a href="FindClass">4. Find a Class</a> <br/>
<a href="FindClasstoSetSchedule">5. Set Schedule for a Class </a> <br/>
<a href="EnrollStudent">6. Enroll Student</a> <br/>
<a href="ListStudents">7. List Students </a><br/>
<a href="Logout">8. Logout</a> <br/>

<%}else{ %>
<a href="Login"> Login</a>
<%} %>
