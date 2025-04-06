package vistas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Objeto.Ataque;
import Objeto.Log;
import Objeto.Pokemon;
import Pokemon.Batalla;
import Pokemon.Constantes;
import Pokemon.Juego;

public class LuchaVentana extends JFrame implements ActionListener {

	private Batalla batalla;
	private JPanel contentPane;
	private JLabel pokemonRival;

	private JLabel pokemonPersonal;

	private JButton atacar;
	private JButton ayuda;
	private JButton pokemons;
	private JButton abandonar;

	private JButton cancelar;
	private JButton volver;

	private JButton ataque1;
	private JButton ataque2;
	private JButton ataque3;
	private JButton ataque4;

	private JLabel escenario;
	private Canvas canvas_1;
	private Canvas canvas_2;

	private JButton pokemon1;
	private JButton pokemon2;
	private JButton pokemon3;
	private JButton pokemon4;

	private Pokemon poke1;
	private Pokemon poke2;
	private Pokemon poke3;
	private Pokemon poke4;

	private Pokemon pokeRival;

	private JLabel vidaRival;
	private JLabel vidaMiPokemon;
	private JLabel mensajeFinal;

	private int contAyuda = 0;
	private String pokePersonal;

	/**
	 * Create the frame.
	 */
	public LuchaVentana() {// Creo que hay que añadir por parametros los pokemon que van a luchar
		batalla = new Batalla(MenuInicioVentana.RIVAL, Juego.USER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ayuda = new JButton("Ayuda");
		ayuda.setBounds(434, 331, 117, 62);
		ayuda.addActionListener(this);
		contentPane.add(ayuda);

		pokemons = new JButton("Pokemons");
		pokemons.setBounds(305, 397, 117, 62);
		pokemons.addActionListener(this);
		contentPane.add(pokemons);

		volver = new JButton("Volver");
		volver.setBackground(new Color(236, 172, 178));
		volver.setBounds(410, 360, 117, 62);
		volver.setVisible(false);
		volver.addActionListener(this);
		contentPane.add(volver);

		atacar = new JButton("Atacar");
		atacar.setBackground(new Color(236, 172, 178));
		atacar.setBounds(305, 331, 117, 62);
		atacar.addActionListener(this);
		contentPane.add(atacar);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(410, 360, 117, 62);
		cancelar.setVisible(false);
		cancelar.addActionListener(this);
		contentPane.add(cancelar);

		pokemon1 = new JButton("");
		pokemon1.setHorizontalAlignment(SwingConstants.LEFT);
		pokemon1.setBounds(11, 331, 170, 62);
		pokemon1.setVisible(false);

		pokemon2 = new JButton("");
		pokemon2.setHorizontalAlignment(SwingConstants.LEFT);
		pokemon2.setBounds(197, 331, 170, 62);
		pokemon2.setVisible(false);

		pokemon3 = new JButton("");
		pokemon3.setHorizontalAlignment(SwingConstants.LEFT);
		pokemon3.setBounds(11, 397, 170, 62);
		pokemon3.setVisible(false);

		pokemon4 = new JButton("");
		pokemon4.setHorizontalAlignment(SwingConstants.LEFT);
		pokemon4.setBounds(197, 397, 170, 62);
		pokemon4.setVisible(false);

		switch (batalla.getEntrenadorYo().getPokemons().size()) {
		case 4:
			pokemon4.setIcon(new ImageIcon(batalla.getEntrenadorYo().getPokemons().get(3).getPng()));
			pokemon4.addActionListener(this);
			poke4 = batalla.getEntrenadorYo().getPokemons().get(3);
			pokemon4.setText("Vida: " + poke4.getVidaRestante() + "/" + poke4.getVidaTotal());

		case 3:
			pokemon3.setIcon(new ImageIcon(batalla.getEntrenadorYo().getPokemons().get(2).getPng()));
			pokemon3.addActionListener(this);
			poke3 = batalla.getEntrenadorYo().getPokemons().get(2);
			pokemon3.setText("Vida: " + poke3.getVidaRestante() + "/" + poke3.getVidaTotal());

		case 2:
			pokemon2.setIcon(new ImageIcon(batalla.getEntrenadorYo().getPokemons().get(1).getPng()));
			pokemon2.addActionListener(this);
			poke2 = batalla.getEntrenadorYo().getPokemons().get(1);
			pokemon2.setText("Vida: " + poke2.getVidaRestante() + "/" + poke2.getVidaTotal());

		case 1:
			pokemon1.setIcon(new ImageIcon(batalla.getEntrenadorYo().getPokemons().get(0).getPng()));
			pokemon1.addActionListener(this);
			poke1 = batalla.getEntrenadorYo().getPokemons().get(0);
			pokePersonal = poke1.getEspecie();
			pokemon1.setText("Vida: " + poke1.getVidaRestante() + "/" + poke1.getVidaTotal());

			break;
		}

		contentPane.add(pokemon4);
		contentPane.add(pokemon3);
		contentPane.add(pokemon2);
		contentPane.add(pokemon1);

		pokeRival = batalla.getLuchaRival();

		ataque1 = new JButton(batalla.getLuchaYo().getAtaques().get(0).getNombre());
		ataque1.setBounds(11, 331, 170, 62);
		ataque1.setVisible(false);
		ataque1.addActionListener(this);
		contentPane.add(ataque1);

		ataque2 = new JButton(batalla.getLuchaYo().getAtaques().get(1).getNombre());
		ataque2.setBounds(197, 331, 170, 62);
		ataque2.setVisible(false);
		ataque2.addActionListener(this);
		contentPane.add(ataque2);

		ataque3 = new JButton(batalla.getLuchaYo().getAtaques().get(2).getNombre());
		ataque3.setBounds(11, 397, 170, 62);
		ataque3.setVisible(false);
		ataque3.addActionListener(this);
		contentPane.add(ataque3);

		ataque4 = new JButton(batalla.getLuchaYo().getAtaques().get(3).getNombre());
		ataque4.setBounds(197, 397, 170, 62);
		ataque4.setVisible(false);
		ataque4.addActionListener(this);
		contentPane.add(ataque4);

		abandonar = new JButton("Abandonar");
		abandonar.setBounds(434, 397, 117, 62);
		abandonar.addActionListener(this);
		contentPane.add(abandonar);

		vidaRival = new JLabel(
				"Vida: " + batalla.getLuchaRival().getVidaRestante() + "/" + batalla.getLuchaRival().getVidaTotal());
		vidaRival.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		vidaRival.setBounds(355, 31, 150, 16);
		contentPane.add(vidaRival);

		vidaMiPokemon = new JLabel(
				"Vida: " + batalla.getLuchaYo().getVidaRestante() + "/" + batalla.getLuchaYo().getVidaTotal());
		vidaMiPokemon.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		vidaMiPokemon.setBounds(100, 180, 151, 16);
		contentPane.add(vidaMiPokemon);

		mensajeFinal = new JLabel("");
		mensajeFinal.setHorizontalAlignment(SwingConstants.CENTER);
		mensajeFinal.setBounds(111, 370, 348, 40);
		contentPane.add(mensajeFinal);

		canvas_2 = new Canvas();
		canvas_2.setBackground(new Color(238, 238, 238));
		canvas_2.setBounds(11, 331, 550, 130);
		contentPane.add(canvas_2);

		canvas_1 = new Canvas();
		canvas_1.setBackground(new Color(137, 141, 146));
		canvas_1.setBounds(6, 325, 560, 141);
		contentPane.add(canvas_1);

		Canvas canvas = new Canvas();
		canvas.setBackground(new Color(0, 0, 0));
		canvas.setBounds(0, 319, 572, 153);
		contentPane.add(canvas);

		pokemonRival = new JLabel("");
		pokemonRival.setIcon(new ImageIcon(batalla.getLuchaRival().getGif()));
		pokemonRival.setBounds(355, 59, 150, 150);
		contentPane.add(pokemonRival);

		pokemonPersonal = new JLabel("");
		pokemonPersonal.setIcon(new ImageIcon(batalla.getLuchaYo().getGifBack()));
		pokemonPersonal.setBounds(101, 205, 150, 150);
		contentPane.add(pokemonPersonal);

		escenario = new JLabel("");
		escenario.setIcon(new ImageIcon("resources/escenario.png"));
		escenario.setBounds(0, 0, 572, 323);
		contentPane.add(escenario);

	}

	private void mostrarBotonesPrincipales() {
		atacar.setVisible(true);
		ayuda.setVisible(true);
		pokemons.setVisible(true);
		abandonar.setVisible(true);
	}

	private void ocultarBotonesPrincipales() {
		atacar.setVisible(false);
		ayuda.setVisible(false);
		pokemons.setVisible(false);
		abandonar.setVisible(false);
	}

	private void mostrarAtaques() {
		ataque1.setVisible(true);
		ataque2.setVisible(true);
		ataque3.setVisible(true);
		ataque4.setVisible(true);
		cancelar.setVisible(true);
	}

	private void ocultarAtaques() {
		ataque1.setVisible(false);
		ataque2.setVisible(false);
		ataque3.setVisible(false);
		ataque4.setVisible(false);
		cancelar.setVisible(false);
	}

	private void ocultarPokemons() {
		volver.setVisible(false);
		pokemon1.setVisible(false);
		pokemon2.setVisible(false);
		pokemon3.setVisible(false);
		pokemon4.setVisible(false);
	}

	private void mostrarPokemons() {
		volver.setVisible(true);
		pokemon1.setVisible(true);
		pokemon2.setVisible(true);
		pokemon3.setVisible(true);
		pokemon4.setVisible(true);
	}

	private void cargarAtaques() {
		ataque1.setText(batalla.getLuchaYo().getAtaques().get(0).getNombre());
		ataque2.setText(batalla.getLuchaYo().getAtaques().get(1).getNombre());
		ataque3.setText(batalla.getLuchaYo().getAtaques().get(2).getNombre());
		ataque4.setText(batalla.getLuchaYo().getAtaques().get(3).getNombre());
	}

	public void actualizarVida() {
		vidaMiPokemon
				.setText("Vida: " + batalla.getLuchaYo().getVidaRestante() + "/" + batalla.getLuchaYo().getVidaTotal());
		vidaRival.setText(
				"Vida: " + batalla.getLuchaRival().getVidaRestante() + "/" + batalla.getLuchaRival().getVidaTotal());

		switch (batalla.getEntrenadorYo().getPokemons().size()) {
		case 4:
			pokemon4.setText("Vida: " + poke4.getVidaRestante() + "/" + poke4.getVidaTotal());

		case 3:

			pokemon3.setText("Vida: " + poke3.getVidaRestante() + "/" + poke3.getVidaTotal());

		case 2:

			pokemon2.setText("Vida: " + poke2.getVidaRestante() + "/" + poke2.getVidaTotal());

		case 1:
			pokemon1.setText("Vida: " + poke1.getVidaRestante() + "/" + poke1.getVidaTotal());
			break;
		}
	}

	// Método de ordenación
	public void ordenacionAtaques(List<Ataque> ataques) {
		Ataque X;
		String ficheroX;

		Integer Der;
		Integer Izq;
		Integer Medio;

		for (int i = 1; i < ataques.size(); i++) {
			X = ataques.get(i);
			// ficheroX = fichero.get(i);
			Izq = 0;
			Der = i - 1;
			while (Izq <= Der) {
				Medio = (Izq + Der) / 2;
				if (X.getPotenciaTotal() > ataques.get(Medio).getPotenciaTotal())
					Der = Medio - 1;
				else
					Izq = Medio + 1;
			}
			for (int j = i - 1; j >= Izq; j--) {
				ataques.set(j + 1, ataques.get(j));
			}
			ataques.set(Izq, X);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();

		LocalDateTime datetime = LocalDateTime.now();

		if (botonPresionado == atacar) {
			cargarAtaques();
			ocultarBotonesPrincipales();
			mostrarAtaques();

		} else if (botonPresionado == ataque1 || botonPresionado == ataque2 || botonPresionado == ataque3
				|| botonPresionado == ataque4 || botonPresionado == cancelar) {
			boolean victoria = false;
			if (botonPresionado == ataque1) {
				victoria = batalla.combate(batalla.getLuchaYo().getAtaques().get(0));
			} else if (botonPresionado == ataque2) {
				victoria = batalla.combate(batalla.getLuchaYo().getAtaques().get(1));
			} else if (botonPresionado == ataque3) {
				victoria = batalla.combate(batalla.getLuchaYo().getAtaques().get(2));
			} else if (botonPresionado == ataque4) {
				victoria = batalla.combate(batalla.getLuchaYo().getAtaques().get(3));
			}

			actualizarVida();
			ocultarAtaques();
			if (victoria) {
				mensajeFinal.setText("TU EQUIPO HA GANADO");
				abandonar.setVisible(true);
			} else {
				if (pokeRival != batalla.getLuchaRival()) {
					pokeRival = batalla.getLuchaRival();
					pokemonRival.setIcon(new ImageIcon(pokeRival.getGif()));
					batalla.calculoPotenciaAtaque(batalla.getLuchaYo(), batalla.getLuchaRival());
					batalla.calculoPotenciaAtaque(batalla.getLuchaRival(), batalla.getLuchaYo());
				}
				if (batalla.getLuchaYo().getVidaRestante() <= 0) {
					if (!batalla.equipoEliminado(batalla.getEntrenadorYo())) {
						mostrarPokemons();
						volver.setVisible(false);
					} else {
						mensajeFinal.setText("TU EQUIPO HA SIDO ELIMINADO, HAS PERDIDO");
						abandonar.setVisible(true);
					}

				} else {

					mostrarBotonesPrincipales();
				}
			}

		} else if (botonPresionado == ayuda) {
			contAyuda++;
			// tu pokemon, el rival y la coleccion de ataques
			List<Ataque> ataques = new ArrayList<>(batalla.getLuchaYo().getAtaques());
			ordenacionAtaques(ataques);

			Log registro = new Log(Juego.USER + datetime, "3", pokePersonal.toLowerCase(),
					pokeRival.getEspecie().toLowerCase(), ataques.get(0).getNombre(),
					ataques.get(0).getTipo().getNombre(), ataques.get(1).getNombre(),
					ataques.get(1).getTipo().getNombre(), ataques.get(2).getNombre(),
					ataques.get(2).getTipo().getNombre(), ataques.get(3).getNombre(),
					ataques.get(3).getTipo().getNombre());
			Constantes.DBDynamo.insertarLogTienda(registro);

			AyudaVentana ayudaVentana = new AyudaVentana(ataques);
			ayudaVentana.setVisible(true);

		} else if (botonPresionado == pokemons) {
			ocultarBotonesPrincipales();
			mostrarPokemons();

		} else if (botonPresionado == pokemon1 || botonPresionado == pokemon2 || botonPresionado == pokemon3
				|| botonPresionado == pokemon4 || botonPresionado == volver) {
			boolean pokemonCambiado = false;
			if (botonPresionado == pokemon1) {
				if (poke1.getVidaRestante() > 0 && poke1 != batalla.getLuchaYo()) {
					pokePersonal = poke1.getEspecie();
					pokemonPersonal.setIcon(new ImageIcon(poke1.getGifBack()));
					batalla.setLuchaYo(poke1);
					pokemonCambiado = true;
				}
			} else if (botonPresionado == pokemon2) {
				if (poke2.getVidaRestante() > 0 && poke2 != batalla.getLuchaYo()) {
					pokePersonal = poke2.getEspecie();
					pokemonPersonal.setIcon(new ImageIcon(poke2.getGifBack()));
					batalla.setLuchaYo(poke2);
					pokemonCambiado = true;
				}
			} else if (botonPresionado == pokemon3) {
				if (poke3.getVidaRestante() > 0 && poke3 != batalla.getLuchaYo()) {
					pokePersonal = poke3.getEspecie();
					pokemonPersonal.setIcon(new ImageIcon(poke3.getGifBack()));
					batalla.setLuchaYo(poke3);
					pokemonCambiado = true;
				}
			} else if (botonPresionado == pokemon4) {
				if (poke4.getVidaRestante() > 0 && poke4 != batalla.getLuchaYo()) {
					pokePersonal = poke4.getEspecie();
					pokemonPersonal.setIcon(new ImageIcon(poke4.getGifBack()));
					batalla.setLuchaYo(poke4);
					pokemonCambiado = true;
				}
			} else {
				actualizarVida();
				ocultarPokemons();
				mostrarBotonesPrincipales();
			}

			// Calculas la potencia solo cuando cambias tu de pokemon, hay que implementar
			// cuando se muere el pokemon rival tambien calcularla
			if (pokemonCambiado) {
				batalla.calculoPotenciaAtaque(batalla.getLuchaYo(), batalla.getLuchaRival());
				batalla.calculoPotenciaAtaque(batalla.getLuchaRival(), batalla.getLuchaYo());
				actualizarVida();
				ocultarPokemons();
				mostrarBotonesPrincipales();

			}

		} else if (botonPresionado == abandonar) {
			JFrame actualVentana = (JFrame) SwingUtilities.getWindowAncestor(abandonar);
			if (contAyuda > 0) {
				Log registro = new Log(Juego.USER + datetime, "4", "SI", contAyuda);
				Constantes.DBDynamo.insertarLogTienda(registro);

			} else {
				Log registro = new Log(Juego.USER + datetime, "4", "NO");
				Constantes.DBDynamo.insertarLogTienda(registro);
			}

			MenuInicioVentana menu = new MenuInicioVentana();
			menu.setVisible(true);
			contentPane.setVisible(false);
			actualVentana.dispose();
		}
	}

	public JLabel getPokemonRival() {
		return pokemonRival;
	}

	public void setPokemonRival(String pokemonRival) {
		this.pokemonRival.setIcon(new ImageIcon(pokemonRival));
	}

	public JLabel getPokemonPersonal() {
		return pokemonPersonal;
	}

	public void setPokemonPersonal(String pokemonPersonal) {
		this.pokemonPersonal.setIcon(new ImageIcon(pokemonPersonal));
	}

	public JButton getAtacar() {
		return atacar;
	}

	public void setAtacar(JButton atacar) {
		this.atacar = atacar;
	}

	public JButton getAyuda() {
		return ayuda;
	}

	public void setAyuda(JButton ayuda) {
		this.ayuda = ayuda;
	}

	public JButton getPokemons() {
		return pokemons;
	}

	public void setPokemons(JButton pokemons) {
		this.pokemons = pokemons;
	}

	public JButton getAbandonar() {
		return abandonar;
	}

	public void setAbandonar(JButton abandonar) {
		this.abandonar = abandonar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public JButton getAtaque1() {
		return ataque1;
	}

	public void setAtaque1(JButton ataque1) {
		this.ataque1 = ataque1;
	}

	public JButton getAtaque2() {
		return ataque2;
	}

	public void setAtaque2(JButton ataque2) {
		this.ataque2 = ataque2;
	}

	public JButton getAtaque3() {
		return ataque3;
	}

	public void setAtaque3(JButton ataque3) {
		this.ataque3 = ataque3;
	}

	public JButton getAtaque4() {
		return ataque4;
	}

	public void setAtaque4(JButton ataque4) {
		this.ataque4 = ataque4;
	}
}
