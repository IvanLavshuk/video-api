<%@ page import="bsu.rfe.lavshuk.video.archive.entity.Director" %>
<%@ page import="bsu.rfe.lavshuk.video.archive.service.DirectorService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.01.2025
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Directors</title>
</head>
<body>
<h1>Directors</h1>


<form action="downloadDirector" method="get">
    <table>
        <tr>
            <td><input type="submit" value="Download"></td>
            <td><a href="home.jsp">Back</a></td>
        </tr>
    </table>
</form>

<table>

    <tr>
        <td>Actor</td>
        <td>Birthdate</td>
    </tr>
    <%
        List<Director> directors = DirectorService.getInstance().getAll();
        int count = 1;
        for (Director director : directors) {
    %>
    <tr>
        <td><%= (count++) + "." + director.getName() + " " + director.getSurname()%>
        </td>
        <td><%= " (" + director.getBirthdate() + ")\n"%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
