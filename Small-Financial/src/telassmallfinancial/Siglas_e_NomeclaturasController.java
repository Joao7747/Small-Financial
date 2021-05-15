/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import Classes.Siglas;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class Siglas_e_NomeclaturasController implements Initializable {

    @FXML
    private TableView<Siglas> tvSiglasNomeclaturas;

    @FXML
    private TableColumn<Siglas, String> tcSiglas;

    @FXML
    private TableColumn<Siglas, String> tcDescricao;

    @FXML
    private Button btnVoltar;

    @FXML
    private void Voltar(ActionEvent event) throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcSiglas.setCellValueFactory(new PropertyValueFactory<>("Siglas"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        
        tvSiglasNomeclaturas.setItems(listarSiglas());
    }

    private ObservableList<Siglas> listarSiglas(){
        return FXCollections.observableArrayList(
                new Siglas("CDB", "Certificado de depósito bancario"),
                new Siglas("LCI", "Letra de Crédito Imobiliário"),
                new Siglas("LCA", "Letra de Crédito do Agronegócio"),
                new Siglas("Tesouro Direto", "É um programa do Tesouro Nacional"
                        + " para venda de títulos públicos federais"
                        + " para pessoas físicas."),
                new Siglas("Alíquotas", "Percentual aplicado para calcular o valor de algum tipo de imposto"),
                new Siglas("Amortização", "Redução gradual de uma dívida baseada em pagamentos periódicos"),
                new Siglas("Taxa Selic", "Selic é uma abreviação para Sistema Espacial"
                        + " de Liquidação e Custódia. Ela é a taxa média ajustada "
                        + "dos financiamentos diários apurados no sistema para títulos federais")
        );
    }
    
}
