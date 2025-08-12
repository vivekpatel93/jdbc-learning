package com.vivek.jdbclearning.batchprocessing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Delete {
private static final String URL="jdbc:mysql://localhost:3306/your_db_name";
private static final String USERNAME="username";
private static final String PASSWORD="password";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        Connection connection=null;
        PreparedStatement preparedstatement=null;
        try{
          connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
          String query="DELETE FROM students WHERE id=?";
          preparedstatement=connection.prepareStatement(query);

          preparedstatement.setInt(1,2);
          preparedstatement.addBatch();

          preparedstatement.setInt(1,3);
          preparedstatement.addBatch();

          int[] result=preparedstatement.executeBatch();
            System.out.println("Number of deleted rows : "+result.length);
        }catch(SQLException s){
            s.printStackTrace();
        }
        finally {
            try{
                if(preparedstatement != null){
                    preparedstatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException conExp){
                System.out.println(conExp.getMessage());
            }
        }
    }
}
