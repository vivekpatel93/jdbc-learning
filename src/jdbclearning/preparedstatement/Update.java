package jdbclearning.preparedstatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Update {
    private static final String URL="jdbc:mysql://localhost:3306/use_your_dbname";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";

    public static void main(String[] args) {
        Connection connection=null; // because this is a local variable that why necessary to initialize
        PreparedStatement preparedstatement=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query="UPDATE students SET age=? WHERE id=?";
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.setInt(1,21);
            preparedstatement.setInt(2,2);
            int row_change=preparedstatement.executeUpdate();
            if(row_change>0){
                System.out.println("Table successfully updated.");
            }else{
                System.out.println("Table not updated.");
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        try{
            if(preparedstatement != null){ // because PreparedStatement depends upon Connection
                preparedstatement.close();
            }
            if(connection != null){
                connection.close();
            }

        }catch(SQLException closeExp){
            System.out.println(closeExp.getMessage());
        }
    }
}
