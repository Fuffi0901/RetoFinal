package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Canta;
import modelo.Playlist;
import modelo.Usuario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class VListaCanciones extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitulo, lblFoto;
	private JButton btnLogo, btnAleatorio, btnAtras;
	private Dao dao;
	private Cancion cancion;
	private Album album;
	private String ruta;
	private JList list;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	private JScrollPane scrollPane;
	private Object object;
	private ImageIcon iconoRedimensionado;
	/**
	 * @wbp.parser.constructor
	 */
	public VListaCanciones(Album album, VPrincipal ven, boolean modal, Dao dao,Usuario usuario) {
		super(ven);
		setModal(modal);
		this.album = album;
		VListaCanciones(album, null, album.getNombreAlbum(), album.getFotoAlbum(), dao);
		setLocationRelativeTo(null);
	}
	
	public VListaCanciones(Album album, VverArtista verArtista, boolean modal, Dao dao) {
		super(verArtista);
		setModal(modal);
		this.album = album;
		VListaCanciones(album, null, album.getNombreAlbum(), album.getFotoAlbum(), dao);
		setLocationRelativeTo(null);
	}
	
	public VListaCanciones(Playlist play, VPrincipal ven, boolean modal, Dao dao) {
		super(ven);
		setModal(modal);
		VListaCanciones(null,play,play.getNombrePlaylist(), play.getFotoPlaylist(), dao);
		setLocationRelativeTo(null);
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
	public void VListaCanciones(Album album,Playlist play , String nombre, String ruta, Dao dao) {
		this.dao = dao;
		
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(195, 10, 89, 89);
		
		if(album!=null) {
			canciones = dao.CancionesDeAlbum(album.getCodAlbum());
			
			ImageIcon icono = new ImageIcon(album.getFotoAlbum());
			Image imagen = icono.getImage();
			Image imagenRedimensionada = imagen.getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_SMOOTH);
			iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		}else {
			canciones = dao.CancionesDePlaylist(play.getCodPlaylist());
			
			ImageIcon icono = new ImageIcon(play.getFotoPlaylist());
			Image imagen = icono.getImage();
			Image imagenRedimensionada = imagen.getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_SMOOTH);
			iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		}
		
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
		
	
		lblFoto.setIcon(iconoRedimensionado);
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
		
		btnAleatorio = new JButton("");
		btnAleatorio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\aleatorio.png"));
		btnAleatorio.setBounds(940, 10, 70, 70);
		btnAleatorio.setOpaque(false);
		btnAleatorio.setBorderPainted(false);
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
		
	
		
		class CenteredTextRenderer extends DefaultListCellRenderer {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            label.setHorizontalAlignment(JLabel.CENTER);
	            return label;
	        }
	    }
        
		
		//crearLista(lista);
        list = new JList<>();
		list.setBounds(195, 121, 815, 553);
		
		list.setFont(new Font("Felix Titling", Font.PLAIN, 22));
		list.setFixedCellHeight(98);
		
		list.setCellRenderer(new CenteredTextRenderer());
		  list.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                escucharCancion();
	            }
	        });
		
		contentPanel.add(list);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(195, 121, 815, 553);
		contentPanel.add(scrollPane);
		
	
		
		JLabel fondo = new JLabel("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
		
		
		cargarLista();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnLogo)) {
			volverVPrincipal();
		}
		if(e.getSource().equals(btnAleatorio)) {
			cancionAleatoria();
		}
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
	}

	private void cancionAleatoria() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int numero = random.nextInt(canciones.size());
		Cancion can = canciones.get(numero);
		this.dispose();
		VentanaPlay ven = new VentanaPlay(this, can, true, dao);
		ven.setVisible(true);
		
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

	 private void cargarLista() {
			// TODO Auto-generated method stub
			DefaultListModel<String> listModel = new DefaultListModel<>();
			
			for (Cancion can:canciones) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+dao.funcionArtistas(can.getCodCancion()));
				list.setModel(listModel);
			}
		
		}
	 

	 protected void escucharCancion() {
	        Cancion can = new Cancion();
	        String nombre = list.getSelectedValue().toString();
	        int pos = nombre.indexOf("  ");
	        int posicion = nombre.indexOf("   |");
	        nombre = nombre.substring(pos + 2, posicion);
	        for (Cancion c : canciones) {
	            if (c.getNombreCancion().equalsIgnoreCase(nombre)) {
	                can = c;
	            }
	        }
	        this.dispose();
			VentanaPlay play = new VentanaPlay(this, can,true, dao);
			play.setVisible(true);
	    }
}
