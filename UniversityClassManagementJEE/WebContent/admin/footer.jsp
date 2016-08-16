<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Date, java.text.SimpleDateFormat,java.text.DateFormat" %>


<p style="font-size: 2; font-family: sans-serif;" align="right"> Developed by Frank Lin</p>
<%DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//get current date time with Date()
Date date = new Date(); %>
 <h3> <%=dateFormat.format(date)%></h3>
<img src="images/logo.png">