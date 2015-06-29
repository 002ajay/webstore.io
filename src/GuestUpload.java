import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/GuestUpload")
public class GuestUpload extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String nm=request.getParameter("fn");
		String nm1=request.getParameter("em");
		String nm2=request.getParameter("ps");
		String nm3=request.getParameter("gen");
        InputStream i1 = null; // input stream of the upload file
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("pix");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // obtains input stream of the upload file
            i1 = filePart.getInputStream();
        }
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore","root","root");

            
            String sql = "insert into admin1 (fname,mail,pass,gen,foto,access_type) values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
    	
    		ps.setString(1, nm);
    		ps.setString(2, nm1);
    		ps.setString(3,nm2);
    		ps.setString(4, nm3);
     
            if (i1 != null) 
            {
                ps.setBlob(5, i1);
            }
            ps.setString(6, "3");
            int row = ps.executeUpdate();
            if (row > 0) 
            {
            	out.println("<body><style> .L h2 { font-family:Lato; font-size:1.9em;	padding-top: 15px;height:50px;width: 280px;background: #000;font-family: Lato;display: block;color: #fff;line-height: 21px;margin: 7px;padding-left: 20px;border-radius: 4px;background: rgba(0,0,0,0.5);position:relative; margin-left: 280px;top:-490px; } </style> <div class=L> <h2>Record Added!</h2></div>");
        		request.getRequestDispatcher("Home2.jsp").forward(request, response);
        		
        	} else {
        		out.println("Fill all Fields");
        		request.getRequestDispatcher("Guest.jsp").include(request, response);
        		
        	}
        }catch(Exception e){e.printStackTrace();}     
}   
}
}