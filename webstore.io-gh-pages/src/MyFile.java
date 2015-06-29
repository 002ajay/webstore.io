
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

@WebServlet("/MyFile")
@MultipartConfig(maxFileSize = 16177215)

public class MyFile extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        
                InputStream inputStream = null;

                Random rand = new Random();
                int  n = rand.nextInt(9999) + 1;
                String idTemp=(String.valueOf(n));

                
                String title=(request.getParameter("title"));
                Part filePart = request.getPart("file_uploaded");
                String dp=(request.getParameter("cr"));
                String cr=(request.getParameter("cr1"));
                String sm=(request.getParameter("cr2"));
                
                if (filePart != null) 
                {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());

                    inputStream = filePart.getInputStream();
                }
        
                try 
                {
                	Class.forName("com.mysql.jdbc.Driver");
                	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");

                       String strr = "SELECT * FROM files where department=? and course=? and sem=?";
                        PreparedStatement ps = conn.prepareStatement(strr);
                        ps.setString(1, dp);
                        ps.setString(2, cr);
                        ps.setString(3, sm);
                        
                        ResultSet rs1=ps.executeQuery();
                        if(rs1.next()) {
                        	
                        	RequestDispatcher rd= request.getRequestDispatcher("Error.jsp");
            				rd.include(request, response);
                        }
                        else 
                        {
                                          
                       
                    String sql = "INSERT INTO files (id, title, file,department,course,sem) values (?, ?, ?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, idTemp);
                    statement.setString(2, title);
             
                    if (inputStream != null) 
                    {
                        statement.setBinaryStream(3, inputStream, (int) filePart.getSize());
                    }

                    statement.setString(4, dp);
                    statement.setString(5, cr);
                    statement.setString(6, sm);
                    int row = statement.executeUpdate();
                    if (row > 0) 
                    {
                    	out.println("<body><style> .L h2 { font-family:Lato; font-size:1.2em;	padding-top: 5px;height: 20px;width: 300px;background: #000;font-family: Lato;display: block;color: #fff;line-height: 11px;margin: 7px;padding-left: 20px;border-radius: 4px;background: rgba(0,0,0,0.5);position:relative; margin-left: 756px;top:-308px; } </style> <div class=L> <h2>Sorry...! Invalid User ID and Password!</h2></div>");
        				 
                        conn.close();
                        
                        RequestDispatcher rs = request.getRequestDispatcher("Sdownload.jsp");
                        rs.include(request, response);
                    }
                    else
                    {
                        out.println("Couldn't upload your file!!!");
                        
                        conn.close();
                        
                        RequestDispatcher rs = request.getRequestDispatcher("Syllabus1.jsp");
                        rs.include(request, response);
                    }    

                }
                }catch(Exception e){e.printStackTrace();}     
    }   
}