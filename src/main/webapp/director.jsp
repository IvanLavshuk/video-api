<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.12.2024
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head>
    <title>Add director</title>
</head>
<body>
<div align=center>
    <h1>Add director</h1>
</div>
<%
    Boolean director = (Boolean) session.getAttribute("incorrect");
    if (director != null && director) {
%>
<div align=center>
    <h1>Incorrect parameters</h1>
</div>
<%
        session.removeAttribute("incorrect");
    }
%>


<div align=center>
    <form action="AddingDirector" method="post">
        <input type="hidden" name="action" value="AddingDirector">
        <table>
            <tr><td>Name:</td><td><input type="text"  name="name" ></td></tr>
            <tr><td>Surname:</td><td><input type="text"  name="surname" ></td></tr>
            <tr><td><p>Year, month ,date : XXXX-XX-XX</p></td></tr>
            <tr><td>Birthdate:</td><td><input type="text"  name="birthdate" ></td></tr>
            <tr><td><button type="submit">Add director</button></td></tr>
        </table>
    </form>
</div>
</body>

</html>
