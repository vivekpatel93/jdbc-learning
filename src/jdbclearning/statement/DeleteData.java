package jdbclearning.statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
public class DeleteData {
    private static final String url="jdbc:mysql://localhost:3306/use_your_dbname";
    private static final String username="username";
    private static final String password="password";
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            con =DriverManager.getConnection(url,username,password);
            st=con.createStatement();
            String query="DELETE FROM students WHERE id=1";
            int row_update=st.executeUpdate(query);
            if(row_update>0){
                System.out.println("Row deleted successfully.");
            }else{
                System.out.println("Something went wrong.");
            }
        }catch(SQLException s){
            s.printStackTrace();
        }

        finally {
            // Always close Statement and Connection in reverse order of creation
            try {
                if (st != null) {
                    st.close();
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
