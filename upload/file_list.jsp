<%-- 
    Document   : file-list
    Created on : Oct 5, 2022, 10:55:40 PM
    Author     : ADMIN
--%>

<%@page import="utils.DBUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            tr,td,th{
                padding: 20px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <br><br><br>
    <center>
        <%!
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        %>
        <table border="2" >
            <tr>
                <th>No.</th><th>Job_id</th><th>Email</th><th>can_cv(File Upload)</th>
                <th>Status</th><th>Download</th><th>Delete</th>           
            </tr>
            <%
                con = DBUtils.makeConnection();
                String sql1 = "select * from Candidates";
                ps = con.prepareStatement(sql1);
                rs = ps.executeQuery();
                while (rs.next()) {
            %>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getString(4)%></td>
                <td><%=rs.getInt(5)%></td>              

                <!--nếu isStatus = 0 => chưa accepted CV-->
                <%--<td><c:if test="${<%=rs.getInt(5)%> == 4}"> 
                             <c:out value="a"
                                    a
                                    </c:if></td>--%>
                <td><a href="upload?op=downloadFile&fileName=<%=rs.getString(4)%>">Download</a></td>
                <td><a href="upload?op=deteleFile&fileName=<%=rs.getString(4)%>">Delete</a></td>    
            </tr>
            <%
                }
            %>
        </table><br>
        
        

        <a href="<c:url value='/upload?op=upload_index'/>">Home</a>
    </center>
</body>
</html>
