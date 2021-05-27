/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;
 //teste
import Classes.Categoria;
import DAO.DAOGanhos;
import MODEL.Ganhos;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnInserirGanhos;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    
    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Ganhos> model;
    public ObservableList<Ganhos> modelParametrizado;
    public List<Ganhos> listaAux = new ArrayList<Ganhos>();
    
    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static Ganhos selecionadoGanho;
    public static boolean validacaoEditarGanho = false;
    @FXML
    private TextField txtPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ListarGanho();
        carregarCategoria();
        
        tvGanhos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionadoGanho = (Ganhos)newValue;
            }
        });
        
        txtPesquisa.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (txtPesquisa.getText().equals("")) {
                    ListarGanho();
                } else {
                    ListagemParametrizada();
                }
            }
        });
      
        tvGanhos.setRowFactory(tv -> {
            TableRow<Ganhos> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    try {
                        chamarTelaVisualizacao(event);
                    } catch (IOException ex) {
                        Logger.getLogger(DividasController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarGanhos.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }
    
    @FXML
    private void InserirGanho(ActionEvent event) throws IOException {
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
        window.centerOnScreen();
    }
    
    public void ListarGanho()
    {
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataGanho"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        DAOGanhos ganho = new DAOGanhos();
        model = FXCollections.observableArrayList(ganho.consultar());
        tvGanhos.setItems(model);
    }
    
    public void ListagemParametrizada() {    
        listaAux.clear();
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataGanho"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));


        if (!txtPesquisa.getText().equals("")) {
            cbCategoria.setOnAction((event) -> {
                selectedIndex = cbCategoria.getSelectionModel().getSelectedIndex();
            });

            for (Ganhos var : model) {
                if (selectedIndex == 0) {
                    if (var.getCategoria().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 1) {
                    String valor = Double.toString(var.getValor());
                    if (valor.equals(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 2) {
                    
                    if (var.getDataGanho().toString().contains(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 3) {
                    if (var.getObservacao().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
            }

        }

        modelParametrizado = FXCollections.observableArrayList(listaAux);
        tvGanhos.setItems(modelParametrizado);
    }

    @FXML
    private void Editar(ActionEvent event) throws IOException {
        validacaoEditarGanho = true;
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
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
    
    public void carregarCategoria() {

        Categoria cat1 = new Categoria("Categoria");
        Categoria cat2 = new Categoria("Valor");
        Categoria cat3 = new Categoria("Data");
        Categoria cat4 = new Categoria("Observação");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);


        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }
}
