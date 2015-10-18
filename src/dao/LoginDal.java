/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import databaseconnection.Dbconnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pojo.Login;
import pojo.User;


public class LoginDal {
    
    public Login assembleloginforinsert(HttpServletRequest request) {
		Login login = new Login();
		login.setUname(request.getParameter("uname"));
		login.setPassword(request.getParameter("password"));
		return login;
	}

    private String validate(Login login)
    {
    	String data="";
    	if(login.getUname().trim().equals("") && login.getPassword().trim().equals(""))
    	{
    		data="Provide UserName And Password";
    				
    	}
    	else if(login.getPassword().trim().equals(""))
    	{
    		data="Provide Password";
    	}
    	else if(login.getUname().trim().equals(""))
    	{
    		data="Provide UserName";
    	} 
    	return data;
    }
    
	public String checklogin(Login login,HttpServletRequest request) throws SQLException {
		boolean success=false;
		String data="";
		
		data=validate(login);
		if(data.trim().equals(""))
		{
			String query = "select * from user where emailID='"+login.getUname()+"' and password='"+login.getPassword()+"'";
			System.out.println("Query:::::::;"+query);
			
			Dbconnection dbconnection = null;
			dbconnection = Dbconnection.getInstance();
			int count=0;
			if(dbconnection.openconnection()==true)
			{
				dbconnection.resultSet = dbconnection.data(query);
				while(dbconnection.resultSet.next())
				{
					count++;
					if(!dbconnection.resultSet.getBoolean(8))
					{
						data="Please Contact To Admin";
					}
					else
					{
						User user = new User();
						user.setId(dbconnection.resultSet.getInt(1));
						user.setFirstName(dbconnection.resultSet.getString(2));
						user.setLastName(dbconnection.resultSet.getString(3));
						user.setContactNo(dbconnection.resultSet.getString(4));
						user.setEmailID(dbconnection.resultSet.getString(5));
						user.setUserMasterID(dbconnection.resultSet.getInt(7));
						user.setActive(dbconnection.resultSet.getBoolean(8));
						HttpSession session = request.getSession();
						session.setAttribute("LoggedUser", user);
					}
				}
				if(count==0)
				{
					data="Provide Correct UserName and Password";
				}
				dbconnection.closeconnection();
			}
			else
			{
				
			}
		}
		return data;
	}
	
	public Login dataLogin(String id,Login login)
	{
		boolean success=false;
		String insertquery = "select * from  Login where id = "+id;
		System.out.println("Query:::::::;"+insertquery);
		Dbconnection dbconnection = null;
		dbconnection = Dbconnection.getInstance();
		try
		{
			if(dbconnection.openconnection()==true)
			{
				dbconnection.resultSet = dbconnection.data(insertquery);
				while(dbconnection.resultSet.next())
				{
					login.setId(dbconnection.resultSet.getInt(1));
					login.setUname(dbconnection.resultSet.getString(2));
					login.setPassword(dbconnection.resultSet.getString(3));
				}
				dbconnection.closeconnection();
			}
			else
			{
			
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("IN Error in resultset:::::"+e);
		}
		return login;
	}
	
	public boolean deleteLogin(String id)
	{
		boolean success=false;
		String insertquery = "Delete from  Login where id = "+id;
		System.out.println("Query:::::::;"+insertquery);
		Dbconnection dbconnection = null;
		dbconnection = Dbconnection.getInstance();
	
		if(dbconnection.openconnection()==true)
		{
			success = dbconnection.executeCommand(insertquery);
			dbconnection.closeconnection();
		}
		else
		{
			
		}
		return success;
	}
	
	public boolean editLogin(Login login)
	{
		boolean success=false;
		String insertquery = "Update Login set uname='"+login.getUname()+"' , password='"+login.getPassword()+"' where id="+login.getId();
		
		System.out.println("Query:::::::;"+insertquery);
		Dbconnection dbconnection = null;
		dbconnection = Dbconnection.getInstance();
	
		if(dbconnection.openconnection()==true)
		{
			success = dbconnection.executeCommand(insertquery);
			dbconnection.closeconnection();
		}
		else
		{
			
		}
		return success;
	}
	
}
