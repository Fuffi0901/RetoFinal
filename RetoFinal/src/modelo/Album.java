package modelo;

import java.time.LocalDate;

public class Album {
	//Atributos.
	private int codAlbum;
	private String nombreAlbum;
	private String fotoAlbum;
	private LocalDate fechaLan;
	
	//Getters y Setters.
	public int getCodAlbum() {
		return codAlbum;
	}
	public void setCodAlbum(int codAlbum) {
		this.codAlbum = codAlbum;
	}
	public String getNombreAlbum() {
		return nombreAlbum;
	}
	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}
	public String getFotoAlbum() {
		return fotoAlbum;
	}
	public void setFotoAlbum(String fotoAlbum) {
		this.fotoAlbum = fotoAlbum;
	}
	public LocalDate getFechaLan() {
		return fechaLan;
	}
	public void setFechaLan(LocalDate fechaLan) {
		this.fechaLan = fechaLan;
	}
}
