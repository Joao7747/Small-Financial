/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Classes.Categoria;
import DAO.DAOMetas;
import MODEL.Metas;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

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
    private Label lblDesejo;

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

    //Variaveis
    private List<Categoria> cat = new ArrayList<>();

    private ObservableList<Categoria> obsCat;

    private LocalDate dataInserido;

    private LocalDate dataAuxChange;

    private LocalDate dataAuxChange2;

    private String custoAux;

    MetasController cont = new MetasController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoria();

        txtDataRealizacao.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                dataAuxChange = (LocalDate) newValue;
                if (cont.verificaEditar == true) {
                    long diferencaMes = ChronoUnit.MONTHS.between(dataInserido, dataAuxChange);
                    double valorPoupar = Double.parseDouble(custoAux) / diferencaMes;
                    DecimalFormat decimal = new DecimalFormat("0.00");

                    txtValorGuardado.setText("Para alcançar a meta na data de "
                            + dataAuxChange.toString() + ", você deverá guardar "
                            + decimal.format(valorPoupar) + " por mês.");
                } else {
                    long diferencaMes = ChronoUnit.MONTHS.between(LocalDate.now(), dataAuxChange);
                    double valorPoupar = Double.parseDouble(custoAux) / diferencaMes;
                    DecimalFormat decimal = new DecimalFormat("0.00");

                    txtValorGuardado.setText("Para alcançar a meta na data de "
                            + dataAuxChange.toString() + ", você deverá guardar "
                            + decimal.format(valorPoupar) + " por mês.");
                }

            }
        });

        txtCusto.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                custoAux = newValue.toString().substring(3);
                if (cont.verificaEditar == true) {
                    long diferencaMes = ChronoUnit.MONTHS.between(dataInserido, dataAuxChange);
                    double valorPoupar = Double.parseDouble(custoAux) / diferencaMes;
                    DecimalFormat decimal = new DecimalFormat("0.00");

                    txtValorGuardado.setText("Para alcançar a meta na data de "
                            + dataAuxChange.toString() + ", você deverá guardar "
                            + decimal.format(valorPoupar) + " por mês.");
                } else {
                    long diferencaMes = ChronoUnit.MONTHS.between(LocalDate.now(), dataAuxChange);
                    double valorPoupar = Double.parseDouble(custoAux) / diferencaMes;
                    DecimalFormat decimal = new DecimalFormat("0.00");

                    txtValorGuardado.setText("Para alcançar a meta na data de "
                            + dataAuxChange.toString() + ", você deverá guardar "
                            + decimal.format(valorPoupar) + " por mês.");
                }
            }
        });

        if (cont.verificaEditar == true) {
            lblDesejo.setText("Altere sua meta ou desejo");
            Categoria cat = new Categoria(cont.selecionado.getCategoria());
            cbCategoria.setValue(cat);
            txtDataRealizacao.setValue(cont.selecionado.getDataRealizacao().toLocalDate());
            txtCusto.setText("R$ " + cont.selecionado.getCustoTotal());
            txtDescricao.setText(cont.selecionado.getDescricao());
            txtObservacao.setText(cont.selecionado.getObservacao());
            dataInserido = cont.selecionado.getDataPrevista().toLocalDate();

            long diferencaMes = ChronoUnit.MONTHS.between(dataInserido, cont.selecionado.getDataRealizacao().toLocalDate());
            double valorPoupar = Double.parseDouble(txtCusto.getText().substring(3)) / diferencaMes;
            DecimalFormat decimal = new DecimalFormat("0.00");
            txtValorGuardado.setText("Para alcançar a meta na data de "
                    + cont.selecionado.getDataRealizacao().toLocalDate().toString() + ", você deverá guardar "
                    + decimal.format(valorPoupar) + " por mês.");
        } else {
            lblDesejo.setText("Insira sua meta ou desejo");
        }
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        cont.verificaEditar = false;
        Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.centerOnScreen();
    }

    @FXML
    private void inserir(ActionEvent event) throws ParseException, IOException, SQLException {

        try {
            
            if (!txtDescricao.getText().equals("") && cbCategoria.getSelectionModel().getSelectedItem() != null
                    && !txtCusto.getText().substring(3).equals("") && Date.valueOf(txtDataRealizacao.getValue()) != null
                    && !txtObservacao.getText().equals("")) {

                LocalDate localDataAux = txtDataRealizacao.getValue();
                Date dataAux = Date.valueOf(localDataAux);
                Categoria cat = cbCategoria.getSelectionModel().getSelectedItem();

                Metas m = new Metas();
                DAOMetas dao = new DAOMetas();
                m.setDescricao(txtDescricao.getText());
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
                double valorGuardado = 100.90;
                m.setValorGuardado(valorGuardado);
                int usuario = 1;
                m.setIdUsuario(usuario);
                if (cont.verificaEditar == true) {
                    m.setIdMetas(cont.selecionado.getIdMetas());
                    Date data = Date.valueOf(dataInserido);
                    m.setDataPrevista(data);
                    dao.alterar(m);
                    cont.verificaEditar = false;
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Meta atualizado com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Metas
                    Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
                    Scene voltarScene = new Scene(voltar);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(voltarScene);
                    window.centerOnScreen();
                } else {
                    Date data = Date.valueOf(hoje);
                    m.setDataPrevista(data);
                    dao.inserir(m);
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Meta salva com sucesso!", ButtonType.OK);
                    alerta.show();

                    //Voltar para Metas
                    Parent voltar = FXMLLoader.load(getClass().getResource("Metas.fxml"));
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

    public void carregarCategoria() {
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
