/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

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
import static VIEW.MenuPublicacoesController.selecionadoPubli;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableRow;
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
    private TableColumn<Dividas, String> tcStatus;
    @FXML
    private Label lblTotal;
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;

    public static Dividas selecionado;
    public static Dividas selectVisualization;

    public static boolean validacaoEditar = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Listagem();

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
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("numeroParcelas"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
        tcObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        DAODividas dividas = new DAODividas();
        ObservableList<Dividas> divida = FXCollections.observableArrayList(dividas.consultar());
        tvContas.setItems(divida);
    }


    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarDividas.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
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
}
