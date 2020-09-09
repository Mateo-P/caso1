import java.util.Random;

public class Main {
private static Buffer buff;
public static void main(String[]args )
{
	int numClientes=5;
	buff = new Buffer(numClientes,1);
	Cliente[] client = new Cliente[numClientes];
	Servidor[] server = new Servidor[1];
	for (int i = 0; i < client.length; i++) {
		client[i] = new Cliente(1, buff);
		client[i].start();
	}
	for (int i = 0; i < server.length; i++) {
		server[i] = new Servidor(buff);
		server[i].start();
	}
}
}
