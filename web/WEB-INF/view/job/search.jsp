<%-- 
    Document   : search
    Created on : Oct 6, 2022, 4:47:37 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--<form action="<c:url value="/job"/>">
            Search: 
            <input type="text" name="search" value=""/><br/>
            <button type="submit" name="op" value="search">Search</button>
        </form>-->
        <div class="container">

            <c:if test="${not empty info && role == 'HR Staff'}">
                <a class="link" href="<c:url value="/job?op=add_job"/>">Add a new job</a>
            </c:if>
                
            <form action="<c:url value="/job"/>">
                <ul style="position: relative">
                    <div style="display: flex;">
                        <div class="input-group">

                            <li class="input-group-text">
                                Major
                            </li>   
                            <li style="list-style-type: none;">
                                <select name="major_id" >
                                    <option value="0" >All</option>
                                    <option value="1"<c:if test="${major_id==1}">selected="true"</c:if>>Marketing</option>
                                    <option value="2"<c:if test="${major_id==2}">selected="true"</c:if>>Logistic</option>
                                    <option value="3"<c:if test="${major_id==3}">selected="true"</c:if>>Data Science and Analytics</option>
                                    <option value="4"<c:if test="${major_id==4}">selected="true"</c:if>>Information Technology</option>
                                    <option value="5"<c:if test="${major_id==5}">selected="true"</c:if>>Graphic Design</option>
                                    <option value="6"<c:if test="${major_id==6}">selected="true"</c:if>>Engineering</option>
                                    <option value="7"<c:if test="${major_id==7}">selected="true"</c:if>>Risk Manager</option>
                                    <option value="8"<c:if test="${major_id==8}">selected="true"</c:if>>Economic Finance</option>
                                    <option value="9"<c:if test="${major_id==9}">selected="true"</c:if>>Business Intelligence and Development</option>
                                    <option value="10"<c:if test="${major_id==10}">selected="true"</c:if>>Operations</option>
                                    </select>
                                </li>
                            </div>
                            <div class="input-group">

                                <li class="input-group-text">
                                    Level:  
                                </li>   
                                <li style="list-style-type: none;">
                                    <select name="level_id">
                                        <option value="0"selected="true">All</option>
                                        <option value="1"<c:if test="${level_id==1}">selected="true"</c:if>>Intern</option>
                                    <option value="2"<c:if test="${level_id==2}">selected="true"</c:if>>Fresher</option>
                                    <option value="3"<c:if test="${level_id==3}">selected="true"</c:if>>Junior</option>
                                    <option value="4"<c:if test="${level_id==4}">selected="true"</c:if>>Senior</option>
                                    <option value="5"<c:if test="${level_id==5}">selected="true"</c:if>>Manager</option>
                                    <option value="6"<c:if test="${level_id==6}">selected="true"</c:if>>Leader</option>
                                    </select>
                                </li>
                            </div>
                            <div class="input-group">
                                <li class="input-group-text">
                                    Salary:  
                                </li>   
                                <li style="list-style-type: none;">
                                    <select name="salary">
                                        <option value="0">All</option>
                                        <option value="1"<c:if test="${salary==1}">selected="true"</c:if>> less than 1000 </option>
                                    <option value="2"<c:if test="${salary==2}">selected="true"</c:if>> 1000 to 5000</option>
                                    <option value="3"<c:if test="${salary==3}">selected="true"</c:if>> more than 5000</option>
                                    </select>
                                </li>
                            </div>
                        </div>
                        <span style="display: inline-block;position: absolute;top: 5px;right:0"><button type="submit" name="op" value="filter_job">Search</button></span>
                    </ul>
                </form>

            </div>
        <c:if test="${empty list}">
            <h3>No result!</h3>
        </c:if>
        <c:if test="${not empty list}">
            <div class="container" style="margin: 5% 0% 5% 0%; ">
                <div class="col-md-6 ">
                    <c:forEach var="job" items="${list}">
                        <div style="background-color: #59c5dc; border-radius: 10px; margin-bottom: 50px;padding-left: 20px;"onclick="#">
                            <h3 style="text-align: left;color: black">
                                Job Name: ${job.job_name}
                            </h3> <br/>
                            <div style="text-align: left; width: 100%" >
                                <p style="display: inline-block; margin-right: 30px;">
                                    Job ID: ${job.job_id} 
                                </p>    
                                <p style="display: inline-block; margin-right: 30px;">
                                    Major ID: ${job.major_id} 
                                </p>    
                                <p style="display: inline-block; margin-right: 30px;">
                                    Vacancy: ${job.job_vacancy} 
                                </p>
                            </div>
                            <p style="text-align: left;">
                                Description: ${job.job_description} <br/>
                            </p>
                            <div style="text-align: left; width: 100%">
                                <p style="display: inline-block; margin-right: 30px">
                                    Level ID: ${job.level_id} <br/>
                                </p>
                                <p style="display: inline-block;">
                                    Salary: ${job.salary} <br/>
                                </p>
                            </div>
                            <p style="text-align: left;">
                                Post Date: ${job.post_date} <br/>
                            </p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </body>
</html>
