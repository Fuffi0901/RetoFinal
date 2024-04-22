package modelo;

import java.util.HashMap;
import java.util.Map;

public class Playlist {
	//Atributos.
	private int codPlaylist;
	private String nombrePlaylist;
	private String fotoPlaylist;
	private Map <Integer, Cancion> listaCanciones;

	
	//Getters y Setters.
	public int getCodPlaylist() {
		return codPlaylist;
	}
	public void setCodPlaylist(int codPlaylist) {
		this.codPlaylist = codPlaylist;
	}
	public String getNombrePlaylist() {
		return nombrePlaylist;
	}
	public void setNombrePlaylist(String nombrePlaylist) {
		this.nombrePlaylist = nombrePlaylist;
	}
	public String getFotoPlaylist() {
		return fotoPlaylist;
	}
	public void setFotoPlaylist(String fotoPlaylist) {
		this.fotoPlaylist = fotoPlaylist;
	}
	public Map <Integer, Cancion> getListaCanciones() {
		return listaCanciones;
	}
	public void setListaCanciones(Map <Integer, Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
}
