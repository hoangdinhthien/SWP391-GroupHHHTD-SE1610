<%-- 
    Document   : index
    Created on : Oct 5, 2022, 4:44:35 PM
    Author     : ADMIN
--%>

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
    <center>
        <c:if test="${not empty Reject}">
            <div class="alert alert-success alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>${Reject}!</strong>
            </div>
        </c:if>
        <c:if test="${not empty Accept}">
            <div class="alert alert-success alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>${Accept}!</strong> 
            </div>
        </c:if>
        <h1>List Recruitment</h1> <br>
        <nav class="header__menu">
            <ul>
                <li><a href="<c:url value="apply?op=sortByCanASCRecruit"/>"> Sort by Can Id </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByCanASCRecruit">Can Id Ascending</a></li>
                        <li><a href="apply?op=sortByCanDESCRecruit">Can Id Descending</a></li>
                    </ul>
                </li>
                <%--<li><a href="<c:url value="/apply?op=sortByJobASCRecruit"/>"> Sort by Job Id </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByJobASCRecruit">Job Id Ascending</a></li>
                        <li><a href="apply?op=sortByJobDESCRecruit">Job Id Descending</a></li>
                    </ul>
                </li>--%>
                <li><a href="<c:url value="/apply?op=sortByScoreASCRecruit"/>"> Sort by Exam Score </a>
                    <ul class="header__menu__dropdown">
                        <li><a href="apply?op=sortByScoreASCRecruit">Score Ascending</a></li>
                        <li><a href="apply?op=sortByScoreDESCRecruit">Score Descending</a></li>
                    </ul>
                </li>

            </ul>
        </nav>
        <br><br>

        <table class="table table-striped" border="1" cellspacing="0" cellpadding="4">
            <c:choose>
                <c:when test="${ not empty list4 }">
                    <thead>
                        <tr>
                            <th>No.</th><th>Can_id</th><th>Job Name</th>
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
                                <%--<td>${can.jobId}</td>--%>
                                <td>${can.jobname.job_name}</td>
                                <td>${can.email}</td>
                                <td>${can.cv}</td>
                                <td>${can.score}</td>
                                <td>${can.interid.score}</td>
                                <!--                                <td><c:choose>
                                    <c:when test="${can.isStatus==5}">
                                        Hired
                                    </c:when></c:choose></td>-->
                                    <td style="text-align: center">
                                        <a href="apply?op=downloadFile&fileName=${can.cv}">Download</a> |
                                    <a href="apply?op=yesupRecruit&can_id=${can.id}&email=${can.email}&job_name=${can.jobname.job_name}">Accept</a> |
                                    <a href="apply?op=rejectFileRecruit&can_id=${can.id}&email=${can.email}&job_name=${can.jobname.job_name}">Reject</a> 
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
        <%--<a href="<c:url value='/upload?op=upload_index'/>">Home</a>--%>
    </center>

</body>


</html>
