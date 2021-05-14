/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

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

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InserirVideoController implements Initializable {

    public TextField txtTituloVideo;
    public TextField txtLinkVideo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
        private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }    
    
         @FXML
        private void InserirVideo(ActionEvent event) throws IOException, SQLException {
        
            String titulo = txtTituloVideo.getText();
            String link = txtLinkVideo.getText();
                    
            try            
            {
                if(!titulo.equals("") && !link.equals(""))
                {
                     Video v = new Video();
            
                    v.setDescricao(txtTituloVideo.getText());
                    v.setLink(txtLinkVideo.getText());
                
                    DAOVideo dao = new DAOVideo();    
                    dao.inserir(v);
                }
            }
            catch(Exception e)
            {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
                    alerta.show();    
            }

            
            
            
    }  
}
