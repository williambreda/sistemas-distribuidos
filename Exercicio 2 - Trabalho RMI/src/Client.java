import java.lang.invoke.MethodHandles;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client {

	public static void main(String s[]) throws RemoteException, NotBoundException {
		Scanner s1 = new Scanner(System.in);

		Registry reg = LocateRegistry.getRegistry("localhost");
		Interface skelton = (Interface) reg.lookup("Programa");
		int controle = 1;

		System.out.println("1 - Para alterar os caracteres para maiusculo \n"
				         + "2 - Para inverter caracteres \n"
				         + "3 - Para mostrar o tamanho da palavra/frase \n"
				         + "4 - Para mostrar data do servidor \n");
		controle = s1.nextInt();

		if (controle == 1) {
			System.out.println("Digite a palavra");
			String palavra = s1.next();
			skelton.maiuscula(palavra);

		}

		if (controle == 2) {
			System.out.println("Digite a palavra");
			String palavra2 = s1.next();
			skelton.inverte(palavra2);

		}

		if (controle == 3) {
			System.out.println("Digite a palavra");
			String palavra3 = s1.next();
			skelton.tamanho(palavra3);

		}

		if (controle == 4) {
			skelton.data();

		}

	}
}