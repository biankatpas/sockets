/*
 * Server.java
 *
 * Created on 11 de Junho de 2013
 */
package exemplos.tcpThread;

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
                new ThreadCliente(serverSocket).start();
            }

        } catch (Exception e) {
            System.err.println("And exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
