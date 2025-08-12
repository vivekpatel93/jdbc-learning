package com.vivek.jdbclearning.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
    private static final String url="jdbc:mysql://localhost:3306/use_your_dbname";
    private static final String username="username";
    private static final String password="password";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(ClassNotFoundException cc){
            System.out.println(cc.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String query=String.format("INSERT INTO students(name,age,marks) VALUES('%s',%d,%f)","Vivek",22,92.5d);
            int row_change=statement.executeUpdate(query);
            if(row_change>0){
                System.out.println("Well! Data Inserted Successfully.");
            }else{
                System.out.println("Data not inserted.");
            }

        }catch(SQLException s){
            s.printStackTrace();
        }

    }
}
