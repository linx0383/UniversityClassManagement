<%@page import="models.UniversityClassManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
				<h1>Students List</h1>
				
				<p>Administrator Mode general</p>
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