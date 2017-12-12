package br.univali.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class AppAluno {

    public static void main(String[] args) {
        String addrString = "127.0.0.1", nome = "", mensagem = "", pergunta = "";
        int port = 12345;
        byte[] buffer = null;

        try {
            InetAddress addr = InetAddress.getByName(addrString);
            try (DatagramSocket socket = new DatagramSocket()) {
                String cadastrar = "cadastrar";
                DatagramPacket datagram_cadastro = new DatagramPacket(cadastrar.getBytes(), 0, cadastrar.getBytes().length, addr, port);
                socket.send(datagram_cadastro); //envio dos dados
                //recebimento de dados

                DatagramPacket datagram_receive1 = new DatagramPacket(new byte[1024], 1024, addr, port);
                socket.receive(datagram_receive1); //recepção
                if (new String(datagram_receive1.getData()).trim().equalsIgnoreCase("informe")) {
                    nome = JOptionPane.showInputDialog("Informe o seu nome para cadastro:");
                    DatagramPacket envia_cadastro = new DatagramPacket(nome.getBytes(), 0, nome.getBytes().length, addr, port);
                    socket.send(envia_cadastro); //envio dos dados
                }

                while (true) {
                    int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                            "O que você deseja fazer?\n "
                            + "(1) enviar uma pergunta para o palestrante\n "
                            + "(2) enviar mensagem para todos os participantes conectados"
                    ));
                    switch (opcao) {
                        case 1:
                            pergunta = JOptionPane.showInputDialog("Digite a pergunta para ser encaminhada ao palestrante pelo monitor:");
                            buffer = (opcao + ";" + pergunta).getBytes();
                            break;
                        case 2:
                            mensagem = JOptionPane.showInputDialog("Digite a mensagem para ser encaminhada a todos os participantes pelo monitor:");
                            buffer = (opcao + ";" + mensagem).getBytes();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Informe uma opção válida.");
                            break;
                    }

                    DatagramPacket datagram_send = new DatagramPacket(buffer, 0, buffer.length, addr, port);
                    socket.send(datagram_send); //envio dos dados

                    //recebimento de dados
                    DatagramPacket datagram_receive2 = new DatagramPacket(new byte[1024], 1024, addr, port);
                    socket.receive(datagram_receive2); //recepção

                    //exibe a msg recebida
                    String message_receive = new String(datagram_receive2.getData());
                    JOptionPane.showMessageDialog(null, "O monitor respondeu: " + message_receive);

                    //TODO
                    //recebe dados via multicast
                    //InetAddress multi_addrs = InetAddress.getByName("225.4.5.6");
                    //MulticastSocket multi_socket = new MulticastSocket(5000);
                    //multi_socket.joinGroup(multi_addrs);
                    //DatagramPacket datagram = new DatagramPacket(new byte[1024], 1024);
                    //socket.receive(datagram);
                    //String message = new String(datagram.getData()).trim();
                    //System.out.println("Foi recebida a mensagem: " + message + " de "
                    //        + datagram.getAddress() + ":" + datagram.getPort());
                }
            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
