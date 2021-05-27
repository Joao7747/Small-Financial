/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOCurso_Online;
import DAO.DAOUsuario;
import DAO.DAOVideo;
import MODEL.Curso_Online;
import MODEL.Metas;
import MODEL.Video;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    
    
    
    @FXML
    public Button btnMenuPublicacao;

    @FXML
    public Button btnMenuCurso;

    @FXML
    public Button btnMenuVideo;
  
    public static Curso_Online cursoSelecionado;
  
    public static Video videoSelecionado;
    
    DAOUsuario user = new DAOUsuario();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //VERIFICAR SE É ADM OU USUÁRIO
      
        if(user.IdNome().getVerif_ADM() == false)
        {
            btnMenuPublicacao.setVisible(false);
            btnMenuCurso.setVisible(false);
            btnMenuVideo.setVisible(false);
        
        }
        if(user.IdNome().getVerif_ADM() == true)
        {
            btnMenuPublicacao.setDisable(false);
            btnMenuCurso.setDisable(false);
            btnMenuVideo.setDisable(false);
            btnMenuPublicacao.setVisible(true);
            btnMenuCurso.setVisible(true);
            btnMenuVideo.setVisible(true);
        
        }
        
        
        //LISTAR CURSO
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
        tcPrazo.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
       
        DAOCurso_Online daoCurso = new DAOCurso_Online();
        ObservableList<Curso_Online> curso = FXCollections.observableArrayList(daoCurso.consultaCurso());
        tvCursos.setItems(curso);
        
        //LISTAR VÍDEO
        tcDescricaoVideo.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcLinkVideo.setCellValueFactory(new PropertyValueFactory<>("link"));
       
       
        DAOVideo daoVideo = new DAOVideo();
        ObservableList<Video> video = FXCollections.observableArrayList(daoVideo.consultaVideo());
        tvVideos.setItems(video);
        
        
        //AÇÃO CLICANDO NO VIDEO
        
        tvVideos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                videoSelecionado = (Video) newValue;
            }
        });
        
        tvVideos.setRowFactory(tv -> {
            TableRow<Video> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        chamarTelaVisualizacaoVideo(event);
                    } catch (IOException ex) {
                        Logger.getLogger(EducacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        
        
        //AÇÃO CLICANDO NO CURSO
        tvCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                cursoSelecionado = (Curso_Online) newValue;
            }
        });
        
        tvCursos.setRowFactory(tv -> {
            TableRow<Curso_Online> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        chamarTelaVisualizacao(event);
                    } catch (IOException ex) {
                        Logger.getLogger(EducacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        
        
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }
    @FXML
    private void MenuCursos(ActionEvent event) throws IOException {
        Parent insere = FXMLLoader.load(getClass().getResource("MenuCursos.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }
    
    @FXML
    private void MenuVideos(ActionEvent event) throws IOException {
        Parent insere = FXMLLoader.load(getClass().getResource("MenuVideos.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }

    @FXML
    private void MenuPublicacao(ActionEvent event) throws IOException {
        Parent insere = FXMLLoader.load(getClass().getResource("MenuPublicacoes.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }

    
    @FXML
    private void siglas(ActionEvent event) throws IOException {
        Parent telaSiglas = FXMLLoader.load(getClass().getResource("Siglas_e_Nomenclaturas.fxml"));
        Scene insereVideoScene = new Scene(telaSiglas);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereVideoScene);
        window.centerOnScreen();
    }
    
    @FXML
    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarCurso_Online.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }
    
    @FXML
    private void chamarTelaVisualizacaoVideo(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarVideo.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

   
    }
    
    

