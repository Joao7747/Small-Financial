/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class ConfiguracaoContaController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
        
    }    
    
    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }
    
//    private void Listagem(){
//        
//    }
//    
//    @FXML
//    private void Salvar() {
//        DAOUsuario usuario = new DAOUsuario();
//        
//    }
}
