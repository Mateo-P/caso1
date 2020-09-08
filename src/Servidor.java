
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
		
		while(buff.hayClientes())
		{
			buff.RetirarMsg();
			yield();	
		}
		System.out.println("YA NO HAY MAS CLIENTES PAI");
	}
}
