/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import MODEL.Gastos;
import DAO.DAOGastos;
import DAO.DAOUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
 * @author jr13f
 */
public class GastosController implements Initializable {

    @FXML
    private TableView<Gastos> tvGastos;

    @FXML
    private TableColumn<Gastos, String> tcCategoria;
    
    @FXML
    private TableColumn<Gastos, Double> tcPreco;

    @FXML
    private TableColumn<Gastos, Date> tcData;

    @FXML
    private TableColumn<Gastos, String> tcObservacao;
    
    @FXML
    private ComboBox<Categoria> cbCategoria;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private TextField txtPesquisa;
    
    @FXML
    private Button btnRemover;
    
    @FXML
    private Button btnAlterar;
    
    
    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Gastos> model;
    public ObservableList<Gastos> modelParametrizado;
    public List<Gastos> listaAux = new ArrayList<Gastos>();
    
    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static Gastos selecionado;
    public static boolean validacaoEditar = false;
    DAOUsuario user = new DAOUsuario();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Listagem();
        carregarCategoria();
        
        tvGastos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            
            @Override 
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            
                selecionado = (Gastos)newValue;
            }
        });
        
        txtPesquisa.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (txtPesquisa.getText().equals("")) {
                    Listagem();
                } else {
                    ListagemParametrizada();
                }
            }
        });
        
        tvGastos.setRowFactory(tv -> {
            TableRow<Gastos> row = new TableRow<>();
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
    
    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarGastos.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
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
    private void Inserir(ActionEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirGastos.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }
    
    private void Listagem(){
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataGasto"));

        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("Observacao"));
        
        DAOGastos gastos = new DAOGastos();
        model = FXCollections.observableArrayList(gastos.consultar(user.IdNome().getIdUsuario()));
        tvGastos.setItems(model);
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

            for (Gastos var : model) {
                if (selectedIndex == 0) {
                    if (var.getCategoria().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 1) {
                    String valor = Double.toString(var.getPreco());
                    if (valor.equals(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 2) {
                    
                    if (var.getDataGasto().toString().contains(txtPesquisa.getText())) {
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
        tvGastos.setItems(modelParametrizado);
    }
    
    @FXML
    private void Deletar(){
        if(selecionado != null){
            try
            {
                DAOGastos dao = new DAOGastos();
                
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado será permanentemente excluido!!");

                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionado.getIdGastos());
                    Listagem();
                    alerta.close();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Gasto NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Gasto!", ButtonType.OK);
            alerta.show(); 
        }
        
    }
    
    @FXML
    private void Alterar(ActionEvent event) throws IOException{
        validacaoEditar = true;
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirGastos.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }
    
    public void carregarCategoria() {

        Categoria cat1 = new Categoria("Categoria");
        Categoria cat2 = new Categoria("Preco");
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
