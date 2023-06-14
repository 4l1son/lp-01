package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btnField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField senhaField;

    @FXML
    void irLogin(ActionEvent event) {
     String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            MensagemRetorno.erro("Por favor, preencha todos os campos.");
            return;
        }

        var usuario = usuarioDAO.getUsuarioBy(email);

        if (usuario == null) {
            MensagemRetorno.erro("Email e/ou senha incorretos.");
            return;
        }

        if (email.trim().equalsIgnoreCase(usuario.getEmail().trim())
                && password.equals(usuario.getCpf_cnpj().substring(0, 3))) {

            UsuarioDAO.usuarioLogado = usuario;
            var tipoUsuario = usuario.getIdTipoUsuario();

            if (tipoUsuario == TipoUsuario.Gestor) {
                MenuController mc = new MenuController();
                mc.irMenuFeedBack(null);
            } else if (tipoUsuario == TipoUsuario.Administrador) {
                MenuController.irMenu();
            } else {
                MenuController.irMenuUsuario();
            }

        } else {
            MensagemRetorno.erro("Email e/ou senha incorretos.");
        }

    }

}
