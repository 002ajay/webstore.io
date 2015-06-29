
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

@WebServlet("/InsertMail")
@MultipartConfig(maxFileSize = 16177215)

public class InsertMail extends HttpServlet 
{

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        
                InputStream inputStream = null;

                
                String title=(request.getParameter("recipient"));
                String dp=(request.getParameter("subject"));
                String cr=(request.getParameter("content"));

         
                try 
                {
                	Class.forName("com.mysql.jdbc.Driver");
                	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");

                
                    String sql = "INSERT INTO mail (id, sub, content) values (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, title);
                    statement.setString(2, dp);
                    statement.setString(3, cr);
               
                
                    int row = statement.executeUpdate();
                    if (row > 0) 
                    {
                    	out.println("<body><style> .L h2 { font-family:Lato; font-size:1.2em;	padding-top: 5px;height: 20px;width: 300px;background: #000;font-family: Lato;display: block;color: #fff;line-height: 11px;margin: 7px;padding-left: 20px;border-radius: 4px;background: rgba(0,0,0,0.5);position:relative; margin-left: 756px;top:-308px; } </style> <div class=L> <h2>Sorry...! Invalid User ID and Password!</h2></div>");
        				 
                        conn.close();
                        
                        RequestDispatcher rs = request.getRequestDispatcher("Data1.jsp");
                        rs.include(request, response);
                    }
                    else
                    {
                        out.println("Couldn't upload your file!!!");
                        
                        conn.close();
                        
                        RequestDispatcher rs = request.getRequestDispatcher("EmailForm.jsp");
                        rs.include(request, response);
                    }    

                }catch(Exception e){e.printStackTrace();}     
    }   
}