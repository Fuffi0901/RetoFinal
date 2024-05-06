package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Estilo;
import modelo.Persona;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;


public class VverAdmin extends JDialog implements ActionListener{

	//paneles + extras
	private final JPanel contentPanel = new JPanel();
	private JPanel padPrincipal;
	private JPanel padCancion;
	private JPanel padArtista;
	private JPanel padAlbum;
	private JTabbedPane tabbedPane;
	private Dao dao;
	//cosas de canciones
	private JTextField textNombre;
	private JTable table;
	private JButton btnArchivos;
	private JComboBox comboAlbum;
	private JButton btnCrear;
	private JTextField textAudio;
	private JComboBox comboCanciones;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancion;
	//cosas de albumes
	
	private JButton btnVolver, btnAlbum;
	private JFileChooser fc;
	private File file;
	private String carpetaFinalAudio = "..\\RetoFinal\\Audio\\";
	private String carpetaFinalImg = "..\\RetoFinal\\Img\\";
	private JFrame ven;
	private JLabel lblAudio;
	private JTextField textNombreAlbum;
	private JTextField textFoto;
	private JTextField textFecha;
	private JButton btnIntroducir;
	private JComboBox comboAlbumA;
	private JButton btnBorrar, btnModificarA;
	private JButton btnArchivos_1;
	private JLabel lblTitulo_1;
	
	//Artista
	private JTextField textDni;
	private JTextField textNombreArt;
	private JTextField textApellidoArt;
	private JTextField textPaisArt;
	private JTextField textEdadArt;
	private JTextField textNombreArtisticoArt;
	private JCheckBox checkboxArt;
	private JComboBox comboEstiloArt;
	private JButton btnAltaArtista, btnModificarArt;
	private JComboBox comboBorrarArt;
	private JButton btnBorrarArt;
	private JLabel lblTitulo_2;
	private JLabel lblArtista_1;
	private JButton btnGestionArtistas;
	

	/**
	 * Create the dialog.
	 * @param dao
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VverAdmin(Inicio_Sesion inicio_Sesion, boolean b, Dao dao) {
		this.setModal(b);
		this.dao=dao;
		setBackground(new Color(73, 197, 250));	
		//PANELES
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1257, 724);
		contentPanel.add(tabbedPane);
		
		padPrincipal = new JPanel();
		tabbedPane.addTab("Menu Admin", null, padPrincipal, null);
		padPrincipal.setBackground(new Color(64, 128, 128));
		padPrincipal.setLayout(null);

		padAlbum = new JPanel();
		tabbedPane.addTab("Gestion Albumes", null, padAlbum, null);
		padAlbum.setLayout(null);
		padAlbum.setBackground(new Color(64, 128, 128));

		//MENU PRINCIPAL
		btnCancion = new JButton("Gestion Canciones");
		btnCancion.setFont(new Font("Arial", Font.BOLD, 20));
		btnCancion.setBounds(486, 142, 245, 101);
		btnCancion.addActionListener(this);
		padPrincipal.add(btnCancion);
		
		btnAlbum = new JButton("Gestion Albumes");
		btnAlbum.setFont(new Font("Arial", Font.BOLD, 20));
		btnAlbum.setBounds(87, 142, 245, 101);
		btnAlbum.addActionListener(this);
		padPrincipal.add(btnAlbum);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBounds(901, 529, 245, 101);
		btnVolver.addActionListener(this);
		padPrincipal.add(btnVolver);
		
		//MENU ALBUM
		textNombreAlbum= new JTextField();
		textNombreAlbum.setBounds(157, 218, 278, 46);
		padAlbum.add(textNombreAlbum);
		textNombreAlbum.setColumns(10);
		
		textFoto = new JTextField();
		textFoto.setColumns(10);
		textFoto.setBounds(157, 356, 278, 46);
		padAlbum.add(textFoto);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(157, 489, 278, 46);
		padAlbum.add(textFecha);
		
		JLabel lblFoto = new JLabel("Foto :");
		lblFoto.setForeground(new Color(255, 255, 255));
		lblFoto.setFont(new Font("Arial", Font.PLAIN, 27));
		lblFoto.setBounds(157, 300, 195, 46);
		padAlbum.add(lblFoto);
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 27));
		lblFecha.setBounds(157, 433, 195, 46);
		padAlbum.add(lblFecha);
		
		btnIntroducir = new JButton("Introducir");
		btnIntroducir.setFont(new Font("Arial", Font.BOLD, 20));
		btnIntroducir.setBounds(1039, 20, 187, 61);
		padAlbum.add(btnIntroducir);
		
		comboAlbumA = new JComboBox();
		comboAlbumA.setBounds(597, 218, 278, 46);
		comboAlbumA.addActionListener(this);
		padAlbum.add(comboAlbumA);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 20));
		btnBorrar.setBounds(1039, 164, 187, 61);
		padAlbum.add(btnBorrar);
		btnIntroducir.addActionListener(this);
		btnBorrar.addActionListener(this);
		
		lblTitulo_1 = new JLabel("GESTION ALBUMES");
		lblTitulo_1.setForeground(Color.WHITE);
		lblTitulo_1.setFont(new Font("Arial", Font.BOLD, 40));
		lblTitulo_1.setBounds(36, 20, 451, 61);
		padAlbum.add(lblTitulo_1);
		
		btnModificarA = new JButton("Modificar");
		btnModificarA.addActionListener(this);
		btnModificarA.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarA.setBounds(1039, 92, 187, 61);
		padAlbum.add(btnModificarA);
			
		JLabel lblNombre_1 = new JLabel("Nombre  :");
		lblNombre_1.setForeground(Color.WHITE);
		lblNombre_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombre_1.setBounds(157, 160, 195, 46);
		padAlbum.add(lblNombre_1);
			
		btnArchivos_1 = new JButton("...");
		btnArchivos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de imagenes","jpg");
				fc.setFileFilter(filtro);
				
				int res = fc.showOpenDialog(ven);				
				if(res != JFileChooser.CANCEL_OPTION){
					file = fc.getSelectedFile();
					if(file == null || file.getName().equals("")) {
					}
					
				}		
				try {
					Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(new File(carpetaFinalImg, file.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING );
					textFoto.setText(file.getAbsolutePath());	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnArchivos_1.setBounds(445, 356, 46, 46);
		padAlbum.add(btnArchivos_1);
		
		JLabel lblAlbumes = new JLabel("Albumes  :");
		lblAlbumes.setForeground(Color.WHITE);
		lblAlbumes.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAlbumes.setBounds(597, 160, 197, 46);
		padAlbum.add(lblAlbumes);
					
		JLabel fondo3 = new JLabel("") {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.dispose();
			}
		};
		fondo3.setBounds(0, 0, 1256, 724);
		padAlbum.add(fondo3);	
		DefaultComboBoxModel<Estilo> modeloCombo = new DefaultComboBoxModel<>(Estilo.values());;
		
		padCancion = new JPanel();
		tabbedPane.addTab("Gestion Canciones", null, padCancion, null);
		padCancion.setLayout(null);
		
			//CANCIONES	

			JLabel lblTitulo = new JLabel("GESTION CANCIONES");
			lblTitulo.setForeground(new Color(255, 255, 255));
			lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
			lblTitulo.setBounds(36, 20, 451, 61);
			padCancion.add(lblTitulo);
			
			JLabel lblNombre = new JLabel("Nombre  :");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Arial", Font.PLAIN, 27));
			lblNombre.setBounds(157, 163, 197, 51);
			padCancion.add(lblNombre);
			
			textNombre = new JTextField();
			textNombre.setColumns(10);
			textNombre.setBounds(157, 223, 278, 46);
			padCancion.add(textNombre);
			
			JLabel lblAlbum = new JLabel("Album  :");
			lblAlbum.setForeground(Color.WHITE);
			lblAlbum.setFont(new Font("Arial", Font.PLAIN, 27));
			lblAlbum.setBounds(157, 293, 197, 51);
			padCancion.add(lblAlbum);
			
			JLabel lblArtista = new JLabel("Artistas  :");
			lblArtista.setForeground(Color.WHITE);
			lblArtista.setFont(new Font("Arial", Font.PLAIN, 27));
			lblArtista.setBounds(605, 160, 197, 51);
			padCancion.add(lblArtista);
			
			table = new JTable();
			table.setBounds(159, 525, 431, 177);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			padCancion.add(table);
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(605, 221, 407, 123);
			padCancion.add(scrollPane);
			
			comboAlbum = new JComboBox();
			comboAlbum.setMaximumRowCount(30);
			comboAlbum.setBounds(157, 353, 278, 46);
			padCancion.add(comboAlbum,BorderLayout.CENTER);
			
			btnCrear = new JButton("Introducir");
			btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
			btnCrear.setBounds(1039, 20, 187, 61);
			padCancion.add(btnCrear);
			btnCrear.addActionListener(this);
			
			textAudio = new JTextField();
			textAudio.setText("");
			textAudio.setBounds(157, 483, 278, 46);
			padCancion.add(textAudio);
			textAudio.setColumns(10);
			
     
			
			btnEliminar = new JButton("Borrar");
			btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
			btnEliminar.setBounds(1039, 160, 187, 61);
			btnEliminar.addActionListener(this);
			padCancion.add(btnEliminar);
			
			JLabel lblCanciones = new JLabel("Canciones  :");
			lblCanciones.setForeground(Color.WHITE);
			lblCanciones.setFont(new Font("Arial", Font.PLAIN, 27));
			lblCanciones.setBounds(571, 30, 197, 51);
			padCancion.add(lblCanciones);
			
			comboCanciones = new JComboBox();
			comboCanciones.setBounds(780, 28, 232, 51);
			comboCanciones.addActionListener(this);
			padCancion.add(comboCanciones);
			
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificar.setBounds(1039, 90, 187, 61);
		btnModificar.addActionListener(this);
		padCancion.add(btnModificar);
		
		lblAudio = new JLabel("Audio :");
		lblAudio.setForeground(Color.WHITE);
		lblAudio.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAudio.setBounds(157, 423, 197, 51);
		padCancion.add(lblAudio);
		
		
		btnArchivos = new JButton("...");
		btnArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo multimedia","wav");
				fc.setFileFilter(filtro);
				
				int res = fc.showOpenDialog(ven);				
				if(res != JFileChooser.CANCEL_OPTION){
					file = fc.getSelectedFile();
					if(file == null || file.getName().equals("")) {
					}
					
				}		
				try {
					Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(new File(carpetaFinalAudio, file.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING );
					textAudio.setText(file.getAbsolutePath());	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnArchivos.setBounds(441, 482, 46, 46);
		padCancion.add(btnArchivos);
		
		padArtista = new JPanel();
		tabbedPane.addTab("Gestion Artistas", null, padArtista, null);
		padArtista.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dni:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel.setBounds(101, 195, 252, 46);
		padArtista.add(lblNewLabel);
		
		JLabel lblNombreArt = new JLabel("Nombre:");
		lblNombreArt.setForeground(new Color(255, 255, 255));
		lblNombreArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombreArt.setBounds(101, 309, 252, 46);
		padArtista.add(lblNombreArt);
		
		JLabel lblApellidopersona = new JLabel("Apellido:");
		lblApellidopersona.setForeground(new Color(255, 255, 255));
		lblApellidopersona.setFont(new Font("Arial", Font.PLAIN, 27));
		lblApellidopersona.setBounds(101, 421, 252, 46);
		padArtista.add(lblApellidopersona);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setForeground(new Color(255, 255, 255));
		lblPais.setFont(new Font("Arial", Font.PLAIN, 27));
		lblPais.setBounds(439, 195, 252, 46);
		padArtista.add(lblPais);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(new Color(255, 255, 255));
		lblEdad.setFont(new Font("Arial", Font.PLAIN, 27));
		lblEdad.setBounds(439, 309, 252, 46);
		padArtista.add(lblEdad);
		
		JLabel lblNombreartistico = new JLabel("Nombre Artistico:");
		lblNombreartistico.setForeground(new Color(255, 255, 255));
		lblNombreartistico.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombreartistico.setBounds(439, 423, 252, 43);
		padArtista.add(lblNombreartistico);
		
		JLabel lblCantaAutor = new JLabel("Canta autor:");
		lblCantaAutor.setForeground(new Color(255, 255, 255));
		lblCantaAutor.setFont(new Font("Arial", Font.PLAIN, 27));
		lblCantaAutor.setBounds(778, 333, 160, 43);
		padArtista.add(lblCantaAutor);
		
		JLabel lblEstilo = new JLabel("Estilo:");
		lblEstilo.setForeground(new Color(255, 255, 255));
		lblEstilo.setFont(new Font("Arial", Font.PLAIN, 27));
		lblEstilo.setBounds(778, 387, 160, 43);
		padArtista.add(lblEstilo);
		
		checkboxArt = new JCheckBox("");
		checkboxArt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxArt.setBounds(944, 333, 69, 43);
		padArtista.add(checkboxArt);
		
		textDni = new JTextField();
		textDni.setBounds(101, 252, 278, 46);
		padArtista.add(textDni);
		textDni.setColumns(10);
		
		textNombreArt = new JTextField();
		textNombreArt.setColumns(10);
		textNombreArt.setBounds(101, 366, 278, 46);
		padArtista.add(textNombreArt);
		
		textApellidoArt = new JTextField();
		textApellidoArt.setColumns(10);
		textApellidoArt.setBounds(101, 478, 278, 46);
		padArtista.add(textApellidoArt);
		
		textPaisArt = new JTextField();
		textPaisArt.setColumns(10);
		textPaisArt.setBounds(439, 252, 278, 46);
		padArtista.add(textPaisArt);
		
		textEdadArt = new JTextField();
		textEdadArt.setColumns(10);
		textEdadArt.setBounds(439, 366, 278, 46);
		padArtista.add(textEdadArt);
		
		textNombreArtisticoArt = new JTextField();
		textNombreArtisticoArt.setColumns(10);
		textNombreArtisticoArt.setBounds(439, 477, 281, 47);
		padArtista.add(textNombreArtisticoArt);
		
		comboEstiloArt = new JComboBox();
		comboEstiloArt.setFont(new Font("Arial", Font.PLAIN, 20));
		comboEstiloArt.setModel(modeloCombo);
		comboEstiloArt.setBounds(895, 395, 160, 30);
		comboEstiloArt.setSelectedIndex(-1);
		comboEstiloArt.addActionListener(this);
		padArtista.add(comboEstiloArt);
		
		btnAltaArtista = new JButton("Introducir");
		btnAltaArtista.setFont(new Font("Arial", Font.BOLD, 20));
		btnAltaArtista.setBounds(1039, 20, 187, 61);
		btnAltaArtista.addActionListener(this);
		padArtista.add(btnAltaArtista);
		
		comboBorrarArt = new JComboBox();
		comboBorrarArt.setBounds(776, 28, 232, 51);
		comboBorrarArt.addActionListener(this);
		padArtista.add(comboBorrarArt);
		
		btnBorrarArt = new JButton("Borrar");
		btnBorrarArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnBorrarArt.setBounds(1039, 160, 187, 61);
		padArtista.add(btnBorrarArt);
		btnBorrarArt.addActionListener(this);
		
		lblTitulo_2 = new JLabel("GESTION ARTISTA");
		lblTitulo_2.setForeground(Color.WHITE);
		lblTitulo_2.setFont(new Font("Arial", Font.BOLD, 40));
		lblTitulo_2.setBounds(36, 20, 451, 61);
		padArtista.add(lblTitulo_2);
		
		lblArtista_1 = new JLabel("Artista:");
		lblArtista_1.setForeground(Color.WHITE);
		lblArtista_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblArtista_1.setBounds(610, 28, 197, 51);
		padArtista.add(lblArtista_1);
		
		btnModificarArt = new JButton("Modificar");
		btnModificarArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarArt.setBounds(1039, 90, 187, 61);
		padArtista.add(btnModificarArt);
		btnModificarArt.addActionListener(this);
		
		btnGestionArtistas = new JButton("Gestion Artistas");
		btnGestionArtistas.addActionListener(this);
		btnGestionArtistas.setFont(new Font("Arial", Font.BOLD, 20));
		btnGestionArtistas.setBounds(901, 142, 245, 101);
		padPrincipal.add(btnGestionArtistas);
		
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
		padPrincipal.add(fondo);

		JLabel fondo2 = new JLabel("") {
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		        g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
		        g2d.fillRect(0, 0, getWidth(), getHeight());
		        g2d.dispose();
		    }
		};
		fondo2.setBounds(0, 0, 1256, 724);
		padCancion.add(fondo2);    

		JLabel fondo4 = new JLabel("") {
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		        g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
		        g2d.fillRect(0, 0, getWidth(), getHeight());
		        g2d.dispose();
		    }
		};
		fondo4.setBounds(0, 0, 1256, 724);
		padArtista.add(fondo4);		
		
		cargarComboArtista();
		cargarTabla();
		cargarComboAlbum();
		cargarComboAlbumA();
		cargarComboCancion();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCancion)) {
			 tabbedPane.setSelectedIndex(2); 
		}
		if(e.getSource().equals(btnCrear)) {
			crearCancion();
		}
		if(e.getSource().equals(btnGestionArtistas)) {
			 tabbedPane.setSelectedIndex(3); 
		}
		if(e.getSource().equals(btnEliminar)) {
			eliminarCancion();
		}
		if(e.getSource().equals(btnModificar)) {
			modificarCancion();
		}
		if(e.getSource().equals(comboCanciones)) {
			cargarDatos();
		}
		if(e.getSource().equals(btnIntroducir)) {
			meterAlbum();
		}
		if(e.getSource().equals(comboAlbumA)) {
			cargarDatosAlbum();
		}
		if(e.getSource().equals(btnModificarA)) {
			modificarAlbum();
		}
		if(e.getSource().equals(btnBorrar)) {
			borrarAlbum();
		}
		if(e.getSource().equals(btnVolver)) {
			volver();
		}
		if(e.getSource().equals(btnAlbum)) {
			 tabbedPane.setSelectedIndex(1); 
		}
		if(e.getSource().equals(btnAltaArtista)) {
			altaArtista();
		}
		if(e.getSource().equals(btnModificarArt)) {
			modificarArtista();
		}
		if(e.getSource().equals(btnBorrarArt)) {
			borrarArtista();
		}
		if(e.getSource().equals(comboBorrarArt)) {
			cargarDatosArtista();
		}
	}
	
	

	private void cargarDatosArtista() {
		if(comboBorrarArt.getSelectedIndex()!=-1) {
			int pos = comboBorrarArt.getSelectedIndex();
			ArrayList<Persona> personas = dao.sacarPersonas();
			ArrayList<Artista> artistas = dao.sacarArtista();
			textDni.setText(artistas.get(pos).getDni());
			textDni.setEnabled(false);
			for (Persona p: personas) {
				if (p.getDni().equalsIgnoreCase(artistas.get(pos).getDni())) {
					textNombreArt.setText(p.getNombrePersona());
					textApellidoArt.setText(p.getApellidoPersona());
					textPaisArt.setText((p.getPais()));
					textEdadArt.setText(""+(p.getEdad()));
				}
			}
			textNombreArtisticoArt.setText((artistas.get(pos).getNombreArtistico()));
			checkboxArt.setSelected(artistas.get(pos).isCantaAutor());
			for (int i = 0; i < comboEstiloArt.getItemCount(); i++) {
	            Estilo item = (Estilo) comboEstiloArt.getItemAt(i);
	            if (item.toString().equalsIgnoreCase(String.valueOf(artistas.get(pos).getEstilo()))) {
	            	comboEstiloArt.setSelectedIndex(i);
	            }
	         }
			btnAltaArtista.setEnabled(false);
		}else {
			btnAltaArtista.setEnabled(true);
			textDni.setEnabled(true);
			textDni.setText("");
			textNombreArt.setText("");
			textApellidoArt.setText("");
			textPaisArt.setText("");
			textEdadArt.setText("");
			textNombreArtisticoArt.setText("");
			checkboxArt.setSelected(false);
			comboEstiloArt.setSelectedIndex(-1);
		}		
	}


	private void cargarComboArtista() {
		// TODO Auto-generated method stub
		comboBorrarArt.removeAllItems();
		ArrayList<Artista> artistas = dao.sacarartistas();
		for(Artista a:artistas) {
			comboBorrarArt.addItem(a.getNombreArtistico());
		}
		comboBorrarArt.setSelectedIndex(-1);
	}
	
	private void borrarArtista() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = dao.sacarartistas();
		int pos = comboBorrarArt.getSelectedIndex();
		dao.borrarArtista(artistas.get(pos).getDni());
		JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : ","",JOptionPane.INFORMATION_MESSAGE);
		comboBorrarArt.setSelectedIndex(-1);	
		cargarComboArtista();
	}


	private void altaArtista() {
		// TODO Auto-generated method stub
		boolean cantaAutor;
		if(checkboxArt.isSelected()) {
			cantaAutor=true;
		}else {
			cantaAutor=false;
		}
		dao.registrarPersona(textDni.getText(),textNombreArt.getText(),textApellidoArt.getText(),textPaisArt.getText(),Integer.valueOf(textEdadArt.getText()));
		dao.registrarArtista(textDni.getText(),textNombreArtisticoArt.getText(),cantaAutor,comboEstiloArt.getSelectedItem().toString());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRERCAMENTE");		
	}


	

	private void cargarComboAlbumA() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = dao.sacarAlbumes();
		for(Album a:albumes) {
			comboAlbumA.addItem(a.getCodAlbum()+" | "+a.getNombreAlbum());
		}
		comboAlbumA.setSelectedIndex(-1);		
	}


	private void modificarCancion() {
		// TODO Auto-generated method stub
		cogerArtistas();
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		String can = comboCanciones.getSelectedItem().toString();
		int donde = can.indexOf(" |");
		can = can.substring(0, donde);
		String cod = comboAlbum.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		String textoOriginal = textAudio.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Audio\\\\");
		if(validar()) {
			dao.modificarCancion(Integer.parseInt(can), sacarDuracionC() , textNombre.getText(), textoModificado,codAlbum);
			dao.eliminarCanta(Integer.parseInt(can));
			for (Artista art:cogerArtistas()) {
				dao.insertarCanta(art.getDni(),Integer.parseInt(can));
			}
			comboCanciones.setSelectedIndex(-1);
		}
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboCancion();

	}
	private void modificarArtista() {
		dao.modificarPersona(textDni.getText(), textNombreArt.getText(), textApellidoArt.getText(), textPaisArt.getText(), Integer.parseInt(textEdadArt.getText()));
		dao.modificarArtista(textDni.getText(), textNombreArtisticoArt.getText(), comboEstiloArt.getSelectedItem().toString(), checkboxArt.isSelected());
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboArtista();
	}

	private void modificarAlbum() {
		// TODO Auto-generated method stub
		int donde=comboAlbumA.getSelectedItem().toString().indexOf(" |");
		String textoOriginal = textFoto.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Img\\\\");
		dao.modificarAlbum(comboAlbumA.getSelectedItem().toString().substring(0, donde),textNombreAlbum.getText(),textoModificado,textFecha.getText());
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboAlbum();		
	}
	
	private int sacarDuracionC() {
		// TODO Auto-generated method stub
		File audioFile = new File(textAudio.getText());
		Clip clip;
		int durationInSeconds=0;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(audioFile));
			long clipLengthInFrames = clip.getFrameLength();
            float frameRate = clip.getFormat().getFrameRate();
			durationInSeconds =(int)(clipLengthInFrames / frameRate);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return durationInSeconds;
	}


	private void cargarDatos() {
		// TODO Auto-generated method stub
		
		if(comboCanciones.getSelectedIndex()!=-1) {
			ArrayList<Cancion> canciones = dao.sacarCanciones();
			int pos = comboCanciones.getSelectedIndex();
			String can = comboCanciones.getSelectedItem().toString();
			int donde = can.indexOf(" |");
			can = can.substring(0, donde);
			Cancion cancion=null;
			for(Cancion c:canciones) {
				if(c.getCodCancion()==Integer.parseInt(can))
					cancion=c;
			}
			for (int i = 0; i < comboAlbum.getItemCount(); i++) {
	            String item = (String) comboAlbum.getItemAt(i);
	            int p = item.indexOf(" |");
	            item = item.substring(0, p);
	            if (item.equalsIgnoreCase(String.valueOf(cancion.getCodAlbum()))) {
	            	comboAlbum.setSelectedIndex(i);
	            }
	         }
			btnCrear.setEnabled(false);
		
			textAudio.setText(canciones.get(pos).getAudio());
			textNombre.setText(canciones.get(pos).getNombreCancion());
			seleccionarArtistas();
			
			
			
		}else {
			btnCrear.setEnabled(true);
			textAudio.setText("");
			textNombre.setText(null);
			comboAlbum.setSelectedIndex(-1);
			table.clearSelection();
		}
	}
	
	private void borrarAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = dao.sacarAlbumes();
		int pos = comboAlbumA.getSelectedIndex();
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar Borando el album?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.borrarAlbum(albumes.get(pos).getCodAlbum());
			JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : "+textNombreAlbum.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
			comboAlbumA.setSelectedIndex(-1);
			
		}else {
			JOptionPane.showMessageDialog(null, "Se mantiene la cancion : "+textNombreAlbum.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
		}
		cargarComboAlbum();
	}

	private void meterAlbum() {
		// TODO Auto-generated method stub
		
		String textoOriginal = textAudio.getText();
		String textoModificado = textoOriginal.replaceAll(".*\\\\", "..\\\\RetoFinal\\\\Img\\\\");
		dao.meterAlbum(dao.crearCodigoAlbum(),textNombreAlbum.getText(),textoModificado,textFecha.getText());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRECTAMENTE CON EL CODIGO  "+ dao.crearCodigoAlbum());
		cargarComboAlbum();
		
	}
	
	

	private void cargarDatosAlbum() {		
		if(comboAlbumA.getSelectedIndex()!=-1) {
			int pos = comboAlbumA.getSelectedIndex();
			ArrayList<Album> albumes = dao.sacarAlbumes();
			btnIntroducir.setEnabled(false);
			textNombreAlbum.setText(albumes.get(pos).getNombreAlbum());
			textFoto.setText(albumes.get(pos).getFotoAlbum());
			textFecha.setText(""+(albumes.get(pos).getFechaLan()));
			
		}else {
			btnIntroducir.setEnabled(true);
			textNombreAlbum.setText("");
			textFoto.setText("");
			textFecha.setText("");
			comboAlbumA.setSelectedIndex(-1);
		}
	}
	
	

	private void seleccionarArtistas() {
		// TODO Auto-generated method stub
		table.clearSelection();
		int pos = comboCanciones.getSelectedIndex();
		ArrayList<Artista> art = dao.sacarartistas();
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		ArrayList<Artista> artistas = dao.artistasPorCancion(canciones.get(pos).getCodCancion());
		
		art = (ArrayList<Artista>) art.stream().sorted(Comparator.comparing(Artista::getNombreArtistico))
		         .collect(Collectors.toList());
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		for(int i=0;i<art.size();i++) {
			for(Artista a:artistas) {
				if(art.get(i).getDni().equalsIgnoreCase(a.getDni())) {
					selectionModel.addSelectionInterval(i, i);
				}
			}
		}
		
	}

	private void eliminarCancion() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		int pos = comboCanciones.getSelectedIndex();
		
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar Borando la cancion?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.borrarCancion(canciones.get(pos).getCodCancion());
			dao.eliminarCanta(canciones.get(pos).getCodCancion());
			JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : "+textNombre.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
			comboCanciones.setSelectedIndex(-1);
			cargarComboCancion();
		}else {
			JOptionPane.showMessageDialog(null, "Se mantiene la cancion : "+textNombre.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void volver() {
		// TODO Auto-generated method stub
		this.dispose();
		Inicio_Sesion ven = new Inicio_Sesion(dao);
		ven.setVisible(true);		
	}
		
	private void crearCancion() {
		// TODO Auto-generated method stub
		cogerArtistas();
		
		int codCancion = crearCodigo();
		String cod = comboAlbum.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		String textoOriginal = textAudio.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Audio\\\\");
		if(validar()) {
			dao.añadirCancion(codCancion, sacarDuracionC(), textNombre.getText(), textoModificado ,codAlbum );
			
			for (Artista art:cogerArtistas()) {
				dao.insertarCanta(art.getDni(),codCancion );
			}
			JOptionPane.showMessageDialog(null, "Se ha creado la cancion : "+textNombre.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
		}
		cargarComboCancion();
	}


	private boolean validar() {
		// TODO Auto-generated method stub
		boolean bien = true;
		if(!textAudio.getText().endsWith(".wav")) {
			JOptionPane.showMessageDialog(null, "Audio tiene que ser tipo .wav","ERROR",JOptionPane.ERROR_MESSAGE);
			bien=false;
		}
		if(comboAlbum.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(null, "Tienes que escoger un album","ERROR",JOptionPane.ERROR_MESSAGE);
			bien=false;
		}
		if(cogerArtistas().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tienes que escoger minimo un artista","ERROR",JOptionPane.ERROR_MESSAGE);
			bien=false;
		}
		if(textNombre.getText()==null) {
			JOptionPane.showMessageDialog(null, "Tienes que poner un nombre","ERROR",JOptionPane.ERROR_MESSAGE);
			bien=false;
		}
		if(table.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Escoge minimo un artista","ERROR",JOptionPane.ERROR_MESSAGE);
			bien=false;
		}
		return bien;
	}

	//crear automaticamente el codigo de la cancion
	private int crearCodigo() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		int cod = canciones.size() + 1 ;
		return cod;
	}


	//coger de la tabla los artistas seleccionados
	private ArrayList<Artista> cogerArtistas() {
		// TODO Auto-generated method stub
		int[] columnas = table.getSelectedRows();
		ArrayList<Artista> artistas = dao.sacarartistas();
		ArrayList<Artista> art = new ArrayList<>();
		
		artistas = (ArrayList<Artista>) artistas.stream().sorted(Comparator.comparing(Artista::getNombreArtistico))
		        .collect(Collectors.toList());
		
		for (int filaSeleccionada : columnas) {
			int indiceModelo = table.convertRowIndexToModel(filaSeleccionada);
			Artista artistaSeleccionado = artistas.get(indiceModelo);
			art.add(artistaSeleccionado);
		}
		
		return art;
	}

	private void cargarComboAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = dao.sacarAlbumes();
		for(Album a:albumes) {
			comboAlbum.addItem(a.getCodAlbum()+" | "+a.getNombreAlbum());
		}
		comboAlbum.setSelectedIndex(-1);
		
	}
	
	private void cargarComboCancion() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		for(Cancion c:canciones) {
			comboCanciones.addItem(c.getCodCancion()+" | "+c.getNombreCancion());
		}
		comboCanciones.setSelectedIndex(-1);
	}
	
	public void cargarTabla() {
		String[] cabeceras = { "DNI", "Nombre Artista"};
		DefaultTableModel model = new DefaultTableModel(cabeceras,0);
		
		ArrayList<Artista> artistas = new ArrayList<>();
		artistas = dao.sacarartistas();
		
		//ordena los artistas por su nombre artistico
		artistas = (ArrayList<Artista>) artistas.stream().sorted(Comparator.comparing(Artista::getNombreArtistico))
		        .collect(Collectors.toList());
		 
		for (Artista art:artistas) {
			String[] fila = new String[4];
			fila[0] = art.getDni()+"";
			fila[1] = art.getNombreArtistico() + "";
			model.addRow(fila);
		}
		table.setModel(model);
	}
}
