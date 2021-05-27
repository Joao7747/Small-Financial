/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class CalculadoraJurosController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblContas;
    @FXML
    private Button btnCalcular1;
    @FXML
    private Button btnCalcular2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Voltar(ActionEvent event) {
    }
    
}
