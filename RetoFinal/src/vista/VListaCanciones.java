package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Album;
import modelo.Cancion;
import modelo.Canta;
import modelo.Playlist;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class VListaCanciones extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitulo, lblFoto;
	private JButton btnLogo, btnAleatorio, btnPlay;
	private JList list;
	private Dao dao;
	private Cancion cancion;
	private String ruta;
	
	/**
	 * @wbp.parser.constructor
	 */
	public VListaCanciones(Album album, VPrincipal ven, boolean modal, Dao dao) {
		super(ven);
		setModal(modal);
		VListaCanciones(album.getListaCanciones(), album.getNombreAlbum(), album.getFotoAlbum(), dao);
	}
	
	public VListaCanciones(Playlist play, VPrincipal ven, boolean modal, Dao dao) {
		super(ven);
		setModal(modal);
		VListaCanciones(play.getListaCanciones(), play.getNombrePlaylist(), play.getFotoPlaylist(), dao);
	}
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 * @param dao 
	 * @param string2 
	 * @param string 
	 */
	/**
	 * @param lista 
	 * @wbp.parser.constructor
	 */
	public void VListaCanciones(Map<Integer, Cancion> lista, String nombre, String ruta, Dao dao) {
		this.dao = dao;
		setBackground(new Color(78, 78, 78));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblTitulo = new JLabel(nombre);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(195, 24, 779, 61);
		contentPanel.add(lblTitulo);
		
		lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon("..\\RetoFinal\\Img\\DondeQuiero.jpg"));
		lblFoto.setBounds(80, 24, 89, 89);
		contentPanel.add(lblFoto);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\playChiquito.png"));
		btnPlay.setBounds(1001, 24, 61, 61);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
        btnPlay.setFocusable(false);
		contentPanel.add(btnPlay);
		
		btnAleatorio = new JButton("");
		btnAleatorio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\aleatorio.png"));
		btnAleatorio.setBounds(1086, 24, 71, 61);
		btnAleatorio.setOpaque(false);
		btnAleatorio.setBorderPainted(false);
        btnAleatorio.setFocusable(false);
		contentPanel.add(btnAleatorio);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1173, 24, 61, 61);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		contentPanel.add(btnLogo);
		
		list = new JList();
		list.setBounds(80, 162, 1077, 476);
		contentPanel.add(list);
		
		crearLista(lista);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnLogo) {
            irCerrar_Sesion();
        }
		if (e.getSource() == btnPlay) {
            play();
        }
	}

	private void crearLista(Map<Integer, Cancion> lista) {
		JButton boton = new JButton();
		String artista;
		Canta canta = new Canta();
	    for (Map.Entry<Integer, Cancion> can : lista.entrySet()) {
	    	if(cancion.getCodCancion() == canta.getCodCancion()) {
	    		
	    	}
	    	boton.setText(can.getValue().getNombreCancion());
	    	list.add(boton);
		}
		
	}

	
	
	  private void play() {
		this.setVisible(false);
	    VentanaPlay ven = new VentanaPlay(this, cancion, ruta, true, dao);
	    ven.setVisible(true);		
	}

	private void irCerrar_Sesion() {
	        this.setVisible(false);
	        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
	        ven.setVisible(true);
	  }
	  
}
