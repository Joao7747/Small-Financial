/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOCurso_Online;
import DAO.DAOVideo;
import MODEL.Curso_Online;
import MODEL.Video;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EducacaoController implements Initializable {

    @FXML
    private TableView<Video> tvVideos;
    @FXML
    private TableColumn<Video, String> tcDescricaoVideo;
    @FXML
    private TableColumn<Video, String> tcLinkVideo;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnSiglas;
    
    @FXML
    public TableView<Curso_Online> tvCursos;
    @FXML
    public TableColumn<Curso_Online, String> tcCurso;
    @FXML
    public TableColumn<Curso_Online, String> tcLink;
    @FXML
    public TableColumn<Curso_Online, Date> tcPrazo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //LISTAR CURSO
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
        tcPrazo.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
       
        DAOCurso_Online daoCurso = new DAOCurso_Online();
        ObservableList<Curso_Online> curso = FXCollections.observableArrayList(daoCurso.consultar());
        tvCursos.setItems(curso);
        
        //LISTAR VÍDEO
        tcDescricaoVideo.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcLinkVideo.setCellValueFactory(new PropertyValueFactory<>("link"));
       
       
        DAOVideo daoVideo = new DAOVideo();
        ObservableList<Video> video = FXCollections.observableArrayList(daoVideo.consultar());
        tvVideos.setItems(video);
        
        
        
        
        // TODO
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
    private void InserirCurso(ActionEvent event) throws IOException {
        Parent insere = FXMLLoader.load(getClass().getResource("InserirCurso.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.show();
    }
    
    @FXML
    private void InserirVideo(ActionEvent event) throws IOException {
        Parent insereVideo = FXMLLoader.load(getClass().getResource("InserirVideo.fxml"));
        Scene insereVideoScene = new Scene(insereVideo);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereVideoScene);
        window.show();
    }
    
    @FXML
    private void siglas(ActionEvent event) throws IOException {
        Parent telaSiglas = FXMLLoader.load(getClass().getResource("Siglas_e_Nomeclaturas.fxml"));
        Scene insereVideoScene = new Scene(telaSiglas);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereVideoScene);
        window.show();
    }
}
