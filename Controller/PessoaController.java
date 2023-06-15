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

import java.io.IOException;
import java.net.URL;
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


    @FXML
    void irLancar(ActionEvent event) {
        String nomeText = nome.getText();
        String idadeText = idade.getText();
        String CPFText = CPF.getText();

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
        // Configurar as colunas da tabela
        col_nome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        col_idade.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdade()));
        col_CPF.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF()));

        // Criar uma lista observável para armazenar os dados da tabela
        tableData = FXCollections.observableArrayList();

        // Atribuir a lista observável à tabela
        table_lancamento.setItems(tableData);
    }

    // Classe Lancamento que representa uma linha de dados
    private static class Lancamento {
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
    void excluirLancamento(ActionEvent event) {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = (Lancamento) table_lancamento.getSelectionModel().getSelectedItem();
        

        // Exibir uma mensagem de confirmação para o usuário
       
      
            // Remover o objeto selecionado da lista observável
            tableData.remove(selecionado);

            // Limpar os campos de entrada de texto
            nome.clear();
            idade.clear();
            CPF.clear();

            
    }
    

    @FXML
    void atualizarLancamento(ActionEvent event) {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            // Nenhum item selecionado, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhum item selecionado.");
            return;
        }

        // Obter os novos valores dos campos de entrada de texto
        String novoDestino = nome.getText();
        String novoFim = idade.getText();
        String novoInicio = CPF.getText();

        // Atualizar os valores do lançamento selecionado
        selecionado.setNome(novoDestino);
        selecionado.setIdade(novoFim);
        selecionado.setCPF(novoInicio);

        // Atualizar a tabela para refletir as alterações
        table_lancamento.refresh();

        // Limpar os campos de entrada de texto
        nome.clear();
        idade.clear();
        CPF.clear();
    }
   

}
