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

public class Aluno {
     public static void main(String[] args) 
    {
        String addrString="";
        int port;
        Scanner s = new Scanner(System.in);
  
    if(args.length < 2)
    {
        addrString = "127.0.0.1";
        port = 12345;
    }
    else
    {
        addrString = args[0];
        port = Integer.parseInt(args[1]);
    }
        try 
        {
            InetAddress addr = InetAddress.getByName(addrString);
            try (DatagramSocket socket = new DatagramSocket()) 
            {
                boolean exit = false;
                do 
                {
                    //solicita a mensagem para enviar ao servidor
                    System.out.println("Digite uma mensagem para o servidor: ");
                    String message = s.nextLine();

                    //verifica se eh para sair
                    if(message.equalsIgnoreCase("exit"))
                        exit = true;

                    //envio dos dados
                    DatagramPacket datagram_send = new DatagramPacket(message.getBytes(), 0,
                            message.getBytes().length, addr, port);
                    socket.send(datagram_send);
                    
                    //recebimento de dados
                    DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024, addr, port);
                    socket.receive(datagram_receive); //recepção
                    
                    //exibe a msg recebida
                    String message_receive = new String(datagram_receive.getData());
                    System.out.println("O servidor respondeu: " + message_receive);
                }
                while(!exit);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
