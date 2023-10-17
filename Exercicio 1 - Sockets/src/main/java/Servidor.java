

import java.net.*;
import java.io.*;

public class Servidor {

    /**
     * @author wbreda
     * @since 08/09/23
     */

   public static void main(String[] args) {

       String clientSentence;
       String capitalized;
  
       try {
 
           // Cria um SocketServer (Socket característico de um servidor)
           ServerSocket socket = new ServerSocket(40000);
   
           while(true) {    
    
               Socket connectionSocket = socket.accept();
               System.out.println("*** Servidor aguardando request");
               BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    
               DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               clientSentence = inFromClient.readLine();
    
               capitalized = clientSentence.toUpperCase() + "\n";
    
               System.out.println("Cliente mandou isso: " + capitalized);
               outToClient.writeBytes(capitalized);
           } 
      
       } catch (IOException e) {
           e.printStackTrace();
       }  
   }
}
