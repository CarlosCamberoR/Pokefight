package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Pokemon.Constantes;
import Pokemon.Juego;

public class NoAmigosVentana extends JFrame implements ListSelectionListener, ActionListener {

	private JPanel contentPane;
	private Object[] noAmigos;
	private JLabel entrenadorSeleccionado;
	private JList list;
	private JScrollPane scrollPane;
	private JButton agregar;
	private JLabel mensajeSeleccion;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public NoAmigosVentana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 400, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 36, 239, 162);
		contentPane.add(scrollPane);

		noAmigos = Constantes.DBNeo.entrenadorNoAmigos(Juego.USER).toArray();
		list = new JList(noAmigos);
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);

		agregar = new JButton("Agregar");
		agregar.setBounds(333, 221, 94, 29);
		agregar.setVisible(false);
		agregar.addActionListener(this);
		contentPane.add(agregar);

		mensajeSeleccion = new JLabel("Entrenador seleccionado:");
		mensajeSeleccion.setBounds(18, 226, 158, 16);
		mensajeSeleccion.setVisible(false);
		contentPane.add(mensajeSeleccion);

		entrenadorSeleccionado = new JLabel("");
		entrenadorSeleccionado.setBounds(188, 226, 145, 16);
		entrenadorSeleccionado.setVisible(false);
		contentPane.add(entrenadorSeleccionado);

		lblNewLabel = new JLabel("Agrega a nuevos entrenadores a tu lista de amigos");
		lblNewLabel.setBounds(56, 8, 323, 16);
		contentPane.add(lblNewLabel);

		JLabel fondo = new JLabel("");
		fondo.setBounds(0, 0, 450, 272);
		fondo.setIcon(new ImageIcon("resources/fondoCubo.png"));
		contentPane.add(fondo);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			String selectedValue = (String) list.getSelectedValue();
			entrenadorSeleccionado.setText(selectedValue);
			entrenadorSeleccionado.setVisible(true);
			mensajeSeleccion.setVisible(true);
			agregar.setVisible(true);
			System.out.println("Opción seleccionada: " + selectedValue);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame actualVentana = (JFrame) SwingUtilities.getWindowAncestor(agregar);

		JButton botonPresionado = (JButton) e.getSource();
		if (botonPresionado == agregar) {
			Constantes.DBNeo.nuevoAmigo(Juego.USER, entrenadorSeleccionado.getText());
			DefaultListModel<String> listModel = Constantes.DBNeo.entrenadoresAmigos(Juego.USER);

			AmigosVentana.list.setModel(listModel);
			actualVentana.dispose();
		}

	}
}
