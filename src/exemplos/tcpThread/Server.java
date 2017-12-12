/*
 * Server.java
 *
 * Created on 11 de Junho de 2013
 */
package exemplos.tcpThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 *
 * @author Michelle Wangham
 */
public class Server {

    public static void main(String[] args) {
        int port = 12345;
        DatagramSocket socket;
        ThreadCliente tc;
        Scanner s = new Scanner(System.in);
        
        try {
            socket = new DatagramSocket(port);
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("Servidor UDP escutando na porta " + serverSocket.getLocalPort());

            while (true) {
                //Inicia thread do cliente
                DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
                serverSocket.receive(dp);
                tc = new ThreadCliente(dp);
                tc.start();
                tc.join();
                
                // Responder Cliente
                
                System.out.println("Selecione o que fazer com a pergunta do aluno: ");
                System.out.println("1. solicitar esclarecimentos");
                int opcao = s.nextInt();
                String mensagem = "aaaa";
                
                switch (opcao) {
                    case 1:
                        mensagem = "Podes reformular sua pergunta";
                        break;
                    default:
                        break;
                }
                
                DatagramPacket solicita_cadastro = new DatagramPacket(mensagem.getBytes(), 0,
                        mensagem.getBytes().length, dp.getAddress(), dp.getPort());
                socket.send(solicita_cadastro);

            }

        } catch (Exception e) {
            System.err.println("And exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
