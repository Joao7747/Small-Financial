/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOGastos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class VisualizarGastosController implements Initializable {

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtData;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private Button btnVoltar;

    GastosController menu = new GastosController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Double preco = menu.selecionado.getPreco();
        String categoria = menu.selecionado.getCategoria();
        String observacao = menu.selecionado.getObservacao();
        Date data = menu.selecionado.getDataGasto();

        txtPreco.setText(preco.toString());
        txtCategoria.setText(categoria);
        txtObservacao.setText(observacao);
        txtData.setText(data.toLocalDate().toString());
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        menu.validacaoEditar = false;
        Parent voltar = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        menu.validacaoEditar = true;
        Parent inserir = FXMLLoader.load(getClass().getResource("InserirGastos.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }

    @FXML
    private void excluir(ActionEvent event) {
        if (menu.selecionado != null) {
            try {
                DAOGastos dao = new DAOGastos();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado será permanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(menu.selecionado.getIdGastos());
                    alerta.close();
                    
                    menu.validacaoEditar = false;
                    Parent voltar = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Gasto NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Gasto!", ButtonType.OK);
            alerta.show();
        }
    }

}
