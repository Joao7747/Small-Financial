package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private Connection conexao;

    public ConexaoBanco() {
        this.servidor = "remotemysql.com";
        this.banco = "YHGRTERuKs";
        this.usuario = "YHGRTERuKs";
        this.senha = "9N457QFFcy";
    }

    
    public boolean conectar() {
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.servidor +
                    "/" + this.banco, this.usuario, this.senha);
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() {
        return conexao;
    }

}