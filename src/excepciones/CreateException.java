package excepciones;

import javax.swing.JOptionPane;

public class CreateException extends Exception{
	
	public CreateException(String message) {
		super(message);
	}
	
	public void mostrarAviso() {
		JOptionPane.showMessageDialog(null, "ERROR EN EL ALTA.");
	}
	
	public static void mensajeRegistro() {
		JOptionPane.showMessageDialog(null, "ERROR en los datos que se han puesto en rojo");
	}
}
