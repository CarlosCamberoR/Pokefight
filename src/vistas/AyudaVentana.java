package vistas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Objeto.Ataque;

public class AyudaVentana extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AyudaVentana(List<Ataque> ataques) {
		getContentPane().setLayout(null);

		setBounds(672, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Los ataques más potentes de tu pokemon contra tu rival son:");
		lblNewLabel.setBounds(6, 6, 438, 27);
		getContentPane().add(lblNewLabel);

		JLabel ataque1 = new JLabel(ataques.get(0).getNombre() + " - Potencia: " + ataques.get(0).getPotenciaTotal());
		ataque1.setBounds(16, 45, 386, 16);
		getContentPane().add(ataque1);

		JLabel ataque2 = new JLabel(ataques.get(1).getNombre() + " - Potencia: " + ataques.get(1).getPotenciaTotal());
		ataque2.setBounds(16, 73, 376, 16);
		getContentPane().add(ataque2);

		JLabel ataque3 = new JLabel(ataques.get(2).getNombre() + " - Potencia: " + ataques.get(2).getPotenciaTotal());
		ataque3.setBounds(16, 101, 386, 16);
		getContentPane().add(ataque3);

		JLabel ataque4 = new JLabel(ataques.get(3).getNombre() + " - Potencia: " + ataques.get(3).getPotenciaTotal());
		ataque4.setBounds(16, 132, 386, 16);
		getContentPane().add(ataque4);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
