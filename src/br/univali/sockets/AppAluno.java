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

public class AppAluno {

    public static void main(String[] args) {
        String addrString = "127.0.0.1";
        int port = 12345;
        Scanner s = new Scanner(System.in);
        String nome ="";
        String mensagem="";
        String pergunta="";
        byte[] buffer = null;

        try {
            InetAddress addr = InetAddress.getByName(addrString);
            try (DatagramSocket socket = new DatagramSocket()) {
                System.out.println("Informe o seu nome para cadastro: ");
                nome = s.nextLine();
                
                DatagramPacket datagram_cadastro = new DatagramPacket(nome.getBytes(), 0, nome.getBytes().length, addr, port);
                socket.send(datagram_cadastro); //envio dos dados
                
                while (true) {
                    System.out.println("O que você deseja fazer?\n (1) enviar uma pergunta para o palestrante\n (2) enviar mensagem para todos os participantes conectados");
                    int opcao = s.nextInt();
                    s.nextLine();
                                      
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite a pergunta para enviar ao monitor: ");
                            pergunta = s.nextLine();
                            buffer = (opcao+";"+pergunta).getBytes();
                            break;
                        case 2:
                            System.out.println("Digite a mensagem para enviar ao monitor: ");
                            mensagem = s.nextLine();
                            buffer = (opcao+";"+mensagem).getBytes();
                            break;
                        default:
                            System.out.println("Informe uma opção válida.");
                            break;
                    }
                                        
                    DatagramPacket datagram_send = new DatagramPacket(buffer, 0, buffer.length, addr, port);
                    socket.send(datagram_send); //envio dos dados

                    //recebimento de dados
                    DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024, addr, port);
                    socket.receive(datagram_receive); //recepção

                    //exibe a msg recebida
                    String message_receive = new String(datagram_receive.getData());
                    System.out.println("O monitor respondeu: " + message_receive);
                }
            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
