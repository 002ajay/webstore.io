
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.*;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;

@WebServlet("/Myfilex4")
@MultipartConfig(maxFileSize = 16177215)

public class Myfilex4 extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        
        		String n1=(String)request.getParameter("nm1");
        		String n2=(String)request.getParameter("nm2");
        		String n3=(String)request.getParameter("nm3");
        		String n4=(String)request.getParameter("nm4");
        		
           
                try 
                {
                	Class.forName("com.mysql.jdbc.Driver");
                	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");

                    
                    String sql ="insert into profile(pin,localaddress,fname,lname) values(?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
             
            		ps.setString(1, n1);
            		ps.setString(2, n2);
            		ps.setString(3, n3);
            		ps.setString(4, n4);
                    
                    int row = ps.executeUpdate();
                    if (row > 0) 
                    {
                		out.println("Added");
                		response.sendRedirect("ManageFaculty.jsp");
                		
                	} else {
                		out.println("Fill all Fields");
                		request.getRequestDispatcher("UserProfileFill.jsp").include(request, response);
                		
                	} 

                }catch(Exception e){e.printStackTrace();}     
    }   
}