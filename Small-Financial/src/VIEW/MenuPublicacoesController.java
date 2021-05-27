/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import DAO.DAOPublicacao;
import MODEL.Publicacao;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MenuPublicacoesController implements Initializable {

    @FXML
    private Button btnInserir;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<Publicacao> tvPublicacao;
    @FXML
    private TableColumn<Publicacao, Integer> tcIdPubli;
    @FXML
    private TableColumn<Publicacao, String> tcTitulo;
    @FXML
    private TableColumn<Publicacao, String> tcAutor;
    @FXML
    private TableColumn<Publicacao, java.sql.Date> tcDataPubli;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private TextField txtPesquisa;
    
    public static int selectedIndex;
    //Listagem Parametrizada
    public ObservableList<Publicacao> model;
    public ObservableList<Publicacao> modelParametrizado;
    public List<Publicacao> listaAux = new ArrayList<Publicacao>();

    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    public static Publicacao selecionadoPubli;
    public static boolean validacaoEditarPubli = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ListarPubli();
        carregarCategoria();

        tvPublicacao.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                selecionadoPubli = (Publicacao) newValue;
            }
        });

        txtPesquisa.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (txtPesquisa.getText().equals("")) {
                    ListarPubli();
                } else {
                    ListagemParametrizada();
                }
            }
        });
    }

    @FXML
    private void telaInserir(ActionEvent event) throws IOException {
        Parent inserePubli = FXMLLoader.load(getClass().getResource("InserirPublicacao.fxml"));
        Scene inserePubliScene = new Scene(inserePubli);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserePubliScene);
        window.centerOnScreen();
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void Editar(ActionEvent event) throws IOException {
        validacaoEditarPubli = true;

        Parent insere = FXMLLoader.load(getClass().getResource("InserirPublicacao.fxml"));
        Scene insereScene = new Scene(insere);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(insereScene);
        window.centerOnScreen();
    }

    public void ListarPubli() {
        tcIdPubli.setCellValueFactory(new PropertyValueFactory<>("idPublicacao"));
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tcDataPubli.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));

        DAOPublicacao daoPubli = new DAOPublicacao();
        model = FXCollections.observableArrayList(daoPubli.consultar());
        tvPublicacao.setItems(model);
    }
    
    public void ListagemParametrizada() {
        listaAux.clear();
        tcIdPubli.setCellValueFactory(new PropertyValueFactory<>("idPublicacao"));
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tcDataPubli.setCellValueFactory(new PropertyValueFactory<>("dataPublicacao"));

        if (!txtPesquisa.getText().equals("")) {
            cbCategoria.setOnAction((event) -> {
                selectedIndex = cbCategoria.getSelectionModel().getSelectedIndex();
            });

            for (Publicacao var : model) {
                if (selectedIndex == 0) {
                    if (var.getIdPublicacao() == Integer.parseInt(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 1) {
                    if (var.getTitulo().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }

                }
                if (selectedIndex == 2) {
                    if (var.getAutor().toUpperCase().contains(txtPesquisa.getText().toUpperCase())) {
                        listaAux.add(var);
                    }
                }
                if (selectedIndex == 3) {
                    if (var.getDataPublicacao().toString().contains(txtPesquisa.getText())) {
                        listaAux.add(var);
                    }
                }
            }

        }

        modelParametrizado = FXCollections.observableArrayList(listaAux);
        tvPublicacao.setItems(modelParametrizado);
    }

    @FXML
    private void Deletar(ActionEvent event) {
        if (selecionadoPubli != null) {
            try {
                DAOPublicacao dao = new DAOPublicacao();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado sera prmanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(selecionadoPubli.getIdPublicacao());
                    ListarPubli();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Publicação NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione uma Publicação!", ButtonType.OK);
            alerta.show();
        }
    }

    public void carregarCategoria() {

        Categoria cat1 = new Categoria("Id");
        Categoria cat2 = new Categoria("Titulo");
        Categoria cat3 = new Categoria("Autor");
        Categoria cat4 = new Categoria("Data da Publicação");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }
}
