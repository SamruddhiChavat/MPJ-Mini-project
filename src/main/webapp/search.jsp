<%@page import="java.util.ArrayList"%>
<%@page import="com.Accio.SearchResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Results for <%= request.getParameter("keyword") %> - MySearch</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #202124;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .search-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 1px solid #e0e0e0;
        }

        .logo {
            font-size: 24px;
            font-weight: 500;
            margin-right: 30px;
            color: #1a73e8;
        }

        .logo span {
            color: #34a853;
        }

        .search-box {
            flex-grow: 1;
            position: relative;
        }

        .search-input {
            width: 100%;
            padding: 12px 20px 12px 45px;
            border-radius: 24px;
            border: 1px solid #dfe1e5;
            font-size: 16px;
            outline: none;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.1);
        }

        .search-icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #9aa0a6;
        }

        .results-count {
            color: #70757a;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .results-table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.1);
            overflow: hidden;
        }

        .results-table th {
            background-color: #f8f9fa;
            padding: 12px 15px;
            text-align: left;
            font-weight: 500;
            color: #3c4043;
            border-bottom: 1px solid #e0e0e0;
        }

        .results-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #e0e0e0;
        }

        .results-table tr:last-child td {
            border-bottom: none;
        }

        .results-table tr:hover {
            background-color: #f8f9fa;
        }

        .result-title {
            color: #1a0dab;
            font-size: 16px;
            font-weight: 500;
            text-decoration: none;
            display: block;
            margin-bottom: 3px;
        }

        .result-title:hover {
            text-decoration: underline;
        }

        .result-link {
            color: #006621;
            font-size: 14px;
            text-decoration: none;
            display: block;
        }

        .result-link:hover {
            text-decoration: underline;
        }

        .footer {
            margin-top: 40px;
            text-align: center;
            color: #70757a;
            font-size: 14px;
            padding: 20px 0;
            border-top: 1px solid #e0e0e0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="search-header">
            <div class="logo">My<span>Search</span></div>
            <div class="search-box">
                <i class="fas fa-search search-icon"></i>
                <form action="Search" method="GET">
                    <input type="text" name="keyword" value="<%= request.getParameter("keyword") %>"
                           class="search-input" placeholder="Search...">
                </form>
            </div>
        </div>

        <div class="results-count">
            About <%= ((ArrayList<SearchResult>)request.getAttribute("results")).size() %> results
        </div>

        <table class="results-table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Link</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
                    for(SearchResult result : results){
                %>
                <tr>
                    <td>
                        <a href="<%= result.getPageLink() %>" class="result-title">
                            <%= result.getPageTitle() %>
                        </a>
                    </td>
                    <td>
                        <a href="<%= result.getPageLink() %>" class="result-link">
                            <%= result.getPageLink() %>
                        </a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="footer">
            JAVA MINI PROJECT |
            <a href="#" style="color: #1a73e8; text-decoration: none;">Privacy</a> |
            <a href="#" style="color: #1a73e8; text-decoration: none;">Terms</a>
        </div>
    </div>
</body>
</html>