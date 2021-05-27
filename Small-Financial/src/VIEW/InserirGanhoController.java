/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;


import Classes.Categoria;
import DAO.DAOGanhos;
import DAO.DAOUsuario;
import MODEL.Ganhos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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

    private ComboBox<Categoria> cbCategoria;

    //Variaveis
    private List<Categoria> cat = new ArrayList<>();
    private ObservableList<Categoria> obsCat;
    GanhosController telaganho = new GanhosController();
    DAOUsuario user = new DAOUsuario();

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

            Categoria cat = new Categoria(telaganho.selecionadoGanho.getCategoria());

            txtDataRecebimento.setValue(datarecebimento.toLocalDate());
            txtValor.setText(valor.toString());
            txtObservacoes.setText(observacao);
            cbCategoria.setValue(cat);
        }
    }

    public void carregarCategoria() {
        Categoria cat1 = new Categoria("Salario");
        Categoria cat2 = new Categoria("PLR");
        Categoria cat3 = new Categoria("Décimo Terceiro");
        Categoria cat4 = new Categoria("Venda");
        Categoria cat5 = new Categoria("Bonificação");
        Categoria cat6 = new Categoria("Premiação");
        Categoria cat7 = new Categoria("Investimentos");
        Categoria cat8 = new Categoria("Outros");

        cat.add(cat1);
        cat.add(cat2);
        cat.add(cat3);
        cat.add(cat4);
        cat.add(cat5);
        cat.add(cat6);
        cat.add(cat7);
        cat.add(cat8);

        obsCat = FXCollections.observableArrayList(cat);
        cbCategoria.setItems(obsCat);
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
        telaganho.validacaoEditarGanho = false;
    }

    @FXML
    private void Inserir(ActionEvent event) throws IOException {

        try {

            if (txtDataRecebimento.getValue() != null && txtValor.getText() != null 
                    && cbCategoria.getSelectionModel().getSelectedItem() != null) {

                DAOGanhos inserirganho = new DAOGanhos();
                Ganhos ganho = new Ganhos();
                Date data = java.sql.Date.valueOf(txtDataRecebimento.getValue());
                Double valor = Double.valueOf(txtValor.getText());
                String observacao = (txtObservacoes.getText());
                String categoria = cbCategoria.getSelectionModel().getSelectedItem().toString();

                if (telaganho.validacaoEditarGanho == true) {

                    telaganho.selecionadoGanho.setDataGanho(data);
                    telaganho.selecionadoGanho.setValor(valor);
                    telaganho.selecionadoGanho.setObservacao(observacao);
                    telaganho.selecionadoGanho.setIdUsuario(user.IdNome().getIdUsuario());
                    telaganho.selecionadoGanho.setCategoria(categoria);

                    inserirganho.alterar(telaganho.selecionadoGanho);
                    telaganho.validacaoEditarGanho = false;
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Ganho atualizado com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Ganhos
                    Parent voltar = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();

                } else {

                    ganho.setIdUsuario(user.IdNome().getIdUsuario());
                    ganho.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().toString());
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

}
