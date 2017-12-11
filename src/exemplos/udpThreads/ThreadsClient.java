/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos.udpThreads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 *
 * @author Vitor
 */
public class ThreadsClient extends Thread{
    private DatagramSocket socket_clie;

  public ThreadsClient(DatagramSocket cliente) {

    this.socket_clie = cliente; 

  }
  public void run() {

    try {
        DatagramPacket client_receive = new DatagramPacket(new byte [1024], 1024);
        socket_clie.receive(client_receive);
        
        String message_receive = new String (client_receive.getData()).trim();
        System.out.println("Recebendo mensagem de "+ socket_clie.getInetAddress().getHostName()+":"+socket_clie.getPort());
        
        System.out.println("Mensagem recebida "+ message_receive + "do Cliente"+
        client_receive.getAddress() + ":" + client_receive.getPort());
    } 
    catch(Exception e) {
    System.out.println("Excecao ocorrida na thread: " + e.getMessage());            
       try {
         socket_clie.close();   
       }
       catch(Exception ec) {}     

    }
  }
}
