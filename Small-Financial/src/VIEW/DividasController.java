/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.CustomImage;
import Classes.Categoria;
import DAO.DAODividas;
import MODEL.Dividas;
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
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class DividasController implements Initializable {

    @FXML
    private Label lblContas;
    @FXML
    private TableView<Dividas> tvContas;
    @FXML
    private TableColumn<Dividas, String> tcCategoria;
    @FXML
    private TableColumn<Dividas, String> tcDescricao;
    @FXML
    private TableColumn<Dividas, Double> tcValor;
    @FXML
    private TableColumn<Dividas, Integer> tcParcelas;
    @FXML
    private TableColumn<Dividas, Date> tcVencimentos;
    @FXML
    private TableColumn<Dividas, String> tcObservacao;
    @FXML
    private TableColumn<Dividas, ImageView> tcStatus;
    @FXML
    private TableColumn<Dividas, Image> tcImage;
    @FXML
    private Label lblTotal;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;
    
    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Dividas> model;
    public ObservableList<Dividas> modelParametrizado;
    public List<Dividas> listaAux = new ArrayList<Dividas>();

    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static Dividas selecionado;
    public static Dividas selectVisualization;
    public static boolean validacaoEditar = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Listagem();
        //Chama Categoria
        carregarCategoria();

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

        tvContas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                selecionado = (Dividas) newValue;
            }
        });

        tvContas.setRowFactory(tv -> {
            TableRow<Dividas> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    selectVisualization = row.getItem();
//                    selecionado = row.getItem();
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

    public void Listagem() {
        DAODividas dividas = new DAODividas();
        model = FXCollections.observableArrayList(dividas.consultar(user.IdNome().getIdUsuario()));
        LocalDate hoje = LocalDate.now();
        Date hojeAux = Date.valueOf(hoje);
        Double Total = 0.0;
        for (Dividas div : model) {
            if (hojeAux.compareTo(div.getVencimento()) <= 0) {
                try {
                    div.setImagem(new Image(new FileInputStream("src/Resources/yellow_ball.PNG")));
                    CustomImage item_1 = new CustomImage(new ImageView(new Image(new FileInputStream("src/Resources/yellow_ball.PNG"))));
                    div.setImg(item_1.getImage());
                } catch (Exception e) {
                    String tente = e.toString();
                    
                }

            } else if (hojeAux.compareTo(div.getVencimento()) > 0) {
                try {
                    div.setImagem(new Image(new FileInputStream("src/Resources/red_ball.PNG")));
                    CustomImage item_1 = new CustomImage(new ImageView(new Image(new FileInputStream("src/Resources/red_ball.PNG"))));
                    div.setImg(item_1.getImage());
                } catch (Exception e) {
                    String tente = e.toString();
                    
                }
                Total += div.getValor();
            }
            
        }
        if (Total <= 0.0){
            lblTotal.setText("Total: R$ 0");
        } else {
            lblTotal.setText("Total: R$ " + Total);
        }
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("numeroParcelas"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("img"));
        tcImage.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        
        tvContas.setItems(model);
    }

    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarDividas.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

    public void ListagemParametrizada() {    
        listaAux.clear();
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("numeroParcelas"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        if (!txtPesquisa.getText().equals("")) {
            cbCategoria.setOnAction((event) -> {
                selectedIndex = cbCategoria.getSelectionModel().getSelectedIndex();
            });

            for (Dividas var : model) {
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
                    if (var.getDescricao().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 3) {
                    if (var.getNumeroParcelas()== Integer.parseInt(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 4) {
                    
                    if (var.getVencimento().toString().contains(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 5) {
                    if (var.getObservacao().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
            }

        }

        modelParametrizado = FXCollections.observableArrayList(listaAux);
        tvContas.setItems(modelParametrizado);
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene voltarScene = new Scene(voltar);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void Inserir(ActionEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirDividas.fxml"));
        Scene inserirScene = new Scene(inserir);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

    @FXML
    private void Alterar(ActionEvent event) throws IOException {
        validacaoEditar = true;
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirDividas.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

    @FXML
    public void deleta() {
        if (selecionado != null) {
            try {

                DAODividas dao = new DAODividas();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionado.getIdDividas());
                    Listagem();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Divida NÃO deletada!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma divida!", ButtonType.OK);
            alerta.show();
        }

    }

    public void carregarCategoria() {

        Categoria cat1 = new Categoria("Categoria");
        Categoria cat2 = new Categoria("Valor");
        Categoria cat3 = new Categoria("Descrição");
        Categoria cat4 = new Categoria("Parcelas");
        Categoria cat5 = new Categoria("Vencimento");
        Categoria cat6 = new Categoria("Observação");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);
        cat.add(cat5);
        cat.add(cat6);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }

}
