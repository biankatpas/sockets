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
public class AppMonitor {

    public static void main(String[] args) {
        AlunosRepositorio repositorio = new AlunosRepositorio();
        int port = 12345;
        DatagramSocket socket;
        Scanner s = new Scanner(System.in);
        int opcao;
        byte[] buffer_resposta;

        try {
            socket = new DatagramSocket(port);

            System.out.println("Monitor escutando na porta: " + port);
            //somente para teste 
            //TODO: verificar como fazer o controle do cadastro de alunos
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

                //imprime a pergunta recebida do aluno
                System.out.println("Mensagem Recebida: " + new String(datagram_receive.getData()).trim() + " do Cliente "
                        + datagram_receive.getAddress() + ":" + datagram_receive.getPort());

                // ---------------------------------------
                // OPCOES DO MONITOR
                System.out.println("Selecione o que fazer com a pergunta do aluno: ");
                System.out.println("1. solicitar esclarecimentos");
                System.out.println("2. responder a pergunta");
                System.out.println("3. encaminhar para o palestrante");
                opcao = s.nextInt();

                buffer_resposta = null;

                switch (opcao) {
                    case 1:
                        String mensagem = "Podes reformular sua pergunta";
                        buffer_resposta = mensagem.getBytes();
                        break;
                    case 2:
                        System.out.println("Digite a resposta para enviar ao aluno: ");
                        String resposta_aluno = s.nextLine();
                        buffer_resposta = resposta_aluno.getBytes();
                        break;
                    case 3: //envia pergunta para o palestrante
                        String message_send = new String(datagram_receive.getData()).trim();
                        DatagramPacket datagram_send_palestrante = new DatagramPacket(message_send.getBytes(), message_send.getBytes().length, InetAddress.getByName("127.0.0.1"), 54321);
                        socket.send(datagram_send_palestrante);
                        break;
                    default:
                        break;

                }

                if (buffer_resposta != null) {
                    DatagramPacket datagram_send_aluno = new DatagramPacket(buffer_resposta, 0,
                            buffer_resposta.length, datagram_receive.getAddress(), datagram_receive.getPort());
                    socket.send(datagram_send_aluno);
                } else {
                    //recebe resposta do palestrante
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
