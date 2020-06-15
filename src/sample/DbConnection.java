
package sample;
import java.security.spec.ECField;
import java.sql.*;
import java.lang.Exception;

public class DbConnection {

    static final private String DbName = "com.mysql.jdbc.Driver";
    static final private String DbURL = "jdbc:mysql://localhost:3306/javafxform?serverTimezone=UTC";
    static final private String DbUser = "root";
    static final private String DbPassword = "pluralsight";
    static private String DbQuery = "";

    static private Connection connection = null;
    static private Statement statement = null;
    static private PreparedStatement preparedStatement = null;
    static private ResultSet resultSet = null;


    public static void setDbQuery(String dbQuery) {
        DbQuery = dbQuery;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public static void setPreparedStatement(PreparedStatement preparedStatement) {
        DbConnection.preparedStatement = preparedStatement;
    }

    public static boolean verifyConnection() throws Exception {
        try {
            Class.forName(DbName);
            connection = DriverManager.getConnection(DbURL, DbUser, DbPassword);
        } catch (Exception e) {
            System.out.println("SQL CONNECTION ERROR: " + e.getMessage());
            connection.close();
            return false;
        }
        return true;
    }

    public static boolean getData() throws Exception {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from client");
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            statement.close();
            connection.close();
            return false;
        }

        return true;
    }

    public static boolean setData(String nomPrenom, String email, String password, String phone) throws Exception {
        DbQuery = "INSERT INTO client(nomPrenom,email,pass,phone) values (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setString(1, nomPrenom);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("INSERT FAILED!");
            return false;
        }

        preparedStatement.close();
        connection.close();
        System.out.println("INSERT DONE SUCCESSFULLY!");
        return true;
    }

    public static boolean deletData(int idClient) throws Exception {
        DbQuery = "DELETE from client where clientID = ?";
        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("DELETE FAILED!");
            return false;
        }
        preparedStatement.close();
        connection.close();
        System.out.println("DELETE DONE SUCCESSFULLY!");
        return true;
    }


    public static boolean modifyName(int idClient, String nomPrenom) throws Exception {
        DbQuery = "UPDATE client SET nomPrenom = ? WHERE clientID = ?";

        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setString(1, nomPrenom);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("MODIFY FAILED!");
            return false;
        }
        System.out.println("MODIFY NAME DONE SUCCESSFULLY!");
        return true;
    }


    public static boolean modifyEmail(int idClient, String email) throws Exception{
        DbQuery = "UPDATE client SET email = ? WHERE clientID = ?";
        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("MODIFY FAILED!");
            return false;
        }
        System.out.println("MODIFY EMAIL DONE SUCCESSFULLY!");
        return true;

    }

    public static boolean modifyPass(int idClient, String password) throws Exception{
        DbQuery = "UPDATE client SET pass = ? WHERE clientID = ?";

        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("MODIFY FAILED!");
            return false;
        }
        System.out.println("MODIFY PASS DONE SUCCESSFULLY!");
        return true;

    }

    public static boolean modifyPhone(int idClient, String phone) throws Exception{
        DbQuery = "UPDATE client SET phone = ? WHERE clientID = ?";

        try {
            preparedStatement = connection.prepareStatement(DbQuery);
            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQL STATEMENT ERROR: " + e.getMessage());
            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println("MODIFY FAILED!");
            return false;
        }
        System.out.println("MODIFY PHONE DONE SUCCESSFULLY!");
        return true;

    }
}
