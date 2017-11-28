package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author biankatpas
 */

public class Server {
    public static void main(String[] args) {
        
        int port = 12345;
        DatagramSocket socket = null;
        boolean run = true;
        
        try{
            //String portString = JOptionPane.showInputDialog("Digite a Porta do Servidor: ");
            //int port = Integer.parseInt(portString);

            socket = new DatagramSocket(port);

            System.out.println("Servidor UDP escutando na porta  "+port);
            
            boolean exit = false;
            do{
                //recebimento dos dados em um datagrama de 1024 bytes
                DatagramPacket datagram_receive = new DatagramPacket(new byte[1024],1024);
                socket.receive(datagram_receive); //recepção
                //dado do datagrama recebido
                String message_receive = new String(datagram_receive.getData()).trim();
                //verifica se eh para sair
                if(message_receive.equalsIgnoreCase("exit")) {
                    exit = true;
                }
                //imprime a mensagem recebida
                System.out.println("Mensagem Recebida: "+message_receive+" do Cliente "+
                  datagram_receive.getAddress()+":"+datagram_receive.getPort());
                //envia dados para o emissor do datagrama recebido
                String message_send = message_receive;
                DatagramPacket datagram_send = new DatagramPacket(message_send.getBytes(), message_send.getBytes().length, datagram_receive.getAddress(), datagram_receive.getPort());
                socket.send(datagram_send);
            }while(!exit);
            
            socket.close();
        }catch(Exception e){
            System.err.println("An exception ocourred: "+e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
