/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOGanhos;
import MODEL.Ganhos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class InserirGanhoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSalvar;
    @FXML
    private DatePicker txtDataRecebimento;
    @FXML
    private TextField txtValor;
    @FXML
    private TextArea txtObservacoes;
    @FXML
    private ComboBox<String> cbCategoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoria();
    } 
    
    public void carregarCategoria(){ 
       ObservableList<String> obsCategoria = FXCollections.observableArrayList();
       obsCategoria.add("Salario");
       obsCategoria.add("Venda");
       obsCategoria.add("Bonificação");
       obsCategoria.add("Outros");
       cbCategoria.setItems(obsCategoria); 
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    @FXML
    private void Inserir(ActionEvent event) {
        Ganhos ganho = new Ganhos();
        ganho.setIdUsuario(1);
        ganho.setCategoria(cbCategoria.getSelectionModel().getSelectedItem());
        Date dataganho = java.sql.Date.valueOf(txtDataRecebimento.getValue());
        ganho.setDataGanho(dataganho);
        ganho.setObservacao(txtObservacoes.getText());
        ganho.setValor(Double.valueOf(txtValor.getText()));
        
        DAOGanhos inserirganho = new DAOGanhos();
        inserirganho.inserir(ganho);  
    }
    
    
    
    
}
