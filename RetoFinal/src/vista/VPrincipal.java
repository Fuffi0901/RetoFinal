package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Playlist;
import modelo.Usuario;

import java.awt.Toolkit;

public class VPrincipal extends JDialog implements ActionListener {

	/**
	 * Create the frame.
	 * @param dao2 
	 * @param b 
	 * @param b 
	 * @param vPrincipal
	 * @wbp.parser.constructor
	 */
    private static final long serialVersionUID = 1L;
    private JButton btnLogo, btnFotoAlbum, btnFotoPlay;
    private JLabel lblPlayList, lblAlbumes;
    private Dao dao;
    private final JPanel contentPanel = new JPanel();
    private int numPlay, numAlbum, num = 1, numListPlay = 0, numListAlbum, pos;
    private JTextField txtBuscar;
    private JButton btnSecreto;
    private JList<String> list;
    private ArrayList<Playlist> plays;
    private JScrollPane scrollPane;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
    private ArrayList<Album> albums;
    private JButton btnSiguientePlay;
    private JButton btnSiguienteAlbum;
    private JPanel panelAlbum = new JPanel();
    private Cancion cancion;
    private Clip clip;
    private boolean play = false;
    private JPanel panelPlay;
    private Playlist playlist;
    private Album album;
    private int numeroA = 0;
    private int numeroP = 0;
    private JButton btnAnteriorAlbum;
    private static Usuario usu;
    private JButton btnAnteriorPlaylist;
    private JButton btnCrearPlaylist;
    private ArrayList<Artista> artistas = new ArrayList<Artista>();
    /**
	 * @param play2 
     * @wbp.parser.constructor
	 */
	
	public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dam\\Desktop\\PGR\\3ª Eva\\RetoFinal\\Img\\logoPequeña.png"));
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VPrincipal(VentanaPlay ventanaPlay, boolean modal, Dao dao, boolean play, Cancion cancion, int pos2) {
		super(ventanaPlay);
		setModal(modal);
		this.play = play;
		this.pos = pos2;
		this.cancion = cancion;
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VPrincipal(VverArtista verArtista, boolean modal, Dao dao) {
		super(verArtista);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VPrincipal(VListaCanciones VlistaCanciones, boolean modal, Dao dao) {
		super(VlistaCanciones);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VPrincipal(VAnadirPlaylist playlist,  boolean modal, Dao dao) {
		super(playlist);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VPrincipal(VCerrar_Sesion vCerrar_Sesion, boolean modal, Dao dao) {
		super(vCerrar_Sesion);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}

	public void pantalla(Dao dao) {
    	this.dao = dao;
    	artistas = dao.sacarartistas();
    	cogerUsuario();
    	
    	
        setBackground(new Color(214, 214, 214));
        setBounds(100, 100, 1259, 749);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                btnSecreto.requestFocus();
                
        	}
        });
        contentPanel.setBackground(new Color(214, 214, 214));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        btnSecreto = new JButton("");
        btnSecreto.setForeground(new Color(75, 75, 75));
        btnSecreto.setBackground(new Color(75, 75, 75));
        btnSecreto.setBounds(0, 0, 1, 1);
    
        contentPanel.add(btnSecreto);
       
    	
		
		
        
        list = new JList<>();
        list.setFont(new Font("Felix Titling", Font.PLAIN, 32));
        list.setVisible(false);
        list.setFixedCellHeight(45);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	escogerLista();      
            }
			
        });
        contentPanel.add(list);
        
        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(208, 99, 846, 368);
        scrollPane.setVisible(false);
        contentPanel.add(scrollPane);
		
        
        btnSiguientePlay = new JButton("");
        btnSiguientePlay.setBackground(new Color(255, 255, 255));
        btnSiguientePlay.setBounds(1110, 217, 85, 100);
        btnSiguientePlay.setOpaque(false);
        btnSiguientePlay.setBorderPainted(false);
        btnSiguientePlay.setFocusable(false);
        ImageIcon icon = new ImageIcon("..\\RetoFinal\\Img\\btnSiguiente.png"); 
		Image image = icon.getImage();
		Image imageRedimensionada = image.getScaledInstance(btnSiguientePlay.getWidth(),btnSiguientePlay.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imageRedimensionada);
        btnSiguientePlay.setIcon(iconRedimensionado);
        btnSiguientePlay.setFont(new Font("Tahoma", Font.PLAIN, 28));
        btnSiguientePlay.addActionListener(this); 	
		
		contentPanel.add(btnSiguientePlay);
		
		btnSiguienteAlbum = new JButton("");
		btnSiguienteAlbum.setBackground(new Color(255, 255, 255));
		btnSiguienteAlbum.setOpaque(false);
		btnSiguienteAlbum.setBorderPainted(false);
		btnSiguienteAlbum.setFocusable(false);
		btnSiguienteAlbum.setIcon(iconRedimensionado);
		btnSiguienteAlbum.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnSiguienteAlbum.setBounds(1110, 460, 85, 100);
		btnSiguienteAlbum.setOpaque(false);
		btnSiguienteAlbum.addActionListener(this);
		contentPanel.add(btnSiguienteAlbum);

        
        
        panelPlay = new JPanel();
		panelPlay.setOpaque(false);
		panelPlay.setBounds(147, 203, 942, 143);
		contentPanel.add(panelPlay);
		panelPlay.setLayout(null);
		
		
        txtBuscar = new JTextField();
        txtBuscar.setText("Buscar");
        txtBuscar.setForeground(new Color(192, 192, 192));
        txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 38));
        txtBuscar.setBounds(208, 29, 846, 69);
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cargarLista();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cargarLista();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	 cargarLista();
            }
        });
        contentPanel.add(txtBuscar);
        txtBuscar.setColumns(10);
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar")) {
                    txtBuscar.setText("");
                    scrollPane.setVisible(true);
                    list.setVisible(true);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {

            	txtBuscar.setText("Buscar");
                scrollPane.setVisible(false);
                list.setVisible(false);
            }
        });

       
        
        
        btnCrearPlaylist = new JButton("...");
        btnCrearPlaylist.setForeground(new Color(128, 128, 128));
		btnCrearPlaylist.setBounds(307, 133, 75, 38);
		
		btnCrearPlaylist.setBackground(new Color(64, 128, 128));
		btnCrearPlaylist.setFont(new Font("Stencil", Font.PLAIN, 44));
		btnCrearPlaylist.setOpaque(false);
		btnCrearPlaylist.setBorderPainted(false);
		btnCrearPlaylist.setFocusable(false);
		btnCrearPlaylist.addActionListener(this);
		contentPanel.add(btnCrearPlaylist);
		
        lblPlayList = new JLabel(" PlayLists");
        lblPlayList.setForeground(new Color(75, 75, 75));
        lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblPlayList.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.gray));
        lblPlayList.setBounds(70, 139, 242, 38);
        contentPanel.add(lblPlayList);

        lblAlbumes = new JLabel(" Albumes");
        lblAlbumes.setForeground(new Color(75, 75, 75));
        lblAlbumes.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.gray));
        lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblAlbumes.setBounds(70, 388, 242, 38);
        contentPanel.add(lblAlbumes);
        
        
        btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setBounds(1181, 10, 56, 75);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		ImageIcon icono = new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png");
		Image imagen = icono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(btnLogo.getWidth(),btnLogo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		btnLogo.setIcon(iconoRedimensionado);
		contentPanel.add(btnLogo);
		btnLogo.addActionListener(this);
		
        //BotonesPlaylist();
		 panelAlbum = new JPanel();
         panelAlbum.setBounds(147, 452, 942, 143);
         panelAlbum.setOpaque(false);
 		 contentPanel.add(panelAlbum);        
 	 	 panelAlbum.setLayout(null);
		
		btnAnteriorPlaylist = new JButton("");
		btnAnteriorPlaylist.setBackground(new Color(255, 255, 255));
		btnAnteriorPlaylist.setBounds(52, 217, 85, 100);
		btnAnteriorPlaylist.setOpaque(false);
		btnAnteriorPlaylist.setBorderPainted(false);
		btnAnteriorPlaylist.setFocusable(false);
		ImageIcon icn = new ImageIcon("..\\RetoFinal\\Img\\btnAnterior.png");
		Image img = icn.getImage();
		Image imgeRedimensionada = img.getScaledInstance(btnAnteriorPlaylist.getWidth(),btnAnteriorPlaylist.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icnRedimensionado = new ImageIcon(imgeRedimensionada);
		btnAnteriorPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAnteriorPlaylist.setIcon(icnRedimensionado);
		
		btnAnteriorPlaylist.addActionListener(this);
		contentPanel.add(btnAnteriorPlaylist);
		
		btnAnteriorAlbum = new JButton("");
		btnAnteriorAlbum.setBackground(new Color(255, 255, 255));
		btnAnteriorAlbum.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAnteriorAlbum.setIcon(icnRedimensionado);
		btnAnteriorAlbum.setBounds(52, 460, 85, 100);
		btnAnteriorAlbum.setBorderPainted(false);
		btnAnteriorAlbum.setFocusable(false);
		btnAnteriorAlbum.setOpaque(false);
		btnAnteriorAlbum.addActionListener(this);
		contentPanel.add(btnAnteriorAlbum);
		
		BotonesAlbum();
		BotonesPlaylist();
		
		   JLabel fondo = new JLabel("") {
				/*
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
			
			fondo.setBounds(0, 0, 1275, 724);
			contentPanel.add(fondo);
			
		
			
	}
	
	  private void cogerUsuario() {
		// TODO Auto-generated method stub
		  Inicio_Sesion ini = new Inicio_Sesion(dao);
		  this.usu = ini.devolverUsuario();
	}

	@Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource().equals(btnLogo)) {
	            irCerrar_Sesion();
	        }
	    	if(e.getSource().equals(btnCrearPlaylist)) {
	    		irAnadirPlaylist();	
	    	}
	    	if(e.getSource().equals(btnSiguientePlay)) {
	    		numeroP++;
				LimpiarPlaylist();
	    		BotonesPlaylist();
	    	}
	    	if(e.getSource().equals(btnAnteriorPlaylist)) {
	    		numeroP--;
				LimpiarPlaylist();
	    		BotonesPlaylist();
	    	}
	    	if(e.getSource().equals(btnSiguienteAlbum)) {
	    		numeroA++;
				LimpiarAlbumes();
		        BotonesAlbum();	
	    	}
	    	if(e.getSource().equals(btnAnteriorAlbum)) {
	    		numeroA--;
				LimpiarAlbumes();
		        BotonesAlbum();
	    	}
	    }
	

	
	

	private void irAnadirPlaylist() {
		// TODO Auto-generated method stub
		this.dispose();
		VAnadirPlaylist ven = new VAnadirPlaylist(this, play, dao, usu);
		ven.setLocationRelativeTo(null);
		ven.setVisible(true);
	}

	private void BotonesPlaylist() {
		plays = dao.sacarPlaylistUsuario(usu.getDni());
		if(numeroP==0) {
    		btnAnteriorPlaylist.setVisible(false);
    	}else {
    		btnAnteriorPlaylist.setVisible(true);
    	}
		if(numeroP+6<plays.size()) {
			btnSiguientePlay.setVisible(true);
    	}else {
    		btnSiguientePlay.setVisible(false);
    	}
        int[] playListButtonX = {20, 180, 340, 500, 660, 820};
         
        	 for (int i = 0; i <  6; i++) {
                 JLabel lblLista;
                 if (i+numeroP < plays.size()) {
                	
                     btnFotoPlay = CrearBoton("", plays.get(i+numeroP).getFotoPlaylist(), playListButtonX[i], 0);
                     panelPlay.add(btnFotoPlay);
                     Playlist play = plays.get(i+numeroP);
                     btnFotoPlay.addActionListener(new ActionListener() {
     	                @Override
     	            	public void actionPerformed(ActionEvent e) {
     	                    menuPlay(play); // Acción específica para este botón
     	                }
                 	});
                     lblLista = new JLabel(plays.get(i+numeroP).getNombrePlaylist());
                 } else {
                     lblLista = new JLabel();
                 }
                 lblLista.setFont(new Font("Calibri", Font.PLAIN, 17));
                 lblLista.setHorizontalAlignment(SwingConstants.CENTER);
                 lblLista.setBounds(playListButtonX[i]-25, 103, 150, 32);
                 lblLista.setForeground(Color.WHITE);
                 panelPlay.add(lblLista);
             }
        //}
       
    }
	
	  
	  
	 
	  
    private void BotonesAlbum() {
    	//crearPanelAlbum();
    	albums = dao.sacarAlbumes();
    
    	if(numeroA==0) {
    		btnAnteriorAlbum.setVisible(false);
    	}else {
    		btnAnteriorAlbum.setVisible(true);
    	}
    	if(numeroA+6<albums.size()) {
    		btnSiguienteAlbum.setVisible(true);
    	}else {
    		btnSiguienteAlbum.setVisible(false);
    	}
    	int[] albumButtonX = { 20, 180, 340, 500, 660, 820};        
    	
    	
        for (int i = 0; i <  6; i++) {
            JLabel lblAlbum;
            if (i+numeroA < albums.size()) {
            	//btnSiguienteAlbum.setVisible(true);
            	btnFotoAlbum = CrearBoton("", albums.get(i+numeroA).getFotoAlbum(), albumButtonX[i], 0);
            	panelAlbum.add(btnFotoAlbum);
            	Album album = albums.get(i+numeroA);
            	btnFotoAlbum.addActionListener(new ActionListener() {
	                @Override
	            	public void actionPerformed(ActionEvent e) {
	                    menuAlbum(album); // Acción específica para este botón
	                }
            	});

                lblAlbum = new JLabel(albums.get(i+numeroA).getNombreAlbum());
            } else {
            	//btnSiguienteAlbum.setVisible(false);
                lblAlbum = new JLabel();
            }
            lblAlbum.setFont(new Font("Calibri", Font.PLAIN, 17));
            lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
            lblAlbum.setBounds(albumButtonX[i]-25, 103, 150, 32);
            lblAlbum.setForeground(Color.WHITE);
            panelAlbum.add(lblAlbum);
        }
    }
       
      
    private void LimpiarAlbumes () {
    	panelAlbum.removeAll();
    	this.albums=null;
    	contentPanel.revalidate();
    	contentPanel.repaint();
    }
    
    private void LimpiarPlaylist() {
    	panelPlay.removeAll();
    	this.plays=null;
    	contentPanel.revalidate();
    	contentPanel.repaint();
    }
    
    protected void escogerLista() {
		// TODO Auto-generated method stub
    	Artista art = new Artista();
    	artistas = dao.sacarartistas();
    	if(list.getSelectedIndex()!=-1) {
    		String nombre = list.getSelectedValue().toString();
        	if(nombre.contains("|")) {	
        		escucharCancion(nombre);
        	}else if(nombre.equalsIgnoreCase(" ") || nombre.equalsIgnoreCase("↓Artistas")  || nombre.equalsIgnoreCase("↓Canciones") ) {
        		
        	}else if(!nombre.contains("|")){
        		
        		for(Artista a: artistas) {
        			if(nombre.contains(a.getNombreArtistico())) {
        				pantallaArtista(a);
        			}
        		}
        		
        	}
        	
    	}
    
	}
    
    protected void pantallaArtista(Artista a) {
    	this.dispose();
		VverArtista ven = new VverArtista(this,true,dao,a);
		ven.setLocationRelativeTo(null);
		ven.setVisible(true);
    }
    
    protected void escucharCancion(String nom) {
        Cancion can = new Cancion();
        ArrayList<Cancion> canc = new ArrayList<Cancion>();
        String nombre = nom;
        int pos = nombre.indexOf("  ");
        int posicion = nombre.indexOf("   |");
        nombre = nombre.substring(pos + 2, posicion);
        for (Cancion c : canciones) {
            if (c.getNombreCancion().equalsIgnoreCase(nombre)) {
                can=c;
            }
        }
        canc.add(can);
        this.dispose();
		VentanaPlay play = new VentanaPlay(this, 0,true, dao,canc);
		play.setVisible(true);
    }
    

    private JButton CrearBoton(String text, String imagePath, int x, int y) {
        JButton button = new JButton("");
        button.setBackground(new Color(64, 128, 128));
        button.setBounds(x, y, 100, 100);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        ImageIcon icono = new ImageIcon(imagePath);
		Image imagen = icono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(button.getWidth(),button.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		button.setIcon(iconoRedimensionado);
		contentPanel.revalidate();
        return button;
        
    }

  
    
    private void cargarLista() {
		// TODO Auto-generated method stub
		canciones = dao.sacarCanciones();
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		boolean exi = false;
	
		listModel.addElement("<html><font size=9 face='Arial'>"+"↓Canciones");
		//introducir primero los que empiezen igual
		for (Cancion can : canciones) {
			if (can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+dao.funcionArtistas(can.getCodCancion()));
				list.setModel(listModel);
				exi = true;
			}
		}
		//introducir las que tengan letras en comun
		for (Cancion can : canciones) {
			if(can.getNombreCancion().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+dao.funcionArtistas(can.getCodCancion()));
				list.setModel(listModel);
				exi = true;
			}
		}
		listModel.addElement(" ");
		listModel.addElement("<html><font size=9 face='Arial'>"+"↓Artistas");

		for (Artista art : artistas) {
			if (art.getNombreArtistico().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+art.getNombreArtistico());
				list.setModel(listModel);
				exi = true;
			}
		}
		//introducir las que tengan letras en comun
		for (Artista art : artistas) {
			if(art.getNombreArtistico().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !art.getNombreArtistico().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+art.getNombreArtistico());
				list.setModel(listModel);
				exi = true;
			}
		}
		
		if (!exi) {
			list.setModel(listModel);
		}
	}

   

    private void menuPlay(Playlist play) {
    	this.dispose();
        VListaCanciones ven = new VListaCanciones(play, this, true, dao);
        ven.setVisible(true);		
	}
    
	private void menuAlbum(Album album) {
		this.dispose();
		VListaCanciones ven = new VListaCanciones(album, this,  true, dao,usu);
        ven.setVisible(true);		
	}

    private void irCerrar_Sesion() {
        this.setVisible(false);
        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
        ven.setVisible(true);
    }
}
