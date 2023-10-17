import java.io.*;
import java.net.*;

public class FileServer {

    /**
     * @author wbreda
     * @since 08/09/23
     */


    public static void main(String[] args) throws Exception {

        int port = 8090;
        byte[] senddata, receivedata;
        DatagramPacket out = null;
        DatagramPacket in = null;
        DatagramSocket socket = new DatagramSocket(port);
        while (true) {
            System.out.println("\n\n*** Servidor aguardando request");

            receivedata = new byte[1000];
            in = new DatagramPacket(receivedata, receivedata.length);
            socket.receive(in);
            InetAddress srcaddress = in.getAddress();
            int srcport = in.getPort();
            String f = new String(in.getData());
            System.out.println(f);

            // envia os arquivos para o cliente, necessário alterar conforme seu sistema operacional
            String dirname = "/home/wbreda/Documents/ifrs/sistemas-distribuidos/";
            File f1 = new File(dirname);
            File direct[] = f1.listFiles();
            StringBuilder sb = new StringBuilder("\n");
            int c = 0;
            for (int i = 0; i < direct.length; i++) {
                if (direct[i].canRead() && (direct[i].toString()).endsWith(".cpp")) {
                    c++;
                }
            }
            sb.append(c + " .cpp arquivo nao encontrado\n\n");
            for (int i = 0; i < direct.length; i++) {
                if (direct[i].toString().endsWith(".cpp")) {
                    sb.append(direct[i].getName() + " ,size :" + direct[i].length() + " Bytes\n");
                }
            }

            sb.append("Digite o nome do arquivo para download: ");
            senddata = (sb.toString()).getBytes();
            out = new DatagramPacket(senddata, 0, senddata.length, srcaddress, srcport);
            socket.send(out);

            // busca o arquvo requisitado para download
            in = new DatagramPacket(receivedata, receivedata.length);
            String fname = new String(in.getData());
            int idx = 0;
            boolean flag = false;
            for (int i = 0; i < direct.length; i++) {
                if (direct[i].getName().toString().equalsIgnoreCase(fname)) {
                    idx = i;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                senddata = ("Arquivo não encontrado!!\n").getBytes();
                out = new DatagramPacket(senddata, senddata.length, srcaddress, srcport);
                socket.send(out);
            } else {
                // copia o arquivo existente
                File copy = new File(direct[idx].getAbsolutePath());
                FileReader fr = new FileReader(copy);
                BufferedReader buf = new BufferedReader(fr);
                sb = new StringBuilder();
                String s = null;
                while ((s=buf.readLine())!=null) {
                    sb.append(s);
                }
                if(buf.readLine()==null)
                    System.out.println("Arquivo lido com sucesso");
                senddata = (sb.toString()).getBytes();
                out = new DatagramPacket(senddata,senddata.length,srcaddress,srcport);
                socket.send(out);
            }
        }
    }

}