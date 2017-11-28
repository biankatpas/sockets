/*
 * Server.java
 *
 * Created on 11 de Junho de 2013
 */

package tcpThread;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Michelle Wangham
 */
public class Server {
    public static void main(String[] args) {
        
        
         try{
            String portString = JOptionPane.showInputDialog("Digite a Porta do Servidor: ");
            int port = Integer.parseInt(portString);

            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Servidor TCP escutando na porta "+serverSocket.getLocalPort());
            
            while(true){
                Socket socket_clie = serverSocket.accept();
                //Inicia thread do cliente

             new ThreadCliente(socket_clie).start();
            }
            
          }catch(Exception e){
            System.err.println("And exception ocourred: "+e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
