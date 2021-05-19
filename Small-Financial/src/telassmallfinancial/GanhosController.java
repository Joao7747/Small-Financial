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
import java.util.Optional;
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
    private ComboBox<?> cbCategoria;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnInserirGanhos;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    
    public static Ganhos selecionadoGanho;
    public static boolean validacaoEditarGanho = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ListarGanho();
        tvGanhos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionadoGanho = (Ganhos)newValue;
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
        window.centerOnScreen();
    }
    
    @FXML
    private void InserirGanho(ActionEvent event) throws IOException {
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
        window.show();
        window.centerOnScreen();
    }
    
    @FXML
    public void ListarGanho()
    {
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataGanho"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        DAOGanhos ganho = new DAOGanhos();
        ObservableList<Ganhos> ganhos = FXCollections.observableArrayList(ganho.consultar());
        tvGanhos.setItems(ganhos);
    }

    @FXML
    private void Editar(ActionEvent event) throws IOException {
        validacaoEditarGanho = true;
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
        window.show();
        window.centerOnScreen();
    }

    @FXML
    private void Deletar(ActionEvent event) {
        if(selecionadoGanho != null){
            try
            {
                DAOGanhos dao = new DAOGanhos();
                
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionadoGanho.getIdGanhos());
                    ListarGanho();
                    alerta.close();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Ganho NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Ganho!", ButtonType.OK);
            alerta.show(); 
        }
    }
    
}
