<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.12.2024
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add movie</title>
</head>
<body>
<div align=center>
    <h1>Add movie</h1>
</div>
<%
    Boolean movie = (Boolean) session.getAttribute("Incorrect");
    if (movie != null && movie) {
%>
<div align=center>
<h1>Incorrect parameters</h1>
</div>
<%
        session.removeAttribute("Incorrect");
    }
%>
<div align=center>
    <form action="Movie" method="post">
        <input type="hidden" name="action" value="Movie">
        <table>
            <tr><td>Title:</td><td><input type="text"  name="title" ></td></tr>
            <tr><td>Genre:</td><td><input type="text"  name="genre" ></td></tr>
            <tr><td>Country:</td><td><input type="text"  name="country" ></td></tr>
            <tr><td><p>Year, month ,date:XXXX-XX-XX</p></td></tr>
            <tr><td>Release date:</td><td><input type="text"  name="releaseDate" ></td></tr>
            <tr><td>director</td><td><input type="text"  name="director" ></td></tr>
            <tr><td><button type="submit">Add movie</button></td><td><a href="home.jsp">Back</a></td></tr>
        </table>
    </form>
</div>
</body>
</html>
