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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class GanhosController implements Initializable {

    @FXML
    private TableView<Ganhos> tvGanhos;
    @FXML
    private TableColumn<Ganhos, String> tcCategoria;
    @FXML
    private TableColumn<Ganhos, Double> tcPreco;
    @FXML
    private TableColumn<Ganhos, java.sql.Date> tcData;
    @FXML
    private TableColumn<Ganhos, String> tcObservacao;
    @FXML
    private TableColumn<Ganhos, Void> tcAcao;
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataGanho"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        DAOGanhos ganho = new DAOGanhos();
        ObservableList<Ganhos> ganhos = FXCollections.observableArrayList(ganho.consultar());
        tvGanhos.setItems(ganhos);
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    @FXML
    private void InserirGanho(ActionEvent event) throws IOException {
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
        window.show();
    }
    
}
