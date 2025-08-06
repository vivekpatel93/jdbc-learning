package jdbclearning.statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class SelectData {
    private static final String url="jdbc:mysql://localhost:3306/use_your_dbname";
    private static final String username="username";
    private static final String password="password";

    public static void main(String[] args) {
        Connection con=null;
        Statement statement = null;
        try{
            // Load the diver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }

        try{

            // Establish the connection
            con=DriverManager.getConnection(url,username,password);
            statement=con.createStatement();
            String query=String.format("SELECT * FROM students WHERE id=%d",2);
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                double marks=rs.getDouble("marks");
                System.out.println(id);
                System.out.println(name);
                System.out.println(age);
                System.out.println(marks);
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
        finally {
            // Always close Statement and Connection in reverse order of creation
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
