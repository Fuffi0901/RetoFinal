package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		btnAniadir.setBounds(104, 254, 273, 123);
		btnAniadir.addActionListener(this);
		contentPanel.add(btnAniadir);
		
		btnBorrar = new JButton("Borrar Canciones");
		btnBorrar.setBackground(Color.RED);
		btnBorrar.setFont(new Font("Stencil", Font.PLAIN, 26));
		btnBorrar.setBounds(892, 254, 283, 123);
		btnBorrar.addActionListener(this);
		contentPanel.add(btnBorrar);
		
		bntModificar = new JButton("Modificar Canciones");
		bntModificar.setBackground(Color.ORANGE);
		bntModificar.setFont(new Font("Stencil", Font.PLAIN, 26));
		bntModificar.setBounds(483, 254, 323, 123);
		bntModificar.addActionListener(this);
		contentPanel.add(bntModificar);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource().equals(btnAniadir)) {
//			aniadirCancion();
//		}
//		if(e.getSource().equals(bntModificar)) {
//			borrarCancion();
//		}
//		if(e.getSource().equals(btnBorrar)) {
//			modificarCancion();
//		}
	}

//	private void modificarCancion() {
//		this.setVisible(false);
//		VModificarCancion ven = new VModificarCancion(this, true);
//		ven.setVisible(true);
//	}
//
//	private void borrarCancion() {
//		this.setVisible(false);
//		VBorrarCancion ven = new VBorrarCancion(this, true);
//		ven.setVisible(true);
//	}
//
//	private void aniadirCancion() {
//		this.setVisible(false);
//		VAniadirCancion ven = new VAniadirCancion(this, true);
//		ven.setVisible(true);
//	}
}
