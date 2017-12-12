package br.univali.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class AppMonitor {

    public static void main(String[] args) {

        HashMap<Integer, Aluno> hashAluno = new HashMap<>();
        int port = 12345;
        DatagramSocket socket;
        Scanner s = new Scanner(System.in);
        int opcao;
        byte[] buffer_resposta;

        try {

            //starta o servidor
            socket = new DatagramSocket(port);
            System.out.println("Monitor escutando na porta: " + port);

            //recebe o primeiro datagrama do aluno
            DatagramPacket datagram_cadastro = new DatagramPacket(new byte[1024], 1024);
            socket.receive(datagram_cadastro); //recepção em um datagrama de 1024 bytes

            //dados do datagrama recebido
            String message_receive_data = new String(datagram_cadastro.getData()).trim();
            InetAddress message_receive_address = datagram_cadastro.getAddress();
            int message_receive_port = datagram_cadastro.getPort();

            //se for para cadastrar
            if (message_receive_data.equalsIgnoreCase("cadastrar")) {

                if (hashAluno.isEmpty()) {

                    //pede o nome do aluno
                    String mensagem = "informe";
                    DatagramPacket solicita_cadastro = new DatagramPacket(mensagem.getBytes(),
                            mensagem.getBytes().length, message_receive_address, message_receive_port);
                    socket.send(solicita_cadastro);

                    //escuta o nome do aluno
                    DatagramPacket recebe_cadastro = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(recebe_cadastro); //recepção em um datagrama de 1024 bytes

                    //save
                    hashAluno.put(recebe_cadastro.getPort(), new Aluno(new String(recebe_cadastro.getData()).trim(), recebe_cadastro.getAddress(), recebe_cadastro.getPort()));

                } else {
                    if (!hashAluno.containsKey(datagram_cadastro.getPort())) {

                        String mensagem = "Informe o seu nome: ";
                        DatagramPacket solicita_cadastro = new DatagramPacket(mensagem.getBytes(), 0,
                                mensagem.getBytes().length, message_receive_address, message_receive_port);
                        socket.send(solicita_cadastro);
                        DatagramPacket recebe_cadastro = new DatagramPacket(new byte[1024], 1024);
                        socket.receive(recebe_cadastro); //recepção em um datagrama de 1024 bytes
                        hashAluno.put(recebe_cadastro.getPort(), new Aluno(new String(recebe_cadastro.getData()).trim(), recebe_cadastro.getAddress(), recebe_cadastro.getPort()));
                    }
                }
            }

            while (true) {
                DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024);
                socket.receive(datagram_receive); //recepção em um datagrama de 1024 bytes

                //imprime a pergunta recebida do aluno
                String datagrama[] = new String(datagram_receive.getData()).trim().split(";");
                int op_aluno = Integer.parseInt(datagrama[0]);
                String acao = "";

                switch (op_aluno) {
                    case 1:
                        acao = "enviar uma pergunta para o palestrante";
                        break;
                    case 2:
                        acao = "enviar mensagem para todos os participantes conectados";
                        break;
                }

                System.out.println("-- Mensagem Recebida --");
                System.out.println("Aluno(a): " + hashAluno.get(datagram_receive.getPort()).getNome());
                System.out.println("Tipo: " + acao);
                System.out.println("Conteúdo: " + datagrama[1]);

                // ---------------------------------------
                // OPCOES DO MONITOR
                opcao = Integer.parseInt(JOptionPane.showInputDialog(
                        "Selecione o que fazer com a pergunta do aluno:\n"
                        + "1. solicitar esclarecimentos\n"
                        + "2. responder a pergunta\n"
                        + "3. encaminhar para o palestrante\n"
                        + "4. encaminhar para todos os participantes"
                ));
                // ---------------------------------------

                buffer_resposta = null;

                switch (opcao) {
                    case 1:
                        String mensagem = "Podes reformular sua pergunta";
                        buffer_resposta = mensagem.getBytes();
                        break;
                    case 2:
                        String resposta_aluno = JOptionPane.showInputDialog("Digite a resposta para enviar ao aluno: ");
                        buffer_resposta = resposta_aluno.getBytes();
                        break;
                    case 3: //encaminha a pergunta para o palestrante
                        String message_send = new String(datagram_receive.getData()).trim();
                        DatagramPacket datagram_send_palestrante = new DatagramPacket(message_send.getBytes(), message_send.getBytes().length, InetAddress.getByName("127.0.0.1"), 54321);
                        socket.send(datagram_send_palestrante);
                        break;
                    case 4:
                        InetAddress multi_addrs = InetAddress.getByName("225.4.5.6");
                        MulticastSocket msocket = new MulticastSocket();
                        DatagramPacket mdatagram = new DatagramPacket(datagrama[1].getBytes(), 0,
                                datagrama[1].getBytes().length, multi_addrs, 5000);
                        msocket.send(mdatagram);
                        break;
                    default:
                        System.out.println("Selecione uma opção válida");
                        break;
                }

                if (buffer_resposta != null) {//da retorno para o aluno
                    DatagramPacket datagram_send_aluno = new DatagramPacket(buffer_resposta, 0,
                            buffer_resposta.length, datagram_receive.getAddress(), datagram_receive.getPort());
                    socket.send(datagram_send_aluno);
                } else {//recebe resposta do palestrante
                    DatagramPacket datagram_receive_palestrante = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(datagram_receive_palestrante); //recepção em um datagrama de 1024 bytes

                    //imprime a resposta recebida do palestrante
                    System.out.println("Mensagem Recebida do Palestrante: " + new String(datagram_receive_palestrante.getData()).trim());
                    String resposta_palestrante = new String(datagram_receive_palestrante.getData());

                    buffer_resposta = resposta_palestrante.getBytes();

                    DatagramPacket datagram_send_aluno = new DatagramPacket(buffer_resposta, 0,
                            buffer_resposta.length, datagram_receive.getAddress(), datagram_receive.getPort());
                    socket.send(datagram_send_aluno);

                }
            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
