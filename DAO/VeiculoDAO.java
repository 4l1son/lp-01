package DAO;

import Conexao.Factory;
import Controller.PessoaController.Lancamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
	private Connection connection;

    public VeiculoDAO(Connection connection) {
    	this.connection = connection;
    }

    public VeiculoDAO() {
        this.connection = Factory.getConnection();
    }

    public void salvar(String nome, String porte, String ano) {
        String sql = "INSERT INTO veiculo (nome, porte, ano) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, porte);
            statement.setString(3, ano);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String string) {
        String sql = "DELETE FROM veiculo WHERE ano = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, string);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Lancamento selecionada, String nome, String porte, String ano) {
        String sql = "UPDATE veiculo SET nome = ?, porte = ?, ano = ? WHERE ano = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, porte);
            statement.setString(3, ano);
            statement.setString(4, selecionada.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
