package br.univali.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class AppMonitor {

    public static void main(String[] args) {
        AlunosRepositorio repositorio = new AlunosRepositorio();
        int port = 12345;
        DatagramSocket socket;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Monitor escutando na porta: " + port);

            DatagramPacket datagram_cadastro = new DatagramPacket(new byte[1024], 1024);
            socket.receive(datagram_cadastro); //recepção em um datagrama de 1024 bytes
            //dados do datagrama recebido
            String message_receive_data = new String(datagram_cadastro.getData()).trim();
            InetAddress message_receive_address = datagram_cadastro.getAddress();
            int message_receive_port = datagram_cadastro.getPort();
            //salva no repositorio de alunos
            repositorio.save(new Aluno(message_receive_data, message_receive_address, message_receive_port));
            //somente para conferencia dos alunos no repositorio
            for (int i = 0; i < repositorio.findAll().size(); i++) {
                System.out.println("id: " + repositorio.findAll().get(i).getId());
                System.out.println("nome: " + repositorio.findAll().get(i).getNome());
                System.out.println("endereco: " + repositorio.findAll().get(i).getAddress());
                System.out.println("porta: " + repositorio.findAll().get(i).getPort());
                System.out.println();
            }

            while (true) {
                DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024);
                socket.receive(datagram_receive); //recepção em um datagrama de 1024 bytes

                //imprime a pergunta recebida
                System.out.println("Mensagem Recebida: " + new String(datagram_receive.getData()).trim() + " do Cliente "
                        + datagram_receive.getAddress() + ":" + datagram_receive.getPort());

                //envia pergunta para o palestrante
                String message_send = new String(datagram_receive.getData()).trim();
                DatagramPacket datagram_send = new DatagramPacket(message_send.getBytes(), message_send.getBytes().length, InetAddress.getByName("127.0.0.1"), 54321);
                socket.send(datagram_send);
                
                //recebe resposta do palestrante
                DatagramPacket datagram_receive_palestrante = new DatagramPacket(new byte[1024], 1024);
                socket.receive(datagram_receive_palestrante); //recepção em um datagrama de 1024 bytes

                //imprime a resposta recebida
                System.out.println("Mensagem Recebida: " + new String(datagram_receive.getData()).trim() + " do Palestrante");
            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
