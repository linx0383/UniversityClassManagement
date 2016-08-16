<%@page import="models.UniversityClassManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="models.Student, java.util.Hashtable" %>
<%
	 Hashtable<Integer,Student> students=(Hashtable<Integer,Student>)session.getAttribute("students");
%>

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
				<h1>Students List</h1><br/>
				<%					
				if(UniversityClassManagement.students.isEmpty()){ %>//check whether there are any elements
					<h3>There are no available students added </h3>
				<%
				}else{%>
					<table>
					<tr>
					<td colspan="2">All Students Information</td>
					</tr>
					<tr>
						<td>Student</td>
						<td>Id</td>
					</tr>
					<%
					 for(Student student: students.values()){
					 student.getName();%>
					 <tr>
						<td><%=student.getName()%></td>
						<td><%=student.getId()%></td>
					</tr>
					<%}%>
					 </table>
					 
					 <h3>Total no of Students :<%=students.size()%>  </h3>
				<% }%>
				
				<p>Administrator Mode</p>
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