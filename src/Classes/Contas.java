/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.scene.control.Button;

/**
 *
 * @author jr13f
 */
public class Contas {
    private String Categoria;
    private String Descricao;
    private double Valor;
    private String Parcelas;
    private String Vencimento;
    private String Observacao;
    private String Status;
    private Button Editar;
    private Button Deletar;
    
    public Contas(String Categoria, String Descricao, double Valor, String Parcelas, String Vencimento, String Observacao, String Status){
        this.Categoria = Categoria;
        this.Descricao = Descricao;
        this.Observacao = Observacao;
        this.Parcelas = Parcelas;
        this.Status = Status;
        this.Valor = Valor;
        this.Vencimento = Vencimento;
        this.Editar = new Button("Editar");
        this.Deletar = new Button("Excluir");
    }

    /**
     * @return the Categoria
     */
    public String getCategoria() {
        return Categoria;
    }

    /**
     * @param Categoria the Categoria to set
     */
    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    /**
     * @return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    /**
     * @return the Valor
     */
    public double getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    /**
     * @return the Parcelas
     */
    public String getParcelas() {
        return Parcelas;
    }

    /**
     * @param Parcelas the Parcelas to set
     */
    public void setParcelas(String Parcelas) {
        this.Parcelas = Parcelas;
    }

    /**
     * @return the Vencimento
     */
    public String getVencimento() {
        return Vencimento;
    }

    /**
     * @param Vencimento the Vencimento to set
     */
    public void setVencimento(String Vencimento) {
        this.Vencimento = Vencimento;
    }

    /**
     * @return the Observacao
     */
    public String getObservacao() {
        return Observacao;
    }

    /**
     * @param Observacao the Observacao to set
     */
    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the Editar
     */
    public Button getEditar() {
        return Editar;
    }

    /**
     * @param Editar the Editar to set
     */
    public void setEditar(Button Editar) {
        this.Editar = Editar;
    }

    /**
     * @return the Deletar
     */
    public Button getDeletar() {
        return Deletar;
    }

    /**
     * @param Deletar the Deletar to set
     */
    public void setDeletar(Button Deletar) {
        this.Deletar = Deletar;
    }
    
}
