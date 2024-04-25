package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private Dao dao;
	private JTextField textDuracion;
	private JTextField textNombre;
	private JTable table;
	private JComboBox comboAlbum;
	private JButton btnCrear;
	private JButton btnVolver;
	private ArrayList<Artista> artistas = new ArrayList<Artista>();
	private JTextField textAudio;
	private JTextField textField;
	private JComboBox comboCanciones;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancion;
	private JTabbedPane tabbedPane;

	/**
	 * Create the dialog.
	 * @param dao2 
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VverAdmin(Inicio_Sesion inicio_Sesion, boolean b, Dao dao) {
		this.setModal(b);
		this.dao=dao;
		setBackground(new Color(64, 128, 128));	
		
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
		
		JPanel PadminPrincipal = new JPanel();
		tabbedPane.addTab("Menu Admin", null, PadminPrincipal, null);
		PadminPrincipal.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(231, 443, 96, 19);
		PadminPrincipal.add(textField);
		textField.setColumns(10);
		
		btnCancion = new JButton("Gestion Canciones");
		btnCancion.setBounds(86, 142, 164, 60);
		btnCancion.addActionListener(this);
		PadminPrincipal.add(btnCancion);
		
		JPanel PadminCancion = new JPanel();
		tabbedPane.addTab("Gestion Canciones", null, PadminCancion, null);
		PadminCancion.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("GESTION CANCIONES");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setBounds(36, 41, 451, 61);
		PadminCancion.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Duracion  :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(157, 160, 197, 51);
		PadminCancion.add(lblNewLabel_1);
		
		textDuracion = new JTextField();
		textDuracion.setBounds(157, 221, 278, 41);
		PadminCancion.add(textDuracion);
		textDuracion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre  :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(715, 166, 197, 51);
		PadminCancion.add(lblNewLabel_1_1);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(710, 219, 278, 46);
		PadminCancion.add(textNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Album  :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1_1.setBounds(159, 316, 197, 51);
		PadminCancion.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Artistas  :");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1_1_1.setBounds(715, 316, 197, 51);
		PadminCancion.add(lblNewLabel_1_1_1_1);
		
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
        scrollPane.setBounds(715, 377, 407, 139);
        PadminCancion.add(scrollPane);
        
        comboAlbum = new JComboBox();
        comboAlbum.setMaximumRowCount(30);
        comboAlbum.setBounds(159, 388, 276, 41);
        PadminCancion.add(comboAlbum,BorderLayout.CENTER);
        
        btnCrear = new JButton("CREAR");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
        btnCrear.setBounds(63, 494, 187, 101);
        PadminCancion.add(btnCrear);
        btnCrear.addActionListener(this);
        
        btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setBounds(373, 494, 187, 101);
        PadminCancion.add(btnVolver);
        btnVolver.addActionListener(this);
        
        textAudio = new JTextField();
        textAudio.setText("..\\\\FinalChalenge\\\\Audio\\\\");
        textAudio.setBounds(727, 616, 300, 35);
        PadminCancion.add(textAudio);
        textAudio.setColumns(10);
		
     
		
		btnEliminar = new JButton("Borrar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btnEliminar.setBounds(1039, 20, 187, 61);
		btnEliminar.addActionListener(this);
		PadminCancion.add(btnEliminar);
		
		JLabel lblNewLabel_1_2 = new JLabel("Canciones  :");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_2.setBounds(571, 30, 197, 51);
		PadminCancion.add(lblNewLabel_1_2);
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
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\fondo1.gif"));
		lblFondo.setBounds(0, 0, 1257, 748);
		PadminCancion.add(lblFondo);
		
		cargarComboAlbum();
		cargarComboCancion();
	
		
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
		if(e.getSource().equals(btnVolver)) {
			volver();
		}
	}
	
	private void modificarCancion() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		String can = comboCanciones.getSelectedItem().toString();
		int donde = can.indexOf(" |");
		can = can.substring(0, donde);
		String cod = comboAlbum.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		
		dao.modificarCancion(Integer.parseInt(can),Integer.parseInt(textDuracion.getText()) , textNombre.getText(), textAudio.getText(),codAlbum);
		comboCanciones.setSelectedIndex(-1);
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
			textDuracion.setText(String.valueOf(canciones.get(pos).getDuracion()));
			textNombre.setText(canciones.get(pos).getNombreCancion());
			seleccionarArtistas();
			
			
			
		}else {
			btnCrear.setEnabled(true);
			textAudio.setText("..\\FinalChalenge\\Audio\\");
			textDuracion.setText(null);
			textNombre.setText(null);
			comboAlbum.setSelectedIndex(-1);
			table.clearSelection();
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
		
	}
	
	
	private void crearCancion() {
		// TODO Auto-generated method stub
		cogerArtistas();
		
		int codCancion = crearCodigo();
		String cod = comboAlbum.getSelectedItem().toString();
		int pos = cod.indexOf(" |");
		int codAlbum = Integer.parseInt((String) cod.subSequence(0, pos));
		
		if(validar()) {
			dao.añadirCancion(codCancion, Integer.parseInt(textDuracion.getText()), textNombre.getText(), textAudio.getText(),codAlbum );
			
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
			JOptionPane.showMessageDialog(null, "Audio tiene que ser tio .wav","ERROR",JOptionPane.ERROR_MESSAGE);
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
		if(textDuracion.getText()==null) {
			JOptionPane.showMessageDialog(null, "Tienes que poner la duracion","ERROR",JOptionPane.ERROR_MESSAGE);
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
