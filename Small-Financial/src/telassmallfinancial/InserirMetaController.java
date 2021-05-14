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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class InserirMetaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnVoltar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
}
