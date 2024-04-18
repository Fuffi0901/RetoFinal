package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

public class VCrearAlbum extends JDialog implements ActionListener{
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textFoto;
	private JTextField textFecha;
	private JButton btnCrear;
	private Dao dao;
	private JButton btnExaminar;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 * @param b 
	 * @param inicio_Sesion 
	 */
	public VCrearAlbum(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao) {
		super(inicio_Sesion);
		setModal(modal);
		this.dao=dao;
		
		setBounds(100, 100, 1259, 749);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(612, 157, 314, 49);
		contentPanel.add(textNombre);
		
		textFoto = new JTextField();
		textFoto.setColumns(10);
		textFoto.setBounds(612, 295, 314, 49);
		contentPanel.add(textFoto);
		
		JLabel lblNewLabel = new JLabel("Nombre del album");
		lblNewLabel.setFont(new Font("Eras Light ITC", Font.PLAIN, 31));
		lblNewLabel.setBounds(126, 150, 291, 50);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFotoDelAlbum = new JLabel("Foto del album");
		lblFotoDelAlbum.setFont(new Font("Eras Light ITC", Font.PLAIN, 31));
		lblFotoDelAlbum.setBounds(126, 295, 291, 50);
		contentPanel.add(lblFotoDelAlbum);
		
		JLabel lblFechaDeLanzamiento = new JLabel("Fecha de lanzamiento");
		lblFechaDeLanzamiento.setFont(new Font("Eras Light ITC", Font.PLAIN, 31));
		lblFechaDeLanzamiento.setBounds(126, 462, 291, 50);
		contentPanel.add(lblFechaDeLanzamiento);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(612, 462, 314, 49);
		contentPanel.add(textFecha);
		
		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrear.setBounds(446, 624, 146, 49);
		btnCrear.addActionListener(this);
		contentPanel.add(btnCrear);
		
		btnExaminar = new JButton("...");
		btnExaminar.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnExaminar.setBounds(936, 295, 49, 49);
		contentPanel.add(btnExaminar);
		btnExaminar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCrear)) {
			crearAlbum();
		}
		if(e.getSource().equals(btnCrear)) {
			
		}
	}

	private void crearAlbum() {
		// TODO Auto-generated method stub
		int NumAlbum;
		NumAlbum=dao.consultarNumAlbum();
		if(NumAlbum==0) {
			NumAlbum=1;
		}else {
			NumAlbum++;
		}
		
		dao.crearAlbum(NumAlbum,textFecha.getText(),textFoto.getText(),textNombre.getText());
	}

}
