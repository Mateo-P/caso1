
public class Mensajes {
	//--------------------------------
	// ATRIBUTOS
	//--------------------------------
	private int contenido;
	public Mensajes(int contenido)
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
