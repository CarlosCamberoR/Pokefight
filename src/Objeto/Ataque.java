package Objeto;

public class Ataque {

	private int ataqueId;
	private String nombre;
	private int potencia;
	private double potenciaTotal;
	private Tipo tipo;

	public Ataque(int ataqueId, String nombre, int potencia, Tipo tipo) {
		this.ataqueId = ataqueId;
		this.nombre = nombre;
		this.potencia = potencia;
		this.tipo = tipo;
	}

	public double getPotenciaTotal() {
		return potenciaTotal;
	}

	public void setPotenciaTotal(double potenciaTotal) {
		this.potenciaTotal = potenciaTotal;
	}

	public int getAtaqueId() {
		return ataqueId;
	}

	public void setAtaqueId(int ataqueId) {
		this.ataqueId = ataqueId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
