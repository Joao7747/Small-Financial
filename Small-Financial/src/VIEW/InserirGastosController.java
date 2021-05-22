/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import DAO.DAOGastos;
import MODEL.Gastos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio o grande
 */
public class InserirGastosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextField txtValor;

    @FXML
    private DatePicker txtData;

    @FXML
    private TextArea txtObservacoes;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    GastosController menu = new GastosController();
    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoria();

        if (menu.validacaoEditar == true) {

            Double preco = menu.selecionado.getPreco();
            Categoria categoria = new Categoria(menu.selecionado.getCategoria());
            String observacao = menu.selecionado.getObservacao();
            Date data = menu.selecionado.getDataGasto();

            txtValor.setText(preco.toString());
            cbCategoria.setValue(categoria);
            txtObservacoes.setText(observacao);
            txtData.setValue(data.toLocalDate());
        }
    }

    @FXML
    public void inserir(ActionEvent event) throws IOException {
        try {

            if (txtData.getValue() != null && cbCategoria.getSelectionModel().getSelectedItem() != null && txtValor.getText() != null && txtObservacoes.getText() != null) {

                DAOGastos gastos = new DAOGastos();
                String categoria = cbCategoria.getSelectionModel().getSelectedItem().toString();
                String preco = txtValor.getText();
                String observacao = txtObservacoes.getText();
                LocalDate localDataAux = txtData.getValue();
                Date dataAux = Date.valueOf(localDataAux);

                if (menu.validacaoEditar == true) {

                    menu.selecionado.setCategoria(categoria);
                    menu.selecionado.setDataGasto(dataAux);
                    menu.selecionado.setIdUsuario(1);
                    menu.selecionado.setObservacao(observacao);
                    menu.selecionado.setPreco(Double.parseDouble(preco));

                    gastos.alterar(menu.selecionado);
                    menu.validacaoEditar = false;
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Gasto atualizado com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Gastos
                    Parent voltar = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();
                } else {
                    Gastos gasto = new Gastos();
                    gasto.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().toString());
                    gasto.setDataGasto(dataAux);
                    gasto.setIdUsuario(1);
                    gasto.setObservacao(txtObservacoes.getText());
                    gasto.setPreco(Double.parseDouble(txtValor.getText()));
                    gastos.inserir(gasto);
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Gasto salvo com sucesso!", ButtonType.OK);
                    alerta.show();
                    
                    //Voltar para Gastos
                    Parent voltar = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
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

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
        menu.validacaoEditar = false;
    }

    public void carregarCategoria() {
        Categoria cat1 = new Categoria("Alimentação");
        Categoria cat2 = new Categoria("Automovel");
        Categoria cat3 = new Categoria("Saúde");
        Categoria cat4 = new Categoria("Lazer");
        Categoria cat5 = new Categoria("Compras");
        Categoria cat6 = new Categoria("Investimentos");
        Categoria cat7 = new Categoria("Outros");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);
        cat.add(cat5);
        cat.add(cat6);
        cat.add(cat7);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }
}
