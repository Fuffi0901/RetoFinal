package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Cancion;
import modelo.Playlist;
import javax.swing.SwingConstants;

public class VverPlaylist extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dao dao;
	private JList list;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	private Playlist play;
	
	public VverPlaylist(VPrincipal vPrincipal, boolean b, Playlist play,Dao dao ) {
		this.dao = dao;
		this.play=play;
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(523, 61, 260, 202);
		lblNewLabel.setIcon(new ImageIcon(play.getFotoPlaylist()));
		contentPanel.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel(play.getNombrePlaylist());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(40, 240, 310, 44);
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 43));
		contentPanel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 319, 740, 368);
		contentPanel.add(scrollPane);

		list = new JList<>();
		list.setFont(new Font("Stencil", Font.PLAIN, 43));
		scrollPane.setColumnHeaderView(list);
		
		
		cargarLista();
	}
	
	private void cargarLista() {
		// TODO Auto-generated method stub
		canciones = dao.sacarCancionesPlaylist(1);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Cancion can : canciones) {
			//String foto=mostrarFoto(can.getCodCancion());
			listModel.addElement(new ImageIcon(mostrarFoto(can.getCodCancion()))+"  |  "+can.getNombreCancion());
		}
		list.setModel(listModel);
	}
	
	private String mostrarFoto(int codCancion) {
		// TODO Auto-generated method stub
		return dao.sacarFotoCancion(codCancion);
	}
}
