package modelo;

import java.sql.Date;
import java.util.Map;

/**
 * @author Grupo6
 * 
 * 
 * */

public class Album {
    // Atributos.
    private int codAlbum;                 // Código único del álbum.
    private String nombreAlbum;           // Nombre del álbum.
    private String fotoAlbum;             // Ruta de la foto del álbum.
    private Date fechaLan;                // Fecha de lanzamiento del álbum.
    private Map<Integer, Cancion> listaCanciones; // Lista de canciones en el álbum, mapeadas por su código.

    // Getters y Setters.
    
    /**
     * Obtiene el código del álbum.
     * 
     * @return El código del álbum.
     */
    public int getCodAlbum() {
        return codAlbum;
    }

    /**
     * Establece el código del álbum.
     * 
     * @param codAlbum El nuevo código del álbum.
     */
    public void setCodAlbum(int codAlbum) {
        this.codAlbum = codAlbum;
    }

    /**
     * Obtiene el nombre del álbum.
     * 
     * @return El nombre del álbum.
     */
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    /**
     * Establece el nombre del álbum.
     * 
     * @param nombreAlbum El nuevo nombre del álbum.
     */
    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    /**
     * Obtiene la ruta de la foto del álbum.
     * 
     * @return La ruta de la foto del álbum.
     */
    public String getFotoAlbum() {
        return fotoAlbum;
    }

    /**
     * Establece la ruta de la foto del álbum.
     * 
     * @param fotoAlbum La nueva ruta de la foto del álbum.
     */
    public void setFotoAlbum(String fotoAlbum) {
        this.fotoAlbum = fotoAlbum;
    }

    /**
     * Obtiene la fecha de lanzamiento del álbum.
     * 
     * @return La fecha de lanzamiento del álbum.
     */
    public Date getFechaLan() {
        return fechaLan;
    }

    /**
     * Establece la fecha de lanzamiento del álbum.
     * 
     * @param date La nueva fecha de lanzamiento del álbum.
     */
    public void setFechaLan(Date date) {
        this.fechaLan = date;
    }

    /**
     * Obtiene la lista de canciones del álbum.
     * 
     * @return Un mapa de las canciones del álbum, donde la clave es el código de la canción.
     */
    public Map<Integer, Cancion> getListaCanciones() {
        return listaCanciones;
    }

    /**
     * Establece la lista de canciones del álbum.
     * 
     * @param listaCanciones El nuevo mapa de canciones del álbum.
     */
    public void setListaCanciones(Map<Integer, Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
}
