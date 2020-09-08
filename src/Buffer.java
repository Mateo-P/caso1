import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private static int tama�o;
	private int numClientes;
	private boolean bufferLleno = Boolean.FALSE;
	public Buffer(int numClientes,int tama�o)
	{
		this.numClientes=numClientes;
		this.tama�o=tama�o;
	}
	private Queue<Mensaje> bandeja = new LinkedList<>();
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
		tama�o--;
		if(tama�o==0)
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
		System.out.println("termino pai");
		numClientes--;
	}
	
	public synchronized void RetirarMsg() 
	{
		
		tama�o++;
		Mensaje tomado = bandeja.poll();
		if(tomado != null)
		{
			tomado.Responder();
			System.out.println("mensaje respondido"+ tomado.getContenido());
				
		}
		notifyAll();		
		
	}
	public boolean hayClientes(){
		return numClientes!=0;
	}

}
