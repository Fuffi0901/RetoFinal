package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class VAlbum extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textFoto;
	private JTextField textFecha;
	private JButton btnIntroducir;
	private Dao dao;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 */
	public VAlbum(Dao dao, VPrincipal vPrincipal, boolean b) {
		this.dao=dao;
		setBounds(100, 100, 1105, 721);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(345, 88, 221, 58);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textFoto = new JTextField();
		textFoto.setColumns(10);
		textFoto.setBounds(345, 232, 221, 58);
		contentPanel.add(textFoto);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(345, 401, 221, 58);
		contentPanel.add(textFecha);
		
		JLabel lblNewLabel = new JLabel("nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(100, 98, 135, 36);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFoto = new JLabel("foto");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFoto.setBounds(100, 254, 135, 36);
		contentPanel.add(lblFoto);
		
		JLabel lblFecha = new JLabel("fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFecha.setBounds(100, 423, 135, 36);
		contentPanel.add(lblFecha);
		
		btnIntroducir = new JButton("Introducir");
		btnIntroducir.setBounds(631, 568, 111, 41);
		contentPanel.add(btnIntroducir);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(865, 98, 155, 36);
		contentPanel.add(comboBox);
		btnIntroducir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnIntroducir)) {
			meterAlbum();
		}
	}

	private void meterAlbum() {
		// TODO Auto-generated method stub
		int numero;
		this.dispose();
		numero=dao.sacarAlbumes().size()+1;
		dao.meterAlbum(numero,textNombre.getText(),textFoto.getText(),textFecha.getText());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRERCAMENTE CON EL CODIGO  "+ numero);
		VPrincipal ven = new VPrincipal(dao, this, true);
		ven.setVisible(true);
		
	}
}
