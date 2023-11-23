<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action="show_details" >
		<center>
			Enter the data in YYYY-MM-DD format<br>
			From:<input type="text" name="FromDate"><br>
			To:<input type="text" name="ToDate"><br>
			Car number:<input type="text" name="Car_no"><br>
			email:<input type="text" name="email" value=${email} readOnly="true" ><br>
		<input type="submit">
		</center>
		
	 </form>

</body>
</html>