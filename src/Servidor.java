
public class Servidor extends Thread{
	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private static Buffer buff;
	public Servidor(Buffer buff)
	{
		this.buff=buff;
	}
	public void run()
	{
		System.out.println(buff.hayClientes());
		while(buff.hayClientes())
		{
			buff.RetirarMsg();
			yield();	
		}
		
	}
}
