package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Album;
import modelo.Cancion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VPrincipal extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnInicio;

	/**
	 * Create the dialog.
	 */
	public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal) {
		super(inicio_Sesion);
		setBackground(new Color(0, 0, 64));
		setModal(modal);
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 64));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPlayList = new JLabel("PlayLists");
		lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblPlayList.setBounds(70, 64, 242, 50);
		contentPanel.add(lblPlayList);
		
		JLabel lblAlbumes = new JLabel("Albumes");
		lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblAlbumes.setBounds(70, 339, 242, 50);
		contentPanel.add(lblAlbumes);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblInicio.setBounds(447, 679, 56, 24);
		contentPanel.add(lblInicio);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblBuscar.setBounds(647, 679, 78, 24);
		contentPanel.add(lblBuscar);
		
		btnInicio = new JButton("");
		btnInicio.setBackground(new Color(0, 0, 128));
		btnInicio.setIcon(new ImageIcon("C:\\Users\\1dam\\Downloads\\casapene-removebg-preview.png"));
		btnInicio.setBounds(447, 631, 47, 48);
		contentPanel.add(btnInicio);
		btnInicio.addActionListener(this);

		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBackground(new Color(0, 0, 128));
		btnBuscar.setIcon(new ImageIcon("C:\\Users\\1dam\\Downloads\\lupa-removebg-preview (4).png"));
		btnBuscar.setBounds(647, 622, 58, 57);
		contentPanel.add(btnBuscar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnInicio)) {
			entrarAplicacion();
		}		
	}

	private void entrarAplicacion() {
		// TODO Auto-generated method stub
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
		VentanaPlay ven = new VentanaPlay(cancion, album);
		ven.setVisible(true);
	}
}
