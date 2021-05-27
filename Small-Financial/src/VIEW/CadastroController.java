/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOUsuario;
import MODEL.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class CadastroController implements Initializable {

    private BorderPane rootLayout;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private PasswordField txtConfirmarSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void Cadastrar(ActionEvent event) throws IOException {
        DAOUsuario dao = new DAOUsuario();
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmacaoSenha = txtConfirmarSenha.getText();

        if (!nome.equals("") && !email.equals("") && !senha.equals("") && !confirmacaoSenha.equals("")) {
            if (senha.equals(confirmacaoSenha)) {
                Usuario u = new Usuario();
                u.setNome(nome);
                u.setEmail(email);
                u.setSenha(senha);
                dao.inserir(u);
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Cadastrado com sucesso!", ButtonType.OK);
                alerta.show();
                
                //voltar para login
                Parent voltar = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.show();
                
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Senhas não coincidem", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
            alerta.show();
        }
    }

}
