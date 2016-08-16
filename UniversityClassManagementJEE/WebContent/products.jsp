<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
 boolean validUser = false;
    String user = (String)session.getAttribute("user");
    if(user == null || user.trim().equals("")){
    	validUser = false;
    	request.setAttribute("message", "Please login to access application to view products page");
    
	}else
    {
		validUser = true;
    }%>

<% if(validUser) {%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="2">
				<!-- Place for Header --> <%-- <%@include file="header.jsp" %>  Static--%>
				<jsp:include page="header.jsp" />
			</td>
		</tr>
		<tr>
			<td width="150">
				<!-- Pleace for menu --> <jsp:include page="menu.jsp" />
			</td>
			<td>
				<!-- Place for Content -->
				<h1>Products</h1>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- Place for Footer --> <jsp:include page="footer.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>
<%} else {%>
<jsp:forward page="login.jsp"></jsp:forward>
<%} %>
