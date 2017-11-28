package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author biankatpas
 */
public class Client {

    public static void main(String[] args) {
        String addrString = "127.0.0.1";
        int port = 12345;
        Scanner s = new Scanner(System.in);

        try {
//            String addrString = JOptionPane.showInputDialog("Digite o Endere�o IP do Servidor: ");
            InetAddress addr = InetAddress.getByName(addrString);
//            String portString = JOptionPane.showInputDialog("Digite a Porta do Servidor: ");
//            int port = Integer.parseInt(portString);
            try (DatagramSocket socket = new DatagramSocket()) {
                boolean exit = false;
                do {
//                    String message = JOptionPane.showInputDialog("Digite uma mensagem para o Servidor: ");
                    //solicita a mensagem para enviar ao servidor
                    System.out.println("Digite uma mensagem para o servidor: ");
                    String message = s.nextLine();
                    //verifica se eh para sair
                    if(message.equalsIgnoreCase("exit")){
                        exit = true;
                    }
                    //envio dos dados
                    DatagramPacket datagram = new DatagramPacket(message.getBytes(), 0,
                            message.getBytes().length, addr, port);
                    socket.send(datagram);
                }while(!exit);
            }
        } catch (Exception e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
