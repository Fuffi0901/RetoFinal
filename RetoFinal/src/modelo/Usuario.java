package modelo;

public class Usuario extends Persona{
	//Atributos.
	private String constrasena;
	private String nombreUsuario;
	
	//Getters y Setters.
	public String getConstrasena() {
		return constrasena;
	}
	public void setConstrasena(String constrasena) {
		this.constrasena = constrasena;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
