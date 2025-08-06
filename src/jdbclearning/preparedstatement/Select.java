package jdbclearning.preparedstatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Select {
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
            String query="SELECT * FROM students";
            preparedstatement=connection.prepareStatement(query);
            ResultSet rs=preparedstatement.executeQuery();
            if(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                double marks=rs.getDouble("marks");
                System.out.println("Student id is : "+id);
                System.out.println("Student name is : "+name);
                System.out.println("Student age is : "+age);
                System.out.println("Student marks is : "+marks);
            }

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
