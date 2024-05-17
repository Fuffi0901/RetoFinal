package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import excepciones.CreateException;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Persona;
import modelo.Playlist;
import modelo.Usuario;

//Dao
/**
 * @author Grupo6
 * 
 * 
 * */

public interface Dao {

    // ComprobarUsuario
    /**
     * Comprueba si un usuario con el nombre y contraseña proporcionados existe.
     * 
     * @param nombre     Nombre del usuario.
     * @param contrasena Contraseña del usuario.
     * @return El objeto Usuario si existe.
     * @throws CreateException Si hay un error durante la comprobación.
     */
    public Usuario comprobarUsuario(String nombre, String contrasena) throws CreateException;

    // ComprobarPersona
    /**
     * Comprueba si una persona con el DNI proporcionado existe.
     * 
     * @param dni DNI de la persona.
     * @return El objeto Persona si existe.
     */
    public Persona comprobarPersona(String dni);

    // ComprobarArtista
    /**
     * Comprueba si un artista con el DNI proporcionado existe.
     * 
     * @param dni DNI del artista.
     * @return El objeto Artista si existe.
     */
    public Artista comprobarArtista(String dni);

    // ModificarUsuario
    /**
     * Modifica los datos de un usuario existente.
     * 
     * @param usu El objeto Usuario con los datos actualizados.
     */
    public void modificarUsuario(Usuario usu);

    // EliminarUsuario
    /**
     * Elimina un usuario basado en su DNI.
     * 
     * @param dni DNI del usuario a eliminar.
     */
    public void eliminarUsuario(String dni);

    // RegistrarPersona
    /**
     * Registra una nueva persona con los datos proporcionados.
     * 
     * @param dni       DNI de la persona.
     * @param nombreP   Nombre de la persona.
     * @param apellido  Apellido de la persona.
     * @param pais      País de la persona.
     * @param edad      Edad de la persona.
     */
    public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad);

    // RegistrarUsuario
    /**
     * Registra un nuevo usuario con los datos proporcionados.
     * 
     * @param dni        DNI del usuario.
     * @param nombreU    Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     */
    public void registrarUsuario(String dni, String nombreU, String contrasena);

    // Canciones
    /**
     * Retorna una lista de todas las canciones.
     * 
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> sacarCanciones();

    /**
     * Borra una canción identificada por su código.
     * 
     * @param cod Código de la canción a borrar.
     */
    public void borrarCancion(int cod);

    /**
     * Modifica los datos de una canción específica.
     * 
     * @param cod         Código de la canción.
     * @param duracion    Duración de la canción.
     * @param nombreCancion Nombre de la canción.
     * @param audio       Ruta del archivo de audio.
     * @param codAlbum    Código del álbum al que pertenece la canción.
     */
    public void modificarCancion(int cod, int duracion, String nombreCancion, String audio, int codAlbum);

    /**
     * Retorna las canciones de una playlist específica.
     * 
     * @param codPlaylist Código de la playlist.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);

    /**
     * Genera un nuevo código único para una canción.
     * 
     * @return Un nuevo código de canción.
     */
    public int crearCodigoCancion();

    /**
     * Retorna la foto asociada a una canción.
     * 
     * @param cod Código de la canción.
     * @return Ruta de la foto de la canción.
     */
    public String sacarFotoCancion(int cod);

    /**
     * Asocia un artista a una canción.
     * 
     * @param dni       DNI del artista.
     * @param codCancion Código de la canción.
     */
    public void insertarCanta(String dni, int codCancion);

    /**
     * Elimina la asociación de un artista con una canción.
     * 
     * @param codCancion Código de la canción.
     */
    public void eliminarCanta(int codCancion);

    /**
     * Añade una nueva canción con los datos proporcionados.
     * 
     * @param cod           Código de la canción.
     * @param duracion      Duración de la canción.
     * @param nombreCancion Nombre de la canción.
     * @param audio         Ruta del archivo de audio.
     * @param codAlbum      Código del álbum al que pertenece la canción.
     */
    public void anadirCancion(int cod, int duracion, String nombreCancion, String audio, int codAlbum);

    /**
     * Retorna las canciones de un artista específico.
     * 
     * @param dni DNI del artista.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> sacarCancionesArtista(String dni);

    /**
     * Retorna las canciones de un artista específico, ordenadas.
     * 
     * @param dni DNI del artista.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> sacarCancionesOrdenadas(String dni);

    // Playlist
    /**
     * Inserta una nueva playlist con los datos proporcionados.
     * 
     * @param cod   Código de la playlist.
     * @param nombre Nombre de la playlist.
     * @param foto   Ruta de la foto de la playlist.
     * @param dni    DNI del usuario propietario.
     */
    public void insertarPlaylist(int cod, String nombre, String foto, String dni);

    /**
     * Elimina una playlist identificada por su código.
     * 
     * @param cod Código de la playlist a eliminar.
     */
    public void eliminarplaylist(int cod);

    /**
     * Retorna una playlist específica de un usuario.
     * 
     * @param cod Código de la playlist.
     * @param dni DNI del usuario propietario.
     * @return El objeto Playlist.
     */
    public Playlist sacarPlaylist(int cod, String dni);

    /**
     * Retorna las playlists de un usuario específico.
     * 
     * @param dni DNI del usuario.
     * @return Lista de objetos Playlist.
     */
    public ArrayList<Playlist> sacarPlaylistUsuario(String dni);

    /**
     * Modifica los datos de una playlist específica.
     * 
     * @param cod   Código de la playlist.
     * @param nombre Nombre de la playlist.
     * @param foto   Ruta de la foto de la playlist.
     */
    public void modificarPlaylist(int cod, String nombre, String foto);

    /**
     * Inserta una canción en una playlist.
     * 
     * @param codP Código de la playlist.
     * @param codC Código de la canción.
     */
    public void insertarPertenece(int codP, int codC);

    /**
     * Genera un nuevo código único para una playlist.
     * 
     * @return Un nuevo código de playlist.
     */
    public int crearCodigoPlaylist();

    /**
     * Retorna una lista de todas las playlists.
     * 
     * @return Lista de objetos Playlist.
     */
    public ArrayList<Playlist> sacarPlaylists();

    /**
     * Retorna las canciones de una playlist específica.
     * 
     * @param cod Código de la playlist.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> CancionesDePlaylist(int cod);

    /**
     * Elimina una canción de una playlist.
     * 
     * @param cod Código de la canción en la playlist.
     */
    public void eliminarPertenece(int cod);

    // Album
    /**
     * Retorna un álbum específico.
     * 
     * @param cod Código del álbum.
     * @return El objeto Album.
     */
    public Album sacarAlbum(int cod);

    /**
     * Retorna una lista de todos los álbumes.
     * 
     * @return Lista de objetos Album.
     */
    public ArrayList<Album> sacarAlbumes();

    /**
     * Borra un álbum identificado por su código.
     * 
     * @param codAlbum Código del álbum a borrar.
     */
    public void borrarAlbum(int codAlbum);

    /**
     * Inserta un nuevo álbum con los datos proporcionados.
     * 
     * @param cod               Código del álbum.
     * @param nombre            Nombre del álbum.
     * @param foto              Ruta de la foto del álbum.
     * @param fechaSeleccionada Fecha de lanzamiento del álbum.
     */
    public void meterAlbum(int cod, String nombre, String foto, java.sql.Date fechaSeleccionada);

    /**
     * Modifica los datos de un álbum específico.
     * 
     * @param cod    Código del álbum.
     * @param nombre Nombre del álbum.
     * @param foto   Ruta de la foto del álbum.
     * @param fecha  Fecha de lanzamiento del álbum.
     */
    public void modificarAlbum(String cod, String nombre, String foto, java.sql.Date fecha);

    /**
     * Genera un nuevo código único para un álbum.
     * 
     * @return Un nuevo código de álbum.
     */
    public int crearCodigoAlbum();

    /**
     * Consulta el número total de álbumes.
     * 
     * @return Número de álbumes.
     */
    public int consultarNumAlbum();

    /**
     * Retorna las canciones de un álbum específico.
     * 
     * @param cod Código del álbum.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> sacarCancionesAlbum(int cod);

    /**
     * Retorna las canciones de un álbum específico.
     * 
     * @param cod Código del álbum.
     * @return Lista de objetos Cancion.
     */
    public ArrayList<Cancion> CancionesDeAlbum(int cod);

    // Artista
    /**
     * Modifica los datos de una persona específica.
     * 
     * @param dni      DNI de la persona.
     * @param nombre   Nombre de la persona.
     * @param apellido Apellido de la persona.
     * @param pais     País de la persona.
     * @param edad     Edad de la persona.
     */
    public void modificarPersona(String dni, String nombre, String apellido, String pais, int edad);

    /**
     * Modifica los datos de un artista específico.
     * 
     * @param dni        DNI del artista.
     * @param nombre     Nombre del artista.
     * @param estilo     Estilo musical del artista.
     * @param cantaAutor Indica si el artista es cantautor.
     */
    public void modificarArtista(String dni, String nombre, String estilo, boolean cantaAutor);

    /**
     * Retorna los artistas que participaron en una canción específica.
     * 
     * @param cod Código de la canción.
     * @return Lista de objetos Artista.
     */
    public ArrayList<Artista> artistasPorCancion(int cod);

    /**
     * Retorna una lista de todos los artistas.
     * 
     * @return Lista de objetos Artista.
     */
    public ArrayList<Artista> sacarartistas();

    /**
     * Realiza una operación específica para artistas (descripción no clara en el nombre del método).
     * 
     * @param cod Código de referencia.
     * @return Resultado de la operación.
     */
    public String funcionArtistas(int cod);

    /**
     * Borra un artista identificado por su DNI.
     * 
     * @param dni DNI del artista a borrar.
     */
    public void borrarArtista(String dni);

    /**
     * Registra un nuevo artista con los datos proporcionados.
     * 
     * @param dni        DNI del artista.
     * @param nombre     Nombre del artista.
     * @param cantaAutor Indica si el artista es cantautor.
     * @param estilo     Estilo musical del artista.
     */
    public void registrarArtista(String dni, String nombre, boolean cantaAutor, String estilo);

    /**
     * Retorna una lista de todos los artistas.
     * 
     * @return Lista de objetos Artista.
     */
    public ArrayList<Artista> sacarArtista();

    /**
     * Retorna una lista de todas las personas.
     * 
     * @return Lista de objetos Persona.
     */
    public ArrayList<Persona> sacarPersonas();
}