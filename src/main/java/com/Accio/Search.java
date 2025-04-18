package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/Search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        System.out.println("Keyword received: " + keyword);

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO history VALUES (?, ?)");
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, "http://localhost:8080/SearchEngineProject/Search?keyword=" + keyword);
            preparedStatement.executeUpdate();

            String query = "SELECT pagetitle, pagelink, " +
                    "(LENGTH(LOWER(pageText)) - LENGTH(REPLACE(LOWER(pageText), ?, ''))) / LENGTH(?) AS countoccurence " +
                    "FROM pages ORDER BY countoccurence DESC LIMIT 30;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, keyword.toLowerCase());
            preparedStatement.setString(2, keyword.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<SearchResult> results = new ArrayList<>();
            while (resultSet.next()) {
                SearchResult result = new SearchResult();
                result.setPageTitle(resultSet.getString("pagetitle"));
                result.setPageLink(resultSet.getString("pagelink"));
                results.add(result);
            }

            for (SearchResult result : results) {
                System.out.println("Title: " + result.getPageTitle() + " Link: " + result.getPageLink());
            }

            request.setAttribute("results", results);
            request.getRequestDispatcher("/search.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/com.Accio.Search")
public class com.Accio.Search extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get keyword from request
        String keyword = request.getParameter("keyword");
        System.out.println("Keyword received: " + keyword);

        Connection connection = com.Accio.DatabaseConnection.getConnection();

        try {
            // SQL query to count keyword occurrences and get top 30 relevant pages
            String query = "SELECT pagetitle, pagelink, " +
                    "(length(lower(pageText)) - length(replace(lower(pageText), '" + keyword + "', ''))) / length('" + keyword + "') " +
                    "AS countoccurence FROM pages ORDER BY countoccurence DESC LIMIT 30;";

            ResultSet resultSet = connection.createStatement().executeQuery(query);

            ArrayList<com.Accio.SearchResult> results = new ArrayList<>();

            while (resultSet.next()) {
                com.Accio.SearchResult searchResult = new com.Accio.SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }

            // Console output for debugging
            for (com.Accio.SearchResult result : results) {
                System.out.println("Title: " + result.getTitle());
                System.out.println("Link: " + result.getLink());
                System.out.println();
            }

            // Send results to JSP for display
            request.setAttribute("results", results);
            request.getRequestDispatcher("search.jsp").forward(request, response);

        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }
}




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/com.Accio.Search")
public class com.Accio.Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //getting keyword
        String keyword = request.getParameter("keyword");
        //System.out.println(keyword);
        Connection connection = com.Accio.DatabaseConnection.getConnection();
        try {
            // Connection connection = com.Accio.DatabaseConnection.getConnection();
            //add keyword into history table
            //PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?, ?)");
            //preparedStatement.setString(1, keyword);
            //preparedStatement.setString(2, "http://localhost:8080/SearchEngineProject/Search?keyword="+keyword);
            //preparedStatement.executeUpdate();

            //get results from pages table
            ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle, pagelink, (length(lower(pageText))-length(replace(lower(pageText), '" + keyword + "', \"\")))/length('" + keyword + "') as countoccurence from pages order by countoccurence desc limit 30;");
            ArrayList<com.Accio.SearchResult> results = new ArrayList<com.Accio.SearchResult>();
            while (resultSet.next()) {
                com.Accio.SearchResult searchResult = new com.Accio.SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
            for (com.Accio.SearchResult result : results) {
                System.out.println(result.getTitle() + "\n" + result.getLink() + "\n");
            }
            request.setAttribute("results", results);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

        }
        catch (SQLException | ServletException sqlException) {
            sqlException.printStackTrace();
        }
    }
}

/*
@WebServlet("/com.Accio.Search")
public class com.Accio.Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        System.out.println("com.Accio.Search keyword: " + keyword);

        try {
            Connection connection = com.Accio.DatabaseConnection.getConnection();

            // Add to history
            PreparedStatement historyStmt = connection.prepareStatement("INSERT INTO history VALUES (?, ?)");
            historyStmt.setString(1, keyword);
            historyStmt.setString(2, "http://localhost:8080/SearchEngineProject/Search?keyword=" + keyword);
            historyStmt.executeUpdate();

            // Safe query
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT pageTitle, pageLink, " +
                            "(LENGTH(LOWER(pageData)) - LENGTH(REPLACE(LOWER(pageData), ?, ''))) / LENGTH(?) AS countoccurence " +
                            "FROM pages ORDER BY countoccurence DESC LIMIT 30");
            ps.setString(1, keyword.toLowerCase());
            ps.setString(2, keyword.toLowerCase());
            ResultSet resultSet = ps.executeQuery();

            ArrayList<com.Accio.SearchResult> results = new ArrayList<>();
            while (resultSet.next()) {
                com.Accio.SearchResult searchResult = new com.Accio.SearchResult();
                searchResult.setPageTitle(resultSet.getString("pageTitle"));
                searchResult.setPageLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }

            request.setAttribute("results", results);
            request.getRequestDispatcher("/search.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(500, "Server Error: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
*/