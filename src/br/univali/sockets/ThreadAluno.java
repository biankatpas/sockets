/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.sockets;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 * @author Vitor
 */
public class ThreadAluno extends Thread {

    private DatagramPacket socket_clie;

    public ThreadAluno(DatagramPacket cliente) {
        System.out.println("Criou Thread");
        this.socket_clie = cliente;

    }

    public void run() {

        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            DatagramPacket datagrama = this.socket_clie; 
                    //new DatagramPacket(new byte[1024], 1024, addr, 1234);
            //socket_clie.receive(datagrama);
            System.out.println("Recebendo mensagem de " + datagrama.getAddress() + ":" + datagrama.getPort());
            System.out.println("Mensagem recebida do cliente: " + new String(datagrama.getData()).trim());
        } catch (Exception e) {
            System.out.println("Excecao ocorrida na thread: " + e.getMessage());
            try {
                //socket_clie.close();
            } catch (Exception ec) {
            }

        }
    }

}
