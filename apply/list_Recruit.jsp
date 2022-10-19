<%-- 
    Document   : index
    Created on : Oct 5, 2022, 4:44:35 PM
    Author     : ADMIN
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <br><br><br><br>
    <center>
        <h1>List Recruitment</h1> <br>
        <nav class="header__menu">
            <ul>
                <li><a href="<c:url value="apply?op=sortASCRecruit"/>"> Sort by Can Id </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortASCRecruit">Can Id Ascending</a></li>
                        <li><a href="apply?op=sortASCRecruit">Can Id Descending</a></li>
                    </ul>
                </li>
            </ul>
        </nav>

                <table class="table table-striped" border="1" cellspacing="0" cellpadding="4">
                    <thead>
                        <tr>
                            <th>No.</th><th>Can_id</th><th>Job_id</th>
                            <th>Email</th><th>File Upload</th>
                            <th>Exam Score</th>
                            <th>Interview Score</th>
                            <th style="text-align: center">Operations</th>    
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="can" items="${list4}" varStatus="loop">
                            <tr>
                                <td style="text-align: left;"><fmt:formatNumber value="${loop.count}" pattern="000" /></td>
                                <td>${can.id}</td>
                                <td>${can.jobId}</td>
                                <td>${can.email}</td>
                                <td>${can.cv}</td>
                                <td>${can.score}</td>
                                <td><c:choose>
                                        <c:when test="${can.isStatus==5}">
                                            Hired
                                        </c:when></c:choose>
                                    <td style="text-align: center">
                                        <a href="apply?op=downloadFile&fileName=${can.cv}">Download</a> |
                                    <a href="apply?op=yesup&can_id=${can.id}">Accept</a> |
                                    <a href="apply?op=deleteFile&can_id=${can.id}">Reject</a> 
                                </td>


                            </tr>
                        </c:forEach>
                    </tbody>
                </table><br>



                <br>
                <%--<a href="<c:url value='/upload?op=upload_index'/>">Home</a>--%>
                </center>

                </body>


                </html>
