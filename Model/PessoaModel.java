package Model;
import javafx.beans.property.SimpleStringProperty;


public class PessoaModel {
        private final SimpleStringProperty nome;
        private final SimpleStringProperty idade;
        private final SimpleStringProperty CPF;

        public PessoaModel(String nome, String idade, String CPF) {
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

