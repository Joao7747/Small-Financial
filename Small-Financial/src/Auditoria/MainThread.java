/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auditoria;

import DAO.DAOUsuario;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caio
 */
public class MainThread {
    
    private static MainThread instancia;
    public String user;

    public static MainThread getInstance() {
        if (instancia == null) {
            instancia = new MainThread();
        }
        return instancia;
    }

    public void StartThread(String acao) throws InterruptedException{

        GerenciadorAuditoria.getInstancia().ativar();

        try{
            String mensagem = String.format("%s - Usuario " +user+ " clicou em %s\n\n", Instant.now().toString(),acao);
            System.out.printf(mensagem);
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(mensagem);
            
        } catch (Exception ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            GerenciadorAuditoria.getInstancia().desativar();
        }
    }
//    public String tela = "vazio";
//    public String usuario;
//    @Override
//    public void run(){
//        
//        System.out.printf("%s - Início da auditoria\n", Instant.now().toString());
//        GerenciadorAuditoria.getInstancia().ativar();
//        try {
//            for (int i=0;i<10;i++){
//                Random rand = new Random(); //instance of random class
//                int upperbound = 10;
//                int proximoSleep = rand.nextInt(upperbound);
//                String msgRandomica = String.format("%s - Entreu na tela de " + tela + "- Com o usuario " + usuario + "\n", Instant.now().toString(),i+1);
//                System.out.printf(msgRandomica);
//                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria("Auditoria - "+msgRandomica);
//                Thread.sleep(proximoSleep);
//            }
//            Thread.sleep(15000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            GerenciadorAuditoria.getInstancia().desativar();
//        }
//
//        System.out.printf("%s - Final da brincadeira\n", Instant.now().toString());
//    }
}
