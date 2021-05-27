/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class VisualizarCurso_OnlineController implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtNomeDoCurso;

    @FXML
    private TextField txtDataDeTermino;

    @FXML
    private TextField txtDescrição;

    @FXML
    private TextField txtLink;
    
    EducacaoController educ = new EducacaoController();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtNomeDoCurso.setText(educ.cursoSelecionado.getNome());
        txtDataDeTermino.setText(educ.cursoSelecionado.getDataLimite().toLocalDate().toString());
        txtDescrição.setText(educ.cursoSelecionado.getDescricao());
        txtLink.setText(educ.cursoSelecionado.getLink());
        
    }    
    
    @FXML
    void Voltar(ActionEvent event) throws IOException {

        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();

    }
    
}
