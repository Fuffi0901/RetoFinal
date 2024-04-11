package controlador;

import java.util.ArrayList;
import java.util.List;
import excepciones.CreateException;
import modelo.Cancion;
import modelo.Persona;
import modelo.Usuario;

public interface Dao {
	
	public Usuario comprobarUsuario(String nombre, String contraseña);
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) ;
	
	public void registrarUsuario(String dni, String nombreU, String contraseña );
	
	
	public ArrayList<Cancion> sacarCanciones(String nombre, String contraseña);
	
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist);
	
	public String sacarFotoCancion(int cod) ;
	
	
}
