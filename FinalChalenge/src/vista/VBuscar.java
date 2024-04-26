package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Dao;
import modelo.Artista;
import modelo.Cancion;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class VBuscar extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JButton btnSecreto;
	private JButton btnInicio;
	private JButton btnLogo;
	private JList list;
	private Dao dao;
	private JLabel lblLogo;
	private JScrollPane scrollPane;
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 * @wbp.parser.constructor
	 */
	public VBuscar(VentanaPlay ventanaPlay, boolean modal, Dao dao) {
		super(ventanaPlay);
		setModal(modal);
		Pantalla(dao);
	}
	
	public VBuscar(VPrincipal vPrincipal, boolean modal, Dao dao) {
		super(vPrincipal);
		setModal(modal);
		Pantalla(dao);
	}
	
	public void Pantalla(Dao dao) {
		this.dao = dao;
		
		setBackground(new Color(64, 128, 128));	
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnInicio = new JButton("");
			btnInicio.setIcon(new ImageIcon("..\\FinalChalengel\\Img\\casaInicio.png"));
			btnInicio.setBackground(new Color(64, 128, 128));
			btnInicio.setBounds(481, 641, 47, 48);
			btnInicio.setOpaque(false);
			btnInicio.setBorderPainted(false);
			btnInicio.setFocusable(false);
			btnInicio.addActionListener(this);
			contentPanel.add(btnInicio);
			
		}
		{
			JLabel lblInicio = new JLabel("Inicio");
			lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
			lblInicio.setBounds(481, 688, 56, 24);
			
			contentPanel.add(lblInicio);
		}
		{
			JButton btnBuscar = new JButton("");
			btnBuscar.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\lupaBuscar.png"));
			btnBuscar.setBackground(new Color(64, 128, 128));
			btnBuscar.setBounds(656, 632, 58, 57);
			btnBuscar.setOpaque(false);
			btnBuscar.setBorderPainted(false);
			btnBuscar.setFocusable(false);
			contentPanel.add(btnBuscar);
		}
		{
			JLabel lblBuscar = new JLabel("Buscar");
			lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
			lblBuscar.setBounds(650, 688, 78, 24);
			contentPanel.add(lblBuscar);
		}
		
		txtBuscar = new JTextField();
		txtBuscar.setText("Buscar");
		txtBuscar.setForeground(new Color(192, 192, 192));
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 38));
		txtBuscar.setBounds(208, 29, 846, 69);
		txtBuscar.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Método llamado cada vez que se inserta un carácter en el JTextField
                cargarLista();
            }

			@Override
            public void removeUpdate(DocumentEvent e) {
                // Método llamado cada vez que se elimina un carácter en el JTextField
				cargarLista();
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
		list.setFont(new Font(""+ "Felix Titling", Font.PLAIN, 32));
		list.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				escucharCancion();
			}
		});
		
		contentPanel.add(list);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(208, 99, 846, 368);
		list.setVisible(false);
		scrollPane.setVisible(false);
		contentPanel.add(scrollPane);
		
		btnSecreto = new JButton("");
		btnSecreto.setBackground(new Color(64, 128, 128));
		btnSecreto.setBounds(0, 0, 11, 9);
		contentPanel.add(btnSecreto);
		{
			btnLogo = new JButton("");
			btnLogo.setForeground(new Color(64, 128, 128));
			btnLogo.setIcon(new ImageIcon("..\\FinalChalenge\\Img\\logoPequeña.png"));
			btnLogo.setBackground(new Color(64, 128, 128));
			btnLogo.setBounds(1181, 10, 56, 75);
			contentPanel.add(btnLogo);
			btnLogo.setOpaque(false);
			btnLogo.setBorderPainted(false);
			btnLogo.setFocusable(false);
			btnLogo.addActionListener(this);
		}
		

	
	}

	protected void escucharCancion() {
		// TODO Auto-generated method stub
		Cancion can = new Cancion();
		String nombre = list.getSelectedValue().toString();
		int pos = nombre.indexOf("  ");
		int posicion = nombre.indexOf("   |");
		nombre = nombre.substring(pos+2, posicion);
		for(Cancion c:canciones) {
			if(c.getNombreCancion().equalsIgnoreCase(nombre)) {
				can=c;
			}
		}
		this.dispose();
		VentanaPlay play = new VentanaPlay(null, can, true, dao);
		play.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInicio)) {
			volverInicio();
		}
		if(e.getSource().equals(btnLogo)) {
			irCerrar_Sesion();
		}
	}

	
	private void cargarLista() {
		// TODO Auto-generated method stub
		canciones = dao.sacarCanciones();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		ArrayList<Cancion> cancion = new ArrayList<Cancion> ();
		
		//introducir primero los que empiezen igual
		for (Cancion can : canciones) {
			if (can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+saberArtistas(can.getCodCancion()));
				list.setModel(listModel);
			}
		}
		//introducir las que tengan letras en comun
		for (Cancion can : canciones) {
			if(can.getNombreCancion().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
				listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+saberArtistas(can.getCodCancion()));
				list.setModel(listModel);
			}
		}
		
	
	}
	  
	private String saberArtistas(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = dao.artistasPorCancion(cod);
		String artista = artistas.get(0).getNombreArtistico();;
		
		if(artistas.size()>1) {
			for(int i = 1; i < artistas.size(); i++) {
				artista  = artista +", "+artistas.get(i).getNombreArtistico();
			}
		
		}
		return artista;
	}
	

	private void irCerrar_Sesion() {
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
		ven.setVisible(true);
	}

	private void volverInicio() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}
}
