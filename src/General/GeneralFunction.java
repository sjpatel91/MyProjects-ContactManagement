package General;

import java.sql.SQLException;

import databaseconnection.Dbconnection;

public class GeneralFunction {

	private int totalRecord=0;
	private int recordsPerPage=10;
	private int pageNo=1;
	private int totalPage=0;
	private int recordNo=0;
	private String pageName;
	
	
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(int recordNo) {
		this.recordNo = recordNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public void count(String sql) 
	{
		int dbCount = 0;
		int temp=0;
		int count=0;
		Dbconnection connection = null;
		connection = Dbconnection.getInstance();
		try 
		{
			connection.openconnection();
			connection.resultSet =connection.data(sql);
			while (connection.resultSet.next()) 
			{
				dbCount = (connection.resultSet.getInt(1));
			}
			count = dbCount / this.getRecordsPerPage();
			temp = dbCount % this.getRecordsPerPage();
			if (temp != 0) 
			{
				count++;
			}
			setTotalPage(count);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error ::::::in count:::::"+e);
		}
		finally
		{
			connection.closeconnection();
		}
	}
	
	public void recordno() 
	{
		if (this.getPageNo() == 1) 
		{
            this.setRecordNo(0);
        } else 
        {
            this.pageNo--;
            this.setRecordNo(2 * this.getPageNo());
        }
	}
	
	public void recordnoForDelete(String sql) 
	{
		count(sql);
		if(this.getPageNo()==this.getTotalPage() && this.getTotalPage()%2!=0)
		{
            this.setPageNo(pageNo--);
        }
	}
	
	public String gridData(String sqlCount,String sql, String pageName,String heading[],String parameter[],String width[])
	{
		Dbconnection connection = null;
		connection = Dbconnection.getInstance();
		this.setPageName(pageName);
		count(sqlCount);
		String str ="";
		try 
		{
			str += "<table>";
			str+="<tr>";
			for(int i=0;i<heading.length;i++)
			{
				str+="<td>";
				str+=heading[i];
				str+="</td>";
			}
			str+="<td>Action</td>";
			str+="</tr>";
			connection.openconnection();
			connection.resultSet = connection.data(sql);
			int count=this.getRecordNo();
			while(connection.resultSet.next())
			{
				count++;
				str+="<tr>";
				str+="<td>";
				str+=count;
				str+="</td>";
				for(int j=0;j<parameter.length;j++)
				{
					str+="<td>";
					str+=connection.resultSet.getObject(Integer.parseInt(parameter[j]));
					str+="</td>";
				}
				str+="<td><a href=\"#\" onclick=edit("+connection.resultSet.getObject(Integer.parseInt(parameter[0]))+",'EditPage')>Edit</a>&nbsp;&nbsp;<a href=\"#\" onclick=deleteData("+connection.resultSet.getObject(Integer.parseInt(parameter[0]))+",'"+this.getPageName()+"','"+this.getPageNo()+"')>Delete</a></td>";
				str+="</tr>";
			}
			if(count==0)
			{
				str+="<tr><td colspan="+parameter.length+" align=\"center\">No Record Found</td></tr>";
			}
			str+="</table><table>";
			str+="<tr>";
			for(int i=1;i<=this.getTotalPage();i++)
			{
				str+="<td>";
				str+="<a href=\"#\" onclick=grid('"+pageName+"',"+i+")>";
				str+=i;
				str+="</a>";
				str+="</td>";
			}
			str+="</tr></table>";
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Error::::In Grid:::"+e);
		}
		finally
		{
			connection.closeconnection();
		}
		return str;
	}
	
	public String gridDataPageLoad(String sqlCount,String sql, String pageName,String heading[],String parameter[],String width[],String Actions[])
	{
		Dbconnection connection = null;
		connection = Dbconnection.getInstance();
		this.setPageName(pageName);
		count(sqlCount);
		String str ="";
		try 
		{
			str += "<table>";
			str+="<tr>";
			for(int i=0;i<heading.length;i++)
			{
				str+="<td>";
				str+=heading[i];
				str+="</td>";
			}
			str+="<td>Action</td>";
			str+="</tr>";
			connection.openconnection();
			connection.resultSet = connection.data(sql);
			int count=this.getRecordNo();
			while(connection.resultSet.next())
			{
				count++;
				str+="<tr>";
				str+="<td>";
				str+=count;
				str+="</td>";
				for(int j=0;j<parameter.length;j++)
				{
					str+="<td>";
					str+=connection.resultSet.getObject(Integer.parseInt(parameter[j]));
					str+="</td>";
				}
				for(int k=0;k<Actions.length;k++)
				{
					str+="<td>";
					if(Actions[k].equalsIgnoreCase("edit"))
					{
						str+="<a href=\"#\" onclick=edit("+connection.resultSet.getObject(1)+",'EditPage')>Edit</a>";
					}
					else if(Actions[k].equalsIgnoreCase("delete"))
					{
						str+="<a href=\"#\" onclick=deleteData("+connection.resultSet.getObject(1)+",'"+this.getPageName()+"','"+this.getPageNo()+"')>Delete</a>";
					}
					else if(Actions[k].equalsIgnoreCase("active"))
					{
						if(connection.resultSet.getBoolean(8)==true)
						{
							str+="<a href=\"#\" onclick=activeDeactive("+connection.resultSet.getObject(1)+",'"+this.getPageName()+"','"+this.getPageNo()+"',1)>Active</a>";
						}
						else if(connection.resultSet.getBoolean(8)==false)
						{
							str+="<a href=\"#\" onclick=activeDeactive("+connection.resultSet.getObject(1)+",'"+this.getPageName()+"','"+this.getPageNo()+"',0)>Deactive</a>";
						}
					}
					str+="</td>";
				}
				str+="<td>&nbsp;&nbsp;</td>";
				str+="</tr>";
			}
			if(count==0)
			{
				str+="<tr><td colspan="+parameter.length+" align=\"center\">No Record Found</td></tr>";
			}
//			str+="</table>";
			str+="<tr>";
			for(int i=1;i<=this.getTotalPage();i++)
			{
				str+="<td>";
				str+="<a href=\"#\" onclick=grid('"+pageName+"',"+i+")>";
				str+=i;
				str+="</a>";
				str+="</td>";
			}
			str+="</tr></table>";
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Error::::In Grid:::"+e);
		}
		finally
		{
			connection.closeconnection();
		}
		return str;
	}
	
	public String ExportData(String sql,String heading[],String parameter[])
	{
		Dbconnection connection = null;
		connection = Dbconnection.getInstance();
		String str="";
		try
		{
			for(int i=0;i<heading.length;i++)
			{
				str+="\t"+heading[i];
			}
			str+="\n";
			//String sql1 = "select * from page_main";
			connection.openconnection();
			connection.resultSet = connection.data(sql);
			while(connection.resultSet.next())
			{
				if(connection.resultSet.getRow()>0)
				{	
					for(int j=0;j<parameter.length;j++)
					{
						str+="\t"+connection.resultSet.getObject(Integer.parseInt(parameter[j]));
					}
					str+="\n";
				}
				else
				{
					str="";
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("Error In Export:::"+e);
		}
		finally
		{
			connection.closeconnection();
		}
		return str;
	}
	
}
