<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Accio.HistoryResult" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Search History</title>
</head>
<body>

    <form action="Search" method="get">
        <input type="text" name="keyword" required>
        <button type="submit">Search</button>
    </form>

    <div class="resultTable">
        <table border="2">
            <tr>
                <th>Keyword</th>
                <th>Link</th>
            </tr>

            <%
                ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
                if (results != null && !results.isEmpty()) {
                    for (HistoryResult result : results) {
            %>
                        <tr>
                            <td><%= result.getKeyword() %></td>
                            <td><a href="<%= result.getLink() %>" target="_blank"><%= result.getLink() %></a></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="2">No history found.</td>
                    </tr>
            <%
                }
            %>

        </table>
    </div>

</body>
</html>
