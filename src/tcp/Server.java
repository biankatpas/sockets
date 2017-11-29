/*
 * Server.java
 *
 * Created on 23 de Maio de 2012
 */

package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            
            boolean exit = false;
            do
            {
                Socket socket = serverSocket.accept();
  
                System.out.println("Recebendo mensagem de "+
                  socket.getInetAddress().getHostName()+":"+socket.getPort());
                
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                String data = dataInput.readUTF();
                if(data.equalsIgnoreCase("exit"))
                    exit = true;
                
                System.out.println("Mensagem recebida do cliente: "+data);
                
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
                System.out.println("Mensagem a ser enviada para o cliente (echo): "+data);
                dataOutput.writeUTF(data);
                
                socket.close();
            }
            while(!exit);
            
            serverSocket.close();
        }
         catch(IOException e)
         {
            System.err.println("And exception ocourred: "+e.getMessage());
            System.exit(-1);
        }
    }
}
