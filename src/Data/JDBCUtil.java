package Data;
import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection()  {
        Connection c=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url ="jdbc:mysql://localhost:3306/vocabulary";
            String user="root";
            String password="Mattroirucro1901@";
            c=DriverManager.getConnection(url,user,password);

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
    public static void closeConnection(Connection c){
        try{
            if(c!=null){
                c.close();}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void printInfo(Connection c) throws SQLException {
        if(c!=null){
            DatabaseMetaData mtdt =c.getMetaData();
            System.out.println(mtdt.getDatabaseProductName());
            System.out.println(mtdt.getDatabaseProductVersion());
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // Handle any potential exceptions here, such as logging or rethrowing
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // Handle any potential exceptions here, such as logging or rethrowing
                e.printStackTrace();
            }
        }
    }
}