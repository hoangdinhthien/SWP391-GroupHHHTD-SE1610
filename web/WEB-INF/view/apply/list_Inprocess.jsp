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
        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
    <center>
        <h1>Inprocess Applications</h1> <br>
        <nav class="header__menu">
            <ul>
                <li><a href="<c:url value="apply?op=sortByCanASCInprocess"/>"> Sort by Can Id </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByCanASCInprocess">Can Id Ascending</a></li>
                        <li><a href="apply?op=sortByCanDESCInprocess">Can Id Descending</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/apply?op=sortByScoreASCInprocess"/>"> Sort by Exam Score </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByScoreASCInprocess">Score Ascending</a></li>
                        <li><a href="apply?op=sortByScoreDESCInprocess">Score Descending</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="apply?op=sortByStatusASCInprocess"/>"> Sort by Status </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByStatusASCInprocess">Status Ascending</a></li>
                        <li><a href="apply?op=sortByStatusDESCInprocess">Status Descending</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <br><br>
        <table class="table table-striped" border="1" cellspacing="0" cellpadding="4">   
            <c:choose>
                <c:when test="${not empty listInprocess  }">
                    <thead>
                        <tr>
                            <th>No.</th><th>Can_id</th><th>Job Name</th>
                            <th>Email</th><th>File Upload</th>
                            <th>Exam Score</th>
                            <!--                    <th>Interview Score</th>-->
                            <th>Status</th><th style="text-align: center">Operations</th>    
                        </tr>
                    </thead>
                    <c:forEach var="can" items="${listInprocess}" varStatus="loop">

                        <tr>
                            <td style="text-align: left;"><fmt:formatNumber value="${loop.count}" pattern="000" /></td>
                            <td>${can.id}</td>
                            <td>${can.jobname.job_name}</td>
                            <td>${can.email}</td>
                            <td>${can.cv}</td>
                            <td>${can.score}</td>
                            <!--<td>${ing.score}</td>-->
                            <td><c:choose>
                                    <c:when test="${can.isStatus==1}">
                                        Accepted
                                    </c:when>
                                    <c:when test="${can.isStatus==2}">
                                        Tested
                                    </c:when>
                                    <c:when test="${can.isStatus==3}">
                                        Has Scheduled
                                    </c:when>
                                    <c:when test="${can.isStatus==4}">
                                        Has Been Interviewed
                                    </c:when>
                                    <c:when test="${can.isStatus==5}">
                                        Hired
                                    </c:when>
                                </c:choose>
                            </td>
                            <td style="text-align: center">
                                <a href="apply?op=downloadFile&fileName=${can.cv}">Download</a> |
                                <a href="apply?op=rejectFileInprocess&can_id=${can.id}&email=${can.email}">Reject</a> 

                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </c:when>
                <c:otherwise>
                    <c:out value="NO DATA FOUND"/>
                </c:otherwise>
            </c:choose>
        </table><br>
        <br>
    </center>
</body>
</html>
