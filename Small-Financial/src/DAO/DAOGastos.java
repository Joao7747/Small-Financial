package DAO;

import MODEL.Gastos;
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

public class DAOGastos implements DAOGenerica<Gastos> {

    private ConexaoBanco conexao;

    public DAOGastos() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Gastos gastos) {
        String sql = "INSERT INTO Gastos (Categoria, Preco , Data_Gasto, "
                + "Observacao, idUsuario) VALUES (?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, gastos.getCategoria());
                sentenca.setDouble(2, gastos.getPreco());
                sentenca.setDate(3, gastos.getDataGasto());
                sentenca.setString(4, gastos.getObservacao());
                sentenca.setInt(5, gastos.getIdUsuario());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Gastos gastos) {
        String sql = "UPDATE Gastos SET Categoria = ? , Preco = ? , Data_Gasto = ?, "
                + "Observacao = ?, idUsuario = ? WHERE idGastos = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                 sentenca.setString(1, gastos.getCategoria());
                sentenca.setDouble(2, gastos.getPreco());
                sentenca.setDate(3, gastos.getDataGasto());
                sentenca.setString(4, gastos.getObservacao());
                sentenca.setInt(5, gastos.getIdUsuario());
                sentenca.setInt(6, gastos.getIdGastos());

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
        String sql = "DELETE FROM Gastos WHERE idGastos = ?";

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
    public ArrayList<Gastos> consultar()
    {
    ArrayList<Gastos> listaGastos = new ArrayList<Gastos>();
    
        String sql = "SELECT * FROM Gastos ORDER BY idGastos";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Gastos gastos = new Gastos();
                    
                    gastos.setIdGastos(resultadoSentenca.getInt("idGastos"));
                    gastos.setCategoria(resultadoSentenca.getString("Categoria"));
                    gastos.setPreco(resultadoSentenca.getDouble("Preco"));
                    gastos.setDataGasto(resultadoSentenca.getDate("Data_Gasto"));
                    gastos.setObservacao(resultadoSentenca.getString("Observacao"));
                    gastos.setIdUsuario(resultadoSentenca.getInt("idUsuario"));
                    
                    listaGastos.add(gastos);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaGastos;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
