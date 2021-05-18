/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOGanhos;
import MODEL.Ganhos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class InserirGanhoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSalvar;
    @FXML
    private DatePicker txtDataRecebimento;
    @FXML
    private TextField txtValor;
    @FXML
    private TextArea txtObservacoes;
    @FXML
    private ComboBox<String> cbCategoria;
    
    GanhosController telaganho = new GanhosController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoria();
        if (telaganho.validacaoEditarGanho == true) {

            Date datarecebimento = telaganho.selecionadoGanho.getDataGanho();
            Double valor = telaganho.selecionadoGanho.getValor();
            String observacao = telaganho.selecionadoGanho.getObservacao();
            
            txtDataRecebimento.setValue(datarecebimento.toLocalDate());
            txtValor.setText(valor.toString());
            txtObservacoes.setText(observacao); 
        }
    } 
    
    public void carregarCategoria(){ 
       ObservableList<String> obsCategoria = FXCollections.observableArrayList();
       obsCategoria.add("Salario");
       obsCategoria.add("Venda");
       obsCategoria.add("Bonificação");
       obsCategoria.add("Outros");
       cbCategoria.setItems(obsCategoria); 
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
        telaganho.validacaoEditarGanho = false;
    }
    
    @FXML
    private void Inserir(ActionEvent event) throws IOException {
        
        DAOGanhos inserirganho = new DAOGanhos();
        Ganhos ganho = new Ganhos();

        Date data = java.sql.Date.valueOf(txtDataRecebimento.getValue());
        Double valor  = Double.valueOf(txtValor.getText());
        String observacao = (txtObservacoes.getText());


        if (!(data == null) && !valor.equals("") && !observacao.equals("") && cbCategoria.getSelectionModel().getSelectedItem() == null) {

            if (telaganho.validacaoEditarGanho == true) {

                telaganho.selecionadoGanho.setDataGanho(data);
                telaganho.selecionadoGanho.setValor(valor);
                telaganho.selecionadoGanho.setObservacao(observacao);
                telaganho.selecionadoGanho.setIdUsuario(1);
                telaganho.selecionadoGanho.setCategoria(cbCategoria.getSelectionModel().getSelectedItem());

                inserirganho.alterar(telaganho.selecionadoGanho);
                telaganho.validacaoEditarGanho = false;
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Ganho atualizado com sucesso!", ButtonType.OK);
                alerta.show();
                
                //Voltar para Ganhos
                Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.show();
                
            } else {
                
                ganho.setIdUsuario(1);
                ganho.setCategoria(cbCategoria.getSelectionModel().getSelectedItem());
                Date dataganho = java.sql.Date.valueOf(txtDataRecebimento.getValue());
                ganho.setDataGanho(dataganho);
                ganho.setObservacao(txtObservacoes.getText());
                ganho.setValor(Double.valueOf(txtValor.getText()));

                inserirganho.inserir(ganho);
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Ganho salvo com sucesso!", ButtonType.OK);
                alerta.show();
                
                //Voltar para Ganhos
                Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
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
