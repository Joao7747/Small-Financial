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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class ContasInsertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<?> cbCategoria;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtNumParcela;

    @FXML
    private TextArea txtObsevacoes;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtVencimento;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton rbParcelado;

    @FXML
    private RadioButton rbFixa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
