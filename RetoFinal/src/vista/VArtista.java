package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VArtista extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textPais;
	private JTextField textEdad;
	private JTextField textNombreArtistico;
	private JCheckBox checkbox;
	private JComboBox comboEstilo;
	private JButton btnAltaArtista;
	private Dao dao;
	/**
	 * Launch the application
	/**
	 * Create the dialog.
	 * @param b 
	 * @param vPrincipal 
	 */
	public VArtista(Dao dao, VPrincipal vPrincipal, boolean b) {
		this.dao=dao;
		setBounds(100, 100, 1028, 714);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dni");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(222, 52, 128, 43);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNombre.setBounds(222, 110, 128, 43);
		contentPanel.add(lblNombre);
		
		JLabel lblApellidopersona = new JLabel("Apellido");
		lblApellidopersona.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblApellidopersona.setBounds(222, 174, 170, 43);
		contentPanel.add(lblApellidopersona);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPais.setBounds(222, 246, 128, 43);
		contentPanel.add(lblPais);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEdad.setBounds(222, 312, 128, 43);
		contentPanel.add(lblEdad);
		
		JLabel lblNombreartistico = new JLabel("Nombre artistico");
		lblNombreartistico.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNombreartistico.setBounds(222, 376, 157, 43);
		contentPanel.add(lblNombreartistico);
		
		JLabel lblCantaAutor = new JLabel("Canta autor");
		lblCantaAutor.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCantaAutor.setBounds(222, 442, 128, 43);
		contentPanel.add(lblCantaAutor);
		
		JLabel lblEstilo = new JLabel("Estilo");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEstilo.setBounds(222, 504, 128, 43);
		contentPanel.add(lblEstilo);
		
		checkbox = new JCheckBox("");
		checkbox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkbox.setBounds(419, 442, 108, 43);
		contentPanel.add(checkbox);
		
		textDni = new JTextField();
		textDni.setBounds(407, 68, 96, 19);
		contentPanel.add(textDni);
		textDni.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(407, 126, 96, 19);
		contentPanel.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(407, 190, 96, 19);
		contentPanel.add(textApellido);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(407, 262, 96, 19);
		contentPanel.add(textPais);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(407, 328, 96, 19);
		contentPanel.add(textEdad);
		
		textNombreArtistico = new JTextField();
		textNombreArtistico.setColumns(10);
		textNombreArtistico.setBounds(407, 392, 96, 19);
		contentPanel.add(textNombreArtistico);
		
		comboEstilo = new JComboBox();
		comboEstilo.setModel(new DefaultComboBoxModel(new String[] {"POP", "ROCK", "REGGAETON", "TRAP", "RAP", "HEAVY", "DRILL", "TECNO", "FLAMENCO", "JAZZ", "BLUES", "PUNK"}));
		comboEstilo.setBounds(407, 519, 96, 21);
		contentPanel.add(comboEstilo);
		
		btnAltaArtista = new JButton("introducir");
		btnAltaArtista.setBounds(545, 612, 85, 21);
		contentPanel.add(btnAltaArtista);
		btnAltaArtista.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAltaArtista)) {
			altaArtista();
		}
	}

	private void altaArtista() {
		// TODO Auto-generated method stub
		dao.registrarPersona(textDni.getText(),textNombre.getText(),textApellido.getText(),textPais.getText(),Integer.valueOf(textEdad.getText()));
		dao.registrarArtista(textDni.getText(),textNombreArtistico.getText(),checkbox.getVerifyInputWhenFocusTarget(),comboEstilo.getSelectedItem().toString());
		JOptionPane.showMessageDialog(null, "INTRODUCIDO CORRERCAMENTE");
	}
}
