package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexao.Factory;
import Controller.PessoaController.Lancamento;

public class PessoaDAO {
    private Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public PessoaDAO() {
        this.connection = Factory.getConnection();
    }

    public void salvar(String nome, String email, String CPF) {
        String sql = "INSERT INTO pessoa (nome, email, CPF) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, CPF);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Lancamento selecionado) {
        String sql = "DELETE FROM pessoa WHERE CPF = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, selecionado.getCPF());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Lancamento selecionada, String nome, String email, String CPF) {
        String sql = "UPDATE pessoa SET nome = ?, email = ?, CPF = ? WHERE CPF = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, CPF);
            statement.setString(4, selecionada.getCPF());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
