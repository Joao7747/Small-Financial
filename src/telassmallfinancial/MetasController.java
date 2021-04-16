/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MetasController implements Initializable {

    @FXML
    private Label lblContas;
    @FXML
    private TableView<?> tvContas;
    @FXML
    private TableColumn<?, ?> tcCategoria;
    @FXML
    private TableColumn<?, ?> tcDescricao;
    @FXML
    private TableColumn<?, ?> tcValor;
    @FXML
    private TableColumn<?, ?> tcParcelas;
    @FXML
    private TableColumn<?, ?> tcVencimentos;
    @FXML
    private TableColumn<?, ?> tcObservacao;
    @FXML
    private TableColumn<?, ?> tcStatus;
    @FXML
    private TableColumn<?, ?> tcAcao;
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
}
