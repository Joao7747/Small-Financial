/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAOUsuario;
import MODEL.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Caio
 */
public class ConfiguracaoContaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    DAOUsuario user = new DAOUsuario();
    @FXML
    private Button btnLogoff;
    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtConfirmSenha;

    @FXML
    private RadioButton rbEuro;

    @FXML
    private RadioButton rbDolar;

    @FXML
    private RadioButton rbLibras;

    @FXML
    private Button btnSaldo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblSenha;
    @FXML
    private Label lblConfirmarSenha;
    @FXML
    private TextField txtSaldo;
    @FXML
    private Label lblSaldo;
    @FXML
    private Label lblAviso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNome.setEditable(false);
        txtEmail.setEditable(false);
        lblConfirmarSenha.setVisible(false);
        lblSenha.setVisible(false);
        txtSenha.setVisible(false);
        txtConfirmSenha.setVisible(false);
        txtSaldo.setVisible(false);
        lblSaldo.setVisible(false);
        txtNome.setText(user.IdNome().getNome());
        txtEmail.setText(user.IdNome().getEmail());
        txtNome.setText(user.IdNome().getNome());
        txtSenha.setText(user.IdNome().getSenha());
        txtConfirmSenha.setText(user.IdNome().getSenha());
        txtSaldo.setText(String.valueOf(user.IdNome().getSaldo()));
    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {

        if (btnVoltar.getText().equals("Voltar")) {
            Parent voltar = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene voltarScene = new Scene(voltar);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(voltarScene);
            window.centerOnScreen();
        } else {
            btnVoltar.setText("Voltar");
            btnSaldo.setVisible(true);
            lblConfirmarSenha.setVisible(false);
            lblSenha.setVisible(false);
            txtSenha.setVisible(false);
            txtConfirmSenha.setVisible(false);
            txtSaldo.setVisible(false);
            lblSaldo.setVisible(false);
            btnSalvar.setText("Editar");

        }

    }

    @FXML
    private void Logoff(ActionEvent event) throws IOException {
        int input = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?");
        if (input == JOptionPane.YES_OPTION) {
            DAOUsuario user = new DAOUsuario();
            user.Logoff();
            Parent voltar = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene voltarScene = new Scene(voltar);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(voltarScene);
            window.centerOnScreen();
        }

    }

    @FXML
    private void UserUpdate(ActionEvent event) throws IOException, SQLException {

        if (btnSalvar.getText().equals("Salvar")) {
            DAOUsuario updateuser = new DAOUsuario();
            Usuario usuario = new Usuario();
            usuario.setEmail(txtEmail.getText());
            usuario.setIdUsuario(user.IdNome().getIdUsuario());
            usuario.setNome(txtNome.getText());
            Double saldo = Double.parseDouble(txtSaldo.getText());
            usuario.setSaldo(saldo);
            String senha = txtSenha.getText();

            if (!"".equals(senha)) {
                if (txtSenha.getText().equals(txtConfirmSenha.getText())) {
                    usuario.setSenha(txtSenha.getText());
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING, "As senhas não conferem!", ButtonType.OK);
                    alerta.show();
                }
            } else {
                usuario.setSenha(user.IdNome().getSenha());
            }
            updateuser.alterar(usuario);
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Alterado com sucesso!", ButtonType.OK);
            alerta.show();
            txtNome.setEditable(false);
            txtEmail.setEditable(false);
            lblAviso.setVisible(true);
            btnSaldo.setVisible(true);
            lblConfirmarSenha.setVisible(false);
            lblSenha.setVisible(false);
            txtSenha.setVisible(false);
            txtConfirmSenha.setVisible(false);
            txtSaldo.setVisible(false);
            lblSaldo.setVisible(false);
            btnSalvar.setText("Editar");
            btnVoltar.setText("Voltar");
        } else {
            lblAviso.setVisible(false);
            lblSaldo.setVisible(true);
            btnSaldo.setVisible(false);
            txtSaldo.setVisible(true);
            lblConfirmarSenha.setVisible(true);
            lblSenha.setVisible(true);
            txtSenha.setVisible(true);
            txtConfirmSenha.setVisible(true);
            btnSalvar.setText("Salvar");
            btnVoltar.setText("Cancelar");
            txtNome.setEditable(true);
            txtEmail.setEditable(true);
        }

    }

    @FXML
    private void Saldo(ActionEvent event) throws IOException, InterruptedException {

        List<Usuario> pegarsalario = user.consultar(user.IdNome().getIdUsuario());

        if (btnSaldo.getText().equals("Saldo")) {
            btnSaldo.setText(String.valueOf(pegarsalario.get(0).getSaldo()));
        } else {
            btnSaldo.setText("Saldo");
        }
    }

}
