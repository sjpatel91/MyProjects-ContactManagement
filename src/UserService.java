

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import dao.EmployeeDal;
import dao.UserDal;

/**
 * Servlet implementation class UserService
 */
@WebServlet("/UserService")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String sqlCount = "Select count(userID) from user where userMasterID=2";
        String label;
        label=request.getParameter("label");   
		if(label.equals("Profile"))
	    {	
        	UserDal userDal  = new UserDal();
            User user = userDal.assembleUserforprofile(request);
            boolean ins1 = userDal.editUserProfile(user);
            request.setAttribute("loginData", user);
			RequestDispatcher  rd = request.getRequestDispatcher("employeeDashboard.jsp");
            rd.forward(request, response);
	    }
		else if(label.equals("ChangePassword"))
	    {	
        	UserDal userDal  = new UserDal();
            User user = userDal.assembleUserforchangePassword(request);
            boolean ins1=false;
			try 
			{
				ins1 = userDal.editPassword(user);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if(ins1==false)
            {
            	String error = "Provide Correct Password";
            	request.setAttribute("error", error);
            	RequestDispatcher  rd = request.getRequestDispatcher("changePassword.jsp");
            	rd.forward(request, response);
            }
            else
            {
            	try 
            	{
					user = userDal.dataUserProfile(user);
				}
            	catch (SQLException e) 
            	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	user.getArrayList().add(user);
            	request.setAttribute("loginData", user);
            	RequestDispatcher  rd = request.getRequestDispatcher("employeeDashboard.jsp");
            	rd.forward(request, response);
            }
	    }
	}

}
