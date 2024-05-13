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
import modelo.Usuario;

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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class VentanaPlay extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblFotoAlbum, lblnombreCancion, lblnombreArtista;
	private JButton btnPlay, btnRebobinar, btnLogo, btnStop, btnadelantar, btnatrasar;
	private JProgressBar progressBar;
	private Timer timer;
	private JButton btnAtras;
	private int progressValue;
	private Clip clip = null;
	private int num = 0, pos = 0;
	private Dao dao;
	private Cancion can;
	private Album album;
	private boolean play;
	
	
	/**
	 * @param nombre 
	 * @wbp.parser.constructor
	 */
	public VentanaPlay(VPrincipal vPrincipal, Cancion cancion, boolean modal, Dao dao) {
		super(vPrincipal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("..\\RetoFinal\\Img\\logoPequeña.png"));
		this.can = cancion;
		setModal(modal);
		Pantalla(dao, cancion);
		setLocationRelativeTo(null);
	}
	
	public VentanaPlay(VverArtista verArtista, Cancion cancion, boolean modal, Dao dao) {
		super(verArtista);
		setIconImage(Toolkit.getDefaultToolkit().getImage("..\\RetoFinal\\Img\\logoPequeña.png"));
		this.can = cancion;
		setModal(modal);
		Pantalla(dao, cancion);
		setLocationRelativeTo(null);
	}
	
	public VentanaPlay(VListaCanciones vListaCanciones, Cancion cancion,boolean modal, Dao dao) {
		super(vListaCanciones);
		setIconImage(Toolkit.getDefaultToolkit().getImage("..\\RetoFinal\\Img\\logoPequeña.png"));
		this.can = cancion;
		setModal(modal);
		Pantalla(dao, cancion);	
		setLocationRelativeTo(null);
	}



	public void Pantalla(Dao dao, Cancion cancion) {
		this.dao = dao;
		can = cancion;
		album = dao.sacarAlbum(can.getCodAlbum());

		File archivoSonido = new File(can.getAudio());
	       AudioInputStream audio;
			try {
				audio = AudioSystem.getAudioInputStream(archivoSonido);
				clip  = AudioSystem.getClip();
		    	clip.open(audio);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		setBackground(new Color(78, 78, 78));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblFotoAlbum = new JLabel("");
		
		lblFotoAlbum.setBounds(443, 10, 373, 373);
		contentPanel.add(lblFotoAlbum);
		
		ImageIcon icono = new ImageIcon(album.getFotoAlbum());
		Image imagen = icono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(lblFotoAlbum.getWidth(),lblFotoAlbum.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		lblFotoAlbum.setIcon(iconoRedimensionado);
		contentPanel.revalidate();
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(cancion.getDuracion());
		progressBar.setBounds(52, 475, 1155, 11);
		contentPanel.add(progressBar);
		
		lblnombreCancion = new JLabel(cancion.getNombreCancion());
		lblnombreCancion.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreCancion.setForeground(new Color(255, 255, 255));
		lblnombreCancion.setFont(new Font("Eras Light ITC", Font.BOLD, 52));
		lblnombreCancion.setBounds(374, 394, 525, 47);
		contentPanel.add(lblnombreCancion);
		
		lblnombreArtista = new JLabel(saberArtistas(cancion.getCodCancion()));
		lblnombreArtista.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreArtista.setForeground(new Color(210, 210, 210));
		lblnombreArtista.setFont(new Font("Eras Light ITC", Font.BOLD, 28));
		lblnombreArtista.setBounds(443, 429, 373, 47);
		contentPanel.add(lblnombreArtista);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\play.png"));
		btnPlay.setBackground(new Color(255, 255, 255));
		btnPlay.setBounds(589, 536, 81, 80);
		btnPlay.setVisible(true);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusable(false);
		contentPanel.add(btnPlay);
		btnPlay.addActionListener(this);
		
		btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon("..\\RetoFinal\\Img\\pause.png"));
		btnStop.setBackground(new Color(255, 255, 255));
		btnStop.setBounds(589, 536, 81, 80);
		btnStop.setVisible(false);
		btnStop.setOpaque(false);
		btnStop.setBorderPainted(false);
		btnStop.setFocusable(false);
		contentPanel.add(btnStop);
		btnStop.addActionListener(this);

		btnRebobinar = new JButton("");		
		btnRebobinar.setBackground(new Color(255, 255, 255));
		btnRebobinar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\rebobinar.png"));
		btnRebobinar.setBounds(1111, 494, 101, 68);
		btnRebobinar.setOpaque(false);
		btnRebobinar.setBorderPainted(false);
		btnRebobinar.setFocusable(false);
		contentPanel.add(btnRebobinar);
		btnRebobinar.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("ALBUM  : ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel.setBounds(52, 496, 219, 47);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNombreAlbum = new JLabel(album.getNombreAlbum());
		lblNombreAlbum.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNombreAlbum.setForeground(new Color(192, 192, 192));
		lblNombreAlbum.setBounds(52, 536, 228, 38);
		contentPanel.add(lblNombreAlbum);
		
		

		
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progressValue += 1;
                progressBar.setValue(progressValue);
                if (progressValue >= cancion.getDuracion()) {
                    timer.stop();
                }
            }
        });
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1181, 10, 56, 75);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		contentPanel.add(btnLogo);
		btnLogo.addActionListener(this);
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(75, 75, 75));
		btnAtras.setIcon(new ImageIcon("..\\RetoFinal\\Img\\flechaAtras.png"));
		btnAtras.setBounds(10, 10, 36, 32);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusable(false);
		contentPanel.add(btnAtras);
		btnAtras.addActionListener(this);
		
		btnadelantar = new JButton();
		btnadelantar.setBounds(680, 536, 81, 80);
		btnadelantar.addActionListener(this);
		ImageIcon icon = new ImageIcon("..\\RetoFinal\\Img\\adelantar.png");
		Image image = icon.getImage();
		Image imageRedimensionada = image.getScaledInstance(btnadelantar.getWidth(),btnadelantar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imageRedimensionada);
		btnadelantar.setIcon(iconRedimensionado);
		btnadelantar.setBackground(new Color(0, 0, 0));
		btnadelantar.setOpaque(false);
		btnadelantar.setBorderPainted(false);
		btnadelantar.setFocusable(false);
		contentPanel.add(btnadelantar);
		
		btnatrasar = new JButton("");
		btnatrasar.setBounds(496, 536, 81, 80);
		ImageIcon ico = new ImageIcon("..\\RetoFinal\\Img\\atrasar.png");
		Image imag = ico.getImage();
		Image imagRedimensionada = imag.getScaledInstance(btnadelantar.getWidth(),btnadelantar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icoRedimensionado = new ImageIcon(imagRedimensionada);
		btnatrasar.setIcon(icoRedimensionado);
		btnatrasar.setBackground(new Color(0, 0, 0));
		btnatrasar.setOpaque(false);
		btnatrasar.setBorderPainted(false);
		btnatrasar.setFocusable(false);
		btnatrasar.addActionListener(this);
		contentPanel.add(btnatrasar);
		
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

	protected void cero() {
		// TODO Auto-generated method stub
		pos = 0;
		clip.setFramePosition(0);
		progressValue = pos;
        progressBar.setValue(progressValue);
        num = 0;
		play();
	}
	
	protected void barraProgreso(Cancion cancion) {
		// TODO Auto-generated method stub
       progressBar.setValue(progressValue);
       timer.start();
	}
	
	protected void stop() {
		// TODO Auto-generated method stub
		if (clip != null && clip.isRunning()) {
            clip.stop();
            num = 0;
            progressValue = progressBar.getValue();
            timer.stop();
	       	btnPlay.setVisible(true);
	       	btnStop.setVisible(false);
	        pos = clip.getFramePosition();
	        play = false;
		}
	}
	public void play() {
		// TODO Auto-generated method stub
		if (num == 0) {
			clip.setFramePosition(pos);
	        clip.start();
	        num++;
	        btnPlay.setVisible(false);
	        btnStop.setVisible(true);	
	        play = true;
		}
		
	}
	
	private Album sacarAlbum() {
		// TODO Auto-generated method stub
		Album al = dao.sacarAlbum(can.getCodAlbum());
		
		return al;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnPlay)) {
			play();
			barraProgreso(can);	
		}
		if(e.getSource().equals(btnStop)) {
			stop();	
		}
		if(e.getSource().equals(btnRebobinar)) {
			cero();
		}
		if(e.getSource().equals(btnLogo)) {
			irACerrarSesion();
		}
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
		if(e.getSource().equals(btnadelantar)) {
			adelantarAudio();
		}
		if(e.getSource().equals(btnatrasar)) {
			atrasarAudio();
		}
	}
	
	private void volverAtras() {
		// TODO Auto-generated method stub
		stop();
		int pos2 = clip.getFramePosition();
		this.dispose();
		VPrincipal ven = new VPrincipal(this, true, dao, play, can, pos2);
		ven.setVisible(true);
	}
	
	private void adelantarAudio() {
		// TODO Auto-generated method stub
		if(clip.isActive()) {
			long currentPosition = clip.getMicrosecondPosition();
	        long newPosition = currentPosition + (10 * 1000000); // 10 segundos en microsegundos
	        clip.setMicrosecondPosition(newPosition);
	        progressValue=progressValue+10;
	        progressBar.setValue(progressValue);
	        parar();
		}
	}
	
	private void parar() {
		if (progressBar.getValue() == progressBar.getMaximum()) {
			stop();
		}
	}

	private void atrasarAudio() {
		// TODO Auto-generated method stub
		if(clip.isActive()) {
			long currentPosition = clip.getMicrosecondPosition();
			long newPosition = currentPosition - (10 * 1000000); // 10 segundos en microsegundos
			clip.setMicrosecondPosition(newPosition);
			progressValue=progressValue-10;
			progressBar.setValue(progressValue);
		}
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

	private void irACerrarSesion() {
		stop();	
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
		ven.setVisible(true);
	}
}
		
























