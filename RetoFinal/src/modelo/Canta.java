package modelo;
/**
 * @author Grupo6
 * 
 * 
 * */

public class Canta {
    // Atributos.
    private int codCancion; // Código de la canción.
    private String dni;     // DNI del artista que canta la canción.

    // Getters y Setters.

    /**
     * Obtiene el código de la canción.
     * 
     * @return El código de la canción.
     */
    public int getCodCancion() {
        return codCancion;
    }

    /**
     * Establece el código de la canción.
     * 
     * @param codCancion El nuevo código de la canción.
     */
    public void setCodCancion(int codCancion) {
        this.codCancion = codCancion;
    }

    /**
     * Obtiene el DNI del artista que canta la canción.
     * 
     * @return El DNI del artista.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del artista que canta la canción.
     * 
     * @param dni El nuevo DNI del artista.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
}

