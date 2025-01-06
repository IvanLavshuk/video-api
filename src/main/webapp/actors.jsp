<%@ page import="bsu.rfe.lavshuk.video.archive.entity.Actor" %>
<%@ page import="bsu.rfe.lavshuk.video.archive.service.ActorService" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.01.2025
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actors</title>
</head>
<body>
<h1>Actors</h1>


<form action="downloadActor" method="get">
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
        List<Actor> actors = ActorService.getInstance().getAll();
        int count = 1;
        for (Actor actor : actors) {
    %>
    <tr>
        <td><%= (count++) + "." + actor.getName() + " " + actor.getSurname()%>
        </td>
        <td><%= " (" + actor.getBirthdate() + ")\n"%>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
