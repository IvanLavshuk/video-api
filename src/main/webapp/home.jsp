<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22.12.2024
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    Boolean reviewRegistered = (Boolean) session.getAttribute("reviewRegistered");
    if (reviewRegistered != null && reviewRegistered) {
%>
<h1>Review was registered</h1>
<%
        session.removeAttribute("reviewRegistered");
    }
%>
<%
    Boolean directorRegistered = (Boolean) session.getAttribute("directorRegistered");
    if (directorRegistered != null && directorRegistered) {
%>
<h1>Director was registered</h1>
<%
        session.removeAttribute("directorRegistered");
    }
%>
<%
    Boolean actorRegistered = (Boolean) session.getAttribute("actorRegistered");
    if (actorRegistered != null && actorRegistered) {
%>
<h1>Actor was registered</h1>
<%
        session.removeAttribute("actorRegistered");
    }
%>
<%
    Boolean movieRegistered = (Boolean) session.getAttribute("movieRegistered");
    if (movieRegistered != null && movieRegistered) {
%>
<h1>Movie was registered</h1>
<%
        session.removeAttribute("movieRegistered");
    }
%>
<%
    Boolean registered = (Boolean) session.getAttribute("registered");
    if (registered != null && registered) {
%>
<h1>Welcome!</h1>
<%
        session.removeAttribute("registered");
    }
%>
<%
    String user = (String) session.getAttribute("user");
%>
<div align=center>
    <h1>Home</h1>
    <h2>Hello, <%=user%> !</h2>
</div>

<div align=center>
    <form action="home" method="post">
        <input type="hidden" name="action" value="home">
        <div align=center>
            <p>Actions in the system:</p>
        </div>
        <table>
            <tr><td><a href= "movie.jsp">Add movie</a></td></tr>
            <tr><td><a href= "actor.jsp">Add actor</a></td></tr>
            <tr><td><a href= "director.jsp">Add director</a></td></tr>
            <tr><td><a href= "review.jsp">Add review</a></td></tr>
            <tr><td><a href= "/FirstVideolibrary_war_exploded/Movie">List of movies</a></td></tr>
            <tr><td><a href= "login.jsp">List of actors</a></td></tr>
            <tr><td><a href= "login.jsp">List of directors</a></td></tr>
        </table>
    </form>
    <td><a href= "login.jsp">Exit</a></td>
</div>



</body>
</html>
