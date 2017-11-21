package scavTweets.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import scavTweets.util.Constant;

public class DB {
      
    static {  
        try {  
            //加载驱动  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            System.err.println("驱动加载出错！");  
            e.printStackTrace();  
        }  
    }  
      
    public static Connection getConnection() {  
        Connection con = null;  
          
        try {  
            con = DriverManager.getConnection(Constant.DB_URL , Constant.DB_USERNAME , Constant.DB_PASSWORD);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        };  
          
        return con;  
    }  
}
