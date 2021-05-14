/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
