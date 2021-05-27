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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


   

public class VisualizarPublicacaoController implements Initializable {

    
    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtDataDePublicacao;

    @FXML
    private TextField txtConteudo;

    @FXML
    private TextField txtAutor;

    EducacaoController educ = new EducacaoController();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtTitulo.setText(educ.PublicacaoSelecionado.getTitulo());
        txtDataDePublicacao.setText(educ.PublicacaoSelecionado.getDataPublicacao().toLocalDate().toString());
        txtConteudo.setText(educ.PublicacaoSelecionado.getConteudo());
        txtAutor.setText(educ.PublicacaoSelecionado.getAutor());
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
