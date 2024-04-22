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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VPrincipal extends JDialog implements ActionListener{
	
	private JButton btnBuscar;
	private JButton btnLogo;
	private JButton btnInicio;
	private JButton btnSecreto;
	private Dao dao;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VPrincipal(VBuscar buscar, boolean modal, Dao dao) {
		super(buscar);
		setModal(modal);
		Pantalla(dao);
	}
	public VPrincipal(VCerrar_Sesion vCerrar_Sesion, boolean modal, Dao dao) {
		super(vCerrar_Sesion);
		setModal(modal);
		Pantalla(dao);
	}
	
	public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		setModal(modal);
		Pantalla(dao);
	}
	
	public VPrincipal(VentanaPlay ventanaPlay, boolean modal, Dao dao) {
		super(ventanaPlay);
		setModal(modal);
		Pantalla(dao);
	}
	
	public VPrincipal(Dao dao, VAlbum vAlbum, boolean modal) {
		super(vAlbum);
		setModal(modal);
		Pantalla(dao);
		// TODO Auto-generated constructor stub
	}
	public void Pantalla(Dao dao) {
		this.dao = dao;
		
		setBackground(new Color(64, 128, 128));
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPlayList = new JLabel("PlayLists");
		lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblPlayList.setBounds(70, 64, 242, 50);
		contentPanel.add(lblPlayList);
		
		JLabel lblAlbumes = new JLabel("Albumes");
		lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblAlbumes.setBounds(70, 339, 242, 50);
		contentPanel.add(lblAlbumes);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblInicio.setBounds(447, 679, 56, 24);
		contentPanel.add(lblInicio);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblBuscar.setBounds(647, 679, 78, 24);
		contentPanel.add(lblBuscar);
		
		
		btnInicio = new JButton("");
		btnInicio.setBackground(new Color(64, 128, 128));
		btnInicio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casaInicio.png"));
		btnInicio.setBounds(447, 631, 47, 48);
		btnInicio.addActionListener(this);
		contentPanel.add(btnInicio);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(64, 128, 128));
		btnBuscar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\lupaBuscar.png"));
		btnBuscar.setBounds(647, 622, 58, 57);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);
		
		btnLogo = new JButton("");
		btnLogo.setForeground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setBounds(1181, 10, 56, 75);
		contentPanel.add(btnLogo);
		btnLogo.addActionListener(this);
		
		btnSecreto = new JButton("");
		btnSecreto.setBackground(new Color(64, 128, 128));
		btnSecreto.setBounds(322, 157, 108, 62);
		contentPanel.add(btnSecreto);
		btnSecreto.addActionListener(this);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\fondo1.gif"));
		lblFondo.setBounds(0, 0, 1257, 748);
		contentPanel.add(lblFondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnBuscar)){
			cambiarABuscar();
		}
		if(e.getSource().equals(btnLogo)) {
			irCerrar_Sesion();
		}
		if(e.getSource().equals(btnSecreto)) {
			irVentanaPlay();
		}
		if(e.getSource().equals(btnInicio)) {
			meterAlbum();
		}
	}

	private void meterAlbum() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		VAlbum ven = new VAlbum(dao, this, true);
		ven.setVisible(true);
	}
	private void irVentanaPlay() {
		Cancion cancion = new Cancion();
		Album album = new Album();
		album.setCodAlbum(1);
		album.setFotoAlbum("..\\RetoFinal\\Img\\DondeQuiero.jpg");
		album.setNombreAlbum("Donde Quiero Estar");
		cancion.setDuracion(220);
		cancion.setCodCancion(1);
		cancion.setNombreCancion("WANDA");
		cancion.setAudio("..\\RetoFinal\\Audio\\Wanda.wav");
		cancion.setCodAlbum(1);
		
		this.setVisible(false);
		VentanaPlay ven = new VentanaPlay(this, cancion,true, dao);
		ven.setVisible(true);
	}
	private void irCerrar_Sesion() {
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(dao, this, true);
		ven.setVisible(true);
	}
	private void cambiarABuscar() {
		this.setVisible(false);
		VBuscar ven = new VBuscar(this, true, dao);
		ven.setVisible(true);
	}
}
