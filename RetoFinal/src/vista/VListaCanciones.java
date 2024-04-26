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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private JButton btnLogo, btnPlay, btnAleatorio, btnAtras;
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
		lblTitulo.setForeground(new Color(75, 75, 75));
		lblTitulo.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(195, 24, 779, 61);
		contentPanel.add(lblTitulo);
		
		lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon("..\\RetoFinal\\Img\\DondeQuiero.jpg"));
		lblFoto.setBounds(80, 24, 89, 89);
		contentPanel.add(lblFoto);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1173, 24, 61, 61);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		btnLogo.addActionListener(this);
		contentPanel.add(btnLogo);
		
		list = new JList();
		list.setBounds(80, 162, 1077, 476);
		contentPanel.add(list);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\playChiquito.png"));
		btnPlay.setBounds(940, 10, 70, 70);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusable(false);
		btnPlay.addActionListener(this);
		contentPanel.add(btnPlay);
		
		btnAleatorio = new JButton("");
		btnAleatorio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\aleatorio.png"));
		btnAleatorio.setBounds(1091, 10, 70, 70);
		btnAleatorio.setOpaque(false);
		btnAleatorio.setBorderPainted(false);
		btnAleatorio.setFocusable(false);
		btnAleatorio.addActionListener(this);
		contentPanel.add(btnAleatorio);
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(75, 75, 75));
		btnAtras.setIcon(new ImageIcon("..\\RetoFinal\\Img\\flechaAtras.png"));
		btnAtras.setBounds(10, 10, 36, 32);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusable(false);
		contentPanel.add(btnAtras);
		btnAtras.addActionListener(this);
		
		//crearLista(lista);
		
		JLabel fondo = new JLabel("") {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.dispose();
			}
		};
		fondo.setBounds(0, 0, 1256, 724);
		contentPanel.add(fondo);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnPlay) {
            play();
        }
		if(e.getSource().equals(btnLogo)) {
			volverVPrincipal();
		}
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
	}

	private void volverAtras() {
		// TODO Auto-generated method stub
		this.dispose();
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}

	private void volverVPrincipal() {
		this.setVisible(false);
        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
        ven.setVisible(true);
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
	    VentanaPlay ven = new VentanaPlay(this, cancion, true, dao);
	    ven.setVisible(true);		
	}
}
