package controlador;

import java.util.ArrayList;
import java.util.List;
import excepciones.CreateException;
import modelo.Cancion;
import modelo.Persona;
import modelo.Playlist;
import modelo.Usuario;

public interface Dao {
	
	public Usuario comprobarUsuario(String nombre, String contraseña);
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) ;
	
	public void registrarUsuario(String dni, String nombreU, String contraseña );
	
	public ArrayList<Cancion> sacarCanciones(String nombre, String contraseña);
	
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);
	
	public String sacarFotoCancion(int cod);
		
	public int sacarNumeroDePlayList();
	
	public Playlist consultaPlaylist(int cod);
	
	public List<Playlist> getAllPlaylists();
	
	public int sacarNumeroDeAlbum();
	
	public void crearAlbum(int numAlbum, String fecha, String foto, String nombre);
	
	public int consultarNumAlbum();
}