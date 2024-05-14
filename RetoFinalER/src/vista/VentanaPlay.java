package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JRadioButton;

public class VentanaPlay extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblFotoAlbum;
	private JLabel lblnombreCancion;
	private JLabel lblnombreArtista;
	private JButton btnPlay, btnHome, btnStop, btnBuscar, btnRebobinar;
	private JProgressBar progressBar;
	private Timer timer;
	private int progressValue;
	private Clip clip = null;
	private int num = 0;
	private int pos = 0;
	public VentanaPlay(Cancion cancion, Album album) {
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
		
		setBackground(new Color(0, 0, 64));
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
		lblnombreCancion.setForeground(new Color(255, 255, 255));
		lblnombreCancion.setFont(new Font("Arial", Font.BOLD, 52));
		lblnombreCancion.setBounds(429, 393, 373, 47);
		contentPanel.add(lblnombreCancion);
		
		lblnombreArtista = new JLabel("QUEVEDO");
		lblnombreArtista.setHorizontalAlignment(SwingConstants.CENTER);
		lblnombreArtista.setForeground(new Color(192, 192, 192));
		lblnombreArtista.setFont(new Font("Arial", Font.PLAIN, 28));
		lblnombreArtista.setBounds(429, 430, 373, 47);
		contentPanel.add(lblnombreArtista);
		
		btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
				barraProgreso(cancion);
			}
		});
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\play.png"));
		btnPlay.setBackground(new Color(64, 128, 128));
		btnPlay.setBounds(573, 496, 81, 80);
		btnPlay.setVisible(true);
		contentPanel.add(btnPlay);
		
		btnHome = new JButton("");
		btnHome.setBackground(new Color(1, 41, 15));
		btnHome.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casapene-removebg-preview.png"));
		btnHome.setBounds(483, 647, 58, 55);
		contentPanel.add(btnHome);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(1, 41, 15));
		btnBuscar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\lupa-removebg-preview (4).png"));
		btnBuscar.setBounds(694, 647, 58, 55);
		contentPanel.add(btnBuscar);
		
		btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		btnStop.setIcon(new ImageIcon("..\\RetoFinal\\Img\\pause.png"));
		btnStop.setBackground(new Color(64, 128, 128));
		btnStop.setBounds(573, 496, 81, 80);
		btnStop.setVisible(false);
		contentPanel.add(btnStop);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Programacion\\3 eva\\RetoFinal\\Img\\FondoFooter.jpg"));
		lblNewLabel.setBackground(new Color(0, 64, 0));
		lblNewLabel.setBounds(0, 636, 1245, 76);
		contentPanel.add(lblNewLabel);
		
		btnRebobinar = new JButton("");
		btnRebobinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cero();
			}
		});
		btnRebobinar.setBackground(new Color(64, 128, 128));
		btnRebobinar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\rebobinar.png"));
		btnRebobinar.setBounds(429, 496, 81, 80);
		contentPanel.add(btnRebobinar);
		
		
		
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progressValue += 1;
                progressBar.setValue(progressValue);
                if (progressValue >= cancion.getDuracion())
                    timer.stop();
            }
        });
		
	}

	protected void cero() {
		// TODO Auto-generated method stub
		clip.setFramePosition(0);
		progressValue = 0;
        progressBar.setValue(progressValue);
		stop();
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
}






















