package vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.neo4j.driver.Record;

import Pokemon.Constantes;
import Pokemon.Juego;

public class PokemonsVentana extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JLabel poke1;
	private JLabel poke2;
	private JLabel poke3;
	private JLabel poke4;
	private JLabel especiePokemon1;
	private JLabel ataque1;
	private JLabel defensa1;
	private JLabel velocidad1;
	private JLabel especialAtaque1;
	private JLabel especialDefensa1;
	private JLabel mote1;
	private JLabel especialAtaque2;
	private JLabel ataque2;
	private JLabel defensa2;
	private JLabel velocidad2;
	private JLabel especiePokemon2;
	private JLabel especialDefensa2;
	private JLabel especialAtaque3;
	private JLabel ataque3;
	private JLabel defensa3;
	private JLabel velocidad3;
	private JLabel especiePokemon3;
	private JLabel especialDefensa3;
	private JLabel especialAtaque4;
	private JLabel ataque4;
	private JLabel defensa4;
	private JLabel velocidad4;
	private JLabel especiePokemon4;
	private JLabel especialDefensa4;
	private JLabel mote2;
	private JLabel mote3;
	private JLabel mote4;
	private JLabel fondo;

	private JButton btnPoke1;
	private JButton btnPoke2;
	private JButton btnPoke3;
	private JButton btnPoke4;
	private List<Record> pokemons;

	/**
	 * Create the frame.
	 */
	public PokemonsVentana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 100, 560, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		pokemons = Constantes.DBNeo.pokemonDe(Juego.USER);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnPoke1 = new JButton("Borrar");
		btnPoke1.setBounds(144, 165, 117, 29);
		btnPoke1.setVisible(false);
		btnPoke1.addActionListener(this);
		contentPane.add(btnPoke1);

		btnPoke2 = new JButton("Borrar");
		btnPoke2.addActionListener(this);
		btnPoke2.setVisible(false);
		btnPoke2.setBounds(437, 165, 117, 29);
		contentPane.add(btnPoke2);

		btnPoke3 = new JButton("Borrar");
		btnPoke3.addActionListener(this);
		btnPoke3.setVisible(false);
		btnPoke3.setBounds(144, 360, 117, 29);
		contentPane.add(btnPoke3);

		btnPoke4 = new JButton("Borrar");
		btnPoke4.addActionListener(this);
		btnPoke4.setVisible(false);
		btnPoke4.setBounds(437, 360, 117, 29);
		contentPane.add(btnPoke4);

		if (pokemons.size() > 0) {
			// POKEMON 1
			especiePokemon1 = new JLabel("New label");
			especiePokemon1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			especiePokemon1.setBounds(132, 22, 150, 16);
			contentPane.add(especiePokemon1);

			especialAtaque1 = new JLabel("New label");
			especialAtaque1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			especialAtaque1.setBounds(132, 100, 150, 16);
			contentPane.add(especialAtaque1);

			ataque1 = new JLabel("New label");
			ataque1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			ataque1.setBounds(132, 41, 150, 16);
			contentPane.add(ataque1);

			defensa1 = new JLabel("New label");
			defensa1.setFont(new Font("Lucida Grande", Font.BOLD, 13));

			defensa1.setBounds(132, 61, 150, 16);
			contentPane.add(defensa1);

			velocidad1 = new JLabel("New label");
			velocidad1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			velocidad1.setBounds(132, 80, 150, 16);
			contentPane.add(velocidad1);

			especialDefensa1 = new JLabel("New label");
			especialDefensa1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			especialDefensa1.setBounds(132, 120, 150, 16);
			contentPane.add(especialDefensa1);

			mote1 = new JLabel("New label");
			mote1.setHorizontalAlignment(SwingConstants.CENTER);
			mote1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			mote1.setBounds(19, 174, 150, 16);
			contentPane.add(mote1);

			poke1 = new JLabel("");
			poke1.setBounds(19, 22, 100, 150);
			contentPane.add(poke1);

			especiePokemon1.setText(pokemons.get(0).get("pokemon").get("pokeEspecie").asString().replace("\"", ""));
			List<Record> especie = Constantes.DBNeo.especieDe(pokemons.get(0).get("pokemon").get("pokeID").asInt());
			ataque1.setText("Ataque: " + especie.get(0).get("especie").get("Attack").toString());
			defensa1.setText("Defensa: " + especie.get(0).get("especie").get("Defense").toString());
			velocidad1.setText("Velocidad: " + especie.get(0).get("especie").get("Speed").toString());
			poke1.setIcon(new ImageIcon(especie.get(0).get("especie").get("Gif").asString().replace("\"", "")));
			mote1.setText(pokemons.get(0).get("pokemon").get("mote").asString().replace("\"", ""));
			especialDefensa1.setText("Defensa Esp.: " + especie.get(0).get("especie").get("SpDef").toString());
			especialAtaque1.setText("Ataque Esp.: " + especie.get(0).get("especie").get("SpAtk").toString());

			btnPoke1.setVisible(true);
		}

		if (pokemons.size() > 1) {
			// POKEMON 2
			poke2 = new JLabel("");
			poke2.setBounds(311, 22, 100, 150);
			poke2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(poke2);

			ataque2 = new JLabel("New label");
			ataque2.setBounds(423, 41, 150, 16);
			ataque2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(ataque2);

			defensa2 = new JLabel("New label");
			defensa2.setBounds(423, 61, 150, 16);
			defensa2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(defensa2);

			mote2 = new JLabel("New label");
			mote2.setHorizontalAlignment(SwingConstants.CENTER);
			mote2.setBounds(311, 174, 150, 16);
			mote2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(mote2);

			velocidad2 = new JLabel("New label");
			velocidad2.setBounds(423, 80, 150, 16);
			velocidad2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(velocidad2);

			especiePokemon2 = new JLabel("New label");
			especiePokemon2.setBounds(423, 22, 150, 16);
			especiePokemon2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especiePokemon2);

			especialDefensa2 = new JLabel("New label");
			especialDefensa2.setBounds(423, 120, 150, 16);
			especialDefensa2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialDefensa2);

			especialAtaque2 = new JLabel("New label");
			especialAtaque2.setBounds(423, 100, 150, 16);
			especialAtaque2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialAtaque2);

			especiePokemon2.setText(pokemons.get(1).get("pokemon").get("pokeEspecie").asString().replace("\"", ""));
			List<Record> especie = Constantes.DBNeo.especieDe(pokemons.get(1).get("pokemon").get("pokeID").asInt());
			ataque2.setText("Ataque: " + especie.get(0).get("especie").get("Attack").toString());
			defensa2.setText("Defensa: " + especie.get(0).get("especie").get("Defense").toString());
			velocidad2.setText("Velocidad: " + especie.get(0).get("especie").get("Speed").toString());
			poke2.setIcon(new ImageIcon(especie.get(0).get("especie").get("Gif").asString().replace("\"", "")));
			mote2.setText(pokemons.get(1).get("pokemon").get("mote").asString().replace("\"", ""));
			especialDefensa2.setText("Defensa Esp.: " + especie.get(0).get("especie").get("SpDef").toString());
			especialAtaque2.setText("Ataque Esp.: " + especie.get(0).get("especie").get("SpAtk").toString());

			btnPoke2.setVisible(true);
		}

		if (pokemons.size() > 2) {
			// POKEMON 3
			poke3 = new JLabel("");
			poke3.setBounds(19, 211, 100, 150);
			poke3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(poke3);

			especialAtaque3 = new JLabel("New label");
			especialAtaque3.setBounds(132, 289, 150, 16);
			especialAtaque3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialAtaque3);

			ataque3 = new JLabel("New label");
			ataque3.setBounds(132, 230, 150, 16);
			ataque3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(ataque3);

			defensa3 = new JLabel("New label");
			defensa3.setBounds(132, 250, 150, 16);
			defensa3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(defensa3);

			velocidad3 = new JLabel("New label");
			velocidad3.setBounds(132, 269, 150, 16);
			velocidad3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(velocidad3);

			especiePokemon3 = new JLabel("New label");
			especiePokemon3.setBounds(132, 211, 150, 16);
			especiePokemon3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especiePokemon3);

			mote3 = new JLabel("New label");
			mote3.setHorizontalAlignment(SwingConstants.CENTER);
			mote3.setBounds(19, 364, 150, 16);
			mote3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(mote3);

			especialDefensa3 = new JLabel("New label");
			especialDefensa3.setBounds(132, 309, 150, 16);
			especialDefensa3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialDefensa3);

			especiePokemon3.setText(pokemons.get(2).get("pokemon").get("pokeEspecie").asString().replace("\"", ""));
			List<Record> especie = Constantes.DBNeo.especieDe(pokemons.get(2).get("pokemon").get("pokeID").asInt());
			ataque3.setText("Ataque: " + especie.get(0).get("especie").get("Attack").toString());
			defensa3.setText("Defensa: " + especie.get(0).get("especie").get("Defense").toString());
			velocidad3.setText("Velocidad: " + especie.get(0).get("especie").get("Speed").toString());
			poke3.setIcon(new ImageIcon(especie.get(0).get("especie").get("Gif").asString().replace("\"", "")));
			mote3.setText(pokemons.get(2).get("pokemon").get("mote").asString().replace("\"", ""));
			especialDefensa3.setText("Defensa Esp.: " + especie.get(0).get("especie").get("SpDef").toString());
			especialAtaque3.setText("Ataque Esp.: " + especie.get(0).get("especie").get("SpAtk").toString());

			btnPoke3.setVisible(true);
		}

		if (pokemons.size() > 3) {
			// POKEMON 4

			poke4 = new JLabel("");
			poke4.setBounds(311, 202, 100, 150);
			poke4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(poke4);

			especialAtaque4 = new JLabel("New label");
			especialAtaque4.setBounds(423, 280, 150, 16);
			especialAtaque4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialAtaque4);

			ataque4 = new JLabel("New label");
			ataque4.setBounds(423, 221, 150, 16);
			ataque4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(ataque4);

			defensa4 = new JLabel("New label");
			defensa4.setBounds(423, 241, 150, 16);
			defensa4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(defensa4);

			velocidad4 = new JLabel("New label");
			velocidad4.setBounds(423, 260, 150, 16);
			velocidad4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(velocidad4);

			especiePokemon4 = new JLabel("New label");
			especiePokemon4.setBounds(423, 202, 150, 16);
			especiePokemon4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especiePokemon4);

			especialDefensa4 = new JLabel("New label");
			especialDefensa4.setBounds(423, 300, 150, 16);
			especialDefensa4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(especialDefensa4);

			mote4 = new JLabel("New label");
			mote4.setHorizontalAlignment(SwingConstants.CENTER);
			mote4.setBounds(311, 364, 150, 16);
			mote4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			contentPane.add(mote4);

			especiePokemon4.setText(pokemons.get(3).get("pokemon").get("pokeEspecie").asString().replace("\"", ""));
			List<Record> especie = Constantes.DBNeo.especieDe(pokemons.get(3).get("pokemon").get("pokeID").asInt());
			ataque4.setText("Ataque: " + especie.get(0).get("especie").get("Attack").toString());
			defensa4.setText("Defensa: " + especie.get(0).get("especie").get("Defense").toString());
			velocidad4.setText("Velocidad: " + especie.get(0).get("especie").get("Speed").toString());
			poke4.setIcon(new ImageIcon(especie.get(0).get("especie").get("Gif").asString().replace("\"", "")));
			mote4.setText(pokemons.get(3).get("pokemon").get("mote").asString().replace("\"", ""));
			especialDefensa4.setText("Defensa Esp.: " + especie.get(0).get("especie").get("SpDef").toString());
			especialAtaque4.setText("Ataque Esp.: " + especie.get(0).get("especie").get("SpAtk").toString());

			btnPoke4.setVisible(true);

		}
		fondo = new JLabel("");
		fondo.setBounds(0, 0, 560, 402);
		fondo.setIcon(new ImageIcon("resources/fondoPokemon.png"));
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == btnPoke1) {
			Constantes.DBNeo.eliminarPokemon(pokemons.get(0).get("pokemon").get("pokeID").asInt());
		}
		if (botonPresionado == btnPoke2) {
			Constantes.DBNeo.eliminarPokemon(pokemons.get(1).get("pokemon").get("pokeID").asInt());
		}
		if (botonPresionado == btnPoke3) {
			Constantes.DBNeo.eliminarPokemon(pokemons.get(2).get("pokemon").get("pokeID").asInt());
		}
		if (botonPresionado == btnPoke4) {
			Constantes.DBNeo.eliminarPokemon(pokemons.get(3).get("pokemon").get("pokeID").asInt());
		}
		JFrame actualVentana = (JFrame) SwingUtilities.getWindowAncestor(btnPoke1);

		PokemonsVentana pokemonVentana = new PokemonsVentana();
		pokemonVentana.setVisible(true);
		actualVentana.dispose();

	}

}
