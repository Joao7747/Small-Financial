/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOPublicacao;
import MODEL.Publicacao;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class InserirPublicacaoController implements Initializable {

    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML
    private PasswordField txtTitulo;
    @FXML
    private TextField txtConteudo;
    @FXML
    private DatePicker txtDataPubli;
    @FXML
    private PasswordField txtAutor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    @FXML
    private void Inserir(ActionEvent event) {
        
        Publicacao publicacao = new Publicacao();
        publicacao.setTitulo(txtTitulo.getText());
        publicacao.setAutor(txtAutor.getText());
        Date dataPubli = java.sql.Date.valueOf(txtDataPubli.getValue());
        publicacao.setDataPublicacao(dataPubli);
        publicacao.setConteudo(txtConteudo.getText());

        
        DAOPublicacao inserirpubli = new DAOPublicacao();
        inserirpubli.inserir(publicacao);   
    }
    
}
