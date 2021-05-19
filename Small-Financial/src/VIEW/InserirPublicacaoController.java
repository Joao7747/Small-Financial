/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
    private TextField txtTitulo;
    @FXML
    private TextField txtConteudo;
    @FXML
    private DatePicker txtDataPubli;
    @FXML
    private TextField txtAutor;

    MenuPublicacoesController telaPubli = new MenuPublicacoesController();
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (telaPubli.validacaoEditarPubli == true) {

            Date datapublicacao = telaPubli.selecionadoPubli.getDataPublicacao();
            String autor = telaPubli.selecionadoPubli.getAutor();
            String titulo = telaPubli.selecionadoPubli.getTitulo();
            String conteudo = telaPubli.selecionadoPubli.getConteudo();
            
            txtDataPubli.setValue(datapublicacao.toLocalDate());
            txtTitulo.setText(titulo);
            txtConteudo.setText(conteudo);
            txtAutor.setText(autor);
        }
    }    

    @FXML
    private void Voltar(ActionEvent event) throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("MenuPublicacoes.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
        telaPubli.validacaoEditarPubli = false;
        
    }
    
    @FXML
    private void Inserir(ActionEvent event) throws IOException {
        
        DAOPublicacao inserirPubli = new DAOPublicacao();
        Publicacao publicacao = new Publicacao();

        Date datapublicacao = java.sql.Date.valueOf(txtDataPubli.getValue());
        String autor = txtAutor.getText();
        String titulo = txtTitulo.getText();
        String conteudo = txtConteudo.getText();


        if (!(datapublicacao == null) && !autor.equals("") && !conteudo.equals("") && !titulo.equals("")) {

            if (telaPubli.validacaoEditarPubli == true) {

                telaPubli.selecionadoPubli.setAutor(autor);
                telaPubli.selecionadoPubli.setConteudo(conteudo);
                telaPubli.selecionadoPubli.setDataPublicacao(datapublicacao);
                telaPubli.selecionadoPubli.setTitulo(titulo);

                inserirPubli.alterar(telaPubli.selecionadoPubli);
                telaPubli.validacaoEditarPubli = false;
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Publicacao atualizada com sucesso!", ButtonType.OK);
                alerta.show();
                
                //Voltar para Publicacoes
                Parent voltar = FXMLLoader.load(getClass().getResource("MenuPublicacoes.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.show();
            } else {
                
                publicacao.setTitulo(txtTitulo.getText());
                publicacao.setAutor(txtAutor.getText());
                Date dataPubli = java.sql.Date.valueOf(txtDataPubli.getValue());
                publicacao.setDataPublicacao(dataPubli);
                publicacao.setConteudo(txtConteudo.getText());

                DAOPublicacao inserirpubli = new DAOPublicacao();
                inserirpubli.inserir(publicacao);
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Publicacao salva com sucesso!", ButtonType.OK);
                alerta.show();
                
                //Voltar para Publicacoes
                Parent voltar = FXMLLoader.load(getClass().getResource("MenuPublicacoes.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.show();
                

            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
            alerta.show();

        }
        
    }
    
}
