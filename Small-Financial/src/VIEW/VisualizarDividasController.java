/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAODividas;
import static VIEW.MenuPublicacoesController.selecionadoPubli;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class VisualizarDividasController implements Initializable {

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtParcelas;

    @FXML
    private TextField txtVencimento;

    @FXML
    private TextArea txtObservacao;

    DividasController menu = new DividasController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Double valor = menu.selectVisualization.getValor();
        Integer parcela = menu.selectVisualization.getNumeroParcelas();
        txtCategoria.setText(menu.selectVisualization.getCategoria());
        txtDescricao.setText(menu.selectVisualization.getDescricao());
        txtValor.setText(valor.toString());
        txtVencimento.setText(menu.selectVisualization.getVencimento().toLocalDate().toString());
        txtParcelas.setText(parcela.toString());
        txtObservacao.setText(menu.selectVisualization.getObservacao());
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        menu.validacaoEditar = false;
        Parent voltar = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        menu.validacaoEditar = true;
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirDividas.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

    @FXML
    private void excluir(ActionEvent event) {
        if (menu.selecionado != null) {
            try {
                DAODividas dao = new DAODividas();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado será permanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(menu.selecionado.getIdDividas());
                    alerta.close();

                    menu.validacaoEditar = false;
                    Parent voltar = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();
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
