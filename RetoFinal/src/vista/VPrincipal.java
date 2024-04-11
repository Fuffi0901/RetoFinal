package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VPrincipal extends JDialog implements ActionListener{
	
	private JButton btnBuscar;
	private JButton btnLogo;
	private JButton btnInicio;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VPrincipal(VBuscar buscar, boolean modal) {
		super(buscar);
		setModal(modal);
		Pantalla();
	}
	public VPrincipal(VCerrar_Sesion vCerrar_Sesion, boolean modal) {
		super(vCerrar_Sesion);
		setModal(modal);
		Pantalla();
	}
	
	public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal) {
		super(inicio_Sesion);
		setModal(modal);
		Pantalla();
	}
	public void Pantalla() {
		setBackground(new Color(64, 128, 128));
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 128, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPlayList = new JLabel("PlayLists");
		lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblPlayList.setBounds(70, 64, 242, 50);
		contentPanel.add(lblPlayList);
		
		JLabel lblAlbumes = new JLabel("Albumes");
		lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
		lblAlbumes.setBounds(70, 339, 242, 50);
		contentPanel.add(lblAlbumes);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblInicio.setBounds(447, 679, 56, 24);
		contentPanel.add(lblInicio);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Informal Roman", Font.PLAIN, 27));
		lblBuscar.setBounds(647, 679, 78, 24);
		contentPanel.add(lblBuscar);
		
		
		btnInicio = new JButton("");
		btnInicio.setBackground(new Color(64, 128, 128));
		btnInicio.setIcon(new ImageIcon("..\\RetoFinal\\Img\\casaInicio.png"));
		btnInicio.setBounds(447, 631, 47, 48);
		contentPanel.add(btnInicio);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(64, 128, 128));
		btnBuscar.setIcon(new ImageIcon("..\\RetoFinal\\Img\\lupaBuscar.png"));
		btnBuscar.setBounds(647, 622, 58, 57);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);
		
		btnLogo = new JButton("");
		btnLogo.setForeground(new Color(64, 128, 128));
		btnLogo.setIcon(new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png"));
		btnLogo.setBackground(new Color(64, 128, 128));
		btnLogo.setBounds(1181, 10, 56, 75);
		contentPanel.add(btnLogo);
		btnLogo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnBuscar)){
			cambiarABuscar();
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
	private void cambiarABuscar() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		VBuscar ven = new VBuscar(this, true);
		ven.setVisible(true);
	}
}
