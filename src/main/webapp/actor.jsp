<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.12.2024
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add actor</title>
</head>
<body>
<div align=center>
    <h1>Add actor</h1>
</div>
<%
    Boolean actor = (Boolean) session.getAttribute("incorrect");
    if (actor != null && actor) {
%>
<div align=center>
    <h1>Incorrect parameters</h1>
</div>
<%
        session.removeAttribute("incorrect");
    }
%>


<div align=center>
    <form action="Actor" method="post">
        <input type="hidden" name="action" value="  Actor">
        <table>
            <tr><td>Name:</td><td><input type="text"  name="nameA" ></td></tr>
            <tr><td>Surname:</td><td><input type="text"  name="surnameA" ></td></tr>
            <tr><td><p>Year, month ,date : XXXX-XX-XX</p></td></tr>
            <tr><td>Birthdate:</td><td><input type="text"  name="birthdate" ></td></tr>
            <tr><td><button type="submit">Add actor</button></td></tr>
        </table>
    </form>
</div>
</body>
</html>
