package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Dao;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class VAnadirCanciones extends JDialog implements ActionListener{

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
	/**
	 * Create the dialog.
	 * @param dao2 
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VAnadirCanciones(Inicio_Sesion inicio_Sesion, boolean b, Dao dao) {
		this.dao=dao;
		this.setModal(b);
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AÑADIR CANCIONES");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setBounds(36, 41, 451, 61);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Duracion  :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(157, 160, 197, 51);
		contentPanel.add(lblNewLabel_1);
		
		textDuracion = new JTextField();
		textDuracion.setBounds(157, 221, 278, 41);
		contentPanel.add(textDuracion);
		textDuracion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre  :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(715, 166, 197, 51);
		contentPanel.add(lblNewLabel_1_1);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(710, 219, 278, 46);
		contentPanel.add(textNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Album  :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1_1.setBounds(159, 316, 197, 51);
		contentPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Artistas  :");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 27));
		lblNewLabel_1_1_1_1.setBounds(715, 316, 197, 51);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		table = new JTable();
		table.setBounds(159, 525, 431, 177);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPanel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(715, 377, 407, 139);
        contentPanel.add(scrollPane);
        
        comboAlbum = new JComboBox();
        comboAlbum.setMaximumRowCount(30);
        comboAlbum.setBounds(159, 388, 276, 41);
        contentPanel.add(comboAlbum,BorderLayout.CENTER);
        
        btnCrear = new JButton("CREAR");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
        btnCrear.setBounds(63, 516, 187, 101);
        contentPanel.add(btnCrear);
        btnCrear.addActionListener(this);
        
        btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
        btnVolver.setBounds(319, 516, 187, 101);
        contentPanel.add(btnVolver);
        btnVolver.addActionListener(this);
        
        textAudio = new JTextField();
        textAudio.setText("..\\\\FinalChalenge\\\\Audio\\\\");
        textAudio.setBounds(727, 616, 300, 35);
        contentPanel.add(textAudio);
        textAudio.setColumns(10);
		
		
		
		cargarComboAlbum();
		cargarTabla();
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCrear)) {
			crearCancion();
		}
		if(e.getSource().equals(btnVolver)) {
			volver();
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
			dao.anadirCancion(codCancion, Integer.parseInt(textDuracion.getText()), textNombre.getText(), textAudio.getText(),codAlbum );
			
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
