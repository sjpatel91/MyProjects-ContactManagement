

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Login;
import pojo.User;
import General.GeneralFunction;
import dao.EmployeeDal;

/**
 * Servlet implementation class EMployeeService
 */
@WebServlet("/EmployeeService")
public class EmployeeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GeneralFunction function = new GeneralFunction();
    	PrintWriter out = response.getWriter();
    	String sql =  "select * from User where userMasterID=2";
		String s = function.ExportData(sql,new String[]{"Name","Desc"},new String[]{"2","3"});
		if(!s.equals(""))
		{
			response.setContentType("application/vnd.ms-excel");
			out.println(s);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String sqlCount = "Select count(userID) from user where userMasterID=2";
        String label;
        label=request.getParameter("label");
        String pageName= request.getParameter("pageName");
		String pageNo=	request.getParameter("pageNo").trim();
        GeneralFunction function = new GeneralFunction();
        if(label.equals("Insert"))
        {
            EmployeeDal employeeDal= new EmployeeDal();
            User user = employeeDal.assembleEmployeeforinsert(request);
            boolean ins1 = employeeDal.insertEmployee(user);
            response.sendRedirect("adminDashboard.jsp");
        }
        else if(label.equals("Grid"))
        {
        	System.out.println("in This");
        	function.setPageNo(Integer.parseInt(pageNo));
			function.recordno();
			String sql = "select * from user where userMasterID=2 order by userid desc limit " + function.getRecordNo() + ","+function.getRecordsPerPage();
			String str="";
			str+=pageNo;
			str+="|";
			str+=pageName;
			str+="|";
			str += function.gridDataPageLoad(sqlCount,sql,pageName, new String []{"Id","First Name","Last Name","Contact No","EmailID"}, new String[]{"2","3","4","5"}, new String[]{"15%","50%","35%"}, new String[]{"Active","Edit","Delete"});
			PrintWriter printWriter = response.getWriter();
			printWriter.print(str);
        }
        else if(label.equals("ExportCVS"))
        {
        	
    		String sql =  "select * from user where userMasterID=2";
    		String s = function.ExportData(sql,new String[]{"Name","Desc"},new String[]{"2","3"});
    		response.setContentType("application/vnd.ms-excel");
    		out.println(s);
        }
        else if(label.equals("Delete"))
        {
        	String id = request.getParameter("id");
        	EmployeeDal employeeDal= new EmployeeDal();
            boolean ins1 = employeeDal.deleteEmployee(id);
            
            function.setPageNo(Integer.parseInt(pageNo));
            function.recordnoForDelete(sqlCount);
            String str = pageName+"|"+function.getPageNo();
            out.println(str);
        }
        else if(label.equals("activeDeactive"))
        {
        	String id = request.getParameter("id");
        	String flag = request.getParameter("flag");
        	EmployeeDal employeeDal= new EmployeeDal();
            boolean ins1 = employeeDal.activeDeactiveEmployee(id,flag);
            
            function.setPageNo(Integer.parseInt(pageNo));
            //function.recordnoForDelete(sqlCount);
            String str = pageName+"|"+function.getPageNo();
            out.println(str);
        }
        
        else if(label.equals("EditPage"))
        {
        	User user= new User();
        	String id = request.getParameter("id");
        	EmployeeDal employeeDal= new EmployeeDal();
            user.getArrayList().add(employeeDal.dataEmployee(id,user));
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageName", pageName);
            request.setAttribute("loginData", user);
            RequestDispatcher  rd = request.getRequestDispatcher("edit.jsp");
            rd.forward(request, response);
        }
        else if(label.equals("Edit"))
        {	
        	
        	EmployeeDal employeeDal= new EmployeeDal();
            User user = employeeDal.assembleEmployeeforinsert(request);
            boolean ins1 = employeeDal.editEmployee(user);
			request.setAttribute("pageName", pageName);
			request.setAttribute("pageNo", pageNo);
			RequestDispatcher  rd = request.getRequestDispatcher("adminDashboard.jsp");
//            rd.forward(request, response);
            response.sendRedirect("adminDashboard.jsp");
            rd.include(request, response);
        }
	}

}
