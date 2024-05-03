package modelo;

public class Persona {
	//Atributos.
	private String Dni;
	private String nombrePersona;
	private String apellidoPersona;
	private String pais;
	private int edad;
	
	//Getters y Setters.
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		this.Dni = dni;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getApellidoPersona() {
		return apellidoPersona;
	}
	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
