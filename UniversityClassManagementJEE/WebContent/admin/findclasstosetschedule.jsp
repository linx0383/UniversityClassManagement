<%@page import="models.UniversityClassManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="models.Student,models.UniversityClass,models.ClassRoomSchedule,java.util.Hashtable, java.util.Date, java.text.SimpleDateFormat" %>


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
				<h1>Welcome to Use Class Management System</h1>
				<h3>Please enter the class number for which you would like to set the schedule</h3>
				<form method="get" action="SetSchedule">
					Class Number: <input type="text" name="classNumber"> <br />
					<input type="submit" value="FindClass">
				</form>
				
				<p>Administrator Mode </p>
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