import java.util.Random;

public class Main {
private static Buffer buff;
public static void main(String[]args )
{
	int numClientes=1;
	buff = new Buffer(numClientes,50);
	Cliente[] client = new Cliente[numClientes];
	Servidor[] server = new Servidor[5];
	for (int i = 0; i < client.length; i++) {
		client[i] = new Cliente(5, buff);
		client[i].start();
	}
	for (int i = 0; i < server.length; i++) {
		server[i] = new Servidor(buff);
		server[i].start();
	}
}
}
