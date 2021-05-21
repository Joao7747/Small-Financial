/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import Classes.Categoria;
import DAO.DAOCurso_Online;
import MODEL.Curso_Online;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MenuCursosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //LISTAR CURSO
        Listagem();
        //Chama Categoria
        carregarCategoria();
        //EXCLUIR CURSO
        
        
        txtPesquisaCurso.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (txtPesquisaCurso.getText().equals(""))
                {
                     Listagem();
                }
                else
                {
                    ListagemParametrizada();
                } 
            }
        });
        
        tvCurso.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionado = (Curso_Online)newValue;
            }
            });
    }    
    
    @FXML
    public TableView<Curso_Online> tvCurso;

    @FXML
    public TableColumn<Curso_Online, String> tcIdCurso;

    @FXML
    public TableColumn<Curso_Online, String> tcNome;

    @FXML
    public TableColumn<Curso_Online, String> tcDescricao;

    @FXML
    public TableColumn<Curso_Online, String> tcDataLimite;

    @FXML
    public TableColumn<Curso_Online, String> tcLink;

    @FXML
    private TableColumn<?, ?> txEditar;

    @FXML
    private TableColumn<?, ?> tcExcluir;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnVoltar;
    
    public static Curso_Online selecionado;
    
     private List<Categoria> cat = new ArrayList<>();
     
     private ObservableList<Categoria> obsCat;
     
     @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    public TextField txtPesquisaCurso;
    
    public static boolean validacaoEditar = false;

    
    @FXML
    void Voltar(ActionEvent event) throws IOException {
        
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();

    }

    @FXML
    void TelaInserirCurso(ActionEvent event) throws IOException {
        
        Parent insere = FXMLLoader.load(getClass().getResource("InserirCurso.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.show();
    }
    
    @FXML
    public void deleta(){
        if(selecionado != null){
            try
            {
                DAOCurso_Online dao = new DAOCurso_Online();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionado.getIdCurso_Online());
                    Listagem();
                    alerta.close();
                } else {
                    alerta.close();
                }
            
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
    public void Listagem()
    {
        tcIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso_Online"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcDataLimite.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
       
        
        DAOCurso_Online daoCurso = new DAOCurso_Online();
        ObservableList<Curso_Online> curso = FXCollections.observableArrayList(daoCurso.consultar());
        tvCurso.setItems(curso);
    }
    
    @FXML
    public void ListagemParametrizada()
    {
        tcIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso_Online"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcDataLimite.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));
       
        
        DAOCurso_Online daoCurso = new DAOCurso_Online();
        ObservableList<Curso_Online> curso = FXCollections.observableArrayList(daoCurso.buscaParametrizada());
        tvCurso.setItems(curso);
    }
    
    @FXML
    void EditarCurso(ActionEvent event) throws IOException {
        
        validacaoEditar = true;
        
        Parent insere = FXMLLoader.load(getClass().getResource("InserirCurso.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.show();
        
        
    }
   
  public void carregarCategoria() {
        
        Categoria cat1 = new Categoria("Id");
        Categoria cat2 = new Categoria("Nome");
        Categoria cat3 = new Categoria("Descrição");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }
  
  
    
}
