/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOVideo;
import MODEL.Video;
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
        // TODO
        
        tcIdVideo.setCellValueFactory(new PropertyValueFactory<>("idVideo"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
       
       
        DAOVideo daoVideo = new DAOVideo();
        ObservableList<Video> video = FXCollections.observableArrayList(daoVideo.consultar());
        tvVideos.setItems(video);
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
    private TableColumn<?, ?> tcEditar;

    @FXML
    private TableColumn<?, ?> tcExcluir;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnVoltar;


    @FXML
    void Voltar(ActionEvent event) throws IOException  {
        
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }

    @FXML
    void telaInserir(ActionEvent event) throws IOException {
        Parent insereVideo = FXMLLoader.load(getClass().getResource("InserirVideo.fxml"));
        Scene insereVideoScene = new Scene(insereVideo);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereVideoScene);
        window.show();
    }
    
    
}
