<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22.12.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Подключение Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('https://images.wallpaperscraft.ru/image/single/drakon_siluety_lestnitsa_214218_3840x2400.jpg'); /* Фоновое изображение */
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
            background: rgba(0, 0, 0, 0.7); /* Полупрозрачный черный фон */
            padding: 30px;
            border-radius: 15px;
            text-align: center;
            animation: fadeIn 1.5s ease-in-out;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn-login {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-login:hover {
            background-color: #0056b3;
        }

        .btn-signup {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .btn-signup:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        .alert {
            margin: 20px auto;
            width: 80%;
            max-width: 400px;
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
    <h1 class="display-4">Login to Videolibrary</h1>


    <%
        Boolean checkPassword = (Boolean) session.getAttribute("checkPassword");
        if (checkPassword != null && checkPassword) {
    %>
    <div class="alert alert-danger" role="alert">
        Password is wrong!
    </div>
    <%
            session.removeAttribute("checkPassword");
        }
    %>

    <form action="login" method="post">
        <input type="hidden" name="action" value="login">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn-login">Login</button>
            <a href="registration.jsp" class="btn-signup">Sign up</a>
        </div>
    </form>
</div>

<!-- Подключение Bootstrap JS (опционально) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
