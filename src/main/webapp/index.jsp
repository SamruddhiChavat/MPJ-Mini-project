<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Search App</title>
    <link rel="stylesheet" href="home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <nav>
            <ul class="nav-links">
                <li><a href="#">About</a></li>
                <li><a href="#">Settings</a></li>
                <% if (session.getAttribute("username") != null) { %>
                    <li><span class="username"><%= session.getAttribute("username") %></span></li>
                    <li><a href="Logout">Logout</a></li>
                <% } else { %>
                    <li><a href="login.jsp">Login</a></li>
                <% } %>
            </ul>
        </nav>
    </header>

    <main>
        <div class="logo-container">
            <h1 class="logo">My<span>Search</span></h1>
        </div>

        <form action="Search" method="GET" class="search-container">
            <div class="search-box">
                <i class="fas fa-search search-icon"></i>
                <input type="text" name="keyword" class="search-input" autofocus>
                <button type="submit" class="hidden-submit"></button>
            </div>

            <div class="search-buttons">
                <button type="submit" class="search-button">Search</button>
                <a href="History" class="search-button">History</a>
            </div>
        </form>
    </main>

    <footer>
        <div class="footer-top">
            <p>JAVA MINI PROJECT</p>
        </div>
        <div class="footer-bottom">
            <ul class="footer-links">
                <li><a href="#">Privacy</a></li>
                <li><a href="#">Terms</a></li>
                <li><a href="#">Help</a></li>
            </ul>
        </div>
    </footer>
</body>
</html>