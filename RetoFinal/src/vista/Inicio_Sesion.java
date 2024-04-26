package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Album;
import modelo.Cancion;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.SwingConstants;

public class Inicio_Sesion extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tFUsuario;
	private JPasswordField pFConstraseña;
	private JRadioButton rbMostrarConstraseña;
	private JButton btnEntrar;
	private JButton btnRegistrarse;
	private JLabel lblLogo;
	private Dao dao;
	private JLabel lblNewLabel_1;
	private JButton btnLogo;

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
		setBackground(new Color(78, 78, 78));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1259, 749);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(78, 78, 78));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitulo = new JLabel("BEATDAM");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setForeground(new Color(78, 78, 78));
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 90));
		lbTitulo.setBounds(10, 40, 1227, 91);
		contentPane.add(lbTitulo);
		
		JLabel lbUsuario = new JLabel("Introduce el usuario");
		lbUsuario.setForeground(new Color(255, 255, 255));
		lbUsuario.setFont(new Font("Eras Light ITC", Font.PLAIN, 30));
		lbUsuario.setBounds(209, 193, 252, 35);
		contentPane.add(lbUsuario);
		
		JLabel lbConstraseña = new JLabel("Contraseña");
		lbConstraseña.setForeground(new Color(255, 255, 255));
		lbConstraseña.setFont(new Font("Eras Light ITC", Font.PLAIN, 30));
		lbConstraseña.setBounds(209, 307, 159, 25);
		contentPane.add(lbConstraseña);
		
		tFUsuario = new JTextField();
		tFUsuario.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFUsuario.setBounds(209, 238, 839, 59);
		contentPane.add(tFUsuario);
		tFUsuario.setColumns(10);
		
		rbMostrarConstraseña = new JRadioButton("Mostrar Constraseña");
		rbMostrarConstraseña.setForeground(new Color(255, 255, 255));
		rbMostrarConstraseña.setBackground(new Color(64, 128, 128));
		rbMostrarConstraseña.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rbMostrarConstraseña.setBounds(209, 414, 193, 35);
		rbMostrarConstraseña.setOpaque(false);
			
		
		rbMostrarConstraseña.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				mostrarConstraseña();
			}
		});
		contentPane.add(rbMostrarConstraseña);
		
		pFConstraseña = new JPasswordField();
		pFConstraseña.setFont(new Font("Tahoma", Font.PLAIN, 43));
		pFConstraseña.setBounds(209, 342, 839, 59);
		contentPane.add(pFConstraseña);
		
		btnEntrar = new JButton("Iniciar Sesion");
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setBackground(new Color(64, 128, 128));
		btnEntrar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 40));
		btnEntrar.setBounds(479, 486, 282, 75);
		btnEntrar.setOpaque(false);
		btnEntrar.setBorderPainted(false);
		btnEntrar.setFocusable(false);
		contentPane.add(btnEntrar);
		btnEntrar.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrate");
		btnRegistrarse.setHorizontalAlignment(SwingConstants.LEADING);
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setBackground(new Color(64, 128, 128));
		btnRegistrarse.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 20));
		btnRegistrarse.setBounds(638, 567, 124, 33);
		btnRegistrarse.setOpaque(false);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setFocusable(false);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(this);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		lblLogo.setBounds(1181, 10, 56, 75);
		contentPane.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("¿No tienes Cuenta?");
		lblNewLabel.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setBounds(489, 571, 167, 25);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("© 2024 BeatDam Application. All rights reserved. Created by Asier Del Campo, Guillermo Flecha, Erlantz Rey, Andoni García.");
		lblNewLabel_1.setFont(new Font("Eras Light ITC", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_1.setBounds(10, 689, 1225, 13);
		contentPane.add(lblNewLabel_1);
		
		btnLogo = new JButton("");
		btnLogo.setOpaque(false);
		btnLogo.setForeground(new Color(64, 128, 128));
		btnLogo.setFocusable(false);
		btnLogo.setBorderPainted(false);
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setBounds(1181, 10, 56, 75);
		contentPane.add(btnLogo);
		
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
		contentPane.add(fondo);
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

	private void abrirExtra() {
		// TODO Auto-generated method stub
		this.dispose();
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
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