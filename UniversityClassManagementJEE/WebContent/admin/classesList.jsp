<%@page import="models.UniversityClassManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="models.Student,models.UniversityClass,models.ClassRoomSchedule,java.util.Hashtable, java.util.Date, java.text.SimpleDateFormat" %>
<%
	 Hashtable<Integer,UniversityClass> classes=(Hashtable<Integer,UniversityClass>)session.getAttribute("classes");
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
				<h1>Classes List</h1><br/>
				<%					
				if(UniversityClassManagement.ucs.isEmpty()){ %>//check whether there are any elements
					<h3>There are no available classes </h3>
				<%
				}else{%>
					<table>
					<tr>
					<td colspan="6"><b>All Classes Information</b></td>
					</tr>
					<tr>
						<td><b>Class Name</b></td>
						<td><b>Class Number</b></td>
						<td><b>Major</b></td>
						<td><b>Classroom</b></td>
						<td><b>Seats</b></td>
						<td><b>Schedule</b></td>
						<td><b>Enrolled Students</b></td>
					</tr>
					<%
					 for(UniversityClass uc: classes.values()){
					 uc.getClassNumber();%>
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
					<%}%>
					 </table>
					 
					 <h3>Total no of Classes :<%=classes.size()%>  </h3>
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