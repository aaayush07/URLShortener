<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADA</title>
</head>
<body>
<h4>Enter URL for Checking Availabilty</h4>
<form action="Checker" method="post">
<input type="text" name="shortURL">
<input type="submit" value="Check">
</form>
<%  
String shortURL = (String)session.getAttribute("shortURL");
if(shortURL!=null){
	out.println("Short URL is:- " + shortURL + "and available");
}
	%>
<form action="Linker" method="post">
<input type="text" name="longURL">
<input type="Submit" value="GO">
</form>
<%  

String status = (String)session.getAttribute("action");
if(status=="done"){
	String serverName = request.getServerName();
	int port = request.getServerPort();
	String contextPath = request.getContextPath();
	shortURL = "https://"+serverName+":"+port+contextPath+"/"+shortURL;
	out.println(shortURL);
}
shortURL = null;
	%>
</body>
</html>