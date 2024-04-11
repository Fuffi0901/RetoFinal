package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class VBuscar extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JButton btnSecreto;
	private JButton btnInicio;
	private JButton btnLogo;

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 */
	public VBuscar(VPrincipal VPrincipal, boolean modal) {
		super(VPrincipal);
		setBackground(new Color(64, 128, 128));
		setModal(modal);
		
		setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnInicio = new JButton("");
			btnInicio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casaInicio.png"));
			btnInicio.setBackground(new Color(64, 128, 128));
			btnInicio.setBounds(481, 641, 47, 48);
			contentPanel.add(btnInicio);
			btnInicio.addActionListener(this);
			
		}
		{
			JLabel lblInicio = new JLabel("Inicio");
			lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
			lblInicio.setBounds(481, 688, 56, 24);
			contentPanel.add(lblInicio);
		}
		{
			JButton btnBuscar = new JButton("");
			btnBuscar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\lupaBuscar.png"));
			btnBuscar.setBackground(new Color(64, 128, 128));
			btnBuscar.setBounds(656, 632, 58, 57);
			contentPanel.add(btnBuscar);
		}
		{
			JLabel lblBuscar = new JLabel("Buscar");
			lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
			lblBuscar.setBounds(650, 688, 78, 24);
			contentPanel.add(lblBuscar);
		}
		
		txtBuscar = new JTextField();
		txtBuscar.setText("Buscar");
		txtBuscar.setForeground(new Color(192, 192, 192));
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 38));
		txtBuscar.setBounds(208, 29, 846, 69);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (txtBuscar.getText().equals("Buscar")) {
	                	txtBuscar.setText("");
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (txtBuscar.getText().isEmpty()) {
	                	txtBuscar.setText("Buscar");
	                }
	            }
	        });
		
		btnSecreto = new JButton("");
		btnSecreto.setBackground(new Color(64, 128, 128));
		btnSecreto.setBounds(0, 0, 11, 9);
		contentPanel.add(btnSecreto);
		{
			btnLogo = new JButton("");
			btnLogo.setForeground(new Color(64, 128, 128));
			btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
			btnLogo.setBackground(new Color(64, 128, 128));
			btnLogo.setBounds(1181, 10, 56, 75);
			contentPanel.add(btnLogo);
			btnLogo.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInicio)) {
			volverInicio();
		}
		if(e.getSource().equals(btnLogo)) {
			irCerrar_Sesion();
		}
	}

	private void irCerrar_Sesion() {
		this.setVisible(false);
		VCerrar_Sesion ven = new VCerrar_Sesion(this, true);
		ven.setVisible(true);
	}

	private void volverInicio() {
		this.setVisible(false);
		VPrincipal ven = new VPrincipal(this, true);
		ven.setVisible(true);
	}
}
