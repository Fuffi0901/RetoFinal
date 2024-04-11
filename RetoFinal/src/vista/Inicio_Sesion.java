package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import modelo.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Inicio_Sesion extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tFUsuario;
	private JPasswordField pFConstraseña;
	private JRadioButton rbMostrarConstraseña;
	private JButton btnEntrar;
	private JButton btnRegistrarse;
	private Dao dao;
	/**
	 * Create the frame.
	 * @param b 
	 * @param vRegistrarse 
	 * @param dao 
	 */
	public Inicio_Sesion(VRegistrarse vRegistrarse, boolean b, Dao dao) {
		this.dao=dao;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1259, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitulo = new JLabel("Inicio Sesión");
		lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
		lbTitulo.setBounds(323, 10, 613, 105);
		contentPane.add(lbTitulo);
		
		JLabel lbUsuario = new JLabel("Usuario:");
		lbUsuario.setFont(new Font("Informal Roman", Font.PLAIN, 52));
		lbUsuario.setBounds(100, 163, 162, 59);
		contentPane.add(lbUsuario);
		
		JLabel lbConstraseña = new JLabel("Contraseña:");
		lbConstraseña.setFont(new Font("Informal Roman", Font.PLAIN, 53));
		lbConstraseña.setBounds(100, 249, 235, 59);
		contentPane.add(lbConstraseña);
		
		tFUsuario = new JTextField();
		tFUsuario.setFont(new Font("Tahoma", Font.PLAIN, 43));
		tFUsuario.setBounds(343, 163, 762, 59);
		contentPane.add(tFUsuario);
		tFUsuario.setColumns(10);
		
		rbMostrarConstraseña = new JRadioButton("Mostrar Constraseña");
		rbMostrarConstraseña.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		rbMostrarConstraseña.setBounds(100, 342, 272, 45);
			
		
		rbMostrarConstraseña.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				mostrarConstraseña();
			}
		});
		contentPane.add(rbMostrarConstraseña);
		
		pFConstraseña = new JPasswordField();
		pFConstraseña.setFont(new Font("Tahoma", Font.PLAIN, 43));
		pFConstraseña.setBounds(345, 249, 760, 59);
		contentPane.add(pFConstraseña);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 62));
		btnEntrar.setBounds(178, 476, 343, 152);
		contentPane.add(btnEntrar);
		btnEntrar.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 62));
		btnRegistrarse.setBounds(661, 476, 343, 152);
		contentPane.add(btnRegistrarse);
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
		Usuario usu = dao.comprobarUsuario(tFUsuario.getText(),pFConstraseña.getText());
		if(null==usu) {
	
		}else {
			VverPlaylist v = new VverPlaylist(null, true, null, dao);
			v.setVisible(true);
			VPrincipal ven = new VPrincipal(this, true);
			ven.setVisible(true);
		}
		
		
	}
	
	private void registrarseAplicacion() {
		this.setVisible(false);
		VRegistrarse ven = new VRegistrarse(this, true,dao);
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
