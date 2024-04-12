package modelo;

public class Usuario extends Persona{
	//Atributos.
	private String constraseña;
	private String nombreUsuario;
	
	//Getters y Setters.
	public String getConstraseña() {
		return constraseña;
	}
	public void setConstraseña(String constraseña) {
		this.constraseña = constraseña;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
