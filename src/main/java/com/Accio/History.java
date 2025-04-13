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
            Connection connection = DatabaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM history");

            ArrayList<HistoryResult> results = new ArrayList<>();
            while (resultSet.next()) {
                HistoryResult historyResult = new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("searchLink"));
                results.add(historyResult);
            }

            request.setAttribute("results", results);
            request.getRequestDispatcher("/history.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
