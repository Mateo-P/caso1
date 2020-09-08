
public class Mensaje {
	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private int contenido;
	public Mensaje(int contenido)
	{
		this.contenido=contenido;
	}
	public int getContenido() {
		return contenido;
	}
	public void Responder() {
		contenido++;
	}
}
