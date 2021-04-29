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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnMetas;  
    @FXML
    private Button btnRelatorios;
    @FXML
    private Button btnGastos;
    @FXML
    private Button btnGanhos;
    @FXML
    private Button btnEducacao;
    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Metas(ActionEvent event) throws IOException {
        Parent metas = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene metasScene = new Scene(metas);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(metasScene);
        window.show();
    }

    @FXML
    private void Gastos(ActionEvent event) throws IOException {
        Parent gastos = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
        Scene gastosScene = new Scene(gastos);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gastosScene);
        window.show(); 
    }

    @FXML
    private void Contas(ActionEvent event) throws IOException {
        Parent contas = FXMLLoader.load(getClass().getResource("Contas.fxml"));
        Scene contasScene = new Scene(contas);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(contasScene);
        window.show(); 
    }
    
    @FXML
    private void Ganhos(ActionEvent event) throws IOException {
        Parent ganhos = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene ganhosScene = new Scene(ganhos);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ganhosScene);
        window.show(); 
    }
    @FXML
    private void Educacao(ActionEvent event) throws IOException {
        Parent educacao = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene educacaoScene = new Scene(educacao);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(educacaoScene);
        window.show(); 
    }
    
}
