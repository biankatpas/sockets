package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author biankatpas
 */

public class Server 
{
    public static void main(String[] args) 
    {
        ServerSocket serverSocket; 
        int port;
                
        if(args.length < 1) 
          port = 12345;
        else
          port = Integer.parseInt(args[0]);
        
         try
         {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor TCP escutando na porta "+serverSocket.getLocalPort());
            
            ArrayList<Pergunta> perguntas = new ArrayList<>();
            perguntas.add(new Pergunta("qual seu nome?", "bianka"));
            perguntas.add(new Pergunta("qual sua idade?", "27"));
            perguntas.add(new Pergunta("mas nem peixe?", "não"));
            
            boolean exit = false;
            for(int i = 0; i<3; i++)
            {
                if(exit) break;
                
                Socket socket = serverSocket.accept();
                                             
                //recebe a primeira msg do cliente
                System.out.println("Recebendo mensagem de "+
                  socket.getInetAddress().getHostName()+":"+socket.getPort());
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                String data = dataInput.readUTF();
                
                //verifica se é para sair
                if(data.equalsIgnoreCase("exit"))
                    exit = true;
               
                //envia a pergunta para o cliente
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
                System.out.println("Mensagem a ser enviada para o cliente (echo): "+perguntas.get(i).getPergunta());
                dataOutput.writeUTF(perguntas.get(i).getPergunta());
                
                //recebe a resposta do cliente
                dataInput = new DataInputStream(socket.getInputStream());
                data = dataInput.readUTF();
                System.out.println("Mensagem recebida do cliente: "+data);
              
                //verifica se a resposta esta correta
                if(data.equalsIgnoreCase(perguntas.get(i).getResposta()))
                    data = "resposta correta!";
                else
                    data = "A resposta correta é: "+perguntas.get(i).getResposta();
                
                //manda a resposta do quiz para o cliente
                dataOutput = new DataOutputStream(socket.getOutputStream());
                System.out.println("Mensagem a ser enviada para o cliente (echo): "+data);
                dataOutput.writeUTF(data);
                
                //fecha a conexao
                socket.close();
            }
            //fecha a conexao
            serverSocket.close();
        }
         catch(IOException e)
         {
            System.err.println("And exception ocourred: "+e.getMessage());
            System.exit(-1);
        }
    }
}
