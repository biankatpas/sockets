/*
 * Client.java
 *
 * Created on 11 de Junho de 2013, 16:38
 */
package exemplos.tcpThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Michelle Wanghan
 */
public class Client {

    public static void main(String[] args) {

        try {
            Scanner s = new Scanner(System.in);
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            try (DatagramSocket socket = new DatagramSocket()) {
                boolean exit = false;
                while(true) {
                    //solicita a mensagem para enviar ao servidor
                    System.out.println("Digite uma mensagem para o servidor: ");
                    String message = s.nextLine();
                    
                    //verifica se eh para sair
                    //if (message.equalsIgnoreCase("exit")) {
                      //  exit = true;
                    //}

                    //envio dos dados
                    DatagramPacket datagram_send = new DatagramPacket(message.getBytes(), 0,
                            message.getBytes().length, addr, 1234);
                    socket.send(datagram_send);

                    //recebimento de dados
                    //DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024, addr, 1234);
                    //socket.receive(datagram_receive); //recepção

                    //exibe a msg recebida
                    //String message_receive = new String(datagram_receive.getData());
                    //System.out.println("O servidor respondeu: " + message_receive);
                    
                    //String message1 = JOptionPane.showInputDialog("Teste1 :");
                }
            }
        } catch (Exception e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
