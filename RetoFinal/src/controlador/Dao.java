package controlador;

import java.util.ArrayList;
import java.util.List;
import excepciones.CreateException;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Persona;
import modelo.Playlist;
import modelo.Usuario;

public interface Dao {
	
	//Usuario
	public Usuario comprobarUsuario(String nombre, String contraseña);
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) ;
	
	public void registrarUsuario(String dni, String nombreU, String contraseña );
	
	
	//Canciones
	public ArrayList<Cancion> sacarCanciones();
	public void borrarCancion(int cod);
	public void modificarCancion(int cod,int duracion, String nombreCancion, String audio,int codAlbum );
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);
	public int crearCodigoCancion();
	public String sacarFotoCancion(int cod);
	public void insertarCanta(String dni,int codCancion);
	public void eliminarCanta(int codCancion);
	public void añadirCancion(int cod,int duracion, String nombreCancion, String audio,int codAlbum );

	//Playlist
	public void insertarPlaylist(int cod,String nombre,String foto,String dni);
	public void insertarPertenece(int cod,String dni);
	public int crearCodigoPlaylist();
	public ArrayList<Playlist> sacararPlaylists();
	public int sacarNumeroDePlayList();

	//Album
	public Album sacarAlbum(int cod);
	public ArrayList<Album> sacarAlbumes();
	public void borrarAlbum(int codAlbum);
	public void meterAlbum(int cod, String nombre, String foto, String fecha) ;
	public void modificarAlbum(String cod, String nombre, String foto, String fecha);
	public int crearCodigoAlbum() ;
	public int consultarNumAlbum();
	ArrayList<Cancion> sacarCancionesAlbum(int cod);
	public int sacarNumeroDeAlbum();
	public Playlist consultaPlaylist(int cod);

	
	//Artista
	public ArrayList<Artista> artistasPorCancion(int cod);
	public ArrayList<Artista> sacarartistas();
	public String funcionArtistas(int cod);
	public void borrarArtista(String dni);
	public void registrarArtista(String text, String text2, boolean cantaAutor, String string);
	public ArrayList<Artista> sacarArtista();

	
}