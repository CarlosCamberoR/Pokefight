package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Objeto.Log;
import Objeto.Pokemon;
import Pokemon.Constantes;
import Pokemon.Juego;

public class TiendaVentana extends JFrame implements ListSelectionListener, ActionListener {

	private JPanel contentPane;
	private JList list;
	private DefaultListModel<String> listModel;
	private JButton recomendacion;
	private String selectedValue;
	private JTextField textField;
	private JLabel tipo1;
	private JLabel tipo2;
	private JLabel icono;
	private JLabel nombre;
	private JButton buscar;
	private JButton add;
	private JButton todos;
	private Boolean banderaRecomendacion = false;
	private String pokemonBusqueda;
	private JLabel tipos;

	/**
	 * Create the frame.
	 */
	public TiendaVentana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 18, 257, 145);
		contentPane.add(scrollPane);

		listModel = Constantes.DBDynamo.obtenerPokemonsTienda();

		list = new JList();
		list.setModel(listModel);
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);

		JLabel lblNewLabel = new JLabel("Nombre de pokemon:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(288, 20, 156, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(288, 48, 156, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		buscar = new JButton("Buscar");
		buscar.addActionListener(this);
		buscar.setBounds(288, 76, 156, 29);
		contentPane.add(buscar);

		recomendacion = new JButton("Recomendación \n");

		recomendacion.setBounds(288, 105, 156, 26);
		recomendacion.addActionListener(this);
		contentPane.add(recomendacion);

		add = new JButton("Añadir");
		add.setBounds(300, 187, 133, 66);
		add.addActionListener(this);
		contentPane.add(add);

		nombre = new JLabel("");
		nombre.setForeground(new Color(255, 255, 255));
		nombre.setBounds(139, 175, 137, 16);
		contentPane.add(nombre);

		icono = new JLabel("");
		icono.setBounds(19, 175, 108, 91);
		contentPane.add(icono);

		tipos = new JLabel("Tipos:");
		tipos.setForeground(new Color(255, 255, 255));
		tipos.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		tipos.setBounds(139, 200, 61, 16);
		tipos.setVisible(false);
		contentPane.add(tipos);

		tipo1 = new JLabel("");
		tipo1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		tipo1.setForeground(new Color(255, 255, 255));
		tipo1.setBounds(169, 221, 107, 16);
		contentPane.add(tipo1);

		tipo2 = new JLabel("");
		tipo2.setForeground(new Color(255, 255, 255));
		tipo2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		tipo2.setBounds(169, 239, 107, 16);
		contentPane.add(tipo2);

		todos = new JButton("Todos");
		todos.setBounds(288, 134, 156, 29);
		todos.addActionListener(this);
		contentPane.add(todos);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 450, 272);
		fondo.setIcon(new ImageIcon("resources/fondoTienda.png"));
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == recomendacion) {
			banderaRecomendacion = true;
			pokemonBusqueda = textField.getText();

			DefaultListModel<String> listModel = Constantes.DBNeo.pokemonConVentaja(textField.getText());
			textField.setText("");
			list.setModel(listModel);

		}

		if (botonPresionado == buscar) {
			try {
				String nombrePoke = textField.getText().substring(0, 1).toUpperCase()
						+ textField.getText().substring(1, textField.getText().length()).toLowerCase();
				Pokemon poke = Constantes.DBDynamo.getElementByName(nombrePoke);

				textField.setText("");
				DefaultListModel<String> pokemonBuscado = new DefaultListModel<>();
				pokemonBuscado.addElement(poke.getEspecie());
				list.setModel(pokemonBuscado);
			} catch (Exception expection) {

			}
		}

		if (botonPresionado == todos) {
			listModel = Constantes.DBDynamo.obtenerPokemonsTienda();
			list.setModel(listModel);
		}

		if (botonPresionado == add) {

			if (Constantes.DBNeo.numeroPokemons(Juego.USER) < 4) {
				LocalDateTime datetime = LocalDateTime.now();
				Pokemon pokeSeleccion = Constantes.DBDynamo.getElementByName(selectedValue.substring(0, 1).toUpperCase()
						+ selectedValue.substring(1, selectedValue.length()).toLowerCase());

				if (banderaRecomendacion) {
					banderaRecomendacion = false;

					Pokemon pokeBusqueda = Constantes.DBDynamo
							.getElementByName(pokemonBusqueda.substring(0, 1).toUpperCase()
									+ pokemonBusqueda.substring(1, pokemonBusqueda.length()).toLowerCase());

					Log registro = new Log(Juego.USER + datetime, "1", pokemonBusqueda.toLowerCase(),
							pokeBusqueda.getTipos().get(0).getNombre(), pokeBusqueda.getTipos().get(1).getNombre(),
							selectedValue.toLowerCase(), pokeSeleccion.getTipos().get(0).getNombre(),
							pokeSeleccion.getTipos().get(1).getNombre());
					Constantes.DBDynamo.insertarLogTienda(registro);
				} else {
					Log registro = new Log(Juego.USER + datetime, "2", selectedValue.toLowerCase(),
							pokeSeleccion.getTipos().get(0).getNombre(), pokeSeleccion.getTipos().get(1).getNombre());
					Constantes.DBDynamo.insertarLogTienda(registro);
				}
				int maxPokeIDActual = Constantes.DBNeo.pokemonMaxId();
				int pokeIDNuevo = maxPokeIDActual + 1;

				Constantes.DBNeo.nuevoPokemon(selectedValue, pokeIDNuevo);
				Constantes.DBNeo.enlacePokemonEntrenadorEspecie(pokeIDNuevo, Juego.USER);

				Random rand = new Random();
				int move1 = rand.nextInt(36);
				int move2 = 0;
				int move3 = 0;
				int move4 = 0;

				while (move2 == move1) {
					move2 = rand.nextInt(36);
				}
				while (move3 == move1 || move3 == move2) {
					move3 = rand.nextInt(36);
				}
				while (move4 == move1 || move4 == move2 || move4 == move3) {
					move4 = rand.nextInt(36);
				}
				Constantes.DBNeo.asignarAtaquesPokemon(pokeIDNuevo, move1, move2, move3, move4);
			} else {
				JOptionPane.showMessageDialog(null, "Ya tienes 4 pokemons, debes eliminar a uno de ellos",
						"Equipo completo", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			if (!e.getValueIsAdjusting()) {
				tipos.setVisible(true);
				selectedValue = (String) list.getSelectedValue();
				Pokemon poke = Constantes.DBDynamo.getElementByName(selectedValue);

				String sTipo1 = (poke.getTipos().get(0) != null && !poke.getTipos().get(0).getNombre().equals("none"))
						? poke.getTipos().get(0).getNombre()
						: "";
				String sTipo2 = (poke.getTipos().get(1) != null && !poke.getTipos().get(1).getNombre().equals("none"))
						? poke.getTipos().get(1).getNombre()
						: "";
				tipo1.setText(sTipo1);
				tipo2.setText(sTipo2);
				icono.setIcon(new ImageIcon(poke.getPng()));
				nombre.setText(poke.getEspecie());

			}
		} catch (Exception expection) {
		}
	}
}
