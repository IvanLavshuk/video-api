<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.12.2024
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add review</title>
</head>
<body>
<div align=center>
    <h1>Add review</h1>
</div>
<%
    Boolean review = (Boolean) session.getAttribute("incorrect");
    if (review != null && review) {
%>
<div align=center>
    <h1>Incorrect parameters</h1>
</div>
<%
        session.removeAttribute("incorrect");
    }
%>


<div align=center>
    <form action="AddingReview" method="post">
        <input type="hidden" name="action" value="AddingReview">
        <table>
            <tr><td>Rating:</td><td><input type="text"  name="rating" ></td></tr>
            <tr><td>Text:</td><td><input type="text"  name="text" ></td></tr>
            <tr><td>Movie:</td><td><input type="text"  name="movie" ></td></tr>
            <tr><td>Users email:</td><td><input type="email"  name="email" ></td></tr>
            <tr><td><button type="submit">Add review</button></td></tr>
        </table>
    </form>
</div>
</body>
</html>
