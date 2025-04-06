package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.neo4j.driver.Record;

import Pokemon.Constantes;
import Pokemon.Juego;

public class MenuInicioVentana extends JFrame implements ActionListener {
	public static String RIVAL;

	private JPanel contentPane;

	private JButton combate;
	private JButton pokemons;
	private JButton tienda;
	private JButton amigos;

	/**
	 * Create the frame.
	 */
	public MenuInicioVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tienda = new JButton("Tienda pokemon");
		tienda.setBounds(30, 16, 150, 55);
		tienda.addActionListener(this);
		contentPane.add(tienda);

		pokemons = new JButton("Mis pokemons");
		pokemons.setBounds(30, 81, 150, 55);
		pokemons.addActionListener(this);
		contentPane.add(pokemons);

		amigos = new JButton("Amigos");
		amigos.setBounds(30, 146, 150, 55);
		amigos.addActionListener(this);
		contentPane.add(amigos);

		combate = new JButton("Combate");
		combate.setBounds(30, 211, 150, 55);
		combate.addActionListener(this);
		contentPane.add(combate);

		JLabel entrenador = new JLabel("");
		entrenador.setBounds(240, 22, 204, 244);
		entrenador.setIcon(new ImageIcon("resources/entrenadores.png"));
		contentPane.add(entrenador);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(302, 177, 61, 16);
		contentPane.add(lblNewLabel);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 450, 272);
		fondo.setIcon(new ImageIcon("resources/fondoPaisaje.png"));
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == amigos) {

			AmigosVentana amigosVentana = new AmigosVentana();
			amigosVentana.setVisible(true);

		}
		if (botonPresionado == tienda) {

			TiendaVentana tiendaVentana = new TiendaVentana();
			tiendaVentana.setVisible(true);

		}
		if (botonPresionado == pokemons) {
			PokemonsVentana pokemonsVentana = new PokemonsVentana();
			pokemonsVentana.setVisible(true);

		} else if (botonPresionado == combate) {
			JFrame actualVentana = (JFrame) SwingUtilities.getWindowAncestor(combate);

			List<Record> rivales = Constantes.DBNeo.posiblesRivales(Juego.USER);

			if (rivales.isEmpty()) {
				// NO TIENES RIVALES, AGREGA A NUEVOS AMIGOS

				JOptionPane.showMessageDialog(null,
						"Necesitas agregar amigos para poder luchar contra ellos y contra sus amigos", "Sin rivales",
						JOptionPane.WARNING_MESSAGE);

			} else {
				int numPokemonPersonal = Constantes.DBNeo.numeroPokemons(Juego.USER);
				if (numPokemonPersonal > 0) {
					Random rand = new Random();
					int randomNumber = rand.nextInt(rivales.size());
					MenuInicioVentana.RIVAL = rivales.get(randomNumber).get("rival").get("Nombre").asString()
							.replace("\"", "");

					LuchaVentana batallaVentana = new LuchaVentana();
					batallaVentana.setVisible(true);
					contentPane.setVisible(false);
					actualVentana.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "No puedes luchar, no tienes pokemons para combatir",
							"Sin pokemons", JOptionPane.WARNING_MESSAGE);
				}
			}

		}

	}
}
