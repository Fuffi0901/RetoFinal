package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Inicio_Sesion extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tFUsuario;
	private JPasswordField pFConstraseña;
	private JRadioButton rbMostrarConstraseña;
	private JButton btnEntrar;
	private JButton btnRegistrarse;
	private JLabel lblLogo;
	private Dao dao;

	/**
	 * Create the frame.
	 * @param dao2 
	 * @param b 
	 * @param b 
	 * @param vRegistrarse 
	 */
	public Inicio_Sesion (Dao dao) {
		Pantallas(dao);
	}
	
	public void Pantallas(Dao dao) {
		this.dao = dao;
		setBackground(new Color(64, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1259, 749);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitulo = new JLabel("Inicio Sesión");
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
		lbTitulo.setBounds(323, 10, 613, 105);
		contentPane.add(lbTitulo);
		
		JLabel lbUsuario = new JLabel("Usuario:");
		lbUsuario.setFont(new Font("Eras Light ITC", Font.PLAIN, 52));
		lbUsuario.setBounds(82, 163, 201, 59);
		contentPane.add(lbUsuario);
		
		JLabel lbConstraseña = new JLabel("Contraseña:");
		lbConstraseña.setFont(new Font("Eras Light ITC", Font.PLAIN, 53));
		lbConstraseña.setBounds(82, 249, 272, 59);
		contentPane.add(lbConstraseña);
		
		tFUsuario = new JTextField();
		tFUsuario.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFUsuario.setBounds(372, 163, 733, 59);
		contentPane.add(tFUsuario);
		tFUsuario.setColumns(10);
		
		rbMostrarConstraseña = new JRadioButton("Mostrar Constraseña");
		rbMostrarConstraseña.setBackground(new Color(64, 128, 128));
		rbMostrarConstraseña.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		rbMostrarConstraseña.setBounds(100, 342, 272, 45);
		rbMostrarConstraseña.setOpaque(false);
			
		
		rbMostrarConstraseña.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				mostrarConstraseña();
			}
		});
		contentPane.add(rbMostrarConstraseña);
		
		pFConstraseña = new JPasswordField();
		pFConstraseña.setFont(new Font("Tahoma", Font.PLAIN, 43));
		pFConstraseña.setBounds(372, 249, 733, 59);
		contentPane.add(pFConstraseña);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(new Color(64, 128, 128));
		btnEntrar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 62));
		btnEntrar.setBounds(178, 476, 343, 152);
		btnEntrar.setOpaque(false);
		btnEntrar.setBorderPainted(false);
		btnEntrar.setFocusable(false);
		contentPane.add(btnEntrar);
		btnEntrar.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(new Color(64, 128, 128));
		btnRegistrarse.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 62));
		btnRegistrarse.setBounds(661, 476, 343, 152);
		btnRegistrarse.setOpaque(false);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setFocusable(false);
		contentPane.add(btnRegistrarse);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		lblLogo.setBounds(1181, 10, 56, 75);
		contentPane.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("..\\RetoFinal\\Img\\fondo1.gif"));
		lblNewLabel.setBounds(0, 0, 1259, 749);
		contentPane.add(lblNewLabel);
		btnRegistrarse.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnEntrar)) {
			entrarAplicacion();
		}
		if(e.getSource().equals(btnRegistrarse)) {
			registrarseAplicacion();
		}
	}

	private void entrarAplicacion() {
		@SuppressWarnings("deprecation")
		Usuario usu = dao.comprobarUsuario(tFUsuario.getText(),pFConstraseña.getText());
		if(null == usu) {
			JOptionPane.showMessageDialog(null, "NOMBRE O CONTRASEÑA INCORRECTO","ERROR",JOptionPane.ERROR_MESSAGE);
		}else {
			this.dispose();
			VPrincipal ven = new VPrincipal(this, true, dao);
			ven.setVisible(true);
		}
	}

	
	private void registrarseAplicacion() {
		this.setVisible(false);
		VRegistrarse ven = new VRegistrarse(this, true, dao);
		ven.setVisible(true);
		
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
}
