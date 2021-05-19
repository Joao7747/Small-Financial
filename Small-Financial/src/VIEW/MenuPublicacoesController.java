/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOPublicacao;
import MODEL.Publicacao;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MenuPublicacoesController implements Initializable {

    @FXML
    private Button btnInserir;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<Publicacao> tvPublicacao;
    @FXML
    private TableColumn<Publicacao, Integer> tcIdPubli;
    @FXML
    private TableColumn<Publicacao, String> tcTitulo;
    @FXML
    private TableColumn<Publicacao, String> tcAutor;
    @FXML
    private TableColumn<Publicacao, java.sql.Date> tcDataPubli;

    /**
     * Initializes the controller class.
     */
    public static Publicacao selecionadoPubli;
    public static boolean validacaoEditarPubli = false;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListarPubli();
        
        tvPublicacao.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionadoPubli = (Publicacao)newValue;
            }
        });
    }    

    @FXML
    private void telaInserir(ActionEvent event) throws IOException {
        Parent inserePubli = FXMLLoader.load(getClass().getResource("InserirPublicacao.fxml"));
        Scene inserePubliScene = new Scene(inserePubli);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserePubliScene);
        window.centerOnScreen();
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void Editar(ActionEvent event) throws IOException {
        validacaoEditarPubli = true;
        
        Parent insere = FXMLLoader.load(getClass().getResource("InserirPublicacao.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }
    
    @FXML
    public void ListarPubli()
    {
        tcIdPubli.setCellValueFactory(new PropertyValueFactory<>("idPublicacao"));
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tcDataPubli.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));
       
        DAOPublicacao daoPubli = new DAOPublicacao();
        ObservableList<Publicacao> publi = FXCollections.observableArrayList(daoPubli.consultar());
        tvPublicacao.setItems(publi);
    }

    @FXML
    private void Deletar(ActionEvent event) {
        if(selecionadoPubli != null){
            try
            {
                DAOPublicacao dao = new DAOPublicacao();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionadoPubli.getIdPublicacao());
                    ListarPubli();
                } else {
                    alerta.close();
                }
            
            }
            catch(Exception e)
            {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Publicação NÃO deletado!", ButtonType.OK);
                    alerta.show(); 
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma Publicação!", ButtonType.OK);
            alerta.show(); 
        }
    }
    
}
