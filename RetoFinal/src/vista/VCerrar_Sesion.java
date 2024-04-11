package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class VCerrar_Sesion extends JDialog implements ActionListener{
	
	private JButton btnSi;
	private JButton btnNo;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 * @wbp.parser.constructor
	 */
	public VCerrar_Sesion(VBuscar vBuscar, boolean modal) {
		super(vBuscar);
		setModal(modal);
		Pantalla();
	}
	
	public VCerrar_Sesion(VPrincipal vPrincipal, boolean modal) {
		super(vPrincipal);
		setModal(modal);
		Pantalla();
	}

	public void Pantalla() {
		setBackground(new Color(64, 128, 128));
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbTitulo = new JLabel("Cerrar Sesión");
			lbTitulo.setBounds(323, 10, 657, 88);
			lbTitulo.setFont(new Font("Stencil", Font.PLAIN, 87));
			contentPanel.add(lbTitulo);
		}
		{
			JLabel lblTexto = new JLabel("¿QUIERES CERRAR SESIÓN?");
			lblTexto.setFont(new Font("Eras Light ITC", Font.PLAIN, 61));
			lblTexto.setBounds(261, 198, 730, 160);
			contentPanel.add(lblTexto);
		}
		{
			btnSi = new JButton("SI");
			btnSi.setBackground(new Color(64, 128, 128));
			btnSi.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 46));
			btnSi.setBounds(402, 430, 122, 97);
			contentPanel.add(btnSi);
			btnSi.addActionListener(this);
			
		}
		{
			btnNo = new JButton("NO");
			btnNo.setBackground(new Color(64, 128, 128));
			btnNo.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 46));
			btnNo.setBounds(697, 430, 122, 97);
			contentPanel.add(btnNo);
			btnNo.addActionListener(this);
		}
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
		Inicio_Sesion ven = new Inicio_Sesion(this, true);
		ven.setVisible(true);
	}

	private void volverVPrincipal() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true);
		ven.setVisible(true);
	}

}
