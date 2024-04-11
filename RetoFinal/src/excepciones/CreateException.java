package excepciones;

import javax.swing.JOptionPane;

public class CreateException extends Exception{
	
	public void mostrarAviso() {
		JOptionPane.showMessageDialog(null, "ERROR EN EL ALTA.");
	}
}
