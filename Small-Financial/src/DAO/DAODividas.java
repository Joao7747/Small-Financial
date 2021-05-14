package DAO;

import MODEL.Dividas;
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

public class DAODividas implements DAOGenerica<Dividas> {

    private ConexaoBanco conexao;

    public DAODividas() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Dividas dividas) {
        String sql = "INSERT INTO Dividas (Categoria, Valor , Vencimento, "
                + "Descricao, Parcelado, Numero_Parcelas, Fixa, Observacao,idUsuario) VALUES (?,?,?,?,?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, dividas.getCategoria());
                sentenca.setDouble(2, dividas.getValor());
                sentenca.setDate(3, dividas.getVencimento());
                sentenca.setString(4, dividas.getDescricao());
                sentenca.setBoolean(5, dividas.isParcelado());
                sentenca.setInt(6, dividas.getNumeroParcelas());
                sentenca.setBoolean(7, dividas.isFixa());
                sentenca.setString(8, dividas.getObservacao());
                sentenca.setInt(9, dividas.getIdUsuario());
                

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Dividas dividas) {
        String sql = "UPDATE Dividas SET Categoria = ? , Valor = ? , Vencimento = ?, "
                + "Descricao = ?, Parcelado = ? , Numero_Parcelas = ? , Fixa = ?, "
                + "Observacao = ?, idUsuario = ? WHERE idDividas = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, dividas.getCategoria());
                sentenca.setDouble(2, dividas.getValor());
                sentenca.setDate(3, dividas.getVencimento());
                sentenca.setString(4, dividas.getDescricao());
                sentenca.setBoolean(5, dividas.isParcelado());
                sentenca.setInt(6, dividas.getNumeroParcelas());
                sentenca.setBoolean(7, dividas.isFixa());
                sentenca.setString(8, dividas.getObservacao());
                sentenca.setInt(9, dividas.getIdUsuario());
                sentenca.setInt(10, dividas.getIdDividas());

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
        String sql = "DELETE FROM Dividas WHERE idDividas = ?";

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
    public ArrayList<Dividas> consultar()
    {
    ArrayList<Dividas> listaDividas = new ArrayList<Dividas>();
    
        String sql = "SELECT * FROM Dividas ORDER BY idDividas";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Dividas dividas = new Dividas();
                    
                    dividas.setIdDividas(resultadoSentenca.getInt("idDividas"));
                    dividas.setCategoria(resultadoSentenca.getString("Categoria"));
                    dividas.setValor(resultadoSentenca.getDouble("Valor"));
                    dividas.setVencimento(resultadoSentenca.getDate("Vencimento"));
                    dividas.setDescricao(resultadoSentenca.getString("Descricao"));
                    dividas.setParcelado(resultadoSentenca.getBoolean("Parcelado"));
                    dividas.setNumeroParcelas(resultadoSentenca.getInt("Numero_Parcelas"));
                    dividas.setFixa(resultadoSentenca.getBoolean("Fixa"));
                    dividas.setObservacao(resultadoSentenca.getString("Observacao"));
                    dividas.setIdUsuario(resultadoSentenca.getInt("idUsuario"));
                    
                    listaDividas.add(dividas);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaDividas;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
