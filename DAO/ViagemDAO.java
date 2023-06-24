package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Controller.ViagemController.Lancamento;

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

    public void excluir(Lancamento lancamento) {
        String sql = "DELETE FROM viagem WHERE destino = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lancamento.getDestino());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(String novoInicio, String novoFim, String novoDestino) {
        String sql = "UPDATE viagem SET inicio = ?, fim = ? WHERE destino = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoInicio);
            statement.setString(2, novoFim);
            statement.setString(3, novoDestino);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
