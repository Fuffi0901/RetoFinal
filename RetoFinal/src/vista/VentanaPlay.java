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

public class VentanaPlay extends JDialog {

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

	public VentanaPlay(Cancion cancion, Album album) {
		
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
			}
		});
		btnPlay.setIcon(new ImageIcon("..\\RetoFinal\\Img\\playBlanco.png"));
		btnPlay.setBackground(new Color(64, 128, 128));
		btnPlay.setBounds(592, 496, 65, 65);
		btnPlay.setVisible(true);
		contentPanel.add(btnPlay);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.setIcon(new ImageIcon("..\\RetoFinal\\Img\\Imagen3.png"));
		btnNewButton_1.setBounds(163, 787, 81, 82);
		contentPanel.add(btnNewButton_1);
		
		JButton btnHome = new JButton("");
		btnHome.setBackground(new Color(1, 41, 15));
		btnHome.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casapene-removebg-preview.png"));
		btnHome.setBounds(483, 647, 58, 55);
		contentPanel.add(btnHome);
		
		JButton btnBuscar = new JButton("");
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
		btnStop.setBounds(592, 496, 65, 65);
		btnStop.setVisible(false);
		contentPanel.add(btnStop);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Programacion\\3 eva\\RetoFinal\\Img\\FondoFooter.jpg"));
		lblNewLabel.setBackground(new Color(0, 64, 0));
		lblNewLabel.setBounds(0, 636, 1245, 76);
		contentPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	btnNewButton.setEnabled(false);
                progressValue = 0;
                progressBar.setValue(progressValue);
                timer.start();
            }
        });
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progressValue += 1;
                progressBar.setValue(progressValue);
                if (progressValue >= cancion.getDuracion()) {
                    timer.stop();
                    btnNewButton.setEnabled(true);
                }
            }
        });
		btnNewButton.setBounds(93, 158, 85, 21);
		contentPanel.add(btnNewButton);
		
		
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
		File archivoSonido = new File("..\\RetoFinal\\Audio\\Wanda.wav");
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
}

	/*public void comienzoCancion(Cancion cancion) {
		timer = new Timer();
        progressValue += 1;
        progressBar.setValue(progressValue);
        if (progressValue >= cancion.getDuracion()) {
            timer.stop();
        }
 
	}
}
		// TODO Auto-generated method stub
		while (progressBar.getValue() < progressBar.getMaximum()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			progressBar.setValue(progressBar.getValue() + 1);
		}
	*/
		
























