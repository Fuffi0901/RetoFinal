package controlador;

import java.util.ArrayList;
import java.util.List;
import excepciones.CreateException;
import modelo.Album;
import modelo.Cancion;
import modelo.Persona;
import modelo.Playlist;
import modelo.Usuario;

public interface Dao {
	
	public Usuario comprobarUsuario(String nombre, String contraseña);
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) ;
	
	public void registrarUsuario(String dni, String nombreU, String contraseña );
		
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);
	
	public String sacarFotoCancion(int cod);
		
	public int sacarNumeroDePlayList();
	
	public Playlist sacarPlaylist(int cod);

	public Album sacarAlbum(int cod);
		
	public int sacarNumeroDeAlbum();
	
	public void crearAlbum(int numAlbum, String fecha, String foto, String nombre);
	
	public int consultarNumAlbum();
}