<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22.12.2024
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align=center>
    <h1>Register in the Videolibrary</h1>
</div>
<%
    Boolean Attribute = (Boolean) session.getAttribute("message");
    if (Attribute != null && Attribute) {
%>
<div align=center>
<h1>All fields are required</h1>
</div>
<%
        session.removeAttribute("message");
    }
%>
<div align=center>
<form action="registration" method="post">
    <input type="hidden" name="action" value="register">
    <table>
        <tr><td>Name:</td><td><input type="text"  name="name" ></td></tr>
        <tr><td>Surname:</td><td><input type="text"  name="surname" ></td></tr>
        <tr><td>Password:</td><td><input type="password"  name="password" ></td></tr>
        <tr><td>Email:</td><td><input type="email"  name="email" ></td></tr>
        <tr><td><a href= "login.jsp">Login</a></td><td><button type="submit">Register</button></td></tr>
    </table>
</form>
</div>
</body>
</html>
