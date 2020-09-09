
public class Cliente extends Thread {

	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private int numMsgEnviados;
	private static Buffer buff;

	public Cliente(int numMsgEnviados, Buffer buff)
	{
		this.numMsgEnviados= numMsgEnviados;
		this.buff= buff;
	}

	public void run() {
		for (int i = 0; i < numMsgEnviados; i++) {
			Mensaje msg= new Mensaje(i);
			buff.enviarMsg(msg);
		}
		System.out.println("ME RETIRO PAI");
		buff.FindelComunicado();
	}

}
