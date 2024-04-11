package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;

public class VRegistrarse extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tFDni;
	private JTextField tFNombre;
	private JTextField tFApellido;
	private JTextField tFPais;
	private JTextField tFEdad;
	private JTextField tFNombreUsuario;
	private JTextField tFContraseña;
	private JButton btnVolver;
	private JButton btnRegistrar;
	private Dao dao;
	/**
	 * Create the dialog.
	 * @param b 
	 * @param inicio_Sesion 
	 * @param dao 
	 */
	public VRegistrarse(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		this.dao=dao;
		setBackground(new Color(64, 128, 128));
		setModal(modal);
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("REGISTRAR USUARIO");
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
		lbTitulo.setBounds(183, 21, 891, 105);
		contentPanel.add(lbTitulo);
		
		JLabel lbDni = new JLabel("DNI:");
		lbDni.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lbDni.setBounds(83, 136, 101, 59);
		contentPanel.add(lbDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblNombre.setBounds(83, 205, 193, 59);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblApellido.setBounds(83, 280, 193, 59);
		contentPanel.add(lblApellido);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblPais.setBounds(83, 361, 101, 59);
		contentPanel.add(lblPais);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblEdad.setBounds(83, 439, 123, 59);
		contentPanel.add(lblEdad);
		
		JLabel lblNombreUsuario = new JLabel("Nombre usuario:");
		lblNombreUsuario.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblNombreUsuario.setBounds(69, 514, 364, 59);
		contentPanel.add(lblNombreUsuario);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lblContraseña.setBounds(83, 593, 304, 59);
		contentPanel.add(lblContraseña);
		
		tFDni = new JTextField();
		tFDni.setBackground(new Color(192, 192, 192));
		tFDni.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFDni.setBounds(329, 136, 480, 59);
		contentPanel.add(tFDni);
		tFDni.setColumns(10);
		
		tFNombre = new JTextField();
		tFNombre.setBackground(new Color(192, 192, 192));
		tFNombre.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFNombre.setColumns(10);
		tFNombre.setBounds(329, 205, 480, 59);
		contentPanel.add(tFNombre);
		
		tFApellido = new JTextField();
		tFApellido.setBackground(new Color(192, 192, 192));
		tFApellido.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFApellido.setColumns(10);
		tFApellido.setBounds(329, 280, 480, 59);
		contentPanel.add(tFApellido);
		
		tFPais = new JTextField();
		tFPais.setBackground(new Color(192, 192, 192));
		tFPais.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFPais.setColumns(10);
		tFPais.setBounds(329, 361, 480, 59);
		contentPanel.add(tFPais);
		
		tFEdad = new JTextField();
		tFEdad.setBackground(new Color(192, 192, 192));
		tFEdad.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFEdad.setColumns(10);
		tFEdad.setBounds(329, 439, 480, 59);
		contentPanel.add(tFEdad);
		
		tFNombreUsuario = new JTextField();
		tFNombreUsuario.setBackground(new Color(192, 192, 192));
		tFNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFNombreUsuario.setColumns(10);
		tFNombreUsuario.setBounds(443, 514, 366, 59);
		contentPanel.add(tFNombreUsuario);
		
		tFContraseña = new JTextField();
		tFContraseña.setBackground(new Color(192, 192, 192));
		tFContraseña.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFContraseña.setColumns(10);
		tFContraseña.setBounds(350, 593, 459, 59);
		contentPanel.add(tFContraseña);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(64, 128, 128));
		btnRegistrar.setFont(new Font("Imprint MT Shadow", Font.BOLD, 26));
		btnRegistrar.setBounds(1052, 617, 183, 53);
		contentPanel.add(btnRegistrar);
		btnRegistrar.addActionListener(this);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(new Color(64, 128, 128));
		btnVolver.setFont(new Font("Imprint MT Shadow", Font.BOLD, 26));
		btnVolver.setBounds(845, 617, 183, 53);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);
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
			dao.registrarUsuario(tFDni.getText(),tFNombreUsuario.getText(),tFContraseña.getText());
			JOptionPane.showMessageDialog(null, "TE HAS REGISTRADO","Registro",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Inicio_Sesion ini = new Inicio_Sesion(null, true, dao);
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
			JOptionPane.showMessageDialog(null, "DNI VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFDni.setBackground(Color.RED);
			bien = false;
		}
		if(tFNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "NOMBRE VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFNombre.setBackground(Color.RED);
			bien = false;
		}
		if(tFApellido.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "APELLIDO VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFApellido.setBackground(Color.RED);
			bien = false;
		}
		if(tFPais.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "PAIS VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFPais.setBackground(Color.RED);
			bien = false;
		}
		if(tFEdad.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "EDAD VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFEdad.setBackground(Color.RED);
			bien = false;
		}
		if(tFNombreUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "NOMBRE USUARIO VACIO","ERROR",JOptionPane.ERROR_MESSAGE);
			tFNombreUsuario.setBackground(Color.RED);
			bien = false;
		}
		if(tFContraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "CONTRASEÑA VACIA","ERROR",JOptionPane.ERROR_MESSAGE);
			tFContraseña.setBackground(Color.RED);
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
		Inicio_Sesion ven = new Inicio_Sesion(this, true,dao);
		ven.setVisible(true);
	}

}
