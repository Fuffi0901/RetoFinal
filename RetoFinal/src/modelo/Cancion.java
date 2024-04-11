package modelo;

public class Cancion {
	//Atributos.
	private int codCancion;
	private String nombreCancion;
	private int duracion;
	private String audio;
	
	//Getters y Setters.
	public int getCodCancion() {
		return codCancion;
	}
	public void setCodCancion(int codCancion) {
		this.codCancion = codCancion;
	}
	public String getNombreCancion() {
		return nombreCancion;
	}
	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
}
