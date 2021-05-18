/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telassmallfinancial;

import DAO.DAOCurso_Online;
import MODEL.Curso_Online;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InserirCursoController implements Initializable {

    public TextField txtNomeCurso;
    public TextField txtLinkDoCurso;
    public DatePicker dtDataCurso;
    public TextField txtDescCurso;
    MenuCursosController menu = new MenuCursosController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (menu.validacaoEditar == true) {

            String nome = menu.selecionado.getNome();
            String descricao = menu.selecionado.getDescricao();
            String link = menu.selecionado.getLink();
            Date data = menu.selecionado.getDataLimite();

            txtNomeCurso.setText(nome);
            txtDescCurso.setText(descricao);
            txtLinkDoCurso.setText(link);
            dtDataCurso.setValue(data.toLocalDate());
        }

    }

    @FXML
    private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("MenuCursos.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
        menu.validacaoEditar = false;
    }

    @FXML
    private void InserirCurso(ActionEvent event) throws IOException, SQLException {

        DAOCurso_Online dao = new DAOCurso_Online();

        String nome = txtNomeCurso.getText();
        String link = txtLinkDoCurso.getText();
        String descricao = txtDescCurso.getText();

        LocalDate localDataAux = dtDataCurso.getValue();
        Date dataAux = Date.valueOf(localDataAux);

        if (!nome.equals("") && !link.equals("") && !descricao.equals("") && dataAux != null) {

            if (menu.validacaoEditar == true) {

                menu.selecionado.setNome(nome);
                menu.selecionado.setLink(link);
                menu.selecionado.setDataLimite(dataAux);
                menu.selecionado.setDescricao(descricao);

                dao.alterar(menu.selecionado);
                menu.validacaoEditar = false;
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Curso atualizado com sucesso!", ButtonType.OK);
                alerta.show();
            } else {
                Curso_Online c = new Curso_Online();

                c.setNome(nome);
                c.setLink(link);
                c.setDataLimite(dataAux);
                c.setDescricao(descricao);
                dao.inserir(c);
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Curso salvo com sucesso!", ButtonType.OK);
                alerta.show();

            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
            alerta.show();

        }

    }

}
