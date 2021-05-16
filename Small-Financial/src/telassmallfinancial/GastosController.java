/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import MODEL.Gastos;
import DAO.DAOGastos;
import MODEL.Curso_Online;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class GastosController implements Initializable {

    @FXML
    private TableView<Gastos> tvGastos;

    @FXML
    private TableColumn<Gastos, String> tcCategoria;
    
    @FXML
    private TableColumn<Gastos, Integer> tcidGastos;

    @FXML
    private TableColumn<Gastos, Double> tcPreco;

    @FXML
    private TableColumn<Gastos, Date> tcData;

    @FXML
    private TableColumn<Gastos, String> tcObservacao;
    
    @FXML
    private ComboBox<String> cbCategoria;
    
    @FXML
    private Button btnVoltar;

    public static Gastos selecionado;
    public static boolean validacaoEditar = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Listagem();
        
        tvGastos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionado = (Gastos)newValue;
            }
            });
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
    private void Inserir(ActionEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("GastosInsert.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.show();
    }
    
    private void Listagem(){
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("Data_Gasto"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("Observacao"));
        
        DAOGastos gastos = new DAOGastos();
        ObservableList<Gastos> gasto = FXCollections.observableArrayList(gastos.consultar());
        tvGastos.setItems(gasto);
    }
    
    @FXML
    private void Deletar(){
        if(selecionado != null){
            try
            {
                DAOGastos gastos = new DAOGastos();
                gastos.excluir(selecionado.getIdGastos());
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Curso deletado com sucesso!", ButtonType.OK);
                alerta.show();
                Listagem();
            
            }
            catch(Exception e)
            {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Curso NÃO deletado!", ButtonType.OK);
                    alerta.show(); 
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Curso!", ButtonType.OK);
            alerta.show(); 
        }
        
    }
    
    @FXML
    private void Alterar(ActionEvent event) throws IOException{
        validacaoEditar = true;
        Parent inserir = FXMLLoader.load(getClass().getResource("GastosInsert.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.show();
    }
}
