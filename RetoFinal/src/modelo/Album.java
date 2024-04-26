package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Album {
	//Atributos.
	private int codAlbum;
	private String nombreAlbum;
	private String fotoAlbum;
	private Date fechaLan;
	private Map <Integer, Cancion> listaCanciones;
	
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
	public Date getFechaLan() {
		return fechaLan;
	}
	public void setFechaLan(Date date) {
		this.fechaLan = date;
	}
	public Map<Integer, Cancion> getListaCanciones() {
		return listaCanciones;
	}
	public void setListaCanciones(Map<Integer, Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
}
