/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import Classes.Categoria;
import DAO.DAOMetas;
import MODEL.Metas;
import java.awt.event.InputMethodEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
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
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class InserirMetaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtDescricao;

    @FXML
    private DatePicker txtDataRealizacao;

    @FXML
    private TextField txtCusto;

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextArea txtObservacao;

    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private TextArea txtValorGuardado;
    
    private List<Categoria> cat = new ArrayList<>();
    
    private ObservableList<Categoria> obsCat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoria();
    }    
    
    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    @FXML
    private void inserir(ActionEvent event) throws ParseException, IOException, SQLException {
        Metas m = new Metas();
        DAOMetas dao = new DAOMetas();
        m.setDescricao(txtDescricao.getText());
        Categoria cat = cbCategoria.getSelectionModel().getSelectedItem();
        m.setCategoria(cat.getCategoria());
        m.setCustoTotal(Double.parseDouble(txtCusto.getText().substring(3)));
        Date datasql = Date.valueOf(txtDataRealizacao.getValue());
        m.setDataRealizacao(datasql);
        m.setObservacao(txtObservacao.getText());
        LocalDate tes = txtDataRealizacao.getValue();
        LocalDate hoje = LocalDate.now();
        long diferencaMes = ChronoUnit.MONTHS.between(hoje, tes);
        double valorPoupar = Double.parseDouble(txtCusto.getText().substring(3)) / diferencaMes;
        m.setValorIdealPoupar(valorPoupar);
        byte b = 1;
        m.setStatusMeta(b);
        Date data = Date.valueOf(hoje);
        m.setDataPrevista(data);
        double valorGuardado = 100.90;
        m.setValorGuardado(valorGuardado);
        int usuario = 1;
        m.setIdUsuario(usuario);
        dao.inserir(m);
        
        Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }
    
    public void carregarCategoria(){
        Categoria cat1 = new Categoria("Viagem");
        Categoria cat2 = new Categoria("Carro");
        Categoria cat3 = new Categoria("Casa");
        Categoria cat4 = new Categoria("Acessórios");
        Categoria cat5 = new Categoria("Outros");
        
        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);
        cat.add(cat5);
        
        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }
}
