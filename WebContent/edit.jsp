<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Colour CRUD Operation</title>
</head>
<body>
	<h1>Edit Colour Details</h1>
	<form action="SaveData" method="post">
		<input type="hidden" name="id" value="${colour.id}"> Colour: <input
			type="text" name="colour" value="${colour.colour}"> Contrast:
		<input type="text" name="contrast" value="${colour.contrast}">
		<input type="submit" value="Save"> <br />
	</form>
	<br />
	<br />
	<br />
	<a href="ViewAllNameServlet">View the complete list</a>
</body>
</html>