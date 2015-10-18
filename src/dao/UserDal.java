package dao;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import databaseconnection.Dbconnection;

import pojo.User;

public class UserDal 
{
	public User assembleUserforprofile(HttpServletRequest request) 
	{
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("LastName"));
		user.setContactNo(request.getParameter("contactNO"));
		user.setEmailID(request.getParameter("emailID"));
		user.setPassword(request.getParameter("password"));
		user.setId(Integer.parseInt(request.getParameter("id")));
		return user;
	}
	
	public User assembleUserforchangePassword(HttpServletRequest request) 
	{
		User user1 = new User();
		user1.setNewPassword(request.getParameter("newPassword"));
		user1.setPassword(request.getParameter("password"));
		user1.setId(Integer.parseInt(request.getParameter("id")));
		return user1;
	}

	
	public boolean editPassword(User user) throws SQLException
	{
		boolean success=false;
		String sql = "select * from user where userID = "+user.getId()+" and password='"+user.getPassword()+"'";
		String insertquery = "Update user set password='"+user.getNewPassword()+"' where userid="+user.getId();
		
		System.out.println("Query:::::::;"+insertquery);
		Dbconnection dbconnection = null;
		dbconnection = Dbconnection.getInstance();
		int size=0;
		if(dbconnection.openconnection()==true)
		{
			dbconnection.resultSet=dbconnection.data(sql);
			while(dbconnection.resultSet.next())
			{
				size=dbconnection.resultSet.getRow();
			}
			System.out.println("size::::::"+size);
			if(size>0)
			{
				success = dbconnection.executeCommand(insertquery);
			}
			else
			{
				success=false;
			}
			dbconnection.closeconnection();
		}
		else
		{
			
		}
		if(success==true)
		{
			user.getArrayList().add(user);
		}
		return success;
	}
	
	public User dataUserProfile(User user) throws SQLException
	{
		boolean success=false;
		String insertquery = "select * from User where userid="+user.getId();
		
		System.out.println("Query:::::::;"+insertquery);
		Dbconnection dbconnection = null;
		dbconnection = Dbconnection.getInstance();
	
		if(dbconnection.openconnection()==true)
		{

			dbconnection.resultSet = dbconnection.data(insertquery);
			while(dbconnection.resultSet.next())
			{
				user = new User();
				user.setFirstName(dbconnection.resultSet.getString(2));
				user.setLastName(dbconnection.resultSet.getString(3));
				user.setContactNo(dbconnection.resultSet.getString(4));
				user.setEmailID(dbconnection.resultSet.getString(5));
				user.setUserMasterID(dbconnection.resultSet.getInt(7));
				user.setActive(dbconnection.resultSet.getBoolean(8));
				
			}
			dbconnection.closeconnection();
			
		}
		else
		{
			
		}
		return user;
	}
	
	public boolean editUserProfile(User user)
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
		if(success==true)
		{
			user.getArrayList().add(user);
		}
		return success;
	}
	
}
