/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAODividas;
import MODEL.Dividas;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class InserirDividasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField cbCategoria;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtNumParcela;

    @FXML
    private TextField txtObservacoes;

    @FXML
    private TextField txtValor;

    @FXML
    private DatePicker txtVencimento;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton rbParcelado;

    @FXML
    private RadioButton rbFixa;

    DividasController menu = new DividasController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (menu.validacaoEditar == true) {

            String categoria = menu.selecionado.getCategoria();
            String descricao = menu.selecionado.getDescricao();
            Integer parcelas = menu.selecionado.getNumeroParcelas();
            Double valor = menu.selecionado.getValor();
            Date data = menu.selecionado.getVencimento();
            String observacoes = menu.selecionado.getObservacao();

            cbCategoria.setText(categoria);
            txtDescricao.setText(descricao);
            txtNumParcela.setText(parcelas.toString());
            txtVencimento.setValue(data.toLocalDate());
            txtValor.setText(valor.toString());
            txtObservacoes.setText(observacoes);
        }
    }

    @FXML
    public void inserir(ActionEvent event) throws IOException {
        try {
            DAODividas dividas = new DAODividas();
            String categoria = cbCategoria.getText();
            String descricao = txtDescricao.getText();
            String parcelas = txtNumParcela.getText();
            LocalDate localDataAux = txtVencimento.getValue();
            Date dataAux = Date.valueOf(localDataAux);
            String valor = txtValor.getText();
            String observacao = txtObservacoes.getText();

            if (menu.validacaoEditar == true) {
                menu.selecionado.setCategoria(categoria);
                menu.selecionado.setVencimento(dataAux);
                menu.selecionado.setIdUsuario(1);
                menu.selecionado.setDescricao(descricao);
                menu.selecionado.setValor(Double.parseDouble(valor));
                menu.selecionado.setObservacao(observacao);
                if (rbFixa.isSelected()) {
                    menu.selecionado.setFixa(true);
                    menu.selecionado.setParcelado(false);
                    menu.selecionado.setNumeroParcelas(1);
                } else {
                    menu.selecionado.setFixa(false);
                    menu.selecionado.setParcelado(true);
                    menu.selecionado.setNumeroParcelas(Integer.parseInt(parcelas));
                }

                dividas.alterar(menu.selecionado);
                menu.validacaoEditar = false;
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Conta atualizada com sucesso!", ButtonType.OK);
                alerta.show();

                //Voltar para Contas
                Parent voltar = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.centerOnScreen();

            } else {
                Dividas divida = new Dividas();
                divida.setCategoria(categoria);
                divida.setDescricao(descricao);
                divida.setIdUsuario(1);
                divida.setVencimento(dataAux);
                if (rbFixa.isSelected()) {
                    divida.setFixa(true);
                    divida.setParcelado(false);
                    divida.setNumeroParcelas(1);
                } else {
                    divida.setFixa(false);
                    divida.setParcelado(true);
                    divida.setNumeroParcelas(Integer.parseInt(parcelas));
                }
                divida.setObservacao(observacao);
                divida.setValor(Double.parseDouble(valor));
                dividas.inserir(divida);
                JOptionPane.showConfirmDialog(null, "Cadastrado com sucesso!", "Alerta!", JOptionPane.DEFAULT_OPTION);

                //Voltar para Contas
                Parent voltar = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
                Scene voltarScene = new Scene(voltar);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(voltarScene);
                window.centerOnScreen();
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.toString(), "Ops, algo deu errado", JOptionPane.DEFAULT_OPTION);
            System.out.println(e);
        }
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        menu.validacaoEditar = false;
        Parent voltar = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

}
