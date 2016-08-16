<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				<%	String errorMessage = (String)request.getAttribute("errorMessage");
					if(errorMessage != null && errorMessage.trim().equals("") == false){
						out.println("<b>" + errorMessage + "</b> <br/>");}
				%>
							
				<%String Message=(String)request.getAttribute("Message");
				if(Message != null && Message.trim().equals("") == false){
					out.println("<b>" + Message + "</b> <br/>");
				}%>
				<form method="post" action="SetSchedule">
					Start Date (yyyy-MM-dd): <input type="text" name="startDate"> <br />
					Start Time (hh:mm:ss): <input type="text" name="startTime"> <br />
					End Time (hh:mm:ss): <input type="text" name="endTime"> <br />
					End Date (yyyy-MM-dd): <input type="text" name="endDate"> <br />
					<input type="submit" value="Set Schedule">
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