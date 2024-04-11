package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modelo.Cancion;
import modelo.Playlist;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JList;
import java.awt.SystemColor;

public class VPlaylist extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

	public VPlaylist(Playlist play) {

		setBounds(100, 100, 1259, 749);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(play.getFotoPlaylist()));
		lblNewLabel.setBounds(523, 61, 260, 202);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(play.getNombrePlaylist());
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 43));
		lblNewLabel_1.setBounds(78, 302, 310, 44);
		contentPanel.add(lblNewLabel_1);

		JList list = new JList();
		list.setBounds(126, 682, 781, -282);
		contentPanel.add(list);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 22, 175, 162);
		contentPanel.add(scrollPane);

		cargarLista();

	}

	private void cargarLista() {
		
		// TODO Auto-generated method stub
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Cancion can : canciones) {
			String foto=mostrarFoto(can.getCodCancion());
			listModel.addElement(new ImageIcon(foto)+"   |   "+can.getNombreCancion());
		}
	}

	private String mostrarFoto(int codCancion) {
		// TODO Auto-generated method stub
		return null;
	}
}
