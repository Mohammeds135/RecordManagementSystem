<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data Record Application</title>
</head>
<body>

<h2>Data Record Management</h2>

<p>
    <a href="addRecord.jsp">Add New Record</a>
</p>

<form action="MainServlet" method="post">
    <input type="hidden" name="action" value="viewAll" />
    <input type="submit" value="View All Records" />
</form>

</body>
</html>
