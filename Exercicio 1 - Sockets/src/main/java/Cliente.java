import java.net.*;
import java.io.*;

public class Cliente {

    /**
     * @author wbreda
     * @since 08/09/23
     */

   public static void main(String[] args) {

       String sentence;
       String modifiedSentence;

       try {
    
            BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));
            Socket client = new Socket("localhost",40000);
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            sentence = inFromUSer.readLine();
            outToServer.writeBytes(sentence + "\n");
            modifiedSentence = inFromServer.readLine();
            System.out.println("Servidor Respondeu: " + modifiedSentence);
            client.close();

       } catch (IOException e) {
           e.printStackTrace();
       }
   } 
}