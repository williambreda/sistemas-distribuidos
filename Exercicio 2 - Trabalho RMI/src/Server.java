import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server implements Interface {

	public void maiuscula(String palavra) throws RemoteException {
		String palavranova = palavra.toUpperCase();
		System.out.println(" " + palavranova);
	}

	public String inverte(String palavra) throws RemoteException {
		String saida = "";

		for (int i = palavra.length() - 1; i >= 0; i--) {
			saida = saida + palavra.charAt(i);

		}
		System.out.println(" " + saida);
		return saida;
	}

	public void data() throws RemoteException {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(" " + timeStamp);
	}

	public String tamanho (String tamanho) throws RemoteException {
		System.out.println(" " + tamanho.length());
		return tamanho;
	}

	public static void main(String[] args) throws RemoteException {
		Server server = new Server();
		Interface stub = (Interface) UnicastRemoteObject.exportObject(server, 0);
		Registry reg = LocateRegistry.getRegistry();
		reg.rebind("Programa", stub);
		System.out.println("server pronto");

	}
}
