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
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaPlay extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblFotoAlbum, lblnombreCancion, lblnombreArtista, lblInicio, lblBuscar;
	private JButton btnPlay, btnHome, btnRebobinar, btnBuscar, btnLogo, btnStop;
	private JProgressBar progressBar;
	private Timer timer;
	private int progressValue;
	private Clip clip = null;
	private int num = 0, pos = 0;
	private Dao dao;
	private Cancion cancion;
	
	
	public VentanaPlay(VPrincipal vPrincipal, Cancion cancion, Album album, boolean modal, Dao dao) {
		super(vPrincipal);
		this.cancion = cancion;
		setModal(modal);
		Pantalla(dao, cancion, album);
	}

	public void Pantalla(Dao dao, Cancion cancion, Album album) {
		this.dao = dao;
		
		File archivoSonido = new File("..\\RetoFinal\\Audio\\Wanda.wav");
	       AudioInputStream audio;
			try {
				audio = AudioSystem.getAudioInputStream(archivoSonido);
				clip  = AudioSystem.getClip();
		    	clip.open(audio);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblFotoAlbum = new JLabel("");
		lblFotoAlbum.setIcon(new ImageIcon(album.getFotoAlbum()));
		lblFotoAlbum.setBounds(429, 10, 373, 373);
		contentPanel.add(lblFotoAlbum);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(cancion.getDuracion());
		progressBar.setBounds(40, 475, 1155, 11);
		contentPanel.add(progressBar);
		
		lblnombreCancion = new JLabel(cancion.getNombreCancion());
		lblnombreCancion.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreCancion.setForeground(new Color(0, 0, 0));
		lblnombreCancion.setFont(new Font("Eras Light ITC", Font.BOLD, 52));
		lblnombreCancion.setBounds(429, 393, 373, 47);
		contentPanel.add(lblnombreCancion);
		
		lblnombreArtista = new JLabel("QUEVEDO");
		lblnombreArtista.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreArtista.setForeground(new Color(128, 128, 128));
		lblnombreArtista.setFont(new Font("Eras Light ITC", Font.BOLD, 28));
		lblnombreArtista.setBounds(429, 430, 373, 47);
		contentPanel.add(lblnombreArtista);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\play.png"));
		btnPlay.setBackground(new Color(255, 255, 255));
		btnPlay.setBounds(573, 496, 81, 80);
		btnPlay.setVisible(true);
		btnPlay.setOpaque(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusable(false);
		contentPanel.add(btnPlay);
		btnPlay.addActionListener(this);
		
		btnHome = new JButton("");
		btnHome.setBackground(new Color(64, 128, 128));
		btnHome.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casaInicio.png"));
		btnHome.setBounds(429, 635, 58, 55);
		btnHome.setOpaque(false);
		btnHome.setBorderPainted(false);
		btnHome.setFocusable(false);
		contentPanel.add(btnHome);
		btnHome.addActionListener(this);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(64, 128, 128));
		btnBuscar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\lupaBuscar.png"));
		btnBuscar.setBounds(744, 635, 58, 55);
		btnBuscar.setOpaque(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusable(false);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);
		
		btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon("..\\RetoFinal\\Img\\pause.png"));
		btnStop.setBackground(new Color(255, 255, 255));
		btnStop.setBounds(573, 496, 81, 80);
		btnStop.setVisible(false);
		btnStop.setOpaque(false);
		btnStop.setBorderPainted(false);
		btnStop.setFocusable(false);
		contentPanel.add(btnStop);
		btnStop.addActionListener(this);

		btnRebobinar = new JButton("");		
		btnRebobinar.setBackground(new Color(255, 255, 255));
		btnRebobinar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\rebobinar.png"));
		btnRebobinar.setBounds(429, 496, 81, 80);
		btnRebobinar.setOpaque(false);
		btnRebobinar.setBorderPainted(false);
		btnRebobinar.setFocusable(false);
		contentPanel.add(btnRebobinar);
		btnRebobinar.addActionListener(this);
		

		
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
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblInicio.setBounds(429, 688, 58, 24);
		contentPanel.add(lblInicio);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblBuscar.setBounds(726, 688, 94, 24);
		contentPanel.add(lblBuscar);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBounds(1181, 10, 56, 75);
		btnLogo.setOpaque(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusable(false);
		contentPanel.add(btnLogo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("..\\RetoFinal\\Img\\fondo1.gif"));
		lblNewLabel.setBounds(0, 0, 1257, 722);
		contentPanel.add(lblNewLabel);
		btnLogo.addActionListener(this);
	}

	protected void cero() {
		// TODO Auto-generated method stub
		clip.setFramePosition(0);
		progressValue = 0;
        progressBar.setValue(progressValue);
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
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnPlay)) {
			play();
			barraProgreso(cancion);	
		}
		if(e.getSource().equals(btnStop)) {
			stop();	
		}
		if(e.getSource().equals(btnRebobinar)) {
			cero();
		}
		if(e.getSource().equals(btnBuscar)) {
			irABuscar();
		}
		if(e.getSource().equals(btnLogo)) {
			irACerrarSesion();
		}
		if(e.getSource().equals(btnHome)) {
			irAPrincipal();
		}
	}

	private void irACerrarSesion() {
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
		ven.setVisible(true);
	}

	private void irAPrincipal() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}

	private void irABuscar() {
		this.setVisible(false);
		VBuscar ven = new VBuscar(this, true, dao);
		ven.setVisible(true);
	}
}
		
























