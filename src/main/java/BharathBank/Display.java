package BharathBank;
	
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/DisplayData")
	public class Display extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    private static final String SELECT_QUERY = "SELECT * FROM admin_dashboard";

	    public Display() {
	        super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        PrintWriter pw = response.getWriter();
	        response.setContentType("text/html");

	        pw.println("<!DOCTYPE html>");
	        pw.println("<html>");
	        pw.println("<head>");
	        pw.println("<meta charset=\"UTF-8\">");
	        pw.println("<title>Display Data</title>");
	        pw.println("<style>");
	        pw.println("table { border-collapse: collapse; width: 100%; }");
	        pw.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
	        pw.println("tr:nth-child(even) { background-color: #f2f2f2; }");
	        pw.println("</style>");
	        pw.println("</head>");
	        pw.println("<body>");
	        pw.println("<h2>Existing Data in Admin Dashboard</h2>");
	        pw.println("<table>");
	        pw.println("<tr><th>Full Name</th><th>Address</th><th>Mobile Number</th><th>Email</th><th>Account Type</th><th>Balance</th><th>Date of Birth</th></tr>");

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6", "root", "root");
	             PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                pw.println("<tr>");
	                pw.println("<td>" + rs.getString("full_name") + "</td>");
	                pw.println("<td>" + rs.getString("address") + "</td>");
	                pw.println("<td>" + rs.getString("mobile_no") + "</td>");
	                pw.println("<td>" + rs.getString("email") + "</td>");
	                pw.println("<td>" + rs.getString("account_type") + "</td>");
	                pw.println("<td>" + rs.getDouble("balance") + "</td>");
	                pw.println("<td>" + rs.getString("dob") + "</td>");
	                pw.println("</tr>");
	            }
	        } catch (SQLException se) {
	            pw.println(se.getMessage());
	            se.printStackTrace();
	        } catch (Exception e) {
	            pw.println(e.getMessage());
	            e.printStackTrace();
	        }

	        pw.println("</table>");
	        pw.println("</body>");
	        pw.println("</html>");
	    }
	}