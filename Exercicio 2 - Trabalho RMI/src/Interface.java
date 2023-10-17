import java.rmi.Remote;

public interface Interface extends Remote {

public  void maiuscula(String palavra) throws java.rmi.RemoteException;
public  String inverte(String palavra) throws java.rmi.RemoteException;
public void data ( ) throws java.rmi.RemoteException;
public String tamanho ( String palavra) throws java.rmi.RemoteException;


	
	
	
	
	
	
	
	
	
	
}
