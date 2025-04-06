package Objeto;

public class Tipo {
	private String nombre;
	private int tipoID;

	public Tipo(String nombre, int tipoID) {
		this.nombre = nombre;
		this.tipoID = tipoID;
	}

	public Tipo() {

	}

	public Tipo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipoID() {
		return tipoID;
	}

	public void setTipoID(int tipoID) {
		this.tipoID = tipoID;
	}

}
