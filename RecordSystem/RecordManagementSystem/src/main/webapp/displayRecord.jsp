<%@ page import="com.wipro.records.bean.DataRecordBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Record Details</title>
</head>
<body>

<h2>Record Details</h2>

<%
    DataRecordBean record =
        (DataRecordBean) request.getAttribute("record");

    if (record != null) {
%>

Record ID: <%= record.getRecordId() %><br><br>
Record Name: <%= record.getRecordName() %><br><br>
Category: <%= record.getCategory() %><br><br>
Created Date: <%= record.getCreatedDate() %><br><br>
Description: <%= record.getDescription() %><br><br>
Remarks: <%= record.getRemarks() %><br><br>

<%
    } else {
%>

<p>No Record Found</p>

<%
    }
%>

<br>
<a href="index.jsp">Bac
