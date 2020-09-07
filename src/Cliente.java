
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
	
	public void run(){
		
		buff.enviarMsg(numMsgEnviados);
		System.out.println("Un cliente trata de enviar un mensaje ");
		}
		
}
