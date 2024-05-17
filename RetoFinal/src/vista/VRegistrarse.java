package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import excepciones.CreateException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class VRegistrarse extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tFDni;
	private JTextField tFNombre;
	private JTextField tFApellido;
	private JTextField tFPais;
	private JTextField tFEdad;
	private JTextField tFNombreUsuario;
	private JTextField tFContrasena;
	private JButton btnVolver;
	private JButton btnRegistrar;
	private JLabel lblLogo;
	private Dao dao;
	private JLabel lblNewLabel;
	private JButton btnLogo;

	/**
	 * Create the dialog.
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VRegistrarse(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dam\\Desktop\\PGR\\3ª Eva\\RetoFinal\\Img\\logoPequeña.png"));
		this.dao = dao;
		setBackground(new Color(78, 78, 78));
		setModal(modal);
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("REGISTRAR USUARIO");
		lbTitulo.setBackground(new Color(0, 128, 255));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setForeground(new Color(75, 75, 75));
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
		lbTitulo.setBounds(10, 10, 1225, 105);
		contentPanel.add(lbTitulo);
		
		JLabel lbDni = new JLabel("DNI:");
		lbDni.setForeground(new Color(255, 255, 255));
		lbDni.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lbDni.setBounds(83, 136, 208, 46);
		contentPanel.add(lbDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblNombre.setBounds(83, 206, 208, 46);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblApellido.setBounds(83, 276, 208, 46);
		contentPanel.add(lblApellido);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setForeground(new Color(255, 255, 255));
		lblPais.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblPais.setBounds(83, 346, 208, 46);
		contentPanel.add(lblPais);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(new Color(255, 255, 255));
		lblEdad.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblEdad.setBounds(83, 416, 208, 46);
		contentPanel.add(lblEdad);
		
		JLabel lblNombreUsuario = new JLabel("Usuario");
		lblNombreUsuario.setForeground(new Color(255, 255, 255));
		lblNombreUsuario.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblNombreUsuario.setBounds(83, 486, 208, 46);
		contentPanel.add(lblNombreUsuario);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
		lblContrasena.setBounds(83, 556, 208, 46);
		contentPanel.add(lblContrasena);
		
		tFDni = new JTextField();
		tFDni.setBackground(new Color(255, 255, 255));
		tFDni.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFDni.setBounds(329, 136, 480, 46);
		contentPanel.add(tFDni);
		tFDni.setColumns(10);
		
		tFNombre = new JTextField();
		tFNombre.setBackground(new Color(255, 255, 255));
		tFNombre.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFNombre.setColumns(10);
		tFNombre.setBounds(329, 206, 480, 46);
		contentPanel.add(tFNombre);
		
		tFApellido = new JTextField();
		tFApellido.setBackground(new Color(255, 255, 255));
		tFApellido.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFApellido.setColumns(10);
		tFApellido.setBounds(329, 276, 480, 46);
		contentPanel.add(tFApellido);
		
		tFPais = new JTextField();
		tFPais.setBackground(new Color(255, 255, 255));
		tFPais.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFPais.setColumns(10);
		tFPais.setBounds(329, 346, 480, 46);
		contentPanel.add(tFPais);
		
		tFEdad = new JTextField();
		tFEdad.setBackground(new Color(255, 255, 255));
		tFEdad.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFEdad.setColumns(10);
		tFEdad.setBounds(329, 416, 480, 46);
		contentPanel.add(tFEdad);
		
		tFNombreUsuario = new JTextField();
		tFNombreUsuario.setBackground(new Color(255, 255, 255));
		tFNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFNombreUsuario.setColumns(10);
		tFNombreUsuario.setBounds(329, 486, 480, 46);
		contentPanel.add(tFNombreUsuario);
		
		tFContrasena = new JTextField();
		tFContrasena.setBackground(new Color(255, 255, 255));
		tFContrasena.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFContrasena.setColumns(10);
		tFContrasena.setBounds(329, 556, 480, 46);
		contentPanel.add(tFContrasena);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(64, 128, 128));
		btnRegistrar.setFont(new Font("Imprint MT Shadow", Font.BOLD, 26));
		btnRegistrar.setBounds(1012, 549, 183, 53);
		btnRegistrar.setOpaque(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setFocusable(false);
		contentPanel.add(btnRegistrar);
		btnRegistrar.addActionListener(this);
		
		btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(64, 128, 128));
		btnVolver.setFont(new Font("Imprint MT Shadow", Font.BOLD, 26));
		btnVolver.setBounds(819, 549, 183, 53);
		btnVolver.setOpaque(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setFocusable(false);
		contentPanel.add(btnVolver);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		lblLogo.setBounds(1181, 10, 56, 75);
		contentPanel.add(lblLogo);
		
		lblNewLabel = new JLabel("© 2024 BeatDam Application. All rights reserved. Created by Asier Del Campo, Guillermo Flecha, Erlantz Rey, Andoni García.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Eras Light ITC", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 689, 1225, 13);
		contentPanel.add(lblNewLabel);
		
		btnLogo = new JButton("");
		btnLogo.setIcon(new ImageIcon("C:\\Users\\1dam\\Desktop\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setOpaque(false);
		btnLogo.setForeground(new Color(64, 128, 128));
		btnLogo.setFocusable(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setBounds(1181, 10, 56, 75);
		contentPanel.add(btnLogo);
		btnVolver.addActionListener(this);
		
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

		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			volverInicio_Sesion();
		}
		if(e.getSource().equals(btnRegistrar)) {
			hacerRegistro();
		}
	}
	
	private void hacerRegistro() {
		// TODO Auto-generated method stub
		
		if(validar()) {
			dao.registrarPersona(tFDni.getText(),tFNombre.getText(),tFApellido.getText(),tFPais.getText(),Integer.valueOf(tFEdad.getText()));
			dao.registrarUsuario(tFDni.getText(),tFNombreUsuario.getText(),tFContrasena.getText());
			JOptionPane.showMessageDialog(null, "TE HAS REGISTRADO","Registro",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Inicio_Sesion ini = new Inicio_Sesion(dao);
			ini.setVisible(true);
		}
	}

	private boolean validar() {
		// TODO Auto-generated method stub
	
		boolean bien=true;
		
		if(!comprobarDni()) {
			JOptionPane.showMessageDialog(null, "DNI NO VALIDO","ERROR",JOptionPane.ERROR_MESSAGE);
			bien = false;
		}
		if(tFDni.getText().isEmpty()) {
			tFDni.setBackground(Color.RED);
			bien = false;
		}
		if(tFNombre.getText().isEmpty()) {
			tFNombre.setBackground(Color.RED);
			bien = false;
		}
		if(tFApellido.getText().isEmpty()) {
			tFApellido.setBackground(Color.RED);
			bien = false;
		}
		if(tFPais.getText().isEmpty()) {
			tFPais.setBackground(Color.RED);
			bien = false;
		}
		if(tFEdad.getText().isEmpty()) {
			tFEdad.setBackground(Color.RED);
			bien = false;
		}
		if(tFNombreUsuario.getText().isEmpty()) {
			tFNombreUsuario.setBackground(Color.RED);
			bien = false;
		}
		if(tFContrasena.getText().isEmpty()) {
			
			tFContrasena.setBackground(Color.RED);
			bien = false;
		}
		
	
		return bien;
	}

	private boolean comprobarDni() {
		String dni = tFDni.getText();

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


	private void volverInicio_Sesion() {
		this.setVisible(false);
		Inicio_Sesion ven = new Inicio_Sesion(dao);
		ven.setVisible(true);
	}

}
