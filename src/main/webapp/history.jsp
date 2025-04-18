<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Accio.HistoryResult" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search History - MySearch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* History-specific styles */
        .history-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
        }

        .history-title {
            color: #202124;
            font-size: 24px;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #e0e0e0;
        }

        .history-table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.1);
            overflow: hidden;
            margin-top: 20px;
        }

        .history-table th {
            background-color: #f8f9fa;
            padding: 12px 15px;
            text-align: left;
            font-weight: 500;
            color: #3c4043;
            border-bottom: 1px solid #e0e0e0;
        }

        .history-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #e0e0e0;
        }

        .history-table tr:last-child td {
            border-bottom: none;
        }

        .history-table tr:hover {
            background-color: #f8f9fa;
        }

        .keyword-cell {
            color: #1a0dab;
            font-weight: 500;
        }

        .link-cell a {
            color: #006621;
            text-decoration: none;
        }

        .link-cell a:hover {
            text-decoration: underline;
        }

        .no-history {
            text-align: center;
            color: #70757a;
            padding: 20px;
        }

        .search-form {
            display: flex;
            justify-content: center;
            margin-bottom: 30px;
        }

        .search-form input {
            padding: 12px 20px;
            border: 1px solid #dfe1e5;
            border-radius: 24px 0 0 24px;
            font-size: 16px;
            width: 400px;
            outline: none;
        }

        .search-form button {
            padding: 12px 24px;
            background-color: #f8f9fa;
            border: 1px solid #dfe1e5;
            border-radius: 0 24px 24px 0;
            cursor: pointer;
            font-size: 16px;
        }

        .search-form button:hover {
            box-shadow: 0 1px 1px rgba(0,0,0,0.1);
            border: 1px solid #dadce0;
        }
    </style>
</head>
<body>
    <div class="history-container">
        <div class="search-form">
            <form action="Search" method="GET">
                <input type="text" name="keyword" placeholder="Search..." required>
                <button type="submit"><i class="fas fa-search"></i> Search</button>
            </form>
        </div>

        <h1 class="history-title">Search History</h1>

        <table class="history-table">
            <thead>
                <tr>
                    <th>Keyword</th>
                    <th>Link</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
                    if (results != null && !results.isEmpty()) {
                        for (HistoryResult result : results) {
                %>
                            <tr>
                                <td class="keyword-cell"><%= result.getKeyword() %></td>
                                <td class="link-cell">
                                    <a href="<%= result.getLink() %>" target="_blank"><%= result.getLink() %></a>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="2" class="no-history">No search history found</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>