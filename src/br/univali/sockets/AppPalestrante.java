package br.univali.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
        byte[] buffer_resposta = null;
        String mensagem;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Palestrante escutando na porta: " + port);

            while (true) {
                DatagramPacket datagram_receive = new DatagramPacket(new byte[1024], 1024);
                socket.receive(datagram_receive); //recepção de dados
                //exibe a msg recebida
                String datagrama[] = new String(datagram_receive.getData()).trim().split(";");
                int id_aluno = Integer.parseInt(datagrama[0]);
                String pergunta = datagrama[1];
                System.out.println("O monitor disse: \n Aluno " + id_aluno + "\nPergunta " +pergunta);

                // ---------------------------------------
                // OPCOES DO PALESTRANTE
                int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                        "Selecione uma resposta para enviar ao monitor:\n"
                        + "1. não há mais tempo para perguntas\n"
                        + "2. pergunta fora do contexto da palestra\n"
                        + "3. a pergunta será respondida para todos os participantes\n"
                        + "4. a pergunta será respondida individualmente posteriormente"));
                buffer_resposta = null;
                // ---------------------------------------

                switch (opcao) {
                    case 1:
                        mensagem = "não há mais tempo para perguntas";
                        buffer_resposta = (id_aluno + ";" + mensagem).getBytes();
                        break;
                    case 2:
                        mensagem = "pergunta fora do contexto da palestra";
                        buffer_resposta = (id_aluno + ";" + mensagem).getBytes();
                        break;
                    case 3:
                        mensagem = " a pergunta será respondida para todos os participantes";
                        buffer_resposta = (id_aluno + ";" + mensagem).getBytes();
                        break;

                    case 4:
                        mensagem = " a pergunta será respondida individualmente posteriormente";
                        buffer_resposta = (id_aluno + ";" + mensagem).getBytes();
                        break;
                    default:
                        break;

                }

                DatagramPacket datagram_send = new DatagramPacket(buffer_resposta, 0,
                        buffer_resposta.length, InetAddress.getByName("127.0.0.1"), 12345);
                socket.send(datagram_send); //envio dos dados para o endereco e a porta do monitor

            }
        } catch (IOException e) {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
