package BharathBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@jakarta.servlet.annotation.WebServlet("/Admin_Login_Page")
public class Admin_Login_Page extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String SELECT_QUERY = "SELECT * FROM Admin_login_page WHERE username=? AND password=?";
    
    public Admin_Login_Page() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String USERNAME = request.getParameter("username");
        String PASSWORD = request.getParameter("password");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6", "root", "root");
            stmt = conn.prepareStatement(SELECT_QUERY);
            stmt.setString(1, USERNAME);
            stmt.setString(2, PASSWORD);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                response.sendRedirect("Admin.html");
            } else {
                System.out.println("Invalid username or password");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}