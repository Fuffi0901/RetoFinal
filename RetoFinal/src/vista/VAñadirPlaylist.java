package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Dao;
import modelo.Cancion;
import modelo.Playlist;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class VAñadirPlaylist extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private Usuario usu;
	private JButton btnAñadirP;
	private JLabel lblNewLabel_2;
	private JList listCanP;
	private Dao dao;
	private JComboBox comboP;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JList list;
	private JTextField txtBuscar;
	private JButton btnSecreto;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneCanP;
	private ArrayList<Cancion> cancionesPlaylist = new ArrayList<Cancion>();
	private JButton btnañadirCancion;
	private JButton btnQuitarCancion;
	private JTextField textFoto;
	private JButton btnArchivos;
	private JButton btnAtras;
	private JFileChooser fc;
	private File file;
	private String carpetaFinal = "..\\RetoFinal\\Img\\";
	private JFrame ven;
	private JButton btnXp;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VAñadirPlaylist(VPrincipal vPrincipal,boolean b, Dao dao,Usuario usu) {
		super(vPrincipal);
		this.setModal(b);
		this.dao=dao;
		this.usu=usu;
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(75, 75, 75));
		btnAtras.setIcon(new ImageIcon("..\\RetoFinal\\Img\\flechaAtras.png"));
		btnAtras.setBounds(10, 10, 36, 32);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusable(false);
		contentPanel.add(btnAtras);
		btnAtras.addActionListener(this);
		
		
		JLabel lblNewLabel = new JLabel("PLAYLIST");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 50));
		lblNewLabel.setBounds(119, 25, 258, 55);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre : ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(140, 199, 193, 30);
		contentPanel.add(lblNewLabel_1);
		
		textNombre = new JTextField("");
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textNombre.setBounds(140, 239, 400, 47);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAñadirP = new JButton("Añadir");
		btnAñadirP.setFont(new Font("Arial", Font.BOLD, 20));
		btnAñadirP.setBounds(1084, 160, 151, 55);
		btnAñadirP.addActionListener(this);
		contentPanel.add(btnAñadirP);
		
		lblNewLabel_2 = new JLabel("Canciones : ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(692, 340, 193, 30);
		contentPanel.add(lblNewLabel_2);
		
		listCanP = new JList();
		listCanP.setFont(new Font("Felix Titling", Font.PLAIN, 22));
		listCanP.setBounds(20, 10, 846, 368);
		contentPanel.add(listCanP);
		
		JScrollPane scrollPaneCanP;
		scrollPaneCanP = new JScrollPane(listCanP);
		scrollPaneCanP.setBounds(692, 380, 367, 158);
		contentPanel.add(scrollPaneCanP);
		
		comboP = new JComboBox();
		comboP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboP.addActionListener(this);
		comboP.setBounds(795, 31, 203, 51);
		contentPanel.add(comboP);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 20));
		btnModificar.setBounds(1084, 27, 151, 55);
		btnModificar.addActionListener(this);
		contentPanel.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btnEliminar.setBounds(1084, 92, 151, 55);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);
		
		txtBuscar = new JTextField();
		txtBuscar.setText("Buscar");
		txtBuscar.setForeground(new Color(192, 192, 192));
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtBuscar.setBounds(140, 343, 400, 38);
		txtBuscar.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Método llamado cada vez que se inserta un carácter en el JTextField
                cargarListaBuscar();
            }

			@Override
            public void removeUpdate(DocumentEvent e) {
                // Método llamado cada vez que se elimina un carácter en el JTextField
				cargarListaBuscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método se llama cuando el estilo del texto se modifica, no aplicable en JTextField
            }
        });
		
		
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (txtBuscar.getText().equals("Buscar")) {
	                	txtBuscar.setText("");  
	                	scrollPane.setVisible(true);
	                	list.setVisible(true);
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (txtBuscar.getText().isEmpty() && list.isSelectionEmpty()) {
	                	txtBuscar.setText("Buscar");
	                }
	            }
	        });
			
		    
		list = new JList<>();
		list.setBounds(208, 99, 846, 368);
		list.setFont(new Font("Felix Titling", Font.PLAIN, 22));
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//pasarCancionListCan();
			}
		});
		
		contentPanel.add(list);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(140, 380, 400, 158);
		contentPanel.add(scrollPane);
		
		
		
		btnañadirCancion = new JButton("+");
		btnañadirCancion.setFont(new Font("Tahoma", Font.PLAIN, 33));
		btnañadirCancion.setBounds(572, 386, 85, 47);
		btnañadirCancion.addActionListener(this);
		contentPanel.add(btnañadirCancion);
		
		btnQuitarCancion = new JButton("-");
		btnQuitarCancion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnQuitarCancion.setBounds(572, 471, 85, 47);
		btnQuitarCancion.addActionListener(this);
		contentPanel.add(btnQuitarCancion);
		
		textFoto = new JTextField();
		textFoto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFoto.setColumns(10);
		textFoto.setBounds(140, 608, 400, 47);
		contentPanel.add(textFoto);
		
		btnArchivos = new JButton(". . .");
		btnArchivos.setBounds(561, 608, 85, 47);
		btnArchivos.addActionListener(this);
		contentPanel.add(btnArchivos);
		
		btnXp = new JButton("X");
		btnXp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXp.setBounds(1008, 31, 51, 51);
		btnXp.addActionListener(this);
		contentPanel.add(btnXp);
		
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
		
		
		
		cargarComboPlaylist();
		cargarDatos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAñadirP)) {
			añadirPlaylist();
		}
		if(e.getSource().equals(btnModificar)) {
			modificarPlaylist();
		}
		if(e.getSource().equals(btnEliminar)) {
			eliminarPlaylist();
		}
		if(e.getSource().equals(btnañadirCancion)) {
			pasarCancionListCan();		
		}
		if(e.getSource().equals(btnQuitarCancion)) {
			quitarCancionlistCan();
		}
		if(e.getSource().equals(btnArchivos)) {
			cogerFoto();
		}
		if(e.getSource().equals(comboP)) {
			cargarDatos();
		}
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
		if(e.getSource().equals(btnXp)) {
			comboP.setSelectedIndex(-1);
		}
	}
	

	private void volverAtras() {
		// TODO Auto-generated method stub
		this.dispose();
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}


	private void añadirPlaylist() {
		// TODO Auto-generated method stub
		int cod = dao.crearCodigoPlaylist();
		dao.insertarPlaylist(cod,  textNombre.getText(), textFoto.getText(), usu.getDni());
		for(Cancion c:cancionesPlaylist) {
			dao.insertarPertenece(cod,c.getCodCancion());
		}
	}
	

	private void modificarPlaylist() {
		// TODO Auto-generated method stub
		
		dao.modificarPlaylist(sacarSeleccionado().getCodPlaylist(), textNombre.getText(), textFoto.getText());
		dao.eliminarPertenece(sacarSeleccionado().getCodPlaylist());
		for(Cancion c:cancionesPlaylist) {
			dao.insertarPertenece(sacarSeleccionado().getCodPlaylist(),c.getCodCancion());
		}
		comboP.setSelectedIndex(-1);
	}
	
	
	private void eliminarPlaylist() {
		// TODO Auto-generated method stub
		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar Borando la Playlist "+sacarSeleccionado().getNombrePlaylist()+" ?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.eliminarplaylist(sacarSeleccionado().getCodPlaylist());
			JOptionPane.showMessageDialog(null, "Se ha borrado la Playlist : "+sacarSeleccionado().getNombrePlaylist(),"Playlist",JOptionPane.INFORMATION_MESSAGE);
			cargarComboPlaylist();
			comboP.setSelectedIndex(-1);
		}else {
			JOptionPane.showMessageDialog(null, "Se ha Mantiene la Playlist : "+sacarSeleccionado().getNombrePlaylist(),"Playlist",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private Playlist sacarSeleccionado() {
		ArrayList<Playlist> playl = dao.sacarPlaylistUsuario(usu.getDni());
		int pos = comboP.getSelectedIndex();
		Playlist seleccionado = playl.get(pos);
		return seleccionado;
	}
	
	private void cogerFoto() {
		// TODO Auto-generated method stub
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen","jpg");
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
		textFoto.setText(Paths.get(file.getAbsolutePath()).toString());
	}

	private void cargarComboPlaylist() {
		// TODO Auto-generated method stub
		ArrayList<Playlist> playl = dao.sacarPlaylistUsuario(usu.getDni());
		for(Playlist p:playl) {
			comboP.addItem(p.getNombrePlaylist());
		}
		comboP.setSelectedIndex(-1);
	}


	
	
	private void cargarDatos() {
		
		if(comboP.getSelectedIndex()!=-1) {
			
			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnAñadirP.setEnabled(false);
			textNombre.setText(sacarSeleccionado().getNombrePlaylist());
			textFoto.setText(sacarSeleccionado().getFotoPlaylist());
			cancionesPlaylist=dao.CancionesDePlaylist(sacarSeleccionado().getCodPlaylist());
			cargarlisCan();
			
		}else {
			btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);
			btnAñadirP.setEnabled(true);
			textNombre.setText(null);
			textFoto.setText(null);
			cancionesPlaylist.removeAll(cancionesPlaylist);
			cargarlisCan();
		}
	}
	
	
	private void cargarListaBuscar() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		//introducir primero los que empiezen igual
		for (Cancion can : canciones) {
			if (can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement(can.getNombreCancion());
				list.setModel(listModel);
			}
		}
		//introducir las que tengan letras en comun
		for (Cancion can : canciones) {
			if(can.getNombreCancion().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement(can.getNombreCancion() );
				list.setModel(listModel);
			}
		}
		
	
	}
	
	
	private void quitarCancionlistCan() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		int pos = list.getSelectedIndex();
		Cancion cancionSeleccionada = canciones.get(pos);
		boolean esta=false;
		for (int i=0; i<cancionesPlaylist.size();i++) {
			if(cancionesPlaylist.get(i).getNombreCancion().equalsIgnoreCase(cancionSeleccionada.getNombreCancion())) {
				esta=true;
				cancionesPlaylist.remove(i);
			}
		}
		if(!esta) {
			JOptionPane.showMessageDialog(null, "Esa Cancion no esta en esta playlist","Playlist",JOptionPane.ERROR_MESSAGE);
		}
		cargarlisCan();
		
	}
	
	
	private void cargarlisCan() {
		// TODO Auto-generated method stub
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Cancion can : cancionesPlaylist) {
			model.addElement(can.getNombreCancion());
		}
		listCanP.setModel(model);
	}

	private void pasarCancionListCan() {
		// TODO Auto-generated method stub
		boolean bien=true;
		ArrayList<Cancion> canciones = dao.sacarCanciones();
		int pos = list.getSelectedIndex();
		Cancion cancionSeleccionada = canciones.get(pos);
		for (Cancion can : cancionesPlaylist) {
			if(can.getNombreCancion().equalsIgnoreCase(cancionSeleccionada.getNombreCancion())) {
				bien=false;
			}
		}
		if(bien) {
			cancionesPlaylist.add(cancionSeleccionada);
			cargarlisCan();
		}else {
			JOptionPane.showMessageDialog(null, "Esa Cancion ya esta","Repetida",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
