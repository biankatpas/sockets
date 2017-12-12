/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos.udpThreadBugado;

import exemplos.udpThread.ThreadCliente;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */
public class Server {

    public static void main(String[] args) {

        DatagramSocket socket;
        try {
            String portString = "12345";
            int port = Integer.parseInt(portString);

            //ServerSocket serverSocket = new ServerSocket(port);
            socket = new DatagramSocket(port);
            System.out.println("Servidor UDP escutando na porta " + port);

            while (true) {
                //Inicia thread do cliente
                /**new Thread() {
                    public void run() {

                        try {
                            DatagramPacket client_receive = new DatagramPacket(new byte[1024], 1024);
                            socket.receive(client_receive);

                            String message_receive = new String(client_receive.getData()).trim();
                            System.out.println("Recebendo mensagem de " + client_receive.getAddress() + ":" + client_receive.getPort());

                            System.out.println("Mensagem recebida " + message_receive + "do Cliente"
                                    + client_receive.getAddress() + ":" + client_receive.getPort());
                        } catch (Exception e) {
                            System.out.println("Excecao ocorrida na thread: " + e.getMessage());
                            try {
                                socket.close();
                            } catch (Exception ec) {
                            }
                        }
                    }
                }.start();**/
            }
        } catch (Exception e) {
            System.err.println("And exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
