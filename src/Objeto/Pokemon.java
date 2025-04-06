package Objeto;

import java.util.List;

public class Pokemon {
	private String mote;
	private int pokeId;
	private int nivel;
	private String especie;
	private int vidaTotal;
	private int vidaRestante;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int ataqueTotal;
	private int defensaTotal;
	private int velocidad;
	private List<Tipo> tipos;
	private List<Ataque> ataques;
	private String gif;
	private String gifBack;
	private String png;

	public Pokemon(String mote, int pokeId, int nivel, String especie, int vidaTotal, int vidaRestante, int ataque,
			int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, List<Tipo> tipos,
			List<Ataque> ataques) {
		this.mote = mote;
		this.pokeId = pokeId;
		this.nivel = nivel;
		this.especie = especie;
		this.vidaTotal = vidaTotal;
		this.vidaRestante = vidaRestante;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipos = tipos;
		this.ataques = ataques;
	}

	public Pokemon(String mote, int pokeId, int nivel, String especie, List<Tipo> tipos, List<Ataque> ataques,
			String gif, String gifBack, String png) {
		this.mote = mote;
		this.pokeId = pokeId;
		this.nivel = nivel;
		this.especie = especie;
		this.tipos = tipos;
		this.ataques = ataques;
		this.gif = gif;
		this.gifBack = gifBack;
		this.png = png;
	}

	public Pokemon(String especie, List<Tipo> tipos, String png) {
		this.especie = especie;
		this.tipos = tipos;
		this.png = png;
	}

	public Pokemon() {

	}

	public String getMote() {
		return mote;
	}

	public void setMote(String mote) {
		this.mote = mote;
	}

	public int getPokeId() {
		return pokeId;
	}

	public void setPokeId(int pokeId) {
		this.pokeId = pokeId;
	}

	public int getAtaqueTotal() {
		return ataqueTotal;
	}

	public void setAtaqueTotal(int ataqueTotal) {
		this.ataqueTotal = ataqueTotal;
	}

	public int getDefensaTotal() {
		return defensaTotal;
	}

	public void setDefensaTotal(int defensaTotal) {
		this.defensaTotal = defensaTotal;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getVidaTotal() {
		return vidaTotal;
	}

	public void setVidaTotal(int vidaTotal) {
		this.vidaTotal = vidaTotal;
	}

	public int getVidaRestante() {
		return vidaRestante;
	}

	public void setVidaRestante(int vidaRestante) {
		this.vidaRestante = vidaRestante;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public List<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(List<Ataque> ataques) {
		this.ataques = ataques;
	}

	public String getGif() {
		return gif;
	}

	public void setGif(String gif) {
		this.gif = gif;
	}

	public String getGifBack() {
		return gifBack;
	}

	public void setGifBack(String gifBack) {
		this.gifBack = gifBack;
	}

	public String getPng() {
		return png;
	}

	public void setPng(String png) {
		this.png = png;
	}

}
