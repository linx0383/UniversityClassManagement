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
				<%	String findClassErrorMessage = (String)request.getAttribute("findClassErrorMessage");
					String foundMessage = (String)request.getAttribute("foundMessage");
					if(findClassErrorMessage != null && findClassErrorMessage.trim().equals("") == false){
						out.println("<b>" + findClassErrorMessage + "</b> <br/>");}
					if(foundMessage!=null){
						UniversityClass uc=(UniversityClass)request.getAttribute("classByNumber");
						%><table>
						<tr>
						<td colspan="6">Class Information</td>
						</tr>
						<tr>
							<td>Class Name</td>
							<td>Class Number</td>
							<td>Major</td>
							<td>Classroom</td>
							<td>Seats</td>
							<td>Schedule</td>
							<td>Enrolled Students</td>
						</tr>
						<tr>
						<td><%=uc.getClassName()%></td>
						<td><%=uc.getClassNumber()%></td>
						<td><%=uc.getMajor()%></td>
						<td><%=uc.getClassRoom().getClassRoomName() %></td>
						<td><%=uc.getClassRoom().getSeatingCapacity()%></td>
						<%if(!uc.isScheduleSet()){%>
						<td>The schedule has not been set </td>
						<%}else{
								SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy EEEE");
								SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss a");
								ClassRoomSchedule schedule=uc.getClassRoomSchedule();%>
						<td><%=timeFormat.format(schedule.getStartTime())%> to <%=timeFormat.format(schedule.getEndTime())%> from <%=dateFormat.format(schedule.getStartDate())%> to <%=dateFormat.format(schedule.getEndDate())%></td>
					<%}%>
						<td><%	for(Student stu:uc.getEnrolledStudents()){
								out.print(stu.getName()+"  ");}%>
					</tr>
					</table>
					<%}
				%>
				<form method="post" action="FindClass">
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