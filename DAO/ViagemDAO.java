package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViagemDAO {
    private Connection connection;

    public ViagemDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(String inicio, String fim, String destino) {
        String sql = "INSERT INTO viagem (inicio, fim, destino) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, inicio);
            statement.setString(2, fim);
            statement.setString(3, destino);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM viagem WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(String inicio, String fim, String destino) {
        String sql = "UPDATE viagem SET inicio = ?, fim = ?, destino = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, inicio);
            statement.setString(2, fim);
            statement.setString(3, destino);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
