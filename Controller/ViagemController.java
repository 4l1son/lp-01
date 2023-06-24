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
import Controller.PessoaController.Lancamento;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import DAO.ViagemDAO;

public class ViagemController implements Initializable {
    @FXML
    private TextField inicio;

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<Lancamento, String> col_inicio;

    @FXML
    private TableColumn<Lancamento, String> col_id;

    @FXML
    private TableColumn<Lancamento, String> col_fim;

    @FXML
    private TableColumn<Lancamento, String> col_destino;

    @FXML
    private TextField fim;

    @FXML
    private TextField destino;

    @FXML
    private TableView<Lancamento> table_lancamento;

    private ObservableList<Lancamento> tableData;

    private Connection con;

    @FXML
    void irLancar(ActionEvent event) {
        String inicioText = inicio.getText();
        String fimText = fim.getText();
        String destinoText = destino.getText();
        ViagemDAO viagem = new ViagemDAO(con);
        viagem.salvar(inicioText, fimText, destinoText);

        // Criar um objeto Lancamento com os dados fornecidos
        Lancamento lancamento = new Lancamento(inicioText, fimText, destinoText);

        // Adicionar o objeto Lancamento à lista observável
        tableData.add(lancamento);

        // Limpar os campos de entrada de texto
        inicio.clear();
        fim.clear();
        destino.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Factory factory = new Factory();
        con = factory.getConnection();

        // Configurar as colunas da tabela
        col_inicio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInicio()));
        col_fim.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFim()));
        col_destino.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestino()));

        // Criar uma lista observável para armazenar os dados da tabela
        tableData = FXCollections.observableArrayList();

        try {
            // Carregar os dados do banco para a lista observável
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM viagem");
            while (rs.next()) {
                String inicioText = rs.getString("inicio");
                String fimText = rs.getString("fim");
                String destinoText = rs.getString("destino");
                Lancamento lancamento = new Lancamento(inicioText, fimText, destinoText);
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
        private final SimpleStringProperty inicio;
        private final SimpleStringProperty fim;
        private final SimpleStringProperty destino;

        public Lancamento(String inicio, String fim, String destino) {
            this.inicio = new SimpleStringProperty(inicio);
            this.fim = new SimpleStringProperty(fim);
            this.destino = new SimpleStringProperty(destino);
        }

        public String getInicio() {
            return inicio.get();
        }

        public String getFim() {
            return fim.get();
        }

        public String getDestino() {
            return destino.get();
        }

        public void setInicio(String inicioText) {
            inicio.set(inicioText);
        }

        public void setFim(String fimText) {
            fim.set(fimText);
        }

        public void setDestino(String destinoText) {
            destino.set(destinoText);
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
        ViagemDAO viagem = new ViagemDAO(con);
        viagem.excluir(selecionado);

        // Remover o objeto selecionado da lista observável
        tableData.remove(selecionado);

        // Limpar os campos de entrada de texto
        inicio.clear();
        fim.clear();
        destino.clear();
    }

    @FXML
    void atualizarLancamento(ActionEvent event)  throws SQLException {
        // Verificar se uma pessoa da tabela está selecionada
        Lancamento selecionado = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            // Nenhuma pessoa selecionada, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhuma pessoa selecionada.");
            return;
        }

       
            // Obter os novos valores dos campos de entrada de texto
         String novoInicio = inicio.getText();
         String novoFim = fim.getText();
         String novoDestino = destino.getText();

            // Atualizar os valores do lançamento selecionado no banco de dados
         ViagemDAO viagem = new ViagemDAO(con);
         viagem.atualizar(novoInicio, novoFim, novoDestino);

            // Atualizar os valores do lançamento selecionado na tabela
         selecionado.setInicio(novoInicio);
         selecionado.setFim(novoFim);
         selecionado.setDestino(novoDestino);

            // Atualizar a tabela para refletir as alterações
         table_lancamento.refresh();

            // Limpar os campos de entrada de texto
         inicio.clear();
         fim.clear();
         destino.clear();
        
    }
}

