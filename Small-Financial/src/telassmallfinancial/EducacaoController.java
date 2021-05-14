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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EducacaoController implements Initializable {

    @FXML
    private TableView<?> tvGanhos;
    @FXML
    private TableColumn<?, ?> tcCategoria;
    @FXML
    private TableColumn<?, ?> tcPreco;
    @FXML
    private TableColumn<?, ?> tcData;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<?> tvGanhos1;
    @FXML
    private TableColumn<?, ?> tcCategoria1;
    @FXML
    private TableColumn<?, ?> tcPreco1;
    @FXML
    private TableColumn<?, ?> tcData1;

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
