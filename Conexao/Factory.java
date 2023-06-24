package Conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
    private static final String URL = "jdbc:mysql://localhost:3306/crud-javafx";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public Factory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
