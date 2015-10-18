

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Login;
import pojo.User;
import dao.LoginDal;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDal loginDal = new LoginDal();
        Login l = loginDal.assembleloginforinsert(request);
        String error;
		try 
		{
			error = loginDal.checklogin(l,request);
			System.out.println("error::::::::"+error);
			if(error.trim().equals(""))
	        {
				HttpSession session = request.getSession();
				User user =(User) session.getAttribute("LoggedUser");
				if(user.getUserMasterID()==1)
				{
					response.sendRedirect("adminDashboard.jsp");
				}
				else if(user.getUserMasterID()==2)
				{
					System.out.println("In Else For Success");
					user.getArrayList().add(user);
					request.setAttribute("loginData", user);
		            RequestDispatcher  rd = request.getRequestDispatcher("employeeDashboard.jsp");
		            rd.forward(request, response);
				}
	        }
	        else
	        {
	        	
	        	request.setAttribute("error", error);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        	dispatcher.forward(request, response);
	        }
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
