package DAO;

import MODEL.Ganhos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class DAOGanhos implements DAOGenerica<Ganhos> {

    private ConexaoBanco conexao;

    public DAOGanhos() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Ganhos ganhos) {
        String sql = "INSERT INTO Ganhos (Categoria, Valor , Data_Ganho, "
                + "Observacao, idUsuario) VALUES (?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, ganhos.getCategoria());
                sentenca.setDouble(2, ganhos.getValor());
                sentenca.setDate(3, ganhos.getDataGanho());
                sentenca.setString(4, ganhos.getObservacao());
                sentenca.setInt(5, ganhos.getIdUsuario());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Ganhos ganhos) {
        String sql = "UPDATE Ganhos SET Categoria = ? , Valor = ? , Data_Ganho = ?, "
                + "Observacao = ?, idUsuario = ? WHERE idGastos = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, ganhos.getCategoria());
                sentenca.setDouble(2, ganhos.getValor());
                sentenca.setDate(3, ganhos.getDataGanho());
                sentenca.setString(4, ganhos.getObservacao());
                sentenca.setInt(5, ganhos.getIdUsuario());
                sentenca.setInt(6, ganhos.getIdGanhos());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Ganhos WHERE idGastos = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setInt(1, id);

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Ganhos> consultar()
    {
    ArrayList<Ganhos> listaGanhos = new ArrayList<Ganhos>();
    
        String sql = "SELECT * FROM Ganhos ORDER BY idGanhos";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Ganhos ganhos = new Ganhos();
                    
                    ganhos.setIdGanhos(resultadoSentenca.getInt("idGanhos"));
                    ganhos.setCategoria(resultadoSentenca.getString("Categoria"));
                    ganhos.setValor(resultadoSentenca.getDouble("Valor"));
                    ganhos.setDataGanho(resultadoSentenca.getDate("Data_Ganho"));
                    ganhos.setObservacao(resultadoSentenca.getString("Observacao"));
                    ganhos.setIdUsuario(resultadoSentenca.getInt("idUsuario"));
                    
                    listaGanhos.add(ganhos);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaGanhos;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
