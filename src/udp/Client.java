package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author biankatpas
 */

public class Client 
{
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
                    DatagramPacket datagram = new DatagramPacket(message.getBytes(), 0,
                            message.getBytes().length, addr, port);
                    socket.send(datagram);
                }
                while(!exit);
            }
        } 
        catch (Exception e) 
        {
            System.err.println("An exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
