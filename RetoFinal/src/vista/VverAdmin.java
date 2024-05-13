package vista;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JCalendar;

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
	private JTextField textNombreCan;
	private JTable table;
	private JButton btnArchivosCan;
	private JComboBox comboAlbumCan;
	private JButton btnIntroducirCan;
	private JTextField textAudioCan;
	private JComboBox comboCancionesCan;
	private JButton btnBorrarCan, btnXAlb;
	private JButton btnModificarCan;
	private JButton btnCancion, btnVolverCan;
	//cosas de albumes
	
	private JButton btnVolver, btnAlbum;
	private JFileChooser fc;
	private File file;
	private String carpetaFinalAudio = "..\\RetoFinal\\Audio\\";
	private String carpetaFinalImg = "..\\RetoFinal\\Img\\";
	private JFrame ven;
	private JLabel lblAudioCan;
	private JTextField textNombreAlb;
	private JTextField textFotoAlb;
	private JButton btnIntroducirAlb;
	private JComboBox comboAlb;
	private JButton btnBorrarAlb, btnModificarAlb, btnVolverAlb;
	private JButton btnArchivosAlb;
	private JLabel lblTituloAlb;
	
	//Artista
	private JTextField textDniArt;
	private JTextField textNombreArt;
	private JTextField textApellidoArt;
	private JTextField textPaisArt;
	private JTextField textEdadArt;
	private JTextField textNombreArtisticoArt;
	private JCheckBox checkboxArt;
	private JComboBox comboEstiloArt;
	private JButton btnAltaArt, btnModificarArt, btnVolverArt;
	private JComboBox comboArt;
	private JButton btnBorrarArt;
	private JLabel lblTituloArt;
	private JLabel lblArtistaArt;
	private JButton btnGestionArtistas;
	private JButton btnXCan;
	private JButton btnXArt;
	private JCalendar calendario;
	

	/**
	 * Create the dialog.
	 * @param dao
	 * @param b 
	 * @param inicio_Sesion 
	 */
	@SuppressWarnings("unchecked")
	public VverAdmin(Inicio_Sesion inicio_Sesion, boolean b, Dao dao) {
		setLocationRelativeTo(null);
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
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBounds(901, 529, 245, 101);
		btnVolver.addActionListener(this);
		padPrincipal.add(btnVolver);
		
		//MENU ALBUM
		textNombreAlb= new JTextField();
		textNombreAlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNombreAlb.setBounds(157, 218, 278, 46);
		padAlbum.add(textNombreAlb);
		textNombreAlb.setColumns(10);
		
		textFotoAlb = new JTextField();
		textFotoAlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFotoAlb.setColumns(10);
		textFotoAlb.setBounds(157, 342, 278, 46);
		padAlbum.add(textFotoAlb);
		
		btnXAlb = new JButton("X");
		btnXAlb.addActionListener(this);
		btnXAlb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXAlb.setBounds(885, 218, 46, 46);
		padAlbum.add(btnXAlb);
		
		JLabel lblFotoAlb = new JLabel("Foto :");
		lblFotoAlb.setForeground(new Color(255, 255, 255));
		lblFotoAlb.setFont(new Font("Arial", Font.PLAIN, 27));
		lblFotoAlb.setBounds(157, 300, 195, 46);
		padAlbum.add(lblFotoAlb);
		
		JLabel lblFechaAlb = new JLabel("Fecha :");
		lblFechaAlb.setForeground(new Color(255, 255, 255));
		lblFechaAlb.setFont(new Font("Arial", Font.PLAIN, 27));
		lblFechaAlb.setBounds(157, 433, 195, 46);
		padAlbum.add(lblFechaAlb);
		
		btnIntroducirAlb = new JButton("Introducir");
		btnIntroducirAlb.setFont(new Font("Arial", Font.BOLD, 20));
		btnIntroducirAlb.setBounds(1039, 20, 187, 61);
		btnIntroducirAlb.addActionListener(this);
		padAlbum.add(btnIntroducirAlb);
		
		comboAlb = new JComboBox();
		comboAlb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboAlb.setBounds(597, 218, 278, 46);
		comboAlb.addActionListener(this);
		padAlbum.add(comboAlb);
		
		btnBorrarAlb = new JButton("Borrar");
		btnBorrarAlb.setFont(new Font("Arial", Font.BOLD, 20));
		btnBorrarAlb.setBounds(1039, 164, 187, 61);
		btnBorrarAlb.setEnabled(false);
		padAlbum.add(btnBorrarAlb);
		btnBorrarAlb.addActionListener(this);
		
		lblTituloAlb = new JLabel("GESTION ALBUMES");
		lblTituloAlb.setForeground(Color.WHITE);
		lblTituloAlb.setFont(new Font("Arial", Font.BOLD, 40));
		lblTituloAlb.setBounds(36, 20, 451, 61);
		padAlbum.add(lblTituloAlb);
		
		calendario = new JCalendar();
		calendario.setBounds(157, 473, 330, 127);
		padAlbum.add(calendario);
		
		
		btnModificarAlb = new JButton("Modificar");
		btnModificarAlb.addActionListener(this);
		btnModificarAlb.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarAlb.setBounds(1039, 92, 187, 61);
		btnModificarAlb.setEnabled(false);
		padAlbum.add(btnModificarAlb);
		
		btnVolverAlb = new JButton("Volver");
		btnVolverAlb.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolverAlb.setBounds(1039, 626, 187, 61);
		btnVolverAlb.addActionListener(this);
		padAlbum.add(btnVolverAlb);
			
		JLabel lblNombreAlb = new JLabel("Nombre  :");
		lblNombreAlb.setForeground(Color.WHITE);
		lblNombreAlb.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombreAlb.setBounds(157, 160, 195, 46);
		padAlbum.add(lblNombreAlb);
			
		btnArchivosAlb = new JButton("...");
		btnArchivosAlb.addActionListener(new ActionListener() {
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
					textFotoAlb.setText(file.getAbsolutePath());	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnArchivosAlb.setBounds(445, 342, 46, 46);
		padAlbum.add(btnArchivosAlb);
		
		JLabel lblAlbumAlb = new JLabel("Albumes  :");
		lblAlbumAlb.setForeground(Color.WHITE);
		lblAlbumAlb.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAlbumAlb.setBounds(597, 160, 197, 46);
		padAlbum.add(lblAlbumAlb);
					
		JLabel fondoAlb = new JLabel("") {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.dispose();
			}
		};
		fondoAlb.setBounds(0, 0, 1256, 724);
		padAlbum.add(fondoAlb);	
		
		
		
		
		DefaultComboBoxModel<Estilo> modeloCombo = new DefaultComboBoxModel<>(Estilo.values());;
		
		padCancion = new JPanel();
		padCancion.setBackground(new Color(64, 128, 128));
		tabbedPane.addTab("Gestion Canciones", null, padCancion, null);
		padCancion.setLayout(null);
		
			//CANCIONES	

			JLabel lblTituloCan = new JLabel("GESTION CANCIONES");
			lblTituloCan.setForeground(new Color(255, 255, 255));
			lblTituloCan.setFont(new Font("Arial", Font.BOLD, 40));
			lblTituloCan.setBounds(36, 20, 451, 61);
			padCancion.add(lblTituloCan);
			
			JLabel lblNombreCan = new JLabel("Nombre  :");
			lblNombreCan.setForeground(Color.WHITE);
			lblNombreCan.setFont(new Font("Arial", Font.PLAIN, 27));
			lblNombreCan.setBounds(139, 197, 197, 51);
			padCancion.add(lblNombreCan);
			
			textNombreCan = new JTextField();
			textNombreCan.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textNombreCan.setColumns(10);
			textNombreCan.setBounds(139, 257, 278, 46);
			padCancion.add(textNombreCan);
			
			JLabel lblAlbumCan = new JLabel("Album  :");
			lblAlbumCan.setForeground(Color.WHITE);
			lblAlbumCan.setFont(new Font("Arial", Font.PLAIN, 27));
			lblAlbumCan.setBounds(139, 327, 197, 51);
			padCancion.add(lblAlbumCan);
			
			JLabel lblArtistaCan = new JLabel("Artistas  :");
			lblArtistaCan.setForeground(Color.WHITE);
			lblArtistaCan.setFont(new Font("Arial", Font.PLAIN, 27));
			lblArtistaCan.setBounds(587, 194, 197, 51);
			padCancion.add(lblArtistaCan);
			
			table = new JTable();
			table.setBounds(159, 525, 431, 177);
			table.setRowHeight(25);
			table.setBorder(null);
	        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			padCancion.add(table);
			
			JTableHeader header = table.getTableHeader();
	        header.setBackground(java.awt.Color.gray);
	        header.setFont(new Font("Stencil", Font.PLAIN, 18));
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        table.setDefaultRenderer(Object.class, centerRenderer);
	        
			JScrollPane scrollPaneCan = new JScrollPane(table);
			scrollPaneCan.setBounds(479, 257, 536, 150);
			padCancion.add(scrollPaneCan);
			
			comboAlbumCan = new JComboBox();
			comboAlbumCan.setFont(new Font("Tahoma", Font.PLAIN, 17));
			comboAlbumCan.setMaximumRowCount(30);
			comboAlbumCan.setBounds(139, 387, 278, 46);
			padCancion.add(comboAlbumCan,BorderLayout.CENTER);
			
			btnIntroducirCan = new JButton("Introducir");
			btnIntroducirCan.setFont(new Font("Arial", Font.BOLD, 20));
			btnIntroducirCan.setBounds(1039, 20, 187, 61);
			padCancion.add(btnIntroducirCan);
			btnIntroducirCan.addActionListener(this);
			
			textAudioCan = new JTextField();
			textAudioCan.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textAudioCan.setText("");
			textAudioCan.setBounds(139, 517, 278, 46);
			padCancion.add(textAudioCan);
			textAudioCan.setColumns(10);
			
     
			
			btnBorrarCan = new JButton("Borrar");
			btnBorrarCan.setFont(new Font("Arial", Font.BOLD, 20));
			btnBorrarCan.setBounds(1039, 160, 187, 61);
			btnBorrarCan.setEnabled(false);
			btnBorrarCan.addActionListener(this);
			padCancion.add(btnBorrarCan);
			
			JLabel lblCancionesCan = new JLabel("Canciones  :");
			lblCancionesCan.setForeground(Color.WHITE);
			lblCancionesCan.setFont(new Font("Arial", Font.PLAIN, 27));
			lblCancionesCan.setBounds(497, 30, 197, 51);
			padCancion.add(lblCancionesCan);
			
			comboCancionesCan = new JComboBox();
			comboCancionesCan.setFont(new Font("Tahoma", Font.PLAIN, 17));
			comboCancionesCan.setBounds(706, 28, 232, 51);
			comboCancionesCan.addActionListener(this);
			padCancion.add(comboCancionesCan);
			
		btnModificarCan = new JButton("Modificar");
		btnModificarCan.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarCan.setBounds(1039, 90, 187, 61);
		btnModificarCan.setEnabled(false);
		btnModificarCan.addActionListener(this);
		padCancion.add(btnModificarCan);
		
		lblAudioCan = new JLabel("Audio :");
		lblAudioCan.setForeground(Color.WHITE);
		lblAudioCan.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAudioCan.setBounds(139, 457, 197, 51);
		padCancion.add(lblAudioCan);
		
		
		btnArchivosCan = new JButton("...");
		btnArchivosCan.addActionListener(new ActionListener() {
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
					textAudioCan.setText(file.getAbsolutePath());	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnArchivosCan.setBounds(423, 516, 46, 46);
		padCancion.add(btnArchivosCan);
		
		padArtista = new JPanel();
		padArtista.setBackground(new Color(64, 128, 128));
		tabbedPane.addTab("Gestion Artistas", null, padArtista, null);
		padArtista.setLayout(null);
		
		btnXCan = new JButton("X");
		btnXCan.addActionListener(this);
		btnXCan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXCan.setBounds(947, 28, 51, 51);
		padCancion.add(btnXCan);
		
		JLabel lblDniArt = new JLabel("Dni:");
		lblDniArt.setForeground(new Color(255, 255, 255));
		lblDniArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblDniArt.setBounds(101, 195, 252, 46);
		padArtista.add(lblDniArt);
		
		JLabel lblNombreArt = new JLabel("Nombre:");
		lblNombreArt.setForeground(new Color(255, 255, 255));
		lblNombreArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombreArt.setBounds(101, 309, 252, 46);
		padArtista.add(lblNombreArt);
		
		JLabel lblApellidoArt = new JLabel("Apellido:");
		lblApellidoArt.setForeground(new Color(255, 255, 255));
		lblApellidoArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblApellidoArt.setBounds(101, 421, 252, 46);
		padArtista.add(lblApellidoArt);
		
		JLabel lblPaisArt = new JLabel("Pais:");
		lblPaisArt.setForeground(new Color(255, 255, 255));
		lblPaisArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblPaisArt.setBounds(439, 195, 252, 46);
		padArtista.add(lblPaisArt);
		
		JLabel lblEdadArt = new JLabel("Edad:");
		lblEdadArt.setForeground(new Color(255, 255, 255));
		lblEdadArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblEdadArt.setBounds(439, 309, 252, 46);
		padArtista.add(lblEdadArt);
		
		JLabel lblNombreArtisticoArt = new JLabel("Nombre Artistico:");
		lblNombreArtisticoArt.setForeground(new Color(255, 255, 255));
		lblNombreArtisticoArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombreArtisticoArt.setBounds(439, 423, 252, 43);
		padArtista.add(lblNombreArtisticoArt);
		
		JLabel lblCantaAutorArt = new JLabel("Canta autor:");
		lblCantaAutorArt.setForeground(new Color(255, 255, 255));
		lblCantaAutorArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblCantaAutorArt.setBounds(778, 333, 160, 43);
		padArtista.add(lblCantaAutorArt);
		
		JLabel lblEstiloArt = new JLabel("Estilo:");
		lblEstiloArt.setForeground(new Color(255, 255, 255));
		lblEstiloArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblEstiloArt.setBounds(778, 387, 160, 43);
		padArtista.add(lblEstiloArt);
		
		checkboxArt = new JCheckBox("");
		checkboxArt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxArt.setBounds(944, 333, 69, 43);
		checkboxArt.setOpaque(false);
		padArtista.add(checkboxArt);
		
		textDniArt = new JTextField();
		textDniArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textDniArt.setBounds(101, 252, 278, 46);
		padArtista.add(textDniArt);
		textDniArt.setColumns(10);
		
		textNombreArt = new JTextField();
		textNombreArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNombreArt.setColumns(10);
		textNombreArt.setBounds(101, 366, 278, 46);
		padArtista.add(textNombreArt);
		
		textApellidoArt = new JTextField();
		textApellidoArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textApellidoArt.setColumns(10);
		textApellidoArt.setBounds(101, 478, 278, 46);
		padArtista.add(textApellidoArt);
		
		textPaisArt = new JTextField();
		textPaisArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPaisArt.setColumns(10);
		textPaisArt.setBounds(439, 252, 278, 46);
		padArtista.add(textPaisArt);
		
		textEdadArt = new JTextField();
		textEdadArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEdadArt.setColumns(10);
		textEdadArt.setBounds(439, 366, 278, 46);
		padArtista.add(textEdadArt);
		
		textNombreArtisticoArt = new JTextField();
		textNombreArtisticoArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
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
		
		btnAltaArt = new JButton("Introducir");
		btnAltaArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnAltaArt.setBounds(1039, 20, 187, 61);
		btnAltaArt.addActionListener(this);
		padArtista.add(btnAltaArt);
		
		comboArt = new JComboBox();
		comboArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboArt.setBounds(706, 30, 232, 51);
		comboArt.addActionListener(this);
		padArtista.add(comboArt);
		
		btnBorrarArt = new JButton("Borrar");
		btnBorrarArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnBorrarArt.setBounds(1039, 160, 187, 61);
		btnBorrarArt.setEnabled(false);
		padArtista.add(btnBorrarArt);
		btnBorrarArt.addActionListener(this);
		
		lblTituloArt = new JLabel("GESTION ARTISTA");
		lblTituloArt.setForeground(Color.WHITE);
		lblTituloArt.setFont(new Font("Arial", Font.BOLD, 40));
		lblTituloArt.setBounds(36, 20, 451, 61);
		padArtista.add(lblTituloArt);
		
		lblArtistaArt = new JLabel("Artista:");
		lblArtistaArt.setForeground(Color.WHITE);
		lblArtistaArt.setFont(new Font("Arial", Font.PLAIN, 27));
		lblArtistaArt.setBounds(540, 30, 197, 51);
		padArtista.add(lblArtistaArt);
		
		btnModificarArt = new JButton("Modificar");
		btnModificarArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificarArt.setBounds(1039, 90, 187, 61);
		btnModificarArt.setEnabled(false);
		padArtista.add(btnModificarArt);
		btnModificarArt.addActionListener(this);
		
		btnGestionArtistas = new JButton("Gestion Artistas");
		btnGestionArtistas.addActionListener(this);
		btnGestionArtistas.setFont(new Font("Arial", Font.BOLD, 20));
		btnGestionArtistas.setBounds(901, 142, 245, 101);
		padPrincipal.add(btnGestionArtistas);
		
		btnVolverCan = new JButton("Volver");
		btnVolverCan.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolverCan.setBounds(1039, 626, 187, 61);
		btnVolverCan.addActionListener(this);
		padCancion.add(btnVolverCan);
		
		btnVolverArt = new JButton("Volver");
		btnVolverArt.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolverArt.setBounds(1039, 626, 187, 61);
		btnVolverArt.addActionListener(this);
		padArtista.add(btnVolverArt);
		
		btnXArt = new JButton("X");
		btnXArt.addActionListener(this);
		btnXArt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXArt.setBounds(944, 30, 51, 51);
		padArtista.add(btnXArt);
		
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

		JLabel fondoCan = new JLabel("") {
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		        g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
		        g2d.fillRect(0, 0, getWidth(), getHeight());
		        g2d.dispose();
		    }
		};
		fondoCan.setBounds(0, 0, 1256, 724);
		padCancion.add(fondoCan);    
		
		
		
		

		JLabel fondoArt = new JLabel("") {
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		        g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
		        g2d.fillRect(0, 0, getWidth(), getHeight());
		        g2d.dispose();
		    }
		};
		fondoArt.setBounds(0, 0, 1256, 724);
		padArtista.add(fondoArt);		
		
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
		if(e.getSource().equals(btnIntroducirCan)) {
			crearCancion();
		}
		if(e.getSource().equals(btnGestionArtistas)) {
			 tabbedPane.setSelectedIndex(3); 
		}
		if(e.getSource().equals(btnVolverAlb)) {
			 volver(); 
		}
		if(e.getSource().equals(btnVolverCan)) {
			 volver(); 
		}
		if(e.getSource().equals(btnVolverArt)) {
			 volver(); 
		}
		if(e.getSource().equals(btnBorrarCan)) {
			eliminarCancion();
		}
		if(e.getSource().equals(btnModificarCan)) {
			modificarCancion();
		}
		if(e.getSource().equals(comboCancionesCan)) {
			cargarDatosCan();
		}
		if(e.getSource().equals(btnIntroducirAlb)) {
			meterAlbum();
		}
		if(e.getSource().equals(comboAlb)) {
			cargarDatosAlbum();
		}
		if(e.getSource().equals(btnModificarAlb)) {
			modificarAlbum();
		}
		if(e.getSource().equals(btnBorrarAlb)) {
			borrarAlbum();
		}
		if(e.getSource().equals(btnVolver)) {
			volver();
		}
		if(e.getSource().equals(btnAlbum)) {
			 tabbedPane.setSelectedIndex(1); 
		}
		if(e.getSource().equals(btnAltaArt)) {
			altaArtista();
		}
		if(e.getSource().equals(btnModificarArt)) {
			modificarArtista();
		}
		if(e.getSource().equals(btnBorrarArt)) {
			borrarArtista();
		}
		if(e.getSource().equals(comboArt)) {
			cargarDatosArtista();
		}
		if(e.getSource().equals(btnXAlb)) {
			comboAlb.setSelectedIndex(-1);
			cargarDatosAlbum();
		}
		if(e.getSource().equals(btnXCan)) {
			comboCancionesCan.setSelectedIndex(-1);
			cargarDatosCan();
		}
		if(e.getSource().equals(btnXArt)) {
			comboArt.setSelectedIndex(-1);
			cargarDatosArtista();
		}
	}




	
	private void borrarArtista() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = dao.sacarartistas();
		int pos = comboArt.getSelectedIndex();
		dao.borrarArtista(artistas.get(pos).getDni());
		JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : ","",JOptionPane.INFORMATION_MESSAGE);
		comboArt.setSelectedIndex(-1);	
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
		dao.registrarPersona(textDniArt.getText(),textNombreArt.getText(),textApellidoArt.getText(),textPaisArt.getText(),Integer.valueOf(textEdadArt.getText()));
		dao.registrarArtista(textDniArt.getText(),textNombreArtisticoArt.getText(),cantaAutor,comboEstiloArt.getSelectedItem().toString());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRERCAMENTE");		
	}




	private void modificarCancion() {
		// TODO Auto-generated method stub
		cogerArtistas();
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		String can = comboCancionesCan.getSelectedItem().toString();
		int donde = can.indexOf(" |");
		can = can.substring(0, donde);
		String cod = comboAlbumCan.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		String textoOriginal = textAudioCan.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Audio\\\\");
		if(validar(1)) {
			dao.modificarCancion(Integer.parseInt(can), sacarDuracionC() , textNombreCan.getText(), textoModificado,codAlbum);
			dao.eliminarCanta(Integer.parseInt(can));
			for (Artista art:cogerArtistas()) {
				dao.insertarCanta(art.getDni(),Integer.parseInt(can));
			}
			comboCancionesCan.setSelectedIndex(-1);
		}
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboCancion();

	}
	private void modificarArtista() {
		dao.modificarPersona(textDniArt.getText(), textNombreArt.getText(), textApellidoArt.getText(), textPaisArt.getText(), Integer.parseInt(textEdadArt.getText()));
		dao.modificarArtista(textDniArt.getText(), textNombreArtisticoArt.getText(), comboEstiloArt.getSelectedItem().toString(), checkboxArt.isSelected());
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboArtista();
	}

	private void modificarAlbum() {
		// TODO Auto-generated method stub
		int donde=comboAlb.getSelectedItem().toString().indexOf(" |");
		String textoOriginal = textFotoAlb.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Img\\\\");
        java.util.Date fechaUtil = calendario.getDate();
        java.sql.Date fchaSql = new java.sql.Date(fechaUtil.getTime());
		dao.modificarAlbum(comboAlb.getSelectedItem().toString().substring(0, donde),textNombreAlb.getText(),textoModificado, fchaSql);
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRECTAMENTE");
		cargarComboAlbumA();		
	}
	
	private int sacarDuracionC() {
		// TODO Auto-generated method stub
		File audioFile = new File(textAudioCan.getText());
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


	
	
	private void borrarAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = dao.sacarAlbumes();
		int pos = comboAlb.getSelectedIndex();
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar Borando el album?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.borrarAlbum(albumes.get(pos).getCodAlbum());
			JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : "+textNombreAlb.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
			comboAlb.setSelectedIndex(-1);
			
		}else {
			JOptionPane.showMessageDialog(null, "Se mantiene la cancion : "+textNombreAlb.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
		}
		cargarComboAlbum();
	}

	private void meterAlbum() {
		// TODO Auto-generated method stub
		
		String textoOriginal = textAudioCan.getText();
		String textoModificado = textoOriginal.replaceAll(".*\\\\", "..\\\\RetoFinal\\\\Img\\\\");
		dao.meterAlbum(dao.crearCodigoAlbum(),textNombreAlb.getText(),textoModificado,calendario.getDate().toString());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRECTAMENTE CON EL CODIGO  "+ dao.crearCodigoAlbum());
		cargarComboAlbum();
		
	}

	private void seleccionarArtistas() {
		// TODO Auto-generated method stub
		table.clearSelection();
		int pos = comboCancionesCan.getSelectedIndex();
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
		int pos = comboCancionesCan.getSelectedIndex();
		
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar Borando la cancion?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.borrarCancion(canciones.get(pos).getCodCancion());
			dao.eliminarCanta(canciones.get(pos).getCodCancion());
			JOptionPane.showMessageDialog(null, "Se ha borrado la cancion : "+textNombreCan.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
			comboCancionesCan.setSelectedIndex(-1);
			cargarComboCancion();
		}else {
			JOptionPane.showMessageDialog(null, "Se mantiene la cancion : "+textNombreCan.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
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
		String cod = comboAlbumCan.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		String textoOriginal = textAudioCan.getText();
        String textoModificado = textoOriginal.replaceAll("^.+\\\\", "..\\\\RetoFinal\\\\Audio\\\\");
		if(validar(1)) {
			dao.añadirCancion(codCancion, sacarDuracionC(), textNombreCan.getText(), textoModificado ,codAlbum );
			
			for (Artista art:cogerArtistas()) {
				dao.insertarCanta(art.getDni(),codCancion );
			}
			JOptionPane.showMessageDialog(null, "Se ha creado la cancion : "+textNombreCan.getText(),"Cancion",JOptionPane.INFORMATION_MESSAGE);
		}
		cargarComboCancion();
	}

	//Validacion
	
	private boolean validar(int num) {
		// TODO Auto-generated method stub
		boolean bien = true;
		if (num == 1) {
			
			if(!textAudioCan.getText().endsWith(".wav")) {
				JOptionPane.showMessageDialog(null, "Audio tiene que ser tipo .wav","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(comboAlbumCan.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Tienes que escoger un album","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(cogerArtistas().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Tienes que escoger minimo un artista","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if (textNombreCan.getText().isEmpty() || textNombreCan.getText().matches(".*\\d.*")) {
			    JOptionPane.showMessageDialog(null, "El nombre no debe contener números", "ERROR", JOptionPane.ERROR_MESSAGE);
			    bien = false;
			}
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "Escoge minimo un artista","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
		}
		if (num == 2) {
			if(!textNombreAlb.getText().endsWith(".jpg")) {
				JOptionPane.showMessageDialog(null, "Imagen tiene que ser tipo .jpg","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textNombreAlb.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un nombre","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
		}
		if (num == 3) {
			if(!comprobarDni()) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un dni","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textNombreArt.getText().isEmpty() || textNombreCan.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un nombre","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textApellidoArt.getText().isEmpty() || textNombreCan.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un apellido","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textEdadArt.getText().isEmpty() || !textNombreCan.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Tienes que poner una edad","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textPaisArt.getText().isEmpty() || textNombreCan.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un pais","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(textNombreArtisticoArt.getText().isEmpty() || textNombreCan.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Tienes que poner un nombre artístico","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
			if(comboEstiloArt.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Tienes que escoger un estilo","ERROR",JOptionPane.ERROR_MESSAGE);
				bien=false;
			}
		}
		return bien;

		
	}
	private boolean comprobarDni() {
		String dni = textDniArt.getText();

		if (dni.isEmpty() || dni.length() != 9)
			return false;

		String numero = dni.substring(0, 8);
		char letraControl = dni.charAt(8);

		try {
			int num = Integer.parseInt(numero);
			int resto = num % 23;
			char[] letrasControl = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
					'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
			return letraControl == letrasControl[resto];
		} catch (NumberFormatException e) {
			return false;
		}
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

	//Cargas de ComboBox
	
	private void cargarComboAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = dao.sacarAlbumes();
		for(Album a:albumes) {
			comboAlbumCan.addItem(a.getCodAlbum()+" | "+a.getNombreAlbum());
		}
		comboAlbumCan.setSelectedIndex(-1);
		
	}

	private void cargarComboAlbumA() {
		// TODO Auto-generated method stub
		comboAlb.removeAllItems();
		ArrayList<Album> albumes = dao.sacarAlbumes();
		for(Album a:albumes) {
			comboAlb.addItem(a.getCodAlbum()+" | "+a.getNombreAlbum());
		}
		comboAlb.setSelectedIndex(-1);		
	}
	
	private void cargarComboCancion() {
		// TODO Auto-generated method stub
		comboCancionesCan.removeAllItems();
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		for(Cancion c:canciones) {
			comboCancionesCan.addItem(c.getCodCancion()+" | "+c.getNombreCancion());
		}
		comboCancionesCan.setSelectedIndex(-1);
	}
	

	private void cargarComboArtista() {
		// TODO Auto-generated method stub
		comboArt.removeAllItems();
		ArrayList<Artista> artistas = dao.sacarartistas();
		for(Artista a:artistas) {
			comboArt.addItem(a.getNombreArtistico());
		}
		comboArt.setSelectedIndex(-1);
	}
	
	//Carga Tabla Canciones
	
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
	
	//Cargas de datos
	
	private void cargarDatosAlbum() {		
		if(comboAlb.getSelectedIndex()!=-1) {
			int pos = comboAlb.getSelectedIndex();
			ArrayList<Album> albumes = dao.sacarAlbumes();
			btnIntroducirAlb.setEnabled(false);
			btnBorrarAlb.setEnabled(true);
			btnModificarAlb.setEnabled(true);
			textNombreAlb.setText(albumes.get(pos).getNombreAlbum());
			textFotoAlb.setText(albumes.get(pos).getFotoAlbum());
			calendario.setDate(albumes.get(pos).getFechaLan());
			
		}else {
			btnIntroducirAlb.setEnabled(true);
			btnBorrarAlb.setEnabled(false);
			btnModificarAlb.setEnabled(false);
			textNombreAlb.setText("");
			textFotoAlb.setText("");
			calendario.setDate(Calendar.getInstance().getTime());
			comboAlb.setSelectedIndex(-1);
		}
	}
	
	private void cargarDatosCan() {
		// TODO Auto-generated method stub
		
		if(comboCancionesCan.getSelectedIndex()!=-1) {
			ArrayList<Cancion> canciones = dao.sacarCanciones();
			int pos = comboCancionesCan.getSelectedIndex();
			String can = comboCancionesCan.getSelectedItem().toString();
			int donde = can.indexOf(" |");
			can = can.substring(0, donde);
			Cancion cancion=null;
			for(Cancion c:canciones) {
				if(c.getCodCancion()==Integer.parseInt(can))
					cancion=c;
			}
			for (int i = 0; i < comboAlbumCan.getItemCount(); i++) {
	            String item = (String) comboAlbumCan.getItemAt(i);
	            int p = item.indexOf(" |");
	            item = item.substring(0, p);
	            if (item.equalsIgnoreCase(String.valueOf(cancion.getCodAlbum()))) {
	            	comboAlbumCan.setSelectedIndex(i);
	            }
	         }
			btnIntroducirCan.setEnabled(false);
			btnBorrarCan.setEnabled(true);
			btnModificarCan.setEnabled(true);
			textAudioCan.setText(canciones.get(pos).getAudio());
			textNombreCan.setText(canciones.get(pos).getNombreCancion());
			seleccionarArtistas();
			
			
			
		}else {
			btnIntroducirCan.setEnabled(true);
			btnBorrarCan.setEnabled(false);
			btnModificarCan.setEnabled(false);
			textAudioCan.setText("");
			textNombreCan.setText(null);
			comboAlbumCan.setSelectedIndex(-1);
			table.clearSelection();
		}
	}
	
	private void cargarDatosArtista() {
		if(comboArt.getSelectedIndex()!=-1) {
			int pos = comboArt.getSelectedIndex();
			ArrayList<Persona> personas = dao.sacarPersonas();
			ArrayList<Artista> artistas = dao.sacarArtista();
			textDniArt.setText(artistas.get(pos).getDni());
			textDniArt.setEnabled(false);
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
			btnAltaArt.setEnabled(false);
			btnBorrarArt.setEnabled(true);
			btnModificarArt.setEnabled(true);
		}else {
			btnAltaArt.setEnabled(true);
			btnBorrarArt.setEnabled(false);
			btnModificarArt.setEnabled(false);
			textDniArt.setEnabled(true);
			textDniArt.setText("");
			textNombreArt.setText("");
			textApellidoArt.setText("");
			textPaisArt.setText("");
			textEdadArt.setText("");
			textNombreArtisticoArt.setText("");
			checkboxArt.setSelected(false);
			comboEstiloArt.setSelectedIndex(-1);
		}		
	}

	
}