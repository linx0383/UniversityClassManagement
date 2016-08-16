<%-- <%@page import="beans.User"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enroll a Class</title>
</head>
<body>
<table>
		<tr>
			<td colspan="2">
			<!-- Place for Header -->
			<%-- <%@include file="header.jsp" %>  Static--%>
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
				<h1>Please Enter the Class Number to Enroll</h1>
			
				<%
					String message = (String)request.getAttribute("enrollMessage");
					if(message != null && message.trim().equals("") == false){
						out.println("<b>" + message + "</b> <br/>");
					}
				%>
				<form method="post">
					Class Number: <input type="text" name="classNumber"> <br />
					<input type="submit" value="Enroll">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<jsp:include page="footer.jsp"/>
			</td>
		</tr>
	</table>
</body>
</html>