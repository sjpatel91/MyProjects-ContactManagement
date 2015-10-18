package dao;

import javax.servlet.http.HttpServletRequest;

import databaseconnection.Dbconnection;

import pojo.User;

public class EmployeeDal 
{
	public User assembleEmployeeforinsert(HttpServletRequest request) {
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("LastName"));
		user.setContactNo(request.getParameter("contactNO"));
		user.setEmailID(request.getParameter("emailID"));
		user.setPassword(request.getParameter("password"));
		user.setId(Integer.parseInt(request.getParameter("id")));
		return user;
	}
	
	public boolean insertEmployee(User user) {
		boolean success=false;
		String insertquery = "insert into user (FirstName,LastName,ContactNo,EmailID,password,userMasterID,active) values('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getContactNo()+"','"+user.getEmailID()+"','"+user.getPassword()+"',2,1)";
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
	
	public User dataEmployee(String id,User user)
	{
		boolean success=false;
		String insertquery = "select * from  User where userID = "+id;
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
					user.setId(dbconnection.resultSet.getInt(1));
					user.setFirstName(dbconnection.resultSet.getString(2));
					user.setLastName(dbconnection.resultSet.getString(3));
					user.setContactNo(dbconnection.resultSet.getString(4));
					user.setEmailID(dbconnection.resultSet.getString(5));
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
		return user;
	}
	
	public boolean deleteEmployee(String id)
	{
		boolean success=false;
		String insertquery = "Delete from  user where userID = "+id;
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
	
	public boolean activeDeactiveEmployee(String id,String flag)
	{
		boolean success=false;
		String insertquery = "";
		if(flag.equals("0"))
		{
			insertquery = "update user set active=1 where userID = "+id;
		}
		else
		{
			insertquery = "update user set active=0 where userID = "+id;
		}
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
	
	public boolean editEmployee(User user)
	{
		boolean success=false;
		String insertquery = "Update user set FirstName='"+user.getFirstName()+"',LastName='"+user.getLastName()+"',contactNo='"+user.getContactNo()+"',EmailID='"+user.getEmailID()+"' where userid="+user.getId();
		
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
