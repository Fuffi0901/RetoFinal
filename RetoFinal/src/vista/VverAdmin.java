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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class VverAdmin extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel PadminPrincipal = new JPanel();
	private JPanel PadminCancion = new JPanel();
	private Dao dao;
	private JTextField textNombre;
	private JTable table;
	private JButton btnArchivos;
	private JComboBox comboAlbum;
	private JButton btnCrear;
	private ArrayList<Artista> artistas = new ArrayList<Artista>();
	private JTextField textAudio;
	private JComboBox comboCanciones;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancion;
	private JTabbedPane tabbedPane;
	private JButton btnVolver;
	private JFileChooser fc;
	private File file;
	private String carpetaFinal = "..\\RetoFinal\\Audio\\";
	private JFrame ven;
	private JLabel lblAudio;
	private JTextField textNombreAlbum;
	private JTextField textFoto;
	private JTextField textFecha;
	private JButton btnIntroducir;
	private JComboBox comboAlbumA;
	private JButton btnBorrar, btnModificarA;
	

	/**
	 * Create the dialog.
	 * @param dao2 
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VverAdmin(Inicio_Sesion inicio_Sesion, boolean b, Dao dao) {
		this.setModal(b);
		this.dao=dao;
		setBackground(new Color(73, 197, 250));	
		
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
		
		PadminPrincipal = new JPanel();
		tabbedPane.addTab("Menu Admin", null, PadminPrincipal, null);
		PadminPrincipal.setLayout(null);
		
		btnCancion = new JButton("Gestion Canciones");
		btnCancion.setBounds(86, 142, 164, 60);
		btnCancion.addActionListener(this);
		PadminPrincipal.add(btnCancion);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBounds(1014, 556, 187, 101);
		btnVolver.addActionListener(this);
		PadminPrincipal.add(btnVolver);
		
		PadminCancion = new JPanel();
		tabbedPane.addTab("Gestion Canciones", null, PadminCancion, null);
		PadminCancion.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("GESTION CANCIONES");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		lblTitulo.setBounds(36, 41, 451, 61);
		PadminCancion.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre  :");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNombre.setBounds(157, 165, 197, 51);
		PadminCancion.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(157, 225, 278, 46);
		PadminCancion.add(textNombre);
		
		JLabel lblAlbum = new JLabel("Album  :");
		lblAlbum.setForeground(Color.WHITE);
		lblAlbum.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAlbum.setBounds(157, 295, 197, 51);
		PadminCancion.add(lblAlbum);
		
		JLabel lblArtista = new JLabel("Artistas  :");
		lblArtista.setForeground(Color.WHITE);
		lblArtista.setFont(new Font("Arial", Font.PLAIN, 27));
		lblArtista.setBounds(605, 162, 197, 51);
		PadminCancion.add(lblArtista);
		
		table = new JTable();
		table.setBounds(159, 525, 431, 177);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		PadminCancion.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(605, 223, 407, 123);
        PadminCancion.add(scrollPane);
        
        comboAlbum = new JComboBox();
        comboAlbum.setMaximumRowCount(30);
        comboAlbum.setBounds(157, 355, 278, 46);
        PadminCancion.add(comboAlbum,BorderLayout.CENTER);
        
        btnCrear = new JButton("CREAR");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
        btnCrear.setBounds(1039, 169, 187, 51);
        PadminCancion.add(btnCrear);
        btnCrear.addActionListener(this);
        
        textAudio = new JTextField();
        textAudio.setText("");
        textAudio.setBounds(157, 485, 278, 46);
        PadminCancion.add(textAudio);
        textAudio.setColumns(10);
		
     
		
		btnEliminar = new JButton("Borrar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btnEliminar.setBounds(1039, 20, 187, 61);
		btnEliminar.addActionListener(this);
		PadminCancion.add(btnEliminar);
		
		JLabel lblCanciones = new JLabel("Canciones  :");
		lblCanciones.setForeground(Color.WHITE);
		lblCanciones.setFont(new Font("Arial", Font.PLAIN, 27));
		lblCanciones.setBounds(571, 30, 197, 51);
		PadminCancion.add(lblCanciones);
		cargarTabla();
		comboCanciones = new JComboBox();
		comboCanciones.setBounds(780, 28, 232, 51);
		comboCanciones.addActionListener(this);
		PadminCancion.add(comboCanciones);
			
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificar.setBounds(1039, 98, 187, 61);
		btnModificar.addActionListener(this);
		PadminCancion.add(btnModificar);
		
		JPanel palbum = new JPanel();
		tabbedPane.addTab("GESTION ALBUMES", null, palbum, null);
		
		lblAudio = new JLabel("Audio :");
		lblAudio.setForeground(Color.WHITE);
		lblAudio.setFont(new Font("Arial", Font.PLAIN, 27));
		lblAudio.setBounds(157, 425, 197, 51);
		PadminCancion.add(lblAudio);
		
		
		btnArchivos = new JButton("...");
		btnArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto","wav");
				fc.setFileFilter(filtro);
				
				int res = fc.showOpenDialog(ven);				
				if(res != JFileChooser.CANCEL_OPTION){
					file = fc.getSelectedFile();
					if(file == null || file.getName().equals("")) {
					}
					
				}		
				try {
					Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(new File(carpetaFinal, file.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textAudio.setText(file.getAbsolutePath());
			}
		});
		btnArchivos.setBounds(441, 484, 46, 46);
		PadminCancion.add(btnArchivos);
		
		palbum = new JPanel();
		tabbedPane.addTab("GESTION ALBUMES", null, palbum, null);
		palbum.setLayout(null);
		palbum.setBackground(new Color(64, 128, 128));
		
		textNombreAlbum= new JTextField();
		textNombreAlbum.setBounds(345, 88, 221, 58);
		palbum.add(textNombreAlbum);
		textNombreAlbum.setColumns(10);
		
		textFoto = new JTextField();
		textFoto.setColumns(10);
		textFoto.setBounds(345, 232, 221, 58);
		palbum.add(textFoto);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(345, 401, 221, 58);
		palbum.add(textFecha);
		
		JLabel lblNewLabel_2 = new JLabel("nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(100, 98, 135, 36);
		palbum.add(lblNewLabel_2);
		
		JLabel lblFoto = new JLabel("foto");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFoto.setBounds(100, 254, 135, 36);
		palbum.add(lblFoto);
		
		JLabel lblFecha = new JLabel("fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFecha.setBounds(100, 423, 135, 36);
		palbum.add(lblFecha);
		
		btnIntroducir = new JButton("Introducir");
		btnIntroducir.setBounds(631, 568, 111, 41);
		palbum.add(btnIntroducir);
		
		comboAlbumA = new JComboBox();
		comboAlbumA.setBounds(865, 98, 155, 36);
		palbum.add(comboAlbumA);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.setBounds(446, 578, 101, 31);
		palbum.add(btnBorrar);
		btnIntroducir.addActionListener(this);
		btnBorrar.addActionListener(this);
	
		btnModificarA = new JButton("modificar");
		btnModificarA.setBounds(278, 583, 85, 21);
		palbum.add(btnModificarA);
		btnModificar.addActionListener(this);
		
		cargarComboAlbum();
		cargarComboCancion();

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
		PadminPrincipal.add(fondo);
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
		PadminCancion.add(fondo2);	
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCancion)) {
			 tabbedPane.setSelectedIndex(1); 
		}
		if(e.getSource().equals(btnCrear)) {
			crearCancion();
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
			cargarDatos();
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
		
		if(validar()) {
			dao.modificarCancion(Integer.parseInt(can), sacarDuracionC() , textNombre.getText(), textAudio.getText(),codAlbum);
			dao.eliminarCanta(Integer.parseInt(can));
			for (Artista art:cogerArtistas()) {
				dao.insertarCanta(art.getDni(),Integer.parseInt(can));
			}
			comboCanciones.setSelectedIndex(-1);
		}
		
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
			ArrayList<Album> albumes = dao.sacarAlbumes();
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
		
		this.dispose();
		
		dao.meterAlbum(dao.crearCodigoAlbum(),textNombreAlbum.getText(),textFoto.getText(),textFecha.getText());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRERCAMENTE CON EL CODIGO  "+ dao.crearCodigoAlbum());
		
	}
	
	private void modificarAlbum() {
		// TODO Auto-generated method stub
		int donde=comboAlbumA.getSelectedItem().toString().indexOf(" |");
		this.dispose();
		dao.modificarAlbum(comboAlbumA.getSelectedItem().toString().substring(0, donde),textNombre.getText(),textFoto.getText(),textFecha.getText());
		JOptionPane.showMessageDialog(null, "MODIFICADO  CORRERCAMENTE");
		
	}

	private void cargarDatosAlbum() {
		// TODO Auto-generated method stub
		if(comboAlbumA.getSelectedIndex()!=-1) {
			int pos = comboAlbumA.getSelectedIndex();
			ArrayList<Album> albumes = dao.sacarAlbumes();
			String can = comboAlbumA.getSelectedItem().toString();
			int donde = can.indexOf(" |");
			can = can.substring(0, donde);
			Album album=null;
			for(Album a:albumes) {
				if(a.getCodAlbum()==Integer.parseInt(can))
					album=a;
			}
			btnIntroducir.setEnabled(false);		
			textNombre.setText(albumes.get(pos).getNombreAlbum());
			textFoto.setText(albumes.get(pos).getFotoAlbum());
			textFecha.setText(""+(albumes.get(pos).getFechaLan()));
			
			
		}else {
			btnIntroducir.setEnabled(true);
			textNombre.setText(null);
			textFoto.setText(null);
			textFecha.setText(null);
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
