package br.univali.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class AppPalestrante {

    public static void main(String[] args) {
        int port = 54321;
        DatagramSocket socket;
        Scanner s = new Scanner(System.in);

        try {
            socket = new DatagramSocket(port);
            System.out.println("Palestrante escutando na porta: " + port);
            
            while (true) {
                DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024);
                socket.receive(datagram_receive); //recepção de dados
                //exibe a msg recebida
                String message_receive = new String(datagram_receive.getData());
                System.out.println("O monitor disse: " + message_receive);

                System.out.println("Selecione uma resposta para enviar ao monitor: ");
                System.out.println("1. não há mais tempo para perguntas");
                System.out.println("2. pergunta fora do contexto da palestra");
                System.out.println("3. a pergunta será respondida para todos os participantes");
                System.out.println("4. a pergunta será respondida individualmente posteriormente");
                String message = "" + s.nextInt();
                
                //TODO: corrigir a resposta envianda, esta enviando a pergunta recebida
                DatagramPacket datagram_send = new DatagramPacket(message.getBytes(), 0,
                        message.getBytes().length, InetAddress.getByName("127.0.0.1"), 12345);
                socket.send(datagram_send); //envio dos dados

            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
