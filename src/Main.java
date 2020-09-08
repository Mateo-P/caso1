import java.util.Random;

public class Main {
private static Buffer buff;
public static void main(String[]args )
{
	buff = new Buffer(10,50);
	Cliente[] client = new Cliente[10];
	Servidor[] server = new Servidor[5];
	for (int i = 0; i < client.length; i++) {
		client[i] = new Cliente(10, buff);
		client[i].start();
	}
	for (int i = 0; i < server.length; i++) {
		server[i] = new Servidor(buff);
		server[i].start();
	}
}
}
