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
<!DOCTYPE html>
<%!
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <br><br><br><br>
        <%--
        <a href="listcv?op=list">Applications List</a><br/>
        <a href="listcv?op=userviewlist">Applications List 2</a><br/>
        <a href="upload?op=upload_index">Applications List 3</a><br><br>
        --%>
    <center>

        <form action="upload" method="post" enctype="multipart/form-data">

            <table width="400px" align="center" border=2>
                <tr>
                    <td align="center" colspan="2">Form Details</td>
                </tr>
                <tr>
                    <td>Select File :</td>
                    <td>
                        <input type="file" required="" name="file">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="op" value="uploadFile"></td>
                </tr>
            </table>
            <table border="2" id="1">


                <tr>
                    <th>Can_id</th><th>Job_id</th><th>Email</th><th>can_cv(File Upload)</th><th>Status</th><th style="text-align: center">Operations</th>               
                </tr>
                <h1>Table 1 (All)(HR view, download,accept,reject)</h1>
                <%
                    Connection con = DBUtils.makeConnection();
                    String sql = "select can_id,job_id,email,can_cv,isStatus from candidates";

                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getInt(5)%></td>
                    <td><a href="upload?op=yesup&can_id=<%=rs.getString(1)%>">Accept</a> |
                        <a href="upload?op=deleteFile&can_id=<%=rs.getString(1)%>">Delete</a> |
                        <a href="upload?op=downloadFile&fileName=<%=rs.getString(4)%>">Download</a></td>

                </tr>
                <%
                    }

                %>

            </table><br>

            <table border="2" id="2">
                <tr>
                    <th>Can_id</th><th>Job_id</th><th>can_cv(File Upload)</th><th>Status</th><th style="text-align: center">Operations</th>               
                </tr>
                <h3>Applied Job(Only 0) (User view, download,delete)</h3>
                <%String sql2 = "select can_id,job_id,can_cv,isStatus from candidates where isStatus = 0";

                    ps = con.prepareStatement(sql2);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getInt(4)%></td>
                    <td><a href="upload?op=downloadFile&fileName=<%=rs.getString(3)%>">Download</a> |
                        <a href="upload?op=deleteFile&can_id=<%=rs.getString(1)%>">Delete</a></td>

                </tr>
                <%
                    }

                %>
            </table><br>

            <table border="2" id="3">
                <tr>
                    <th>Can_id</th><th>Job_id</th><th>Email</th><th>can_cv(File Upload)</th><th>Status</th><th style="text-align: center">Operations</th>               
                </tr>
                <h1>Table 3 (1-4)</h1>
                <%                    String sql3 = "select can_id,job_id,email,can_cv,isStatus from candidates where isStatus > 0 and isStatus < 5";
                    ps = con.prepareStatement(sql3);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getInt(5)%></td>
                    <td><a href="upload?op=yesup&can_id=<%=rs.getString(1)%>">Accept</a> |
                        <a href="upload?op=deleteFile&can_id=<%=rs.getString(1)%>">Delete</a> |
                        <a href="upload?op=downloadFile&fileName=<%=rs.getString(4)%>">Download</a></td>
                </tr>
                <%
                    }

                %>
            </table><br>

            <table border="2" id="4">
                <tr>
                    <th>Can_id</th><th>Name</th><th>Email</th><th>Job</th><th>Major</th><th>CV</th><th>Score</th><th style="text-align: center">Operations</th>               
                </tr>
                <h1>Recruitment Board  (Only 4)</h1>
                <%                    String sql4 = "SELECT c.email, j.job_name, m.major_name, c.can_cv , i.inter_score  FROM Interviewing i "
                            + "LEFT JOIN Candidates c ON i.can_id = c.can_id "
                            + "Left JOIN User u ON c.email = u.email "
                            + "Left JOIN Jobs j ON j.job_id = c.job_id "
                            + "Left JOIN Major m ON m.major_id = j.major_id "
                            + " where isStatus = 4";

                    ps = con.prepareStatement(sql4);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getInt(5)%></td>

                    <%-- <td><a href="upload?op=yesup&can_id=<%=rs.getString(1)%>">Accept</a> |
                         <a href="upload?op=deleteFile&can_id=<%=rs.getString(1)%>">Delete</a> |
                         <a href="upload?op=downloadFile&fileName=<%=rs.getString(6)%>">Download</a></td>
                 </tr> 
                    --%>
                    <%
                        }

                    %>
            </table><br>

        </form>

        <h1>Nếu ko có Dữ liệu thì hiện "No Data Found"</h1>
        <br><a href="file-list.jsp">View List</a>
    </center>
</body>

</html>
