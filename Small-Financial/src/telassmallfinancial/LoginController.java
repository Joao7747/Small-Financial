/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import Auditoria.MainThread;
import DAO.DAOUsuario;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Caio
 */
public class LoginController implements Initializable {

    DAOUsuario usuario = new DAOUsuario();

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCadastrar;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void changeScreen(ActionEvent event) throws IOException {
        Parent cadastro = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
        Scene cadastroScene = new Scene(cadastro);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(cadastroScene);
        window.show();
    }

    @FXML
    public Boolean verificaLogin() {
        String email = txtUser.getText();
        String senha = txtSenha.getText();
        Boolean verifica = usuario.UsuarioLogado(email, senha);
        return verifica;
    }

    @FXML
    public void menu(ActionEvent event) throws IOException, InterruptedException {
        if (verificaLogin()) {
            MainThread auditoria = new MainThread();
            auditoria.user = txtUser.getText();
            auditoria.StartThread("Menu");
            //fim do inicio a auditoria

            Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene menuScene = new Scene(menu);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(menuScene);
            window.show();
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Usuário e/ou senha incorretos!", ButtonType.OK);
            alerta.show();
        }
        //iniciar a auditoria

    }
}
