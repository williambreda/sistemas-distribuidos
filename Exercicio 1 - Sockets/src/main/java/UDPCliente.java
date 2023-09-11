/*
 * Cliente.java
 *
 * Sistemas Distribu�dos/UTFPR  Prof. Cesar Augusto Tacla
 *
 * Argumentos: <mensage> <HostIP>
 * Ex. java Cliente 127.0.0.1 6789 "mensagem teste"
 * O servidor devolve a msg (eco)
 */
import java.net.*;
import java.io.*;

public class UDPCliente {

    public static String msg="Mensagem de teste enviada pelo cliente";

    /**
     * @author wbreda
     * @since 08/09/23
     */

    public static void main(String args[]) {
        DatagramSocket s = null;
        try {
            String servidor="localhost";
            int porta=6789;

            
            if (args.length > 0) servidor = args[0];
            if (args.length > 1) porta = Integer.parseInt(args[1]);
            if (args.length > 2) msg = args[2];
            byte[] m = msg.getBytes(); // transforma arg em bytes
            InetAddress serv = InetAddress.getByName(servidor);


   
            s = new DatagramSocket(); // cria um socket UDP
            System.out.println("* Socket criado na porta: " + s.getLocalPort());
            DatagramPacket req = new DatagramPacket(m, msg.length(), serv, porta);
            s.send(req); // envia datagrama contendo a mensagem m
            System.out.println("* Datagrama enviado...: " + msg);
            
            byte[] buffer = new byte[1000];
            DatagramPacket resp = new DatagramPacket(buffer, buffer.length);
            s.setSoTimeout(10000); // timeout em ms
            s.receive(resp); // aguarda resposta do servidor - bloqueante
            System.out.println("* Resposta do servidor: " + 
                    new String(resp.getData(),0,resp.getLength()));
            
        } catch (SocketException e) {
            // timeout, erro na cria��o
            System.out.println("! Erro socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("! Erro envio/recepcao do pacote: " + e.getMessage());
        }  finally {
            if (s != null) s.close();
        }     
    }
    
}
