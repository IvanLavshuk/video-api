<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @keyframes backgroundAnimation {
            0% {
                filter: brightness(100%);
            }
            50% {
                filter: brightness(120%);
            }
            100% {
                filter: brightness(100%);
            }
        }
        body {
            /* Замените на ваше фоновое изображение */
            background-image: url('https://images.wallpaperscraft.ru/image/single/dom_gory_oblaka_141061_3840x2400.jpg');
            background-size: cover;
            background-position: center;
            font-family: 'Arial', sans-serif;
            color: white;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        .container {
            background: rgba(0, 0, 0, 0.7); /* Полупрозрачный черный фон для контента */
            padding: 30px;
            border-radius: 15px;
            text-align: center;
            animation: fadeIn 1.5s ease-in-out;
        }
        .welcome-message {
            margin-bottom: 30px;
        }
        .actions-list {
            margin: 20px auto;
            width: 80%;
            max-width: 400px;
        }
        .actions-list a {
            display: block;
            margin: 15px 0;
            padding: 12px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: transform 0.3s ease, background-color 0.3s ease;
        }
        .actions-list a:hover {
            background-color: #0056b3;
            transform: scale(1.05); /* Анимация увеличения при наведении */
        }
        .btn-exit {
            margin-top: 20px;
        }
        .btn-exit a {
            padding: 10px 20px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: transform 0.3s ease, background-color 0.3s ease;
        }
        .btn-exit a:hover {
            background-color: #c82333;
            transform: scale(1.05); /* Анимация увеличения при наведении */
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
<div class="container">
        <%
        Boolean reviewRegistered = (Boolean) session.getAttribute("reviewRegistered");
        if (reviewRegistered != null && reviewRegistered) {
    %>
    <div class="alert alert-success" role="alert">
        Review was registered successfully!
    </div>
        <%
            session.removeAttribute("reviewRegistered");
        }
    %>
        <%
        Boolean directorRegistered = (Boolean) session.getAttribute("directorRegistered");
        if (directorRegistered != null && directorRegistered) {
    %>
    <div class="alert alert-success" role="alert">
        Director was registered successfully!
    </div>
        <%
            session.removeAttribute("directorRegistered");
        }
    %>
        <%
        Boolean actorRegistered = (Boolean) session.getAttribute("actorRegistered");
        if (actorRegistered != null && actorRegistered) {
    %>
    <div class="alert alert-success" role="alert">
        Actor was registered successfully!
    </div>
        <%
            session.removeAttribute("actorRegistered");
        }
    %>
        <%
        Boolean movieRegistered = (Boolean) session.getAttribute("movieRegistered");
        if (movieRegistered != null && movieRegistered) {
    %>
    <div class="alert alert-success" role="alert">
        Movie was registered successfully!
    </div>
        <%
            session.removeAttribute("movieRegistered");
        }
    %>
        <%
        Boolean registered = (Boolean) session.getAttribute("registered");
        if (registered != null && registered) {
    %>
    <div class="alert alert-success" role="alert">
        Welcome! You have successfully registered.
    </div>
        <%
            session.removeAttribute("registered");
        }
    %>
        <%
        String user = (String) session.getAttribute("user");
    %>
<div class="container">
    <div class="welcome-message">
        <h1 class="display-4">Home</h1>
        <h2 class="text-muted">Hello, <%= user %>!</h2>
    </div>

    <div class="actions-list">
        <a href="movie.jsp">Add Movie</a>
        <a href="actor.jsp">Add Actor</a>
        <a href="director.jsp">Add Director</a>
        <a href="review.jsp">Add Review</a>
        <a href="movies.jsp">List of Movies</a>
        <a href="actors.jsp">List of Actors</a>
        <a href="directors.jsp">List of Directors</a>
    </div>

    <div class="btn-exit">
        <a href="login.jsp">Exit</a>
    </div>
</div>

<!-- Подключение Bootstrap JS (опционально) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>