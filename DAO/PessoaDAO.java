package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PessoaDAO {
    private Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(String nome, String idade, String CPF) {
        String sql = "INSERT INTO pessoa (nome, idade, CPF) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, idade);
            statement.setString(3, CPF);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(int id, String nome, String idade, String CPF) {
        String sql = "UPDATE pessoa SET nome = ?, idade = ?, CPF = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, idade);
            statement.setString(3, CPF);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
