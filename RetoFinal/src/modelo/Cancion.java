package modelo;

/**
 * @author Grupo6
 * 
 * 
 * */

public class Cancion {
    // Atributos.
    private int codCancion;       // Código único de la canción.
    private int codAlbum;         // Código del álbum al que pertenece la canción.
    private String nombreCancion; // Nombre de la canción.
    private int duracion;         // Duración de la canción en segundos.
    private String audio;         // Ruta del archivo de audio de la canción.

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
     * Obtiene el código del álbum al que pertenece la canción.
     * 
     * @return El código del álbum.
     */
    public int getCodAlbum() {
        return codAlbum;
    }

   
/**
     * Establece el código del álbum al que pertenece la canción.
     * 
     * @param codAlbum El nuevo código del álbum.
     */
    public void setCodAlbum(int codAlbum) {
        this.codAlbum = codAlbum;
    }

    /**
     * Obtiene el nombre de la canción.
     * 
     * @return El nombre de la canción.
     */
    public String getNombreCancion() {
        return nombreCancion;
    }

    /**
     * Establece el nombre de la canción.
     * 
     * @param nombreCancion El nuevo nombre de la canción.
     */
    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    /**
     * Obtiene la duración de la canción en segundos.
     * 
     * @return La duración de la canción.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la canción en segundos.
     * 
     * @param duracion La nueva duración de la canción.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la ruta del archivo de audio de la canción.
     * 
     * @return La ruta del archivo de audio.
     */
    public String getAudio() {
        return audio;
    }

    /**
     * Establece la ruta del archivo de audio de la canción.
     * 
     * @param audio La nueva ruta del archivo de audio.
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }
}