package jdbclearning.preparedstatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Delete {
    private static final String URL="jdbc:mysql://localhost:3306/use_your_dbname";
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
            String query="DELETE FROM students WHERE id=?";
            preparedstatement=connection.prepareStatement(query);
            preparedstatement.setInt(1,4);
            int row_change=preparedstatement.executeUpdate();
            if(row_change>0){
                System.out.println("Row deleted successfully.");
            }else{
                System.out.println("Row not deleted something went wrong.");
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
        finally{
            try{
                if(preparedstatement != null){
                    preparedstatement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch(SQLException conExp){
                System.out.println(conExp.getMessage());
            }
        }
    }
}
