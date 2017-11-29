package tcp;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author biankatpas
 */

public class Client 
{

    public static void main(String[] args) 
    {
        String addrString = "";
        int port;
        Scanner s = new Scanner(System.in);
        Socket socket;

        if (args.length < 2) 
        {
            addrString = "127.0.0.1";
            port = 12345;
        } 
        else 
        {
            addrString = args[0];
            port = Integer.parseInt(args[1]);
        }
        try 
        {
            InetAddress addr = InetAddress.getByName(addrString);
            
            boolean exit = false;
            do 
            {
                String message = "Desejo fazer o quiz.";
                String data;
             
                socket = new Socket(addr, port);
                
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
                dataOutput.writeUTF(message);
                
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());
                data = dataInput.readUTF();

//                if (data.equals(message)) 
//                    System.out.println("Echo: " + data + " - bem sucedido.");
//                else 
                    System.out.println("Enviado: " + message + "\nRecebido: " + data);
                
                //solicita a mensagem para enviar ao servidor
                System.out.println("Digite a resposta para o servidor: ");
                message = s.nextLine();
                
                //verifica se eh para sair
                if(message.equalsIgnoreCase("exit"))
                    exit = true;
                
                //envia a resposta
                dataOutput = new DataOutputStream(socket.getOutputStream());
                System.out.println("Mensagem a ser enviada para o servidor (echo): "+message);
                dataOutput.writeUTF(message);
                
                //recebe retorno do servidor
                dataInput = new DataInputStream(socket.getInputStream());
                data = dataInput.readUTF(); 
                System.out.println("Enviado: " + message + "\nRecebido: " + data);
                
            }while(!exit);
        } 
        catch (HeadlessException | IOException e) 
        {
            System.err.println("An exception ocourred: " + e.getMessage());
            System.exit(-1);
        }
    }
}
