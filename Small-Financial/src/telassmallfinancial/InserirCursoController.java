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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
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
     
     
     
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
// TODO
    }

        @FXML
        private void Voltar(ActionEvent event) throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("Educacao.fxml"));
        Scene voltarScene = new Scene(voltar);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(voltarScene);
        window.show();
    }    
        
        @FXML
        private void InserirCurso(ActionEvent event) throws IOException, SQLException {
        
            
            
            try{
                Curso_Online c = new Curso_Online();
                
                String nome = txtNomeCurso.getText();
                String link = txtLinkDoCurso.getText();
                String descricao = txtDescCurso.getText();
            
                LocalDate localDataAux = dtDataCurso.getValue();
                Date dataAux = Date.valueOf(localDataAux);
                
                
                if(!nome.equals("") && !link.equals("") && !descricao.equals("") && dataAux != null)
                {
                    c.setNome(nome);
                    c.setLink(link);
                    c.setDataLimite(dataAux);
                    c.setDescricao(descricao);
                
                  DAOCurso_Online dao = new DAOCurso_Online();    
         
                    dao.inserir(c);
                }
            }
            catch(Exception e)
            {
                 Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos os campos precisam estar preenchidos", ButtonType.OK);
                 alerta.show(); 
            
            }
          
            
            
            
    }  
        
    
}
