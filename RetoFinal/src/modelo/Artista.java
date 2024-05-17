package modelo;

/**
 * @author Grupo6
 * 
 * 
 * */

public class Artista extends Persona {
    // Atributos.
    private String nombreArtistico; // Nombre artístico del artista.
    private boolean cantaAutor;     // Indica si el artista es cantautor.
    private Estilo estilo;          // Estilo musical del artista.

    // Getters y Setters.

    /**
     * Obtiene el nombre artístico del artista.
     * 
     * @return El nombre artístico del artista.
     */
    
   
public String getNombreArtistico() {
        
return nombreArtistico;
    }

    /**
     * Establece el nombre artístico del artista.
     * 
     * @param nombreArtistico El nuevo nombre artístico del artista.
     */
    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    /**
     * Indica si el artista es cantautor.
     * 
     * @return {@code true} si el artista es cantautor, {@code false} en caso contrario.
     */
    public boolean isCantaAutor() {
        return cantaAutor;
    }

    /**
     * Establece si el artista es cantautor.
     * 
     * @param cantaAutor {@code true} si el artista es cantautor, {@code false} en caso contrario.
     */
    public void setCantaAutor(boolean cantaAutor) {
        this.cantaAutor = cantaAutor;
    }

    /**
     * Obtiene el estilo musical del artista.
     * 
     * @return El estilo musical del artista.
     */
    public Estilo getEstilo() {
        return estilo;
    }

    /**
     * Establece el estilo musical del artista.
     * 
     * @param estilo El nuevo estilo musical del artista.
     */
    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }
}