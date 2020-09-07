import java.util.Random;

public class Main {
private static Buffer buff;
public static void main(String[]args )
{
	buff = new Buffer();
	Cliente[] client = new Cliente[5];
	Servidor[] server = new Servidor[5];
	for (int i = 0; i < client.length; i++) {
		client[i] = new Cliente(2, buff);
		client[i].start();
	}
}
}
