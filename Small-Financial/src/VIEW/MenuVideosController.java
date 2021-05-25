/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;


import DAO.DAOVideo;
import MODEL.Video;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MenuVideosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ListarVideos();
        
        tvVideos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionadoVideo = (Video)newValue;
            }
        });   
    }
    
  @FXML
    public TableView<Video> tvVideos;

    @FXML
    public TableColumn<Video, String> tcIdVideo;

    @FXML
    public TableColumn<Video, String> tcDescricao;

    @FXML
    public TableColumn<Video, String> tcLink;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Label lblContas;
    
     @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;
    
    
    public static Video selecionadoVideo;
    public static boolean validacaoEditarVideo = false;

    @FXML
    void Voltar(ActionEvent event) throws IOException  {
        
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    void telaInserir(ActionEvent event) throws IOException {
        Parent insereVideo = FXMLLoader.load(getClass().getResource("InserirVideo.fxml"));
        Scene insereVideoScene = new Scene(insereVideo);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereVideoScene);
        window.centerOnScreen();
    }
    
    @FXML
    public void ListarVideos()
    {
        tcIdVideo.setCellValueFactory(new PropertyValueFactory<>("idVideo"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
       
       
        DAOVideo daoVideo = new DAOVideo();
        ObservableList<Video> video = FXCollections.observableArrayList(daoVideo.consultar());
        tvVideos.setItems(video);
    
    }
    
    @FXML
    public void deleta(){
        if(selecionadoVideo != null){
            try
            {
                DAOVideo dao = new DAOVideo();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionadoVideo.getIdVideo());
                    ListarVideos();
                } else {
                    alerta.close();
                }
            
            }
            catch(Exception e)
            {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Video NÃO deletado!", ButtonType.OK);
                    alerta.show(); 
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Video!", ButtonType.OK);
            alerta.show(); 
        }
        
    }
    
    @FXML
    void EditarVideo(ActionEvent event) throws IOException {
        
        validacaoEditarVideo = true;
        
        Parent insere = FXMLLoader.load(getClass().getResource("InserirVideo.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }
    
    
}
