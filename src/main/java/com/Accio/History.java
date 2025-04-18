package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Get connection from DatabaseConnection class
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("Database connection is null.");
                response.getWriter().println("Database connection failed.");
                return;
            }

            // Correct the query to use 'link' column instead of 'searchLink'
            String query = "SELECT keyword, link FROM history"; // Fixed here
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Store results in an ArrayList
            ArrayList<HistoryResult> results = new ArrayList<>();

            // Iterate through the result set
            while (resultSet.next()) {
                HistoryResult historyResult = new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("link")); // Fixed here
                results.add(historyResult);
            }

            // Set the results as an attribute for use in JSP
            request.setAttribute("results", results);
            // Forward the request and response to the JSP page
            request.getRequestDispatcher("/history.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
