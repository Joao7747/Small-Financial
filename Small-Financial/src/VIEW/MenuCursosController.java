/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import DAO.DAOCurso_Online;
import MODEL.Curso_Online;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MenuCursosController implements Initializable {

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
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnVoltar;

    @FXML
    public ComboBox<Categoria> cbCategoria;

    @FXML
    public TextField txtPesquisaCurso;

    @FXML
    private Label lblContas;
    

    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Curso_Online> model;
    public ObservableList<Curso_Online> modelParametrizado;
    public List<Curso_Online> listaAux = new ArrayList<Curso_Online>();
    
    public static Curso_Online selecionado;
    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static boolean validacaoEditar = false;

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
                if (txtPesquisaCurso.getText().equals("")) {
                    Listagem();
                } else {
                    ListagemParametrizada();
                }
            }
        });

        tvCurso.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                selecionado = (Curso_Online) newValue;
            }
        });
    }

    @FXML
    void Voltar(ActionEvent event) throws IOException {

        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();

    }

    @FXML
    void TelaInserirCurso(ActionEvent event) throws IOException {

        Parent insere = FXMLLoader.load(getClass().getResource("InserirCurso.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }

    @FXML
    public void deleta() {
        if (selecionado != null) {
            try {
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

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Curso NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Curso!", ButtonType.OK);
            alerta.show();
        }

    }

    public void Listagem() {
        tcIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso_Online"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcDataLimite.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));

        DAOCurso_Online daoCurso = new DAOCurso_Online();
        model = FXCollections.observableArrayList(daoCurso.consultar());

        tvCurso.setItems(model);
    }

    public void ListagemParametrizada() {
        listaAux.clear();
        tcIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso_Online"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcDataLimite.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));
        tcLink.setCellValueFactory(new PropertyValueFactory<>("link"));

        if (!txtPesquisaCurso.getText().equals("")) {
            cbCategoria.setOnAction((event) -> {
                selectedIndex = cbCategoria.getSelectionModel().getSelectedIndex();
            });

            for (Curso_Online var : model) {
                if (selectedIndex == 0) {
                    if (var.getIdCurso_Online() == Integer.parseInt(txtPesquisaCurso.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 1) {
                    if (var.getNome().toUpperCase().contains(txtPesquisaCurso.getText().toUpperCase())) {
                        listaAux.add(var);
                    }

                }
                if (selectedIndex == 2) {
                    if (var.getDescricao().toUpperCase().contains(txtPesquisaCurso.getText().toUpperCase())) {
                        listaAux.add(var);
                    }

                }
            }

        }

        modelParametrizado = FXCollections.observableArrayList(listaAux);
        tvCurso.setItems(modelParametrizado);
    }

    @FXML
    void EditarCurso(ActionEvent event) throws IOException {

        validacaoEditar = true;

        Parent insere = FXMLLoader.load(getClass().getResource("InserirCurso.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();

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
