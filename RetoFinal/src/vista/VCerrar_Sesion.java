package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Dao;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VCerrar_Sesion extends JDialog implements ActionListener{
	
	private JButton btnSi;
	private JButton btnNo;
	private Dao dao;

	private final JPanel contentPanel = new JPanel();

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
		setBackground(new Color(78, 78, 78));
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(78, 78, 78));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbTitulo = new JLabel("Cerrar Sesión");
			lbTitulo.setForeground(new Color(75, 75, 75));
			lbTitulo.setBounds(323, 10, 657, 88);
			lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
			contentPanel.add(lbTitulo);
		}
		{
			JLabel lblTexto = new JLabel("¿QUIERES CERRAR SESIÓN?");
			lblTexto.setForeground(new Color(255, 255, 255));
			lblTexto.setFont(new Font("Eras Light ITC", Font.PLAIN, 61));
			lblTexto.setBounds(261, 198, 730, 160);
			contentPanel.add(lblTexto);
		}
		
			btnSi = new JButton("SI");
			btnSi.setForeground(new Color(255, 255, 255));
			btnSi.setBackground(new Color(64, 128, 128));
			btnSi.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 46));
			btnSi.setBounds(402, 430, 122, 97);
			btnSi.setOpaque(false);
			btnSi.setBorderPainted(false);
			btnSi.setFocusable(false);
			contentPanel.add(btnSi);
			btnSi.addActionListener(this);
			
			btnNo = new JButton("NO");
			btnNo.setForeground(new Color(255, 255, 255));
			btnNo.setBackground(new Color(64, 128, 128));
			btnNo.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 46));
			btnNo.setBounds(697, 430, 122, 97);
			btnNo.setOpaque(false);
			btnNo.setBorderPainted(false);
			btnNo.setFocusable(false);
			contentPanel.add(btnNo);
			btnNo.addActionListener(this);
			
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
		if(e.getSource().equals(btnSi)) {
			volverInicio_Sesion();
		}
		if(e.getSource().equals(btnNo)) {
			volverVPrincipal();
		}
	}

	private void volverInicio_Sesion() {
		this.setVisible(false);
		Inicio_Sesion ven = new Inicio_Sesion(dao);
		ven.setVisible(true);
	}

	private void volverVPrincipal() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true, dao);
		ven.setVisible(true);
	}

}
