
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/MyFile1")
@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB
public class MyFile1 extends HttpServlet {
	
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/mystore";
	private String dbUser = "root";
	private String dbPass = "root";
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String nm=request.getParameter("x");
		String nm1=request.getParameter("xx");
		String nm2=request.getParameter("xxx");
		String nm4=request.getParameter("xxxx");
		String nm3=request.getParameter("xxxxx");
		
		InputStream inputStream = null;	// input stream of the upload file
		
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			
			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
		
		Connection conn = null;	// connection to the database
		String message = null;	// message will be sent back to client
		
		try {
			// connects to the database
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			// constructs SQL statement
			String sql = "INSERT INTO news (id, title,auther,date,disc,file) values (?, ?, ?, ?, ?,  ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1,nm);
			statement.setString(2,nm1);
			statement.setString(3,nm2);
			statement.setString(4,nm4);
			statement.setString(5,nm3);
			
			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(6, inputStream);
			}

			// sends the statement to the database server
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "File uploaded and saved into database";
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			// sets the message in request scope
			request.setAttribute("Message", message);
			
			// forwards to the message page
			getServletContext().getRequestDispatcher("/News.jsp").forward(request, response);
		}
	}
}