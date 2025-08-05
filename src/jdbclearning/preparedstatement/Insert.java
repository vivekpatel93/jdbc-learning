package jdbclearning.preparedstatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Insert {
    private static final String URL="jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    public static void main(String[] args){
        Connection connection=null;
        PreparedStatement preparedstatement=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query="INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.setString(1,"Dhananjay");
            preparedstatement.setInt(2,26);
            preparedstatement.setDouble(3,98.5d);
            int row_change=preparedstatement.executeUpdate();
            if(row_change>0){
                System.out.println("Row inserted successfully.");
            }else{
                System.out.println("Row did not insert.");
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
        try{
            if(connection!=null){
                connection.close();
            }
            if(preparedstatement != null){
                preparedstatement.close();
            }
        }catch(SQLException closeExp){
            System.out.println(closeExp.getMessage());
        }
    }
}
