<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22.12.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align=center>
    <h1 >Login to Videolibrary</h1>
</div>
<%
    Boolean Attribute = (Boolean) session.getAttribute("checkPassword");
    if (Attribute != null && Attribute) {
%>
<div align=center>
    <h1>Password is wrong</h1>
</div>
<%
        session.removeAttribute("checkPassword");
    }
%>
<div align=center>
<form action="login" method="post">
    <input type="hidden" name="action" value="login">
    <table>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" ></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Login</button>
            </td>
            <td>
                <a href="registration.jsp">Sign up</a>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
