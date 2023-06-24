package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Conexao.Factory;
import DAO.PessoaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PessoaController implements Initializable {
    @FXML
    private TextField CPF;

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<Lancamento, String> col_CPF;

    @FXML
    private TableColumn<Lancamento, String> col_id;

    @FXML
    private TableColumn<Lancamento, String> col_idade;

    @FXML
    private TableColumn<Lancamento, String> col_nome;

    @FXML
    private TextField idade;

    @FXML
    private TextField nome;

    @FXML
    private TableView<Lancamento> table_lancamento;

    private ObservableList<Lancamento> tableData;

    private Connection con;

    @FXML
    void irLancar(ActionEvent event) throws SQLException {
        String nomeText = nome.getText();
        String idadeText = idade.getText();
        String CPFText = CPF.getText();
        PessoaDAO pessoa = new PessoaDAO(con);
        pessoa.salvar(nomeText, idadeText, CPFText);

        // Criar um objeto Lancamento com os dados fornecidos
        Lancamento lancamento = new Lancamento(nomeText, idadeText, CPFText);

        // Adicionar o objeto Lancamento à lista observável
        tableData.add(lancamento);

        // Limpar os campos de entrada de texto
        nome.clear();
        idade.clear();
        CPF.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Factory factory = new Factory();
        con = factory.getConnection();

        // Configurar as colunas da tabela
        col_nome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        col_idade.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdade()));
        col_CPF.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF()));

        // Criar uma lista observável para armazenar os dados da tabela
        tableData = FXCollections.observableArrayList();

        try {
            // Carregar os dados do banco para a lista observável
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pessoa");
            while (rs.next()) {
                String nomeText = rs.getString("nome");
                String idadeText = rs.getString("email");
                String CPFText = rs.getString("CPF");
                Lancamento lancamento = new Lancamento(nomeText, idadeText, CPFText);
                tableData.add(lancamento);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao carregar lançamentos: " + e.getMessage());
        }

        // Atribuir a lista observável à tabela
        table_lancamento.setItems(tableData);
    }

    // Classe Lancamento que representa uma linha de dados
    public static class Lancamento {
        private final SimpleStringProperty nome;
        private final SimpleStringProperty idade;
        private final SimpleStringProperty CPF;

        public Lancamento(String nome, String idade, String CPF) {
            this.nome = new SimpleStringProperty(nome);
            this.idade = new SimpleStringProperty(idade);
            this.CPF = new SimpleStringProperty(CPF);
        }

        public String getNome() {
            return nome.get();
        }

        public String getIdade() {
            return idade.get();
        }

        public String getCPF() {
            return CPF.get();
        }

        public void setNome(String nomeText) {
            nome.set(nomeText);
        }

        public void setIdade(String idadeText) {
            idade.set(idadeText);
        }

        public void setCPF(String CPFText) {
            CPF.set(CPFText);
        }
    }

    @FXML
    public void irMenu() {
        // Carregar o arquivo FXML do menu
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/crud/view/menu.fxml"));
            Parent root = loader.load();

            // Obter o controlador do menu
            MenuController menuController = loader.getController();

            // Obter o estágio atual
            Stage stage = (Stage) btn_lancar.getScene().getWindow();

            // Configurar o estágio e exibir o menu
            if (stage != null) {
                menuController.setStage(stage);
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.err.println("Stage is not set.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    void excluirLancamento(ActionEvent event) throws SQLException {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            // Nenhum item selecionado, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhum item selecionado.");
            return;
        }

        // Remover o objeto selecionado do banco de dados
        PessoaDAO pessoa = new PessoaDAO(con);
        pessoa.excluir(selecionado);

        // Remover o objeto selecionado da lista observável
        tableData.remove(selecionado);

        // Limpar os campos de entrada de texto
        nome.clear();
        idade.clear();
        CPF.clear();
    }

    @FXML
    void atualizarLancamento(ActionEvent event) throws SQLException {
        // Verificar se uma pessoa da tabela está selecionada
        Lancamento selecionada = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            // Nenhuma pessoa selecionada, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhuma pessoa selecionada.");
            return;
        }

        // Obter os novos valores dos campos de entrada de texto
        String novoNome = nome.getText();
        String novaIdade = idade.getText();
        String novoCpf = CPF.getText();

        // Atualizar os valores da pessoa selecionada no banco de dados
        PessoaDAO pessoa = new PessoaDAO(con);
        pessoa.atualizar(selecionada, novoNome, novaIdade, novoCpf);

        // Atualizar os valores da pessoa selecionada na tabela
        selecionada.setNome(novoNome);
        selecionada.setIdade(novaIdade);
        selecionada.setCPF(novoCpf);

        // Atualizar a tabela para refletir as alterações
        table_lancamento.refresh();

        // Limpar os campos de entrada de texto
        nome.clear();
        idade.clear();
        CPF.clear();
    }

}
