
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

@WebServlet("/UploadFileController3")
@MultipartConfig(maxFileSize = 16177215)

public class UploadFileController3 extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        
                Connection con=null;
                PreparedStatement ps=null;
                String st=null;
                		
                		String n26=request.getParameter("nm25");
                		String n1=request.getParameter("nm1");
                		String n2=request.getParameter("nm2");
                		
                		String n3=request.getParameter("nm3");
                		String n02=request.getParameter("nm02");
                		String n4=request.getParameter("nm7");
                		String n5=request.getParameter("nm6");
                		String n6=request.getParameter("nm9");
                		String n7=request.getParameter("nm10");
                		String n8=request.getParameter("nm8");
                		String n9=request.getParameter("gen");
                		String n10=request.getParameter("nm4");
                		String n11=request.getParameter("nm20");
                		String n12=request.getParameter("nm13");
                		String n13=request.getParameter("nm3");
                		String n14=request.getParameter("nm21");
                		String n15=request.getParameter("nm22");
                		String n16=request.getParameter("nm12");
                		String n17=request.getParameter("nm11");
                		String n18=request.getParameter("nm15");
                		String n19=request.getParameter("nm16");
                		
                		InputStream inputStream = null; // input stream of the upload file
                        // obtains the upload file part in this multipart request
                        Part filePart = request.getPart("nm26");
                        if (filePart != null) {
                            // prints out some information for debugging
                            System.out.println(filePart.getName());
                            System.out.println(filePart.getSize());
                            System.out.println(filePart.getContentType());
                            // obtains input stream of the upload file
                            inputStream = filePart.getInputStream();
                        }
                        
                        
                		String n20=request.getParameter("nm17");
                		String n21=request.getParameter("nm23");
                		String n22=request.getParameter("nm18");
                		String n23=request.getParameter("nm19");
                		String n24=request.getParameter("nm5");
                		
                	
                		   
                try 
                {
                	Class.forName("com.mysql.jdbc.Driver");
                	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");
                	st="insert into profile (userid,firstname,lastname,fathername,localaddress,streeet, city,state,pin,gender,dob,bloodgroup,languageknown,maritalstatus,religion,nationality,experience,qualification,dateofjoining,dateofleaving,profilephoto,departmentid,post,researcharea,specialization,mail)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                	ps=con.prepareStatement(st);
                	
                		ps.setString(1, n26);
                		ps.setString(2, n1);
                		ps.setString(3, n2);
                		ps.setString(4, n02);
                		ps.setString(5, n4);
                		ps.setString(6, n5);
                		ps.setString(7, n6);
                		ps.setString(8, n7);
                		ps.setString(9, n8);
                		ps.setString(10, n9);
                		ps.setString(11, n10);
                		ps.setString(12, n11);
                		ps.setString(13, n12);
                		ps.setString(14, n3);
                		ps.setString(15, n14);
                		ps.setString(16, n15);
                		ps.setString(17, n16);
                		ps.setString(18,n17);
                		ps.setString(19, n18);
                		ps.setString(20,n19);
                		ps.setBlob(21, inputStream);
                		ps.setString(22, n20);
                		ps.setString(23, n21);
                		ps.setString(24, n22);
                		ps.setString(25, n23);
                		ps.setString(26, n24);
                        
                      
                		
                	int i=ps.executeUpdate();
                	if(i>0) {
                		out.println("Added");
                		response.sendRedirect("ManageFaculty.jsp");
                		
                	} else {
                		out.println("Fill all Fields");
                		request.getRequestDispatcher("UserProfileFill.jsp").include(request, response);
                		
                	}
                }catch(Exception e) { out.println(e);}     
    }   
}