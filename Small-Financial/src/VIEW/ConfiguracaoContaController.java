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
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class ConfiguracaoContaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    DAOUsuario user = new DAOUsuario();
    @FXML
    private Button btnLogoff;
     @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtConfirmSenha;

    @FXML
    private RadioButton rbEuro;

    @FXML
    private RadioButton rbDolar;

    @FXML
    private RadioButton rbLibras;

    @FXML
    private Button btnSaldo;

    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnVoltar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNome.setText(user.IdNome().getNome());
        txtEmail.setText(user.IdNome().getEmail());
    }    
    
    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }
    @FXML
    private void Logoff(ActionEvent event) throws IOException {
        int input = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?");
        if(input == JOptionPane.YES_OPTION){
            DAOUsuario user = new DAOUsuario();
            user.Logoff();
            Parent voltar = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene voltarScene = new Scene(voltar);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(voltarScene);
            window.centerOnScreen();
        }
        
    }
    @FXML
    private void UserUpdate(ActionEvent event) throws IOException, SQLException {
        DAOUsuario updateuser = new DAOUsuario();
        Usuario usuario = new Usuario();
        usuario.setEmail(txtEmail.getText());
        usuario.setIdUsuario(user.IdNome().getIdUsuario());
        usuario.setNome(txtNome.getText());
        usuario.setSaldo(user.IdNome().getSaldo());
        String senha = txtSenha.getText();
        System.out.println(senha);
        if(!"".equals(senha)){
            if(txtSenha.getText() == txtConfirmSenha.getText()){
                usuario.setSenha(txtSenha.getText());
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.WARNING, "As senhas não conferem!", ButtonType.OK);
                alerta.show();
            }
        }
        else{
            usuario.setSenha(user.IdNome().getSenha());
        }
        updateuser.alterar(usuario);
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Alterado com sucesso!", ButtonType.OK);
        alerta.show();
    }
}
