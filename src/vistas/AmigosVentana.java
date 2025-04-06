package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Pokemon.Constantes;
import Pokemon.Juego;

public class AmigosVentana extends JFrame implements ListSelectionListener, ActionListener {

	public static JList list;
	private DefaultListModel<String> listModel;

	private JPanel contentPane;
	private String selectedValue;
	private JLabel nombreAmigo;
	private JButton nuevoAmigo;

	private JLabel poke1;
	private JLabel poke2;
	private JLabel poke3;
	private JLabel poke4;

	private JButton eliminar;
	private JButton combatir;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public AmigosVentana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// String[] items = {"Item 1", "Item 2", "Item 3","Item 4", "Item 5", "Item
		// 6","Item 7", "Item 8", "Item 9"};

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 32, 289, 116);
		contentPane.add(scrollPane);

		listModel = Constantes.DBNeo.entrenadoresAmigos(Juego.USER);

		list = new JList();
		list.setModel(listModel);
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);

		nuevoAmigo = new JButton("Añadir amigo");
		nuevoAmigo.setBounds(327, 45, 117, 57);
		nuevoAmigo.addActionListener(this);
		contentPane.add(nuevoAmigo);

		eliminar = new JButton("Eliminar");
		eliminar.setBounds(258, 237, 149, 29);
		eliminar.setVisible(false);
		eliminar.addActionListener(this);
		contentPane.add(eliminar);

		combatir = new JButton("Combatir");
		combatir.setBounds(69, 237, 117, 29);
		combatir.setVisible(false);
		combatir.addActionListener(this);
		contentPane.add(combatir);

		poke1 = new JLabel("");
		poke1.setBounds(26, 169, 61, 60);
		poke1.setVisible(false);
		contentPane.add(poke1);

		poke2 = new JLabel("");
		poke2.setBounds(126, 169, 60, 60);
		poke2.setVisible(false);
		contentPane.add(poke2);

		poke3 = new JLabel("");
		poke3.setBounds(220, 169, 61, 60);
		poke3.setVisible(false);
		contentPane.add(poke3);

		poke4 = new JLabel("");
		poke4.setBounds(327, 169, 61, 60);
		poke4.setVisible(false);
		contentPane.add(poke4);

		nombreAmigo = new JLabel("");
		nombreAmigo.setBounds(81, 153, 234, 16);
		nombreAmigo.setVisible(false);
		contentPane.add(nombreAmigo);

		JLabel lblNewLabel = new JLabel("Tus amigos:");
		lblNewLabel.setBounds(26, 6, 117, 16);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Amigo:");
		lblNewLabel_1.setBounds(26, 153, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 450, 272);
		fondo.setIcon(new ImageIcon("resources/fondoPika.png"));
		contentPane.add(fondo);

	}

	public void mostrarElementos() {
		poke1.setVisible(true);
		poke2.setVisible(true);
		poke3.setVisible(true);
		poke4.setVisible(true);
		eliminar.setVisible(true);
		combatir.setVisible(true);
		nombreAmigo.setVisible(true);

	}

	public void eliminarElementos() {
		poke1.setVisible(false);
		poke2.setVisible(false);
		poke3.setVisible(false);
		poke4.setVisible(false);
		eliminar.setVisible(false);
		combatir.setVisible(false);
		nombreAmigo.setVisible(false);

	}

	public void limpiarPokemons() {
		poke4.setIcon(new ImageIcon("/Pokemon/fotos/nada.png"));
		poke3.setIcon(new ImageIcon("/Pokemon/fotos/nada.png"));
		poke2.setIcon(new ImageIcon("/Pokemon/fotos/nada.png"));
		poke1.setIcon(new ImageIcon("/Pokemon/fotos/nada.png"));

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			if (!e.getValueIsAdjusting()) {
				selectedValue = (String) list.getSelectedValue();
				nombreAmigo.setText(selectedValue);
				List<String> pokemonSprite = Constantes.DBNeo.pokemonDeSprite(selectedValue);
				limpiarPokemons();
				switch (pokemonSprite.size()) {
				case 4:
					poke4.setIcon(new ImageIcon(pokemonSprite.get(3)));
				case 3:
					poke3.setIcon(new ImageIcon(pokemonSprite.get(2)));
				case 2:
					poke2.setIcon(new ImageIcon(pokemonSprite.get(1)));
				case 1:
					poke1.setIcon(new ImageIcon(pokemonSprite.get(0)));
					break;

				}
				mostrarElementos();
				System.out.println("Opción seleccionada: " + selectedValue);
			}
		} catch (Exception expection) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == nuevoAmigo) {
			NoAmigosVentana noAmigosVentana = new NoAmigosVentana();
			noAmigosVentana.setVisible(true);
		}
		if (botonPresionado == eliminar) {
			Constantes.DBNeo.eliminaAmigo(Juego.USER, selectedValue);
			listModel = Constantes.DBNeo.entrenadoresAmigos(Juego.USER);
			list.setModel(listModel);
			eliminarElementos();
		}
		if (botonPresionado == combatir) {
			int numPokemonPersonal = Constantes.DBNeo.numeroPokemons(Juego.USER);
			int numPokemonRival = Constantes.DBNeo.numeroPokemons(selectedValue);
			if (numPokemonPersonal > 0) {
				if (numPokemonRival > 0) {
					MenuInicioVentana.RIVAL = selectedValue;
					eliminarElementos();
					LuchaVentana batallaVentana = new LuchaVentana();
					batallaVentana.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No puedes luchar con entrenadores si ellos no tienen pokemons",
							"Sin pokemons", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No puedes luchar, no tienes pokemons para combatir",
						"Sin pokemons", JOptionPane.WARNING_MESSAGE);
			}

		}
	}
}
