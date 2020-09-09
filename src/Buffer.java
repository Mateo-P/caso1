import java.lang.management.ClassLoadingMXBean;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private static int tamaño;
	private static int numClientes;
	private static boolean bufferLleno = Boolean.FALSE;
	public Buffer(int numClientes,int tamaño)
	{
		this.numClientes=numClientes;
		this.tamaño=tamaño;
	}
	private static Queue<Mensaje> bandeja = new LinkedList<>();
	public synchronized void enviarMsg(Mensaje msg) 
	{
		while(bufferLleno)
		{
			try {
				System.out.println("pai tiene que esperar");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("se rompio pai");
			}
			
		}
	
		tamaño--;

		if(tamaño==0)
		{
			bufferLleno=true;
		}
		System.out.println("Envio:" +msg.getContenido());
			bandeja.add(msg);
		try {
			System.out.println("esperando...");
			wait();
		} catch (InterruptedException e) {
			System.out.println("se rompio pai");
		}
		
	}
	public synchronized void FindelComunicado()
	{
		numClientes--;
	}
	public synchronized void RetirarMsg() 
	{
		
		tamaño++;

		Mensaje tomado = bandeja.poll();
		if(tomado != null)
		{
			tomado.Responder();
			System.out.println("Respuesta: "+ tomado.getContenido());
				
		}
		if(bufferLleno)
		{
			bufferLleno=false;
		}
		notifyAll();		
		
	}
	public boolean hayClientes(){
		return numClientes!=0;
	}

}
