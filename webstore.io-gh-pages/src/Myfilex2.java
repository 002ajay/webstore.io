

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Myfilex2
 */
@WebServlet("/Myfilex2")

@MultipartConfig(maxFileSize = 16177215)
public class Myfilex2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
    

    		String dob=(String)request.getParameter("ss");
    		String fon=(String)request.getParameter("adm");
    		String sx=(String)request.getParameter("sn");
    		String ms=(String)request.getParameter("em");
    		String ad=(String)request.getParameter("cr");
    		String gr=(String)request.getParameter("sm");
    		String clg1=(String)request.getParameter("gen");
    		String yr1=(String)request.getParameter("fn");
    		String pg=(String)request.getParameter("nt");
    		String clg=(String)request.getParameter("rl");
    		String yr=(String)request.getParameter("fo");
    		String ex=(String)request.getParameter("ph");
    		String id=(String)request.getParameter("addr");
    		String mon=(String)request.getParameter("mon");
    		String fof=(String)request.getParameter("fof");
    		String mop=(String)request.getParameter("mop");
    		String foe=(String)request.getParameter("foe");
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
            try 
            {
            	Class.forName("com.mysql.jdbc.Driver");
            	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");

                
                String sql = "insert into stud2 (session,exam,fname,email,course,sem,gender,ffname,nation,religion,foccu,fone,address,mname,ffone,moccu,gmail,foto)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
        	
        		ps.setString(1, dob);
        		ps.setString(2, fon);
        		ps.setString(3,sx);
        		ps.setString(4, ms);
        		ps.setString(5, ad);
        		ps.setString(6, gr);
        		ps.setString(7, clg1);
        		ps.setString(8, yr1);
        		ps.setString(9, pg);
        		ps.setString(10, clg);
        		ps.setString(11, yr);
        		ps.setString(12, ex);
        		ps.setString(13, id);
        		ps.setString(14, mon);
        		ps.setString(15, fof);
        		ps.setString(16, mop);
        		ps.setString(17, foe);
         
                if (inputStream != null) 
                {
                    ps.setBlob(18, inputStream);
                }
                
                int row = ps.executeUpdate();
                if (row > 0) 
                {
                	out.println("<body><style> .L h2 { font-family:Lato; font-size:1.9em;	padding-top: 15px;height:50px;width: 280px;background: #000;font-family: Lato;display: block;color: #fff;line-height: 21px;margin: 7px;padding-left: 20px;border-radius: 4px;background: rgba(0,0,0,0.5);position:relative; margin-left: 280px;top:-490px; } </style> <div class=L> <h2>Record Added!</h2></div>");
            		request.getRequestDispatcher("ManageStud.jsp").forward(request, response);
            		
            	} else {
            		out.println("Fill all Fields");
            		request.getRequestDispatcher("SignUp.jsp").include(request, response);
            		
            	}
            }catch(Exception e){e.printStackTrace();}     
}   
}
}