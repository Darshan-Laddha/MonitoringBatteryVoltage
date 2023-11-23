<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
	<form action="add_car_and_insert_into_user_information">
	<center>
	    email<input type="text" name="email" value= ${email } readOnly="true"><br>
		password<input type="password" name="password" value=${password } readOnly="true"><nr>
		Car license number<input type="text" name ="car_no"><br>
		Threshold voltage<input type="text" name ="threshold_voltage"><br>
		<input type="submit" value="ADD CAR">
	</center>
	</form>
	<form action="home">
	<center>
		<input type="submit" value="GO TO HOME PAGE"><br>
	</center>
		
	</form>
		
	

</body>
</html>