<%@ page import="java.util.List" %>
<%@ page import="com.wipro.records.bean.DataRecordBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Records</title>
</head>
<body>

<h2>All Records</h2>

<%
    List<DataRecordBean> records =
        (List<DataRecordBean>) request.getAttribute("records");

    if (records != null && !records.isEmpty()) {
        for (DataRecordBean r : records) {
%>

<div style="margin-bottom:15px; padding:10px; border:1px solid #ccc;">
    <strong>Record ID:</strong> <%= r.getRecordId() %><br>
    <strong>Record Name:</strong> <%= r.getRecordName() %><br>
    <strong>Category:</strong> <%= r.getCategory() %><br>
    <strong>Created Date:</strong> <%= r.getCreatedDate() %><br>
    <strong>Description:</strong> <%= r.getDescription() %><br>
    <strong>Remarks:</strong> <%= r.getRemarks() %><br>
</div>

<%
        }
    } else {
%>

<p>No Records Found</p>

<%
    }
%>

<br>
<a href="index.jsp">Back</a>

</body>
</html>
