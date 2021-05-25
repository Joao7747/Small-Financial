/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOMetas;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
public class VisualizarMetasController implements Initializable {

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtRealizacao;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextArea txtValorPoupar;

    MetasController cont = new MetasController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DecimalFormat decimal = new DecimalFormat("0.00");
        Double preco = cont.selecionado.getCustoTotal();
        txtCategoria.setText(cont.selecionado.getCategoria());
        txtRealizacao.setText(cont.selecionado.getDataRealizacao().toLocalDate().toString());
        txtPreco.setText(preco.toString());
        txtDescricao.setText(cont.selecionado.getDescricao());
        txtObservacao.setText(cont.selecionado.getObservacao());

        txtValorPoupar.setText("Para alcançar a meta na data de "
                + cont.selecionado.getDataRealizacao().toLocalDate().toString() + ", você deverá guardar "
                + decimal.format(cont.selecionado.getValorIdealPoupar()) + " por mês.");
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        cont.verificaEditar = true;

        Parent voltar = FXMLLoader.load(getClass().getResource("InserirMeta.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void excluir(ActionEvent event) {
        try {
            DAOMetas dao = new DAOMetas();
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("O dado será permanentemente excluido!!");
            alerta.setContentText("tem certeza que deseja excluir?");

            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.excluir(cont.selecionado.getIdMetas());
                alerta.close();

                Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.centerOnScreen();
            } else {
                alerta.close();
            }

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Meta NÃO deletada!", ButtonType.OK);
            alerta.show();
        }
    }

}
