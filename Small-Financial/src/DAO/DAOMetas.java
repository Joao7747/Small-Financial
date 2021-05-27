package DAO;

import MODEL.Metas;
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


public class DAOMetas implements DAOGenerica<Metas> {

    private ConexaoBanco conexao;

    public DAOMetas() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Metas metas) {
        String sql = "INSERT INTO Metas (Descricao, DataPrevista , DataRealizacao, "
                + "CustoTotal, Categoria, Observacao, ValorGuardado, Status_Meta, Valor_Ideal_Poupar, idUsuario) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, metas.getDescricao());
                sentenca.setDate(2, metas.getDataPrevista());
                sentenca.setDate(3, metas.getDataRealizacao());
                sentenca.setDouble(4, metas.getCustoTotal());
                sentenca.setString(5, metas.getCategoria());
                sentenca.setString(6, metas.getObservacao());
                sentenca.setDouble(7, metas.getValorGuardado());
                sentenca.setByte(8, metas.isStatusMeta());
                sentenca.setDouble(9, metas.getValorIdealPoupar());
                sentenca.setInt(10, metas.getIdUsuario());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Metas metas) {
        String sql = "UPDATE Metas SET Descricao = ? , DataPrevista = ? , DataRealizacao = ?, "
                + "CustoTotal = ?,Categoria = ? , Observacao = ? , ValorGuardado = ?, "
                + "Status_Meta = ?, Valor_Ideal_Poupar = ?,  idUsuario = ? WHERE idMetas = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, metas.getDescricao());
                sentenca.setDate(2, metas.getDataPrevista());
                sentenca.setDate(3, metas.getDataRealizacao());
                sentenca.setDouble(4, metas.getCustoTotal());
                sentenca.setString(5, metas.getCategoria());
                sentenca.setString(6, metas.getObservacao());
                sentenca.setDouble(7, metas.getValorGuardado());
                sentenca.setByte(8, metas.isStatusMeta());
                sentenca.setDouble(9, metas.getValorIdealPoupar());
                sentenca.setInt(10, metas.getIdUsuario());
                sentenca.setInt(11, metas.getIdMetas());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    
    public void poupar(Metas metas){
        String sql = "UPDATE Metas Set ValorGuardado = ? WHERE idMetas = ?";
        
        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setDouble(1, metas.getValorGuardado());
                sentenca.setInt(2, metas.getIdMetas());

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
        String sql = "DELETE FROM Metas WHERE idMetas = ?";

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
    public ArrayList<Metas> consultar()
    {
    ArrayList<Metas> listaMetas = new ArrayList<Metas>();
    
        String sql = "SELECT * FROM Metas ORDER BY idMetas";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Metas metas = new Metas();
                    
                    metas.setIdMetas(resultadoSentenca.getInt("idMetas"));
                    metas.setDescricao(resultadoSentenca.getString("Descricao"));
                    metas.setDataPrevista(resultadoSentenca.getDate("DataPrevista"));
                    metas.setDataRealizacao(resultadoSentenca.getDate("DataRealizacao"));
                    metas.setCustoTotal(resultadoSentenca.getDouble("CustoTotal"));
                    metas.setCategoria(resultadoSentenca.getString("Categoria"));
                    metas.setObservacao(resultadoSentenca.getString("Observacao"));
                    metas.setValorGuardado(resultadoSentenca.getDouble("ValorGuardado"));
                    metas.setStatusMeta(resultadoSentenca.getByte("Status_Meta"));
                    metas.setValorIdealPoupar(resultadoSentenca.getDouble("Valor_Ideal_Poupar"));
                    metas.setIdUsuario(resultadoSentenca.getInt("idUsuario"));
                    
                    listaMetas.add(metas);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaMetas;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
