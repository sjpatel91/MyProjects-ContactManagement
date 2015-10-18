/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnection {
	
	public static String URL="jdbc:mysql://localhost:3306/shruti";
	public static String PASSWORD="cs532_12539";
	public static String USERNAME="cs532_12539";
	public  Statement statement=null;
	
	public Connection objConnection=null;
	
	private static Dbconnection dbConnection;
	
	public ResultSet resultSet = null;

	public Dbconnection()
	{
		dbConnection=null;
	}
	
	
	public static Dbconnection getInstance()
	{
		if(dbConnection==null)
		{
			dbConnection = new Dbconnection();
		}
		return dbConnection;
	}
	
	public boolean openconnection() 
        {
		boolean conreturnvalue=false;
		try 
                {
			Class.forName("com.mysql.jdbc.Driver");
			objConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			if(objConnection== null)
			{
				System.out.println("Error");
                                conreturnvalue = false;
			}
                        else
                        {
                            conreturnvalue = true;
                        }
		} 
                catch (Exception e) 
                {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conreturnvalue;
	}
        
	public  ResultSet data(String sql)
	{
		try 
		{
			statement = objConnection.createStatement();
			resultSet = statement.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
        public boolean executeCommand(String queries) 
        {
		boolean conreturnvalue=false;
		if(objConnection!=null)
		{
			try 
                        {
				statement = objConnection.createStatement();
				int a = statement.executeUpdate(queries);
				conreturnvalue = true;
			} 
                        catch (SQLException e) 
                        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return conreturnvalue;
	}
        
        public int executeGetId(String query) 
        {
        int id = 0;
        if (objConnection != null) 
        {
            try 
            {
                statement = objConnection.createStatement();
                id = statement.executeUpdate(query);
                resultSet = statement.getGeneratedKeys();
                while (resultSet.next()) 
                {
                    id = resultSet.getInt(1);
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println("Error : " + ex);
            }
        }
        //        String q="select LAST_INSERT_ID()";
        //        id = Statement.executeUpdate(q);
        return id;
    }
        
	public boolean closeconnection() 
        {
		boolean conreturnvalue=false;
		try 
                {
			objConnection.close();
			conreturnvalue=true;
		} 
                catch (SQLException e) 
                {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conreturnvalue;
	}
}
