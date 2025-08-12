package com.vivek.jdbclearning.batchprocessing;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
public class Insert {
    private static final String URL="jdbc:mysql://localhost:3306/your_db_name";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        Connection connection=null;
        PreparedStatement preparedstatement=null;
        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query="INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.setString(1,"Abhinav");
            preparedstatement.setInt(2,24);
            preparedstatement.setDouble(3,81.7d);
            preparedstatement.addBatch();

            preparedstatement.setString(1,"Aman");
            preparedstatement.setInt(2,52);
            preparedstatement.setDouble(3,75.5d);
            preparedstatement.addBatch();

            preparedstatement.setString(1,"Yash");
            preparedstatement.setInt(2,55);
            preparedstatement.setDouble(3,87.5d);
            preparedstatement.addBatch();

            int[] result=preparedstatement.executeBatch();
            System.out.println("Number of update rows are : "+result.length);
        }catch(SQLException s){
            s.printStackTrace();
        }
        finally{
            try{
                if(connection != null){
                    connection.close();
                }
                if(preparedstatement != null){
                    preparedstatement.close();
                }
            }catch(SQLException expConn){
                System.out.println(expConn.getMessage());
            }
        }
    }
}
