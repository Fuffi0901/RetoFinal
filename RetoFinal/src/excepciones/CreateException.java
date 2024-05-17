package excepciones;

import javax.swing.JOptionPane;

/**
 * @author Grupo6
 * 
 * 
 * */

public class CreateException extends Exception {
    // Constructor.

    /**
     * Constructor de la excepción CreateException.
     * 
     * @param message Mensaje de error que se desea asociar con la excepción.
     */
    public CreateException(String message) {
        super(message);
    }

    // Métodos.

    /**
     * Muestra un cuadro de diálogo de aviso de error genérico para altas.
     */
    public void mostrarAviso() {
        JOptionPane.showMessageDialog(null, "ERROR EN EL ALTA.");
    }

    /**
     * Muestra un cuadro de diálogo de aviso de error específico para registros con datos incorrectos.
     */
    public static void mensajeRegistro() {
        JOptionPane.showMessageDialog(null, "ERROR en los datos que se han puesto en rojo");
    }
}

