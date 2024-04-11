package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class VAdmin extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnBorrar;
	private JButton btnAniadir;
	private JButton bntModificar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 */
	public VAdmin(VPrincipal vPrincipal, boolean b) {
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnAniadir = new JButton("Añadir Canciones");
		btnAniadir.setBackground(Color.GREEN);
		btnAniadir.setFont(new Font("Stencil", Font.PLAIN, 26));
		btnAniadir.setBounds(104, 254, 236, 123);
		btnAniadir.addActionListener(this);
		contentPanel.add(btnAniadir);
		
		btnBorrar = new JButton("Borrar Canciones");
		btnBorrar.setBackground(Color.RED);
		btnBorrar.setFont(new Font("Stencil", Font.PLAIN, 26));
		btnBorrar.setBounds(923, 254, 252, 123);
		btnBorrar.addActionListener(this);
		contentPanel.add(btnBorrar);
		
		bntModificar = new JButton("Modificar Canciones");
		bntModificar.setBackground(Color.ORANGE);
		bntModificar.setFont(new Font("Stencil", Font.PLAIN, 26));
		bntModificar.setBounds(520, 254, 241, 123);
		bntModificar.addActionListener(this);
		contentPanel.add(bntModificar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAniadir)) {
			aniadirCancion();
		}
		if(e.getSource().equals(bntModificar)) {
			borrarCancion();
		}
		if(e.getSource().equals(btnBorrar)) {
			modificarCancion();
		}
	}

	private void modificarCancion() {
		// TODO Auto-generated method stub
		VModificarCancion ven = new VModificarCancion(this, true);
		ven.setVisible(true);
	}

	private void borrarCancion() {
		// TODO Auto-generated method stub
		VBorrarCancion ven = new VBorrarCancion(this, true);
		ven.setVisible(true);
	}

	private void aniadirCancion() {
		// TODO Auto-generated method stub
		VAniadirrCancion ven = new VAniadirrCancion(this, true);
		ven.setVisible(true);
	}
}
