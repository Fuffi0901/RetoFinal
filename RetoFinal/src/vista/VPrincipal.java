package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Album;
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
    private JButton btnBuscar, btnLogo, btnInicio, btnSecreto, btnFotoAlbum;
    private JLabel lblPlayList, lblAlbumes, lblInicio, lblBuscar, lblAlbum;
    private Dao dao;
    private final JPanel contentPanel = new JPanel();
    private int numPlay, numAlbum;
    private ArrayList<Playlist> plays;
    private ArrayList<Album> albums;

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

    public void pantalla(Dao dao) {
    	this.dao = dao;
    	plays = (ArrayList<Playlist>) dao.getAllPlaylists();
        setBackground(new Color(64, 128, 128));
        setBounds(100, 100, 1259, 749);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(64, 128, 128));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        numPlay = dao.sacarNumeroDePlayList();
        numAlbum = dao.sacarNumeroDeAlbum();

        lblPlayList = new JLabel("PlayLists");
        lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblPlayList.setBounds(70, 64, 242, 50);
        contentPanel.add(lblPlayList);

        lblAlbumes = new JLabel("Albumes");
        lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblAlbumes.setBounds(70, 339, 242, 50);
        contentPanel.add(lblAlbumes);

        lblInicio = new JLabel("Inicio");
        lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
        lblInicio.setBounds(447, 679, 56, 24);
        contentPanel.add(lblInicio);

        lblBuscar = new JLabel("Buscar");
        lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
        lblBuscar.setBounds(647, 679, 78, 24);
        contentPanel.add(lblBuscar);

        btnInicio = createButton("", "..\\RetoFinal\\Img\\casaInicio.png", 447, 631);
        btnInicio.addActionListener(this);
        contentPanel.add(btnInicio);

        btnBuscar = createButton("", "..\\RetoFinal\\Img\\lupaBuscar.png", 647, 622);
        btnBuscar.addActionListener(this);
        contentPanel.add(btnBuscar);

        btnLogo = createButton("", "..\\RetoFinal\\Img\\logoPequeña.png", 1181, 10);
        btnLogo.addActionListener(this);
        contentPanel.add(btnLogo);

        btnSecreto = new JButton("");
        btnSecreto.setBackground(new Color(64, 128, 128));
        btnSecreto.setBounds(600, 10, 108, 62);
        btnSecreto.addActionListener(this);
        contentPanel.add(btnSecreto);

        createPlayListButtons();
        createAlbumButtons();

        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\fondo1.gif"));
        lblFondo.setBounds(0, 0, 1257, 748);
        
        contentPanel.add(lblFondo);
    }

    private void createPlayListButtons() {
        int[] playListButtonX = { 70, 210, 350, 490, 630, 770, 910, 1050 };
        
        for (int i = 0; i < Math.min(numPlay, 8); i++) {
            Playlist play = null; // Creamos la instancia de Playlist dentro del bucle
            if (plays != null && i < plays.size()) { // Verificamos si la lista de playlists no es nula y si hay suficientes elementos en ella
                play = dao.consultaPlaylist(plays.get(i).getCodPlaylist()); // CORREGIDO: Pasamos el código de playlist correcto al método consultaPlayList()
            }
            
            JButton btnFotoPlay;
            if (play != null) { // Verificamos si se encontró una playlist
                btnFotoPlay = createButton("", play.getFotoPlaylist(), playListButtonX[i], 135);
            } else {
                btnFotoPlay = createButton("", "", playListButtonX[i], 135);
            }
            contentPanel.add(btnFotoPlay);
            
            JLabel lblLista;
            if (play != null) {
                lblLista = new JLabel(play.getNombrePlaylist());
            } else {
                lblLista = new JLabel("Nombre Playlist");
            }
            lblLista.setFont(new Font("Calibri", Font.PLAIN, 20));
            lblLista.setHorizontalAlignment(SwingConstants.CENTER);
            lblLista.setBounds(playListButtonX[i], 255, 100, 32);
            contentPanel.add(lblLista);
        }
    }
    private void createAlbumButtons() {
        int[] albumButtonX = { 70, 212, 350, 490, 630, 770, 910, 1050 };
        for (int i = 0; i < Math.min(numAlbum, 8); i++) {
        	if (albums != null) {
                btnFotoAlbum = createButton("", albums.get(i).getFotoAlbum(), albumButtonX[i], 418);
        	} else {
                btnFotoAlbum = createButton("", "", albumButtonX[i], 418);
        	}
            contentPanel.add(btnFotoAlbum);
            if (albums != null) {
                lblAlbum = new JLabel(albums.get(i).getNombreAlbum());
            } else {
                lblAlbum = new JLabel("Nombre Album");
            }
            lblAlbum.setFont(new Font("Calibri", Font.PLAIN, 20));
            lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
            lblAlbum.setBounds(albumButtonX[i], 538, 100, 32);
            contentPanel.add(lblAlbum);
        }
    }

    private JButton createButton(String text, String imagePath, int x, int y) {
        JButton button = new JButton("");
        button.setBackground(new Color(64, 128, 128));
        button.setIcon(new ImageIcon(imagePath));
        button.setBounds(x, y, 100, 100);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            cambiarABuscar();
        }
        if (e.getSource() == btnLogo) {
            irCerrar_Sesion();
        }
        if (e.getSource() == btnSecreto) {
            irVentanaPlay();
        }
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
        VentanaPlay ven = new VentanaPlay(this, cancion, album, true, dao);
        ven.setVisible(true);
    }

    private void irCerrar_Sesion() {
        this.setVisible(false);
        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
        ven.setVisible(true);
    }

    private void cambiarABuscar() {
        this.setVisible(false);
        VBuscar ven = new VBuscar(this, true, dao);
        ven.setVisible(true);
    }
}