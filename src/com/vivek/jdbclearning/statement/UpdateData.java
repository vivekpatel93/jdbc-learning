package com.vivek.jdbclearning.statement;

import java.sql.*;

public class UpdateData {
    private static final String url="jdbc:mysql://localhost:3306/use_your_dbname";
    private static final String username="username";
    private static final String password="password";
    public static void main(String[] args) {
        try{
            // 1. Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            // 2. Establish connection
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String query=String.format("UPDATE students SET age = %d WHERE id = %d",22,2);
            int rs=statement.executeUpdate(query);
            if(rs>0){
                System.out.println("Update successful.");
            }else{
                System.out.println("Update not successful.");
            }

        }catch (SQLException s){
            s.printStackTrace();
        }
    }
}
