package jsp.cms.quiz.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.cms.quiz.Exam;
import jsp.cms.quiz.QuizQuestion;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/exam")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean finish=false;
		
		HttpSession session=request.getSession();		
		try
		{
			if(session.getAttribute("currentExam")==null)
		  {  session=request.getSession(); 	
		     String selectedExam=(String)request.getSession().getAttribute("exam"); 
		     System.out.println("Setting Exam "+selectedExam);
			 Exam newExam=new Exam(selectedExam);		  
			 session.setAttribute("currentExam",newExam);
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started=dateFormat.format(date);
			  session.setAttribute("started",started);
		  }
		
		}catch(Exception e){e.printStackTrace();}
		
        Exam exam=(Exam)request.getSession().getAttribute("currentExam");		
        
        if(exam.currentQuestion==0){	
			exam.setQuestion(exam.currentQuestion);
		    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			session.setAttribute("quest",q);
        }
			
			String action=request.getParameter("action");
			
			String radio=request.getParameter("answer");
			int selectedRadio=-1;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			if("1".equals(radio))
			{
				selectedRadio=1;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
			}
			else if("2".equals(radio))
			{
				selectedRadio=2;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
			}
			else if("3".equals(radio))
			{
				selectedRadio=3;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
			}
			else if("4".equals(radio))
			{
				selectedRadio=4;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
			}
			
						
			if("Next".equals(action)){
				exam.currentQuestion++;
				exam.setQuestion(exam.currentQuestion);
			    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			  	session.setAttribute("quest",q);
			}
			else if("Previous".equals(action))
			{   System.out.println("You clicked Previous Button");
				exam.currentQuestion--;
				exam.setQuestion(exam.currentQuestion);
			    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
				session.setAttribute("quest",q);
			}
			else if("Finish Exam".equals(action))
			{   finish=true;
				int result=exam.calculateResult(exam);				
				request.setAttribute("result",result);
				request.getSession().setAttribute("currentExam",null);
				request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request,response);
				
			}
						
		if(finish!=true){
		request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request,response);
		}
		
	}

}
