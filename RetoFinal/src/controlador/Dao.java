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
	
	public Usuario comprobarUsuario(String nombre, String contraseña);
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) ;
	
	public void registrarUsuario(String dni, String nombreU, String contraseña );
	
	public ArrayList<Cancion> sacarCanciones();
	public void borrarCancion(int cod);
	public void modificarCancion(int cod,int duracion, String nombreCancion, String audio,int codAlbum );
	
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);
	
	public String sacarFotoCancion(int cod);
	public void insertarCanta(String dni,int codCancion);
	public void eliminarCanta(int codCancion);
	public void añadirCancion(int cod,int duracion, String nombreCancion, String audio,int codAlbum );

		
	public int sacarNumeroDePlayList();
	
	public Playlist sacarPlaylist(int cod);

	public Album sacarAlbum(int cod);
	public ArrayList<Album> sacarAlbumes();
	
	
	public int sacarNumeroDeAlbum();
	
	public ArrayList<Artista> artistasPorCancion(int cod);
	
	public ArrayList<Artista> sacarartistas();
	
	public void crearAlbum(int numAlbum, String fecha, String foto, String nombre);
	
	public int consultarNumAlbum();
}