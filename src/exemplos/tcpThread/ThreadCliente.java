/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos.tcpThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Wangham
 */
public class ThreadCliente extends Thread {

    private DatagramPacket socket_clie;


    public ThreadCliente(DatagramPacket cliente) throws SocketException {
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
