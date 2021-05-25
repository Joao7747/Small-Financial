/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOGanhos;
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
public class VisualizarGanhosController implements Initializable {

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtDataRecebimento;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private Button btnVoltar;

    GanhosController telaganho = new GanhosController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date datarecebimento = telaganho.selecionadoGanho.getDataGanho();
        Double valor = telaganho.selecionadoGanho.getValor();
        String observacao = telaganho.selecionadoGanho.getObservacao();

        txtCategoria.setText(telaganho.selecionadoGanho.getCategoria());
        txtDataRecebimento.setText(datarecebimento.toLocalDate().toString());
        txtPreco.setText(valor.toString());
        txtObservacao.setText(observacao);
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
        telaganho.validacaoEditarGanho = false;
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        telaganho.validacaoEditarGanho = true;
        Parent inserirganho = FXMLLoader.load(getClass().getResource("InserirGanho.fxml"));
        Scene inserirganhoScene = new Scene(inserirganho);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirganhoScene);
        window.centerOnScreen();
    }

    @FXML
    private void excluir(ActionEvent event) {
        if (telaganho.selecionadoGanho != null) {
            try {
                DAOGanhos dao = new DAOGanhos();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("O dado será permanentemente excluido!!");
                alerta.setContentText("tem certeza que deseja excluir?");

                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    dao.excluir(telaganho.selecionadoGanho.getIdGanhos());
                    alerta.close();
     
                    Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();
                } else {
                    alerta.close();
                }

            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Ganho NÃO deletado!", ButtonType.OK);
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um Ganho!", ButtonType.OK);
            alerta.show();
        }
    }

}
