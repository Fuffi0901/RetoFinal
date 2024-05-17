package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Dao;
import excepciones.CreateException;
import modelo.Persona;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class VCerrar_Sesion extends JDialog implements ActionListener{
	
	private JButton btnCerrarSesion;
	private Dao dao;
	private Usuario usu = new Usuario();
	private final JPanel contentPanel = new JPanel();
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textPais;
	private JTextField textEdad;
	private JTextField textNombreUsuario;
	private JPasswordField pFConstraseña;
	private JRadioButton rbMostrarConstraseña;
	private JLabel lbTitulo;
	private JLabel lblNewLabel_1;
	private JButton btnModificar, btnAtras,btnEliminar;

	/**
	 * Create the dialog.
	 * @param b 
	 * @param VCerrar_Sesion 
	 * @wbp.parser.constructor
	 */
	
	public VCerrar_Sesion(VentanaPlay ventanaPlay, boolean modal, Dao dao) {
		super(ventanaPlay);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}
	
	public VCerrar_Sesion(VPrincipal vPrincipal, boolean modal, Dao dao) {
		super(vPrincipal);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}

	public VCerrar_Sesion(VListaCanciones vListaCanciones, boolean modal, Dao dao) {
		super(vListaCanciones);
		setModal(modal);
		pantalla(dao);
		setLocationRelativeTo(null);
	}

	public void pantalla(Dao dao) {
		this.dao = dao;
		cogerUsuario();
		
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		setBackground(new Color(78, 78, 78));
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lbTitulo = new JLabel("");
			lbTitulo.setForeground(new Color(0, 0, 0));
			lbTitulo.setBounds(56, 41, 657, 88);
			lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));			
			contentPanel.add(lbTitulo);
		}
			
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(75, 75, 75));
		btnAtras.setIcon(new ImageIcon("..\\RetoFinal\\Img\\flechaAtras.png"));
		btnAtras.setBounds(10, 10, 36, 32);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setFocusable(false);
		contentPanel.add(btnAtras);
		btnAtras.addActionListener(this);
		
			btnCerrarSesion = new JButton("CERRAR SESION");
			btnCerrarSesion.setForeground(new Color(255, 255, 255));
			btnCerrarSesion.setBackground(new Color(64, 128, 128));
			btnCerrarSesion.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 35));
			btnCerrarSesion.setBounds(843, 555, 353, 79);
			btnCerrarSesion.setOpaque(false);
			btnCerrarSesion.setBorderPainted(false);
			btnCerrarSesion.setFocusable(false);
			contentPanel.add(btnCerrarSesion);
			btnCerrarSesion.addActionListener(this);
			
			JLabel lblNewLabel = new JLabel("DNI :");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblNewLabel.setBounds(67, 148, 175, 44);
			contentPanel.add(lblNewLabel);
			
			textDni = new JTextField();
			textDni.setForeground(new Color(255, 255, 255));
			textDni.setBounds(67, 198, 275, 31);
			textDni.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 19));
			textDni.setEditable(false);
			textDni.setOpaque(false);
	        textDni.setBorder(border);
			textDni.setColumns(10);
			contentPanel.add(textDni);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblNombre.setBounds(67, 276, 175, 44);
			contentPanel.add(lblNombre);
			
			textNombre = new JTextField();
			textNombre.setOpaque(false);
			textNombre.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 19));
			textNombre.setBorder(border);
			textNombre.setForeground(Color.WHITE);
			textNombre.setColumns(10);
			textNombre.setBounds(67, 326, 275, 31);
			contentPanel.add(textNombre);
			
			JLabel lblApellido = new JLabel("Apellido");
			lblApellido.setForeground(Color.WHITE);
			lblApellido.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblApellido.setBounds(400, 276, 175, 44);
			contentPanel.add(lblApellido);
			
			textApellido = new JTextField();
			textApellido.setOpaque(false);
			textApellido.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 19));
			textApellido.setForeground(Color.WHITE);
			textApellido.setBorder(border);
			textApellido.setColumns(10);
			textApellido.setBounds(400, 326, 275, 31);
			contentPanel.add(textApellido);
			
			JLabel lblPais = new JLabel("Pais");
			lblPais.setForeground(Color.WHITE);
			lblPais.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblPais.setBounds(67, 414, 175, 44);
			contentPanel.add(lblPais);
			
			textPais = new JTextField();
			textPais.setOpaque(false);
			textPais.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 19));
			textPais.setForeground(Color.WHITE);
			textPais.setBorder(border);
			textPais.setColumns(10);
			textPais.setBounds(67, 464, 275, 31);
			contentPanel.add(textPais);
			
			JLabel lblEdad = new JLabel("Edad");
			lblEdad.setForeground(Color.WHITE);
			lblEdad.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblEdad.setBounds(400, 412, 175, 44);
			contentPanel.add(lblEdad);
			
			textEdad = new JTextField();
			textEdad.setOpaque(false);
			textEdad.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 19));
			textEdad.setForeground(Color.WHITE);
			textEdad.setBorder(border);
			textEdad.setColumns(10);
			textEdad.setBounds(400, 464, 275, 31);
			contentPanel.add(textEdad);
			
			JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
			lblNombreUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNombreUsuario.setForeground(Color.WHITE);
			lblNombreUsuario.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblNombreUsuario.setBounds(970, 150, 246, 44);
			contentPanel.add(lblNombreUsuario);
			
			textNombreUsuario = new JTextField();
			textNombreUsuario.setOpaque(false);
			textNombreUsuario.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 21));
			textNombreUsuario.setForeground(Color.WHITE);
			textNombreUsuario.setBorder(border);
			textNombreUsuario.setColumns(10);
			textNombreUsuario.setBounds(941, 198, 275, 31);
			contentPanel.add(textNombreUsuario);
			
			JLabel lblContrasea = new JLabel("Contraseña");
			lblContrasea.setHorizontalAlignment(SwingConstants.TRAILING);
			lblContrasea.setForeground(Color.WHITE);
			lblContrasea.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 32));
			lblContrasea.setBounds(1041, 276, 175, 44);
			contentPanel.add(lblContrasea);
			
			pFConstraseña = new JPasswordField();
			pFConstraseña.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 21));
			pFConstraseña.setForeground(new Color(255, 255, 255));
			pFConstraseña.setOpaque(false);
			pFConstraseña.setBorder(border);
			pFConstraseña.setBounds(941, 326, 275, 31);
			contentPanel.add(pFConstraseña);
			
			
			
	        
			rbMostrarConstraseña = new JRadioButton();
			rbMostrarConstraseña.setOpaque(false);
			rbMostrarConstraseña.setBounds(1202, 363, 28, 31);
			rbMostrarConstraseña.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					mostrarConstraseña();
				}
			});
			contentPanel.add(rbMostrarConstraseña);
			
			lblNewLabel_1 = new JLabel("Mostrar Contraseña");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(1038, 363, 158, 31);
			contentPanel.add(lblNewLabel_1);
			
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setOpaque(false);
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 16));
			btnModificar.setFocusable(false);
			btnModificar.setBorderPainted(false);
			btnModificar.setBackground(new Color(64, 128, 128));
			btnModificar.setBounds(867, 636, 169, 44);
			contentPanel.add(btnModificar);
			
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setOpaque(false);
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 16));
			btnEliminar.setFocusable(false);
			btnEliminar.setBorderPainted(false);
			btnEliminar.setBackground(new Color(64, 128, 128));
			btnEliminar.setBounds(1015, 636, 159, 44);
			btnEliminar.addActionListener(this);
			contentPanel.add(btnEliminar);
			
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
			
			cargarDatos();
	}

	

	private void cogerUsuario() {
		// TODO Auto-generated method stub
		Inicio_Sesion ini = new Inicio_Sesion(dao);	
		Usuario u = ini.devolverUsuario();
		Persona per =  dao.comprobarPersona(u.getDni());
		usu.setDni(per.getDni());
		usu.setNombrePersona(per.getNombrePersona());
		usu.setApellidoPersona(per.getApellidoPersona());
		usu.setEdad(per.getEdad());
		usu.setPais(per.getPais());
		
		usu.setNombreUsuario(u.getNombreUsuario());
		usu.setConstraseña(u.getConstraseña());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCerrarSesion)) {
			volverInicio_Sesion();
		}
		if(e.getSource().equals(btnModificar)) {
			modificarUsu();
		}
		if(e.getSource().equals(btnEliminar)) {
			eliminarUsu();
		}
		if(e.getSource().equals(btnAtras)) {
			volverAtras();
		}
	}
	
	
	private void eliminarUsu() {
		// TODO Auto-generated method stub
		int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere eliminar esta cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			dao.eliminarUsuario(textDni.getText());
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente",usu.getDni(),JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Se mantendran la cuenta de "+usu.getNombreUsuario(),usu.getDni(),JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void modificarUsu() {
		// TODO Auto-generated method stub
		if(validar()) {
			Usuario usuario = new Usuario();
			usuario.setDni(textDni.getText());
			usuario.setNombrePersona(textNombre.getText());
			usuario.setApellidoPersona(textApellido.getText());
			usuario.setPais(textPais.getText());
			usuario.setEdad(Integer.parseInt(textEdad.getText()));
			usuario.setNombreUsuario(textNombreUsuario.getText());
			usuario.setConstraseña(pFConstraseña.getText());
			int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere modificar tus datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				dao.modificarUsuario(usuario);
				JOptionPane.showMessageDialog(null, "Se ha modificado correctamente",usu.getDni(),JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Se mantendran los datos",usu.getDni(),JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Error al Modificar a "+textDni.getText(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private boolean validar() {
		// TODO Auto-generated method stub
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED);
		Border bordernormal = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		boolean bien = true;
		if(textNombre.getText().isEmpty()) {
			bien=false;
			textNombre.setBorder(border);
		}
		if(textApellido.getText().isEmpty()) {
			bien=false;
			textApellido.setBorder(border);
		}
		if(textPais.getText().isEmpty()) {
			bien=false;
			textPais.setBorder(border);
		}
		if(textNombreUsuario.getText().isEmpty()) {
			bien=false;
			textNombreUsuario.setBorder(border);
		}
		if(pFConstraseña.getText().isEmpty()) {
			bien=false;
			pFConstraseña.setBorder(border);
		}
		if(textEdad.getText().isEmpty() || !textEdad.getText().matches(".*\\d.*")) {
			bien=false;
			textEdad.setBorder(border);
		}
		
		if(bien) {
			textNombre.setBorder(bordernormal);
			textApellido.setBorder(bordernormal);
			textPais.setBorder(bordernormal);
			textNombreUsuario.setBorder(bordernormal);
			textEdad.setBorder(bordernormal);
			pFConstraseña.setBorder(bordernormal);
		}else {
			CreateException.mensajeRegistro();
		}
		return bien;
	}

	private void volverAtras() {
		// TODO Auto-generated method stub
		this.dispose();
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}
	
	private void cargarDatos() {
		// TODO Auto-generated method stub
		lbTitulo.setText(usu.getNombreUsuario());
		textDni.setText(usu.getDni());
		textNombre.setText(usu.getNombrePersona());
		textApellido.setText(usu.getApellidoPersona());
		textEdad.setText(String.valueOf(usu.getEdad()));
		textPais.setText(usu.getPais());
		textNombreUsuario.setText(usu.getNombreUsuario());
		pFConstraseña.setText(usu.getConstraseña());
	}

	private void volverInicio_Sesion() {
		int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere cerrar sesion?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			Inicio_Sesion ven = new Inicio_Sesion(dao);
			ven.setVisible(true);
		}
		
	}
	
	private void mostrarConstraseña() {
		if (rbMostrarConstraseña.isSelected()) {
            // Mostrar contraseña
			pFConstraseña.setEchoChar((char) 0);
        } else {
            // Ocultar contraseña
        	pFConstraseña.setEchoChar('\u2022');
        }
	}
		
	private void volverVPrincipal() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}
}
