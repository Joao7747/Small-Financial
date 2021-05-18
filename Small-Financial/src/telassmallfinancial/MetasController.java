/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOMetas;
import MODEL.Metas;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static telassmallfinancial.MenuPublicacoesController.selecionadoPubli;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MetasController implements Initializable {

    @FXML
    private Label lblContas;
    @FXML
    private TableView<Metas> tvContas;
    @FXML
    private TableColumn<Metas, String> tcCategoria;
    @FXML
    private TableColumn<Metas, String> tcDescricao;
    @FXML
    private TableColumn<Metas, Double> tcValor;
    @FXML
    private TableColumn<Metas, Date> tcParcelas;
    @FXML
    private TableColumn<Metas, String> tcVencimentos;
    @FXML
    private TableColumn<Metas, Byte> tcStatus;
    @FXML
    private TableColumn<Metas, Integer> tcId;
    @FXML
    private TableColumn<Metas, Date> tcDataInserido;
    
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;
    
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnInserir;
    
    public static boolean verificaEditar = false;
    
    public static Metas selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Listar();
        
        tvContas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                selecionado = (Metas)newValue;
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
    private void telaInserir(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("InserirMeta.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    private void Listar(){
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("custoTotal"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("dataRealizacao"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("observacao"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("statusMeta"));
        tcId.setCellValueFactory(new PropertyValueFactory<>("idMetas"));
        tcDataInserido.setCellValueFactory(new PropertyValueFactory<>("dataPrevista"));
        
        DAOMetas dao = new DAOMetas();
        ObservableList<Metas> metas = FXCollections.observableArrayList(dao.consultar());
        tvContas.setItems(metas);
    }
    
    @FXML
    private void excluir(ActionEvent event) {
        if(selecionado != null){
            try
            {
                DAOMetas dao = new DAOMetas();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionadoPubli.getIdPublicacao());
                    Listar();
                } else {
                    alerta.close();
                }
            
            }
            catch(Exception e)
            {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Meta NÃO deletada!", ButtonType.OK);
                    alerta.show(); 
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma meta para excluir!", ButtonType.OK);
            alerta.show();
        }
    }
    
    @FXML
    public void editar(ActionEvent event) throws IOException {
        if(selecionado != null){
            verificaEditar = true;
            
            Parent voltar = FXMLLoader.load(getClass().getResource("InserirMeta.fxml"));
            Scene voltarScene = new Scene(voltar);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(voltarScene);
            window.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma meta para poder editar!", ButtonType.OK);
            alerta.show();
        }
    }
    
}
