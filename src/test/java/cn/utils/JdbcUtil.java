package cn.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtil {
   private static	Properties properties=null;
   private static   Connection conn=null;
   private static 	PreparedStatement pStatement=null;
   
	static{
		
		properties=new Properties();
		try {
			
			properties.load(JdbcUtil.class.getResourceAsStream("db.properties"));
			Class.forName(properties.getProperty("driver"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
	
	public static void closeAll(ResultSet rs,PreparedStatement st,Connection conn){
		
		 if(rs != null){
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(st != null){
	            try {
	                st.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(conn != null){
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		
	}
	//获取用户customid
	public static String gainCustomid(String phone)
	{
		
		String sql="SELECT customer_id FROM UserLogin  where phone=?";
		ResultSet resultSet=null;
		ResultSetMetaData rSetMetaData;
		String customid="";
		try {
			
			conn=JdbcUtil.getConnection();
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, phone);
			resultSet=pStatement.executeQuery();
			while(resultSet.next()){
		    customid=resultSet.getString("customer_id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return customid;
	}
	
	
	
	
	//删除普通用户
	public static boolean deleteUser(String phone)
	{
		String sql="DELETE from UserLogin  WHERE phone=?";
		boolean flog=true;
		try {
			conn=JdbcUtil.getConnection();
			pStatement =conn.prepareStatement(sql);
	        pStatement.setString(1, phone);
	        int i=pStatement.executeUpdate();
	        if(i==0)
	        {
	        	flog=false;
	        	
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(null, pStatement, conn);
			
			
			
		}
    	
        
        return flog;
        
	}
	
	
	public static Connection getConnection() throws IOException, SQLException
	{
		
	    Connection connection=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
		return connection;
		
	}
	
	public static void main(String[] args) throws IOException {
		
	System.out.println(gainCustomid("18244281624"))	;
		
	}
	
	

}
