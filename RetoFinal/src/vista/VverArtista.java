package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Persona;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.JList;

public class VverArtista extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbTitulo;
	private Dao dao;
	private Artista art;
	private JButton btnUltimoAlbum;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnAtras, btnAleatorio;
	private ArrayList<Cancion> canciones;
	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 */
	public VverArtista(VPrincipal vPrincipal, boolean b, Dao dao, Artista art) {
		this.dao=dao;
		this.art=art;
		this.setModal(b);
		cogerArtista();
		canciones = dao.sacarCancionesArtista(art.getDni());
		
		setBackground(new Color(78, 78, 78));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(75, 75, 75));
		btnAtras.setIcon(new ImageIcon("..\\RetoFinal\\Img\\flechaAtras.png"));
		btnAtras.setBounds(10, 10, 36, 32);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusable(false);
		contentPanel.add(btnAtras);
		btnAtras.addActionListener(this);
		
		lbTitulo = new JLabel(art.getNombreArtistico());
		lbTitulo.setForeground(Color.BLACK);
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 95));
		lbTitulo.setBounds(10, 53, 657, 88);
		contentPanel.add(lbTitulo);
		
		JLabel lblNewLabel = new JLabel(art.getNombrePersona()+" "+art.getApellidoPersona()+"   |   Edad : "+art.getEdad());
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(20, 120, 647, 46);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(art.getEstilo().toString());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(30, 176, 468, 46);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ULTIMO LANZAMIENTO ");
		lblNewLabel_2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel_2.setBounds(801, 38, 417, 53);
		contentPanel.add(lblNewLabel_2);
		
		btnUltimoAlbum = new JButton(dao.sacarUltimaAlbum(art.getDni()).getNombreAlbum());
		btnUltimoAlbum.setForeground(new Color(255, 255, 255));
		btnUltimoAlbum.setBackground(new Color(64, 128, 128));
		btnUltimoAlbum.setBounds(485, 92, 733, 53);	
		btnUltimoAlbum.setHorizontalAlignment(SwingConstants.TRAILING);
		btnUltimoAlbum.setOpaque(false);
		btnUltimoAlbum.setBorderPainted(false);
		btnUltimoAlbum.setFocusable(false);
		btnUltimoAlbum.setFont(new Font("Century Schoolbook", Font.PLAIN, 59));
		btnUltimoAlbum.addActionListener(this);
		contentPanel.add(btnUltimoAlbum);
		
		list = new JList();
		list.setBounds(277, 291, 647, 398);
		list.setFont(new Font("Felix Titling", Font.PLAIN, 32));
		list.setFixedCellHeight(87);
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	escucharCancion();      
            }
			
        });
		contentPanel.add(list);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(277, 304, 647, 398);	
	    contentPanel.add(scrollPane);
	    
	    btnAleatorio = new JButton("");
		btnAleatorio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\aleatorio.png"));
		btnAleatorio.setBounds(854, 224, 70, 70);
		btnAleatorio.setOpaque(false);
		btnAleatorio.setBorderPainted(false);
		btnAleatorio.addActionListener(this);
		contentPanel.add(btnAleatorio);
		
		JLabel lblNewLabel_2_1 = new JLabel(" CANCIONES : ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel_2_1.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.WHITE));
		lblNewLabel_2_1.setBounds(277, 232, 271, 62);
		contentPanel.add(lblNewLabel_2_1);
		
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
		
		
		cargarLista();
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
		if(e.getSource().equals(btnUltimoAlbum)) {
			menuAlbum();
		}
		if(e.getSource().equals(btnAleatorio)) {
			cancionAleatoria();
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
	
	private void cargarLista() {
		// TODO Auto-generated method stub
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Cancion c:canciones) {
			listModel.addElement(c.getNombreCancion());
		}
		list.setModel(listModel);
	}
	private void cogerArtista() {
		// TODO Auto-generated method stub
		Persona per = dao.comprobarPersona(art.getDni());
		art.setDni(per.getDni());
		art.setNombrePersona(per.getNombrePersona());
		art.setApellidoPersona(per.getApellidoPersona());
		art.setEdad(per.getEdad());
		art.setPais(per.getPais());
		Artista artista = dao.comprobarArtista(art.getDni());
		art.setNombreArtistico(artista.getNombreArtistico());
		art.setCantaAutor(artista.isCantaAutor());
		art.setEstilo(artista.getEstilo());
	}
	
	 protected void escucharCancion() {
		 
	     String nombre = list.getSelectedValue().toString();
	        for (Cancion c : canciones) {
	            if (c.getNombreCancion().equalsIgnoreCase(nombre)) {
	            	 this.dispose();
	     			VentanaPlay play = new VentanaPlay(this, c,true, dao);
	     			play.setVisible(true);
	            }
	        }
	       
	    }
	
	 private void menuAlbum() {
			this.dispose();
			VListaCanciones ven = new VListaCanciones(dao.sacarUltimaAlbum(art.getDni()), this,  true, dao);
	        ven.setVisible(true);		
		}
	
}
