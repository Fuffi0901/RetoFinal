package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    private int numPlay, numAlbum, num = 1, numListPlay = 0, numListAlbum;
    private JTextField txtBuscar;
    private JButton btnSecreto;
    private JList<String> list;
    private ArrayList<Playlist> plays;
    private JScrollPane scrollPane;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
    private ArrayList<Album> albums;
    private JButton btnSiguientePlay;
    private JButton btnSiguienteAlbum;
    private JPanel panelAlbum;
    
    /**
	 * @wbp.parser.constructor
	 */
    
    public VPrincipal(VBuscar buscar, boolean modal, Dao dao) {
		super(buscar);
		setModal(modal);
		pantalla(dao);
	}
	
	public VPrincipal(VCerrar_Sesion vCerrar_Sesion, boolean modal, Dao dao) {
		super(vCerrar_Sesion);
		setModal(modal);
		pantalla(dao);
	}
	
	public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		setModal(modal);
		pantalla(dao);
	}
	
	public VPrincipal(VentanaPlay ventanaPlay, boolean modal, Dao dao) {
		super(ventanaPlay);
		setModal(modal);
		pantalla(dao);
	}
	
	public VPrincipal(VListaCanciones VlistaCanciones, boolean modal, Dao dao) {
		super(VlistaCanciones);
		setModal(modal);
		pantalla(dao);
	}

    public void pantalla(Dao dao) {
    	this.dao = dao;
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
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                escucharCancion();
            }
        });
        contentPanel.add(list);
        
        
        panelAlbum = new JPanel();
        panelAlbum.setBounds(70, 445, 1009, 100);
		contentPanel.add(panelAlbum);
		
        btnSiguientePlay = new JButton("New button");
        btnSiguientePlay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		numListPlay++;
                BotonesPlaylist();
        	}
        });
		btnSiguientePlay.setBounds(1089, 200, 100, 100);
		contentPanel.add(btnSiguientePlay);
		
		btnSiguienteAlbum = new JButton("New button");
		btnSiguienteAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numListAlbum++;
				LimpiarAlbumes();
		        BotonesAlbum();
			}
		});
		btnSiguienteAlbum.setBounds(1089, 445, 100, 100);
		contentPanel.add(btnSiguienteAlbum);

        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(208, 99, 846, 368);
        scrollPane.setVisible(false);
        contentPanel.add(scrollPane);
        
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

        numPlay = dao.sacarNumeroDePlayList();
        numAlbum = dao.sacarNumeroDeAlbum();

        lblPlayList = new JLabel("PlayLists");
        lblPlayList.setForeground(new Color(75, 75, 75));
        lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblPlayList.setBounds(70, 127, 242, 50);
        contentPanel.add(lblPlayList);

        lblAlbumes = new JLabel("Albumes");
        lblAlbumes.setForeground(new Color(75, 75, 75));
        lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblAlbumes.setBounds(70, 376, 242, 50);
        contentPanel.add(lblAlbumes);

        btnLogo = CrearBoton("", "..\\RetoFinal\\Img\\logoPequeña.png", 1141, 10);
        btnLogo.addActionListener(this);
        contentPanel.add(btnLogo);
       

        BotonesPlaylist();
        BotonesAlbum();
        
        JLabel fondo = new JLabel("") {
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

    private void BotonesPlaylist() {
        int[] playListButtonX = { 70, 210, 350, 490, 630, 770, 910, 1050 };
        num = dao.sacarNumeroDePlayList();
        plays = new ArrayList<>();
        for (int i = numListPlay; i < num; i++) {
            Playlist play = dao.sacarPlaylist(i+1);
            plays.add(play);
        }
        
        for (int i = numListPlay; i < Math.min(numPlay, 7); i++) {
        	JLabel lblLista = null;
            if (i < plays.size()) {
                Playlist play = plays.get(i);
                btnFotoPlay = CrearBoton("", plays.get(i).getFotoPlaylist(), playListButtonX[i], 200);
                btnFotoPlay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción al presionar el botón
                        menuPlay(play);
                    }
                });
                lblLista = new JLabel(plays.get(i).getNombrePlaylist());      	

            } else {
                btnFotoPlay = CrearBoton("", "", playListButtonX[i], 200);
                lblLista = new JLabel("Nombre Playlist");

            }
            contentPanel.add(btnFotoPlay);

            lblLista.setFont(new Font("Calibri", Font.PLAIN, 17));
            lblLista.setHorizontalAlignment(SwingConstants.CENTER);
            lblLista.setBounds(playListButtonX[i]-25, 305, 150, 32);
            lblLista.setForeground(Color.WHITE);
            contentPanel.add(lblLista);
        }
    }
    
    private void LimpiarAlbumes () {
    	for (Component component : contentPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JButton) {
                    contentPanel.remove(panel);
                    break;
                }
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void BotonesAlbum() {
    	int[] albumButtonX = { 70, 212, 350, 490, 630, 770, 910, 1050 };        
    	num = dao.sacarNumeroDeAlbum();
        albums = new ArrayList<>();
        for (int i = numListAlbum; i < num; i++) {
            Album album = dao.sacarAlbum(i+1);
            albums.add(album);
        }
        
        for (int i = numListAlbum; i < Math.min(numAlbum, 7); i++) {
            JLabel lblAlbum;
            if (i < albums.size()) {
            	Album album = albums.get(i);
            	btnFotoAlbum = CrearBoton("", albums.get(i).getFotoAlbum(), albumButtonX[i], 445);
                btnFotoAlbum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción que deseas realizar para este botón
                        menuAlbum(album);
                    }
                });

                lblAlbum = new JLabel(albums.get(i).getNombreAlbum());
            } else {
            	btnFotoAlbum = CrearBoton("", "", albumButtonX[i], 418);
                lblAlbum = new JLabel("Nombre Album");
            }
            panelAlbum.add(btnFotoAlbum);
            lblAlbum.setFont(new Font("Calibri", Font.PLAIN, 17));
            lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
            lblAlbum.setBounds(albumButtonX[i]-25, 550, 150, 32);
            lblAlbum.setForeground(Color.WHITE);
            panelAlbum.add(lblAlbum);
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
		VentanaPlay play = new VentanaPlay(this, can, true, dao);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogo) {
            irCerrar_Sesion();
        }
    }
    
    private void cargarLista() {
		// TODO Auto-generated method stub
		canciones = dao.sacarCanciones();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		ArrayList<Cancion> cancion = new ArrayList<Cancion> ();
		
		//introducir primero los que empiezen igual
		for (Cancion can : canciones) {
			if (can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+saberArtistas(can.getCodCancion()));
				list.setModel(listModel);
			}
		}
		//introducir las que tengan letras en comun
		for (Cancion can : canciones) {
			if(can.getNombreCancion().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+saberArtistas(can.getCodCancion()));
				list.setModel(listModel);
			}
		}
	}

    private String saberArtistas(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = dao.artistasPorCancion(cod);
		String artista = artistas.get(0).getNombreArtistico();
		
		if(artistas.size()>1) {
			for(int i = 1; i < artistas.size(); i++) {
				artista  = artista +", "+artistas.get(i).getNombreArtistico();
			}
		}
		return artista;
	}

    private void menuPlay(Playlist play) {
    	this.dispose();
        VListaCanciones ven = new VListaCanciones(play, this, true, dao);
        ven.setVisible(true);		
	}
    
	private void menuAlbum(Album album) {
		this.setVisible(false);
		VListaCanciones ven = new VListaCanciones(album, this,  true, dao);
        ven.setVisible(true);		
	}

    private void irCerrar_Sesion() {
        this.setVisible(false);
        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
        ven.setVisible(true);
    }
}