/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOMetas;
import MODEL.Metas;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class TelaPouparController implements Initializable {

    @FXML
    private TextField txtPoupar;

    @FXML
    private Button btnPoupar;

    @FXML
    private Button btnVoltar;

    MetasController cont = new MetasController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void pouparMeta(ActionEvent event) {
        try {
            DAOMetas dao = new DAOMetas();
            Metas m = new Metas();

            Double valor = Double.parseDouble(txtPoupar.getText());
            Double Total = valor + cont.selecionado.getValorGuardado();
            m.setValorGuardado(Total);
            m.setIdMetas(cont.selecionado.getIdMetas());
            dao.poupar(m);
            cont.verificaEditar = false;
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Valor poupado com sucesso!", ButtonType.OK);
            alerta.show();

            Parent inserir = FXMLLoader.load(getClass().getResource("Metas.fxml"));
            Scene inserirScene = new Scene(inserir);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inserirScene);
            window.centerOnScreen();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.toString(), "Ops, algo deu errado", JOptionPane.DEFAULT_OPTION);
            System.out.println(e);
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        Parent inserir = FXMLLoader.load(getClass().getResource("VisualizarMetas.fxml"));
        Scene inserirScene = new Scene(inserir);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inserirScene);
        window.centerOnScreen();
    }
}
