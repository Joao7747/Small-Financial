/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Auditoria.MainThread;
import DAO.DAOUsuario;
import MODEL.Usuario;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr13f
 */
public class MenuController implements Initializable {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblSaudacao;
    @FXML
    private Button btnMetas;
    @FXML
    private Button btnRelatorios;
    @FXML
    private Button btnGastos;
    @FXML
    private Button btnGanhos;
    @FXML
    private Button btnEducacao;
    @FXML
    private Button btnConfig;
    @FXML
    private Button btnSaldo;
    @FXML
    private Button btnContas;
    
    DAOUsuario user = new DAOUsuario();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] horarios = {"12:00", "17:59", "4:59"};
        Calendar dia = Calendar.getInstance();
        Calendar tarde = Calendar.getInstance();
        Calendar noite = Calendar.getInstance();
        try {
            dia.setTime(new SimpleDateFormat("h:mm").parse(horarios[0]));
            tarde.setTime(new SimpleDateFormat("h:mm").parse(horarios[1]));
            noite.setTime(new SimpleDateFormat("h:mm").parse(horarios[2]));
            
        } catch (ParseException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date horaAtual = new Date(System.currentTimeMillis());

        if (dia.getTime().compareTo(horaAtual) < 0 && noite.getTime().compareTo(horaAtual) > 0) {
            lblSaudacao.setText("Bom dia");
        }
        else if(tarde.getTime().compareTo(horaAtual) < 0 && dia.getTime().compareTo(horaAtual) > 0){
            lblSaudacao.setText("Boa tarde");
        }
        else{
            lblSaudacao.setText("Boa noite");
        }
        lblNome.setText(user.IdNome().getNome());
    }

    @FXML
    private void Metas(ActionEvent event) throws IOException, InterruptedException {

        //auditoria
//        MainThread auditoria = new MainThread();
//        auditoria.StartThread("Metas");
        //fim auditoria

        Parent metas = FXMLLoader.load(getClass().getResource("Metas.fxml"));
        Scene metasScene = new Scene(metas);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(metasScene);
        window.centerOnScreen();
    }

    @FXML
    private void Gastos(ActionEvent event) throws IOException, InterruptedException {

        //auditoria
//        MainThread auditoria = new MainThread();
//        auditoria.StartThread("Gastos");
        //fim auditoria

        Parent gastos = FXMLLoader.load(getClass().getResource("Gastos.fxml"));
        Scene gastosScene = new Scene(gastos);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gastosScene);
        window.centerOnScreen();
    }

    @FXML
    private void Contas(ActionEvent event) throws IOException, InterruptedException {

        //auditoria
//        MainThread auditoria = new MainThread();
//        auditoria.StartThread("Contas");
        //fim auditoria

        Parent contas = FXMLLoader.load(getClass().getResource("Dividas.fxml"));
        Scene contasScene = new Scene(contas);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(contasScene);
        window.centerOnScreen();
    }

    @FXML
    private void Ganhos(ActionEvent event) throws IOException, InterruptedException {

        //auditoria
//        MainThread auditoria = new MainThread();
//        auditoria.StartThread("Ganhos");
        //fim auditoria

        Parent ganhos = FXMLLoader.load(getClass().getResource("Ganhos.fxml"));
        Scene ganhosScene = new Scene(ganhos);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ganhosScene);
        window.centerOnScreen();
    }

    @FXML
    private void Educacao(ActionEvent event) throws IOException, InterruptedException {

        //auditoria
//        MainThread auditoria = new MainThread();
//        auditoria.StartThread("Educacao");
        //fim auditoria

        Parent educacao = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene educacaoScene = new Scene(educacao);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(educacaoScene);
        window.centerOnScreen();
    }

    @FXML
    private void Configuracao(ActionEvent event) throws IOException, InterruptedException {
        Parent config = FXMLLoader.load(getClass().getResource("ConfiguracaoConta.fxml"));
        Scene configScene = new Scene(config);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(configScene);
        window.centerOnScreen();
    }
    
    @FXML
    private void Saldo(ActionEvent event) throws IOException, InterruptedException {
        
        List<Usuario> pegarsalario = user.consultar(user.IdNome().getIdUsuario());
        
        if (btnSaldo.getText().equals("Saldo")) {
            btnSaldo.setText(String.valueOf(pegarsalario.get(0).getSaldo()));
        }
        else
            btnSaldo.setText("Saldo");
    }
    
    
}
