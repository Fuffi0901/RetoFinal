package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;

import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaPlay extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblFotoAlbum;
	private JLabel lblnombreCancion;
	private JLabel lblnombreArtista;
	private JButton btnPlay;
	private JButton btnStop;
	private JProgressBar progressBar;
	private Timer timer;
	private int progressValue;
	private Clip clip = null;
	private int num = 0;
	private JButton btnHome;
	private JButton btnBuscar;
	private JLabel lblInicio;
	private JLabel lblBuscar;
	private JButton btnLogo;
	private Dao dao;
	private Album album;
	private Cancion can;
	
	public VentanaPlay(VPrincipal vPrincipal, Cancion cancion,boolean modal, Dao dao) {
		super(vPrincipal);
		Pantalla(dao, cancion);
		setModal(modal);
	}

	public void Pantalla(Dao dao, Cancion cancion) {
		this.dao = dao;
		can = cancion;
		album = dao.sacarAlbum(can.getCodAlbum());
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		lblFotoAlbum = new JLabel("");
		
		lblFotoAlbum.setBounds(429, 10, 373, 373);
		contentPanel.add(lblFotoAlbum);
		
		ImageIcon icono = new ImageIcon(album.getFotoAlbum());
		Image imagen = icono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(lblFotoAlbum.getWidth(),lblFotoAlbum.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		lblFotoAlbum.setIcon(iconoRedimensionado);
		contentPanel.revalidate();
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(cancion.getDuracion());
		progressBar.setBounds(40, 475, 1155, 11);
		contentPanel.add(progressBar);
		
		lblnombreCancion = new JLabel(can.getNombreCancion());
		lblnombreCancion.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreCancion.setForeground(new Color(255, 255, 255));
		lblnombreCancion.setFont(new Font("Arial", Font.BOLD, 52));
		lblnombreCancion.setBounds(429, 393, 373, 47);
		contentPanel.add(lblnombreCancion);
		
		lblnombreArtista = new JLabel(saberArtistas(cancion.getCodCancion()));
		lblnombreArtista.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreArtista.setForeground(new Color(192, 192, 192));
		lblnombreArtista.setFont(new Font("Arial", Font.PLAIN, 28));
		lblnombreArtista.setBounds(429, 430, 373, 47);
		contentPanel.add(lblnombreArtista);
		
		btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		btnPlay.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\playBlanco.png"));
		btnPlay.setBackground(new Color(64, 128, 128));
		btnPlay.setBounds(592, 496, 65, 65);
		btnPlay.setVisible(true);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusable(false);
		contentPanel.add(btnPlay);
		
		btnHome = new JButton("");
		btnHome.setBackground(new Color(64, 128, 128));
		btnHome.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\casaInicio.png"));
		btnHome.setBounds(483, 635, 58, 55);
		btnHome.setOpaque(false);
		btnHome.setBorderPainted(false);
		btnHome.setFocusable(false);
		contentPanel.add(btnHome);
		btnHome.addActionListener(this);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(64, 128, 128));
		btnBuscar.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\lupaBuscar.png"));
		btnBuscar.setBounds(695, 635, 58, 55);
		contentPanel.add(btnBuscar);
		btnBuscar.setOpaque(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusable(false);
		btnBuscar.addActionListener(this);
		
		btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		btnStop.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\pause.png"));
		btnStop.setBackground(new Color(64, 128, 128));
		btnStop.setBounds(592, 496, 65, 65);
		btnStop.setVisible(false);
		contentPanel.add(btnStop);
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progressValue += 1;
                progressBar.setValue(progressValue);
                if (progressValue >= cancion.getDuracion()) {
                    timer.stop();
                 
                }
            }
        });
		
		lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblInicio.setBounds(485, 688, 56, 24);
		contentPanel.add(lblInicio);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblBuscar.setBounds(687, 688, 78, 24);
		contentPanel.add(lblBuscar);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1181, 10, 56, 75);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		contentPanel.add(btnLogo);
		
		JLabel lblNewLabel = new JLabel("ALBUM  : ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel.setBounds(40, 496, 219, 47);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNombreAlbum = new JLabel(album.getNombreAlbum());
		lblNombreAlbum.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNombreAlbum.setForeground(new Color(192, 192, 192));
		lblNombreAlbum.setBounds(40, 536, 228, 38);
		contentPanel.add(lblNombreAlbum);
		btnLogo.addActionListener(this);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\fondo1.gif"));
		lblFondo.setBounds(0, 0, 1257, 748);
		contentPanel.add(lblFondo);
	}

	private Album sacarAlbum() {
		// TODO Auto-generated method stub
		Album al = dao.sacarAlbum(can.getCodAlbum());
		
		return al;
	}

	protected void stop() {
		// TODO Auto-generated method stub
		if (clip != null && clip.isRunning()) {
            clip.stop();
            num = 0;
        	btnPlay.setVisible(true);
        	btnStop.setVisible(false);
		}
	}

	public void play() {
		// TODO Auto-generated method stub
		File archivoSonido = new File(can.getAudio());
        AudioInputStream audio;
		try {
			if (num == 0) {
				audio = AudioSystem.getAudioInputStream(archivoSonido);
				clip  = AudioSystem.getClip();
	        	clip.open(audio);
	        	clip.start();
	        	num++;
	        	btnPlay.setVisible(false);
	        	btnStop.setVisible(true);
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnHome)) {
			irAPrincipal();
		}
		if(e.getSource().equals(btnBuscar)) {
			irABuscar();
		}
		if(e.getSource().equals(btnLogo)) {
			irACerrarSesion();
		}
	}

	private void irACerrarSesion() {
		stop();
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
		ven.setVisible(true);
	}

	private void irAPrincipal() {
		stop();
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}

	private void irABuscar() {
		stop();
		this.setVisible(false);
		VBuscar ven = new VBuscar(this, true, dao);
		ven.setVisible(true);
	}
	
	private String saberArtistas(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = dao.artistasPorCancion(cod);
		String artista = artistas.get(0).getNombreArtistico();;
		
		if(artistas.size()>1) {
			for(int i = 1; i < artistas.size(); i++) {
				artista  = artista +", "+artistas.get(i).getNombreArtistico();
			}
		
		}
		return artista;
	}
}
		
























