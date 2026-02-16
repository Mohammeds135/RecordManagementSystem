<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Record</title>
</head>
<body>

<h2>Add Record</h2>

<form action="MainServlet" method="post">
    <input type="hidden" name="action" value="add" />

    <label>Record Name:</label>
    <input type="text" name="recordName" required />
    <br><br>

    <label>Category:</label>
    <input type="text" name="category" required />
    <br><br>

    <label>Created Date:</label>
    <input type="date" name="createdDate" required />
    <br><br>

    <label>Description:</label>
    <input type="text" name="description" />
    <br><br>

    <label>Remarks:</label>
    <input type="text" name="remarks" />
    <br><br>

    <input type="submit" value="Add Record" />
</form>

<br>
<a href="index.jsp">Back</a>

</body>
</html>
