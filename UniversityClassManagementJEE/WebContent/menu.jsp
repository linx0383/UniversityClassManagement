<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="models.User" %>

<%

User user = (User)session.getAttribute("loggedUser");
%>

<h3>Menu</h3>
<a href="index.jsp">Home </a> <br/>
<%
 if(user != null){
%>
<a href="classesList.jsp">List Classes</a> <br/>
<a href="EnrollClassServlet">Enroll Into a Class </a> <br/>

<a href="Logout">Logout</a> <br/>
<%}else{ %>
<a href="Login"> Login</a>
<%} %>
