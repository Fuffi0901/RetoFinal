package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Album;
import modelo.Playlist;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VListaCanciones extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblFondo, lblTitulo, lblFoto;
	private JButton btnLogo, btnAleatorio, btnPlay;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VListaCanciones(Album album, VPrincipal ven, boolean modal, Dao dao) {
		super(ven);
		setModal(modal);
		VListaCanciones(album.getNombreAlbum(), album.getFotoAlbum(), dao);
	}
	
	public VListaCanciones(Playlist play, VPrincipal ven, boolean modal, Dao dao) {
		super(ven);
		setModal(modal);
		VListaCanciones(play.getNombrePlaylist(), play.getFotoPlaylist(), dao);
	}
	
	
	public void VListaCanciones(String nombre, String ruta, Dao dao) {
		setBounds(100, 100, 1258, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1181, 10, 56, 75);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		contentPanel.add(btnLogo);
		
		lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon("..\\RetoFinal\\Img\\DondeQuiero.jpg"));
		lblFoto.setBounds(20, 20, 61, 61);
		contentPanel.add(lblFoto);
		
		lblTitulo = new JLabel("Donde Quiero Estar");
		lblTitulo.setFont(new Font("Stencil", Font.PLAIN, 35));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(91, 24, 747, 61);
		contentPanel.add(lblTitulo);
		
		
		
		btnAleatorio = new JButton("");
		btnAleatorio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\aleatorio.png"));
		btnAleatorio.setBounds(1025, 24, 61, 61);
		btnAleatorio.setOpaque(false);
		btnAleatorio.setBorderPainted(false);
        btnAleatorio.setFocusable(false);
		contentPanel.add(btnAleatorio);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\playPeque.png"));
		btnPlay.setBounds(887, 24, 61, 61);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
        btnPlay.setFocusable(false);
		contentPanel.add(btnPlay);
		
		lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Stencil", Font.PLAIN, 25));
		lblFondo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\fondo1.gif"));
		lblFondo.setBounds(0, 0, 1254, 722);
		contentPanel.add(lblFondo);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
