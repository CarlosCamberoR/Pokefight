package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.neo4j.driver.Record;

import Pokemon.Constantes;
import Pokemon.Juego;

public class RegistroVentana extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton entrar;
	private JTextField txtNombre;

	/**
	 * Create the frame.
	 */
	public RegistroVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		entrar = new JButton("Entrar");
		entrar.setBounds(171, 174, 81, 29);
		entrar.addActionListener(this);

		JLabel letrasSombra = new JLabel("Nombre de entrenador:");
		letrasSombra.setForeground(new Color(43, 115, 185));
		letrasSombra.setFont(new Font("Chalkboard SE", Font.BOLD, 20));
		letrasSombra.setBounds(105, 110, 246, 29);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setForeground(new Color(181, 181, 181));
		txtNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtNombre.setBounds(148, 142, 130, 26);
		txtNombre.setColumns(10);
		contentPane.add(entrar);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre de entrenador:");
		lblNewLabel_1.setForeground(new Color(255, 203, 5));
		lblNewLabel_1.setFont(new Font("Chalkboard SE", Font.BOLD, 20));
		lblNewLabel_1.setBounds(102, 107, 246, 29);
		contentPane.add(lblNewLabel_1);
		contentPane.add(letrasSombra);
		contentPane.add(txtNombre);
		contentPane.add(entrar);

		JLabel letrasPokemon = new JLabel("");
		letrasPokemon.setBounds(81, 6, 328, 100);
		letrasPokemon.setIcon(new ImageIcon("resources/pokemonInicio.png"));
		contentPane.add(letrasPokemon);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 450, 272);
		fondo.setIcon(new ImageIcon("resources/fondoInicio.png"));
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == entrar) {
			JFrame actualVentana = (JFrame) SwingUtilities.getWindowAncestor(entrar);

			Juego.USER = txtNombre.getText();

			try {
				boolean existe = false;
				String[] options = { "Sí", "No" };
				int seleccion = -1;

				if (Constantes.DBNeo.existeEntrenador(txtNombre.getText())) {
					System.out.println("ENTRENADOR EXISTENTE");
					existe = true;
				} else {

					seleccion = JOptionPane.showOptionDialog(null,
							"Este usuario no existe en la base de datos, ¿desea crear un usuario llamado '"
									+ txtNombre.getText() + "'?",
							"Seleccione una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
							options, options[0]);

				}

				if (existe || seleccion == 0) {
					if (seleccion == 0) {// CREAR NUEVO ENTRENADOR
						List<Record> id = Constantes.DBNeo.trainerMaxId();
						List<Record> nuevoEntrenador = Constantes.DBNeo.nuevoEntrenador(txtNombre.getText(),
								id.get(0).get("max(t.TrainerID)").asInt() + 1);
					}
					actualVentana.dispose();

					MenuInicioVentana menu = new MenuInicioVentana();
					menu.setVisible(true);
					contentPane.setVisible(false);
				}
			} finally {
			}

		}
	}
}
