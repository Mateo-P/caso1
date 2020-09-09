import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
	private static Buffer buff;
	public static void main(String[]args ) throws IOException
	{
		int numClientes;
		int numServers;
		int numMensajes;
		int tamaño;
		File info = new File("./data/config");
		try (BufferedReader br = new BufferedReader(new FileReader(info))) {
			String[] line = br.readLine().split(",");
			numClientes= Integer.parseInt(line[0].split(":")[1]);
			numMensajes= Integer.parseInt(line[1].split(":")[1]);
			tamaño= Integer.parseInt(line[2].split(":")[1]);
			numServers= Integer.parseInt(line[3].split(":")[1]);

			br.close();

			buff = new Buffer(numClientes,tamaño);
			Cliente[] client = new Cliente[numClientes];
			Servidor[] server = new Servidor[numServers];


			for (int i = 0; i < client.length; i++) {
				client[i] = new Cliente(numMensajes, buff);
				client[i].start();
			}
			for (int i = 0; i < server.length; i++) {
				server[i] = new Servidor(buff);
				server[i].start();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
