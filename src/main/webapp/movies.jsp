<%@ page import="bsu.rfe.lavshuk.video.archive.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="bsu.rfe.lavshuk.video.archive.service.MovieService" %>
<%@ page import="bsu.rfe.lavshuk.video.archive.dao.MovieDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.01.2025
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies</title>
</head>
<body>
<h1>Movies</h1>
<form action="downloadMovie" method="get">
    <table>
        <tr>
            <td><input type="submit" value="Download"></td>
            <td><a href="home.jsp">Back</a></td>
        </tr>
    </table>
</form>
<table>
    <tr>
        <td>Movie</td>
        <td>|Genre</td>
        <td>|Country</td>
        <td>|Director</td>
    </tr>
    <%
        List<Movie> movies = MovieService.getInstance().getAll();
        int count = 1;
        for (Movie m : movies) {
    %>
    <tr>
        <td><%= (count++) + "." + m.getTitle() + "(" + m.getReleaseDate() + ")"%>
        </td>
        <td><%="|" + m.getGenre() %>
        </td>
        <td><%="|" + m.getCountry() %>
        </td>
        <td><%="|" + MovieService.getInstance().getDirector(m.getDirector()) %>
        </td>
    </tr>


    <%
        }
    %>
</table>

</body>
</html>
