import java.util.LinkedList;
public class ProdConsum extends Thread{

	//VARIABLES ESTATICAS //
	public static LinkedList<Mensaje> pilaMensajesEspera= new LinkedList<Mensaje>();	
	public static LinkedList<Mensaje> mensajesRespondidos = new LinkedList<Mensaje>();	
	public static int indexRespuestas = 0;
	public static int bufferSize; 	
	public static Object monitor = new Object();	
	public static int clientesActivos = 0;
	
	//VARIABLES DE THREAD	
	
	private boolean consumidor;
	private LinkedList<Mensaje> mensajesCliente;	
	
	public ProdConsum(boolean esConsumidor) {
		consumidor = esConsumidor;
		mensajesCliente = null;
	}
	
	public ProdConsum(int numeroMensajes) {
		consumidor = false;
		mensajesCliente = new LinkedList<Mensaje>();
		for (int i = 0; i < numeroMensajes; i++) {
			mensajesCliente.add(new Mensaje("mensaje cualquiera id" + i + "-" + this.getId()));			
		}		
	}
	
	@Override
	public void run() {
		while(true) {
			if (consumidor){	consumir();
			}else {		producir();	}
			
			if(clientesActivos == 0 && pilaMensajesEspera.size() == 0) {
				break;
			}
		}
		
		
	}
	
	public void consumir() {
		
		synchronized (monitor) {
			
			if(pilaMensajesEspera.size()!=0) {
				Mensaje m = pilaMensajesEspera.pop();
				m.responder("resp X - por server" + this.getId() + " quedan " + pilaMensajesEspera.size() + " en el buffer");
				mensajesRespondidos.add(m);
				System.out.println((indexRespuestas+1) + ". mensaje: " + m.getQuery() + " | respuesta: " + m.getAnswer());
				indexRespuestas++;
				yield();
				monitor.notifyAll();
			}			
		}		
	}
	
	public void producir() {
		
		synchronized (monitor) {
			if(pilaMensajesEspera.size() >= bufferSize) {
				try {monitor.wait();//el buffer esta lleno debe esperar
				} catch (InterruptedException e) {}
			}if(mensajesCliente.size() != 0 && pilaMensajesEspera.size() < bufferSize){
				pilaMensajesEspera.add(mensajesCliente.pop());
				monitor.notifyAll();
				if(mensajesCliente.size() == 0) {
					clientesActivos--;
				}				
			}			
		}		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//leer de archivo
		int numServidores = 20;
		int numClientes = 100;
		int numeroMensajesXCliente = 5;
		int tamanioBuffer = 100;
		//definir tamanio buffer
		ProdConsum obj = new ProdConsum(true);
		obj.bufferSize = tamanioBuffer;
		obj.clientesActivos = numClientes;
		
		
		
		Thread[] hilos = new Thread[numClientes + numServidores];
		
		long ini = System.currentTimeMillis();
		
		for (int i = 0; i < hilos.length; i++) {
			Runnable runnable = null;
			
			if(i < numServidores) {//revisar 
				runnable = new ProdConsum(true);
			}else {
				runnable = new ProdConsum(numeroMensajesXCliente);
			}			
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}		
		//co rutina para lectura limpia
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].join();
		}
		long  fin = System.currentTimeMillis() - ini;	
		
		System.out.println(mensajesRespondidos.size() + " mensajes respondidos de " + (numClientes*numeroMensajesXCliente) + " generados");
		System.out.println("tiempo total: " + ((double)fin/1000) + " segundos");
	}	
	
}

class Mensaje{
	private String query;
	private String answer;
	
	public Mensaje(String query) {
		this.query = query;
	}
	
	public void responder(String s) {
		this.answer = s;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getAnswer() {
		return answer;
	}
}
