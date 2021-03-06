/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOVideo;
import MODEL.Video;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InserirVideoController implements Initializable {

    public TextField txtTituloVideo;
    public TextField txtLinkVideo;
    MenuVideosController menuVideos = new MenuVideosController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (menuVideos.validacaoEditarVideo == true) {

            String descricao = menuVideos.selecionadoVideo.getDescricao();
            String link = menuVideos.selecionadoVideo.getLink();

            txtTituloVideo.setText(descricao);
            txtLinkVideo.setText(link);
        }
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("MenuVideos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
        menuVideos.validacaoEditarVideo = false;
    }

    @FXML
    private void InserirVideo(ActionEvent event) throws IOException, SQLException {

        try {
            if (txtTituloVideo.getText() != null && txtLinkVideo.getText() != null) {

                String titulo = txtTituloVideo.getText();
                String link = txtLinkVideo.getText();

                DAOVideo dao = new DAOVideo();

                if (menuVideos.validacaoEditarVideo == true) {

                    menuVideos.selecionadoVideo.setDescricao(titulo);
                    menuVideos.selecionadoVideo.setLink(link);

                    dao.alterar(menuVideos.selecionadoVideo);
                    menuVideos.validacaoEditarVideo = false;
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "V??deo atualizado com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Video
                    Parent voltar = FXMLLoader.load(getClass().getResource("MenuVideo.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();

                } else {
                    Video v = new Video();
                    v.setLink(link);
                    v.setDescricao(titulo);
                    dao.inserir(v);
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "V??deo salvo com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Video
                    Parent voltar = FXMLLoader.load(getClass().getResource("MenuVideo.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();

                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
                alerta.show();
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.toString(), "Ops, algo deu errado", JOptionPane.DEFAULT_OPTION);
            System.out.println(e);
        }

    }
}
