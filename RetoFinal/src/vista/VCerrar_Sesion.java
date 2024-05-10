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
	private Usuario usu;
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
	private JButton btnModificar;
	private JButton btnEliminar;

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
	}
	
	public VCerrar_Sesion(VPrincipal vPrincipal, boolean modal, Dao dao) {
		super(vPrincipal);
		setModal(modal);
		pantalla(dao);
	}

	public VCerrar_Sesion(VListaCanciones vListaCanciones, boolean modal, Dao dao) {
		super(vListaCanciones);
		setModal(modal);
		pantalla(dao);	}

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
			lbTitulo.setBounds(40, 27, 657, 88);
			lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));			
			contentPanel.add(lbTitulo);
		}
		
			btnCerrarSesion = new JButton("CERRAR SESION");
			btnCerrarSesion.setForeground(new Color(255, 255, 255));
			btnCerrarSesion.setBackground(new Color(64, 128, 128));
			btnCerrarSesion.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 35));
			btnCerrarSesion.setBounds(845, 544, 353, 79);
			btnCerrarSesion.setOpaque(false);
			btnCerrarSesion.setBorderPainted(false);
			btnCerrarSesion.setFocusable(false);
			contentPanel.add(btnCerrarSesion);
			btnCerrarSesion.addActionListener(this);
			
			JLabel lblNewLabel = new JLabel("DNI :");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblNewLabel.setBounds(69, 137, 175, 44);
			contentPanel.add(lblNewLabel);
			
			textDni = new JTextField();
			textDni.setForeground(new Color(255, 255, 255));
			textDni.setBounds(69, 187, 255, 31);
			textDni.setOpaque(false);
	        textDni.setBorder(border);
			textDni.setColumns(10);
			contentPanel.add(textDni);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblNombre.setBounds(69, 265, 175, 44);
			contentPanel.add(lblNombre);
			
			textNombre = new JTextField();
			textNombre.setOpaque(false);
			textNombre.setBorder(border);
			textNombre.setForeground(Color.WHITE);
			textNombre.setColumns(10);
			textNombre.setBounds(69, 315, 255, 31);
			contentPanel.add(textNombre);
			
			JLabel lblApellido = new JLabel("Apellido");
			lblApellido.setForeground(Color.WHITE);
			lblApellido.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblApellido.setBounds(402, 265, 175, 44);
			contentPanel.add(lblApellido);
			
			textApellido = new JTextField();
			textApellido.setOpaque(false);
			textApellido.setForeground(Color.WHITE);
			textApellido.setBorder(border);
			textApellido.setColumns(10);
			textApellido.setBounds(402, 320, 255, 31);
			contentPanel.add(textApellido);
			
			JLabel lblPais = new JLabel("Pais");
			lblPais.setForeground(Color.WHITE);
			lblPais.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblPais.setBounds(69, 403, 175, 44);
			contentPanel.add(lblPais);
			
			textPais = new JTextField();
			textPais.setOpaque(false);
			textPais.setForeground(Color.WHITE);
			textPais.setBorder(border);
			textPais.setColumns(10);
			textPais.setBounds(69, 453, 255, 31);
			contentPanel.add(textPais);
			
			JLabel lblEdad = new JLabel("Edad");
			lblEdad.setForeground(Color.WHITE);
			lblEdad.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblEdad.setBounds(402, 401, 175, 44);
			contentPanel.add(lblEdad);
			
			textEdad = new JTextField();
			textEdad.setOpaque(false);
			textEdad.setForeground(Color.WHITE);
			textEdad.setBorder(border);
			textEdad.setColumns(10);
			textEdad.setBounds(402, 453, 255, 31);
			contentPanel.add(textEdad);
			
			JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
			lblNombreUsuario.setForeground(Color.WHITE);
			lblNombreUsuario.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblNombreUsuario.setBounds(1017, 139, 201, 44);
			contentPanel.add(lblNombreUsuario);
			
			textNombreUsuario = new JTextField();
			textNombreUsuario.setOpaque(false);
			textNombreUsuario.setForeground(Color.WHITE);
			textNombreUsuario.setBorder(border);
			textNombreUsuario.setColumns(10);
			textNombreUsuario.setBounds(943, 187, 255, 31);
			contentPanel.add(textNombreUsuario);
			
			JLabel lblContrasea = new JLabel("Contraseña");
			lblContrasea.setForeground(Color.WHITE);
			lblContrasea.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
			lblContrasea.setBounds(1070, 265, 135, 44);
			contentPanel.add(lblContrasea);
			
			pFConstraseña = new JPasswordField();
			pFConstraseña.setForeground(new Color(255, 255, 255));
			pFConstraseña.setOpaque(false);
			pFConstraseña.setBorder(border);
			pFConstraseña.setBounds(943, 315, 255, 31);
			contentPanel.add(pFConstraseña);
			
			
			
	        
			rbMostrarConstraseña = new JRadioButton();
			rbMostrarConstraseña.setOpaque(false);
			rbMostrarConstraseña.setBounds(1181, 352, 28, 35);
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
			lblNewLabel_1.setBounds(1017, 356, 158, 23);
			contentPanel.add(lblNewLabel_1);
			
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnModificar.setOpaque(false);
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 16));
			btnModificar.setFocusable(false);
			btnModificar.setBorderPainted(false);
			btnModificar.setBackground(new Color(64, 128, 128));
			btnModificar.setBounds(869, 625, 169, 44);
			contentPanel.add(btnModificar);
			
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setOpaque(false);
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 16));
			btnEliminar.setFocusable(false);
			btnEliminar.setBorderPainted(false);
			btnEliminar.setBackground(new Color(64, 128, 128));
			btnEliminar.setBounds(1017, 625, 159, 44);
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
		VPrincipal prin= new VPrincipal(this, true, dao);
		usu=(Usuario) dao.comprobarPersona(prin.devolverUsuario().getDni());
		usu.setNombreUsuario(prin.devolverUsuario().getNombreUsuario());
		usu.setConstraseña(prin.devolverUsuario().getConstraseña());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCerrarSesion)) {
			volverInicio_Sesion();
		}
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
