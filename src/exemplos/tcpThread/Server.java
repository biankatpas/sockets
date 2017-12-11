/*
 * Server.java
 *
 * Created on 11 de Junho de 2013
 */
package exemplos.tcpThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Michelle Wangham
 */
public class Server {

    public static void main(String[] args) {

        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("Servidor UDP escutando na porta " + serverSocket.getLocalPort());

            while (true) {
                //Inicia thread do cliente
                DatagramPacket dp = new DatagramPacket(new byte[1024],1024);
                serverSocket.receive(dp);
                new ThreadCliente(dp).start();
            }

        } catch (Exception e) {
            System.err.println("And exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
