package DAL;
import java.sql.*;

class DatabaseAccess {
    // private static String conString = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=QLVPP;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static String conString = "jdbc:mysql://localhost:3306/qlvpp?useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String passwd = "";
    protected static Connection conn;
    protected static Statement statement;
    protected static PreparedStatement ps;
    protected static ResultSet resultSet;
    protected static ResultSet resultSet1;

    protected static void getConnection(){
        try{
            conn = DriverManager.getConnection(conString, username, passwd);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    protected static void closeConnection(){
        try{
        	if (conn != null)
        		conn.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
