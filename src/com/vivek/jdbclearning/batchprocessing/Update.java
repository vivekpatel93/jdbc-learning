package com.vivek.jdbclearning.batchprocessing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Update {
    private static final String URL="jdbc:mysql://localhost:3306/your_db_name";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        Connection connection = null;
        PreparedStatement preparedstatement=null;
        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String url="UPDATE students SET age=? WHERE id=?";
            preparedstatement=connection.prepareStatement(url);
            preparedstatement.setInt(1,23);
            preparedstatement.setInt(2,7);
            preparedstatement.addBatch();

            preparedstatement.setInt(1,24);
            preparedstatement.setInt(2,8);
            preparedstatement.addBatch();
             int[]result=preparedstatement.executeBatch();
            System.out.println("No. of updated rows : "+result.length);
        }catch(SQLException s){
            s.printStackTrace();

        }
        finally{
            try{
                if(preparedstatement != null){
                    preparedstatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException expConn){
            System.out.println(expConn.getMessage());
            }
        }
    }
}
