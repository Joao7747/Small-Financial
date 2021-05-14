/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAODividas;
import MODEL.Dividas;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class ContasInsertController implements Initializable {

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
    private TextArea txtObsevacoes;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtVencimento;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton rbParcelado;

    @FXML
    private RadioButton rbFixa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void inserir(){
        try{
            DAODividas dividas = new DAODividas();
        Dividas divida = new Dividas();
        divida.setCategoria(cbCategoria.getText());
        divida.setDescricao(txtDescricao.getText());
        if(rbFixa.isSelected()){
            divida.setFixa(true);
            divida.setParcelado(false);
        }
        else{
            divida.setParcelado(true);
            divida.setFixa(false);
            divida.setNumeroParcelas(Integer.parseInt(txtNumParcela.getText()));
        }
        divida.setObservacao(txtObsevacoes.getText());
        divida.setValor(Integer.parseInt(txtValor.getText()));
        Date date = Date.valueOf(txtVencimento.getText());
        divida.setVencimento(date);
        divida.setIdUsuario(1);
        dividas.inserir(divida);
        JOptionPane.showConfirmDialog(null, "Cadastrado com sucesso!", "Alerta!", JOptionPane.DEFAULT_OPTION);
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.toString(), "Ops, algo deu errado", JOptionPane.DEFAULT_OPTION);
            System.out.println(e);
        }
    }
}
