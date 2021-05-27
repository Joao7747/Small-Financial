/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import DAO.DAOMetas;
import DAO.DAOUsuario;
import MODEL.Metas;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.ProgressBarTableCell;
import static javafx.scene.control.cell.ProgressBarTableCell.forTableColumn;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MetasController implements Initializable {

    @FXML
    private Label lblMetas;
    @FXML
    private TableView<Metas> tvMetas;
    @FXML
    private TableColumn<Metas, String> tcCategoria;
    @FXML
    private TableColumn<Metas, String> tcDescricao;
    @FXML
    private TableColumn<Metas, Double> tcValor;
    @FXML
    private TableColumn<Metas, Date> tcParcelas;
    @FXML
    private TableColumn<Metas, String> tcVencimentos;
    @FXML
    private TableColumn<Metas, Double> tcStatus;
    @FXML
    private TableColumn<Metas, Date> tcDataInserido;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnInserir;

    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Metas> model;
    public ObservableList<Metas> modelParametrizado;
    public List<Metas> listaAux = new ArrayList<Metas>();

    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static boolean verificaEditar = false;
    public static Metas selecionado;

    DAOUsuario user = new DAOUsuario();
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Listar();
        carregarCategoria();

        tvMetas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (Metas) newValue;
            }
        });

        txtPesquisa.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (txtPesquisa.getText().equals("")) {
                    Listar();
                } else {
                    ListagemParametrizada();
                }
            }
        });

        tvMetas.setRowFactory(tv -> {
            TableRow<Metas> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
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
    private void chamarTelaVisualizacao(MouseEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarMetas.fxml"));
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
    private void telaInserir(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("InserirMeta.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    private void Listar() {
        DAOMetas dao = new DAOMetas();
        model = FXCollections.observableArrayList(dao.consultar(user.IdNome().getIdUsuario()));
        DecimalFormat decimal = new DecimalFormat("0.00");

        for (Metas meta : model) {

            try {
                double porcentagem = (meta.getValorGuardado() * 100) / meta.getCustoTotal();
                if (porcentagem > 100.0) {
                    meta.setPercent(100.0);
                } else {
                    String duasCasas = decimal.format(porcentagem).replace(',', '.');
                    double percento = Double.parseDouble(duasCasas);
                    meta.setPercent(percento);
                }
            } catch (Exception e) {
                String erro = e.toString();
                String tal = "";
            }

        }

        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("custoTotal"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("dataRealizacao"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("observacao"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("percent"));
        tcDataInserido.setCellValueFactory(new PropertyValueFactory<>("dataPrevista"));


        


        tvMetas.setItems(model);
    }

    public void ListagemParametrizada() {
        listaAux.clear();
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("custoTotal"));
        tcParcelas.setCellValueFactory(new PropertyValueFactory<>("dataRealizacao"));
        tcVencimentos.setCellValueFactory(new PropertyValueFactory<>("observacao"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("percent"));
        tcDataInserido.setCellValueFactory(new PropertyValueFactory<>("dataPrevista"));

        if (!txtPesquisa.getText().equals("")) {
            cbCategoria.setOnAction((event) -> {
                selectedIndex = cbCategoria.getSelectionModel().getSelectedIndex();
            });

            for (Metas var : model) {
                if (selectedIndex == 0) {
                    if (var.getCategoria().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 1) {
                    if (var.getDescricao().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 2) {
                    String valor = Double.toString(var.getCustoTotal());
                    if (valor.equals(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 3) {
                    if (var.getDataRealizacao().toString().contains(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 4) {
                    if (var.getObservacao().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
            }

        }

        modelParametrizado = FXCollections.observableArrayList(listaAux);
        tvMetas.setItems(modelParametrizado);
    }

    @FXML
    private void excluir(ActionEvent event) {
        if (selecionado != null) {
            try {
                DAOMetas dao = new DAOMetas();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado será permanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionado.getIdMetas());
                    Listar();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Meta NÃO deletada!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma meta para excluir!", ButtonType.OK);
            alerta.show();
        }
    }

    @FXML
    public void editar(ActionEvent event) throws IOException {
        if (selecionado != null) {
            verificaEditar = true;

            Parent voltar = FXMLLoader.load(getClass().getResource("InserirMeta.fxml"));
            Scene voltarScene = new Scene(voltar);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(voltarScene);
            window.centerOnScreen();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma meta para poder editar!", ButtonType.OK);
            alerta.show();
        }
    }

    public void carregarCategoria() {

        Categoria cat1 = new Categoria("Categoria");
        Categoria cat2 = new Categoria("Descrição");
        Categoria cat3 = new Categoria("Preço");
        Categoria cat4 = new Categoria("Realização");
        Categoria cat5 = new Categoria("Observação");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);
        cat.add(cat5);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }

}
