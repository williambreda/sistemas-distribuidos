import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UDPServidor{

    /**
     * @author wbreda
     * @since 08/09/23
     */

    public static void main(String args[]) {
        DatagramSocket s = null;
        try {
            // cria um socket UDP
            s = new DatagramSocket(6789);

            while(true) {

                byte[] buffer = new byte[100];

                System.out.println("\n\n*** Servidor aguardando request");
                // cria datagrama para recepcionar solicita��o do cliente
                DatagramPacket req = new DatagramPacket(buffer, buffer.length);
                s.receive(req);
                System.out.println("*** Request recebido de: " + req.getAddress() + ":" + req.getPort());

                String mensagemAlterada = "Alterando a mensagem no servidor " + new String(req.getData());
                System.out.println(mensagemAlterada);

                // envia resposta
                DatagramPacket resp = new DatagramPacket(mensagemAlterada.getBytes(), mensagemAlterada.length(), req.getAddress(), req.getPort());
                s.send(resp);
            }

        } catch (SocketException e) {
            System.out.println("Erro de socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro envio/recepcao pacote: " + e.getMessage());
        } finally {
            if (s != null) s.close();
        }
    }
}
