/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOGastos;
import MODEL.Gastos;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio o grande
 */
public class GastosInsertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField cbCategoria;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtData;

    @FXML
    private TextArea txtObservacoes;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void inserir() {
        try {
            DAOGastos gastos = new DAOGastos();
            Gastos gasto = new Gastos();
            gasto.setCategoria(cbCategoria.getText());
            Date date = Date.valueOf(txtData.getText());
            gasto.setDataGasto(date);
            gasto.setIdUsuario(1);
            gasto.setObservacao(txtObservacoes.getText());
            gasto.setPreco(Double.parseDouble(txtValor.getText()));
            gastos.inserir(gasto);
            JOptionPane.showConfirmDialog(null, "Cadastrado com sucesso!", "Alerta!", JOptionPane.DEFAULT_OPTION);
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.toString(), "Ops, algo deu errado", JOptionPane.DEFAULT_OPTION);
            System.out.println(e);
        }
    }
}
