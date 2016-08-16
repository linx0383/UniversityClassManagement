<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="models.User, models.UniversityClassManagement" %>
<%
User user = (User)session.getAttribute("loggedUser");
//System.out.println(user.getUserid());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Class Management System</title>
</head>
<body>
<table>
		<tr>
			<td colspan="2">
			<!-- Place for Header -->
			<jsp:include page="header.jsp"/>
			</td>
		</tr>
		<tr>
			<td width="150"> 
				<!-- Place for menu -->
				<jsp:include page="menu.jsp"/>
			</td>
			<td colspan="3">
				<!-- Place for Content -->
				<h1>Welcome to Use Class Management System</h1>
				<%
				if(user==null){ %> 
				<h3>Please Login to access other pages..... </h3>
					<%}
				else if(user.getUserid().equals("admin")){
					%><jsp:forward page="admin/index.jsp"></jsp:forward><%
				}else{%>
					<h3>Welcome, <%=UniversityClassManagement.findStudentbyId(Integer.parseInt((String)request.getSession().getAttribute("userid"))).getName()%> !</h3>
				<%}%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<!-- Place for Footer -->
			<jsp:include page="footer.jsp"/>
			</td>
		</tr>
	</table>
</body>
</html>