package modelo;

public class Artista extends Persona{
	//Atributos.
	private String nombreArtistico;
	private boolean cantaAutor;
	private Estilo estilo;
	
	//Getters y Setters.
	public String getNombreArtistico() {
		return nombreArtistico;
	}
	public void setNombreArtistico(String nombreArtistico) {
		this.nombreArtistico = nombreArtistico;
	}
	public boolean isCantaAutor() {
		return cantaAutor;
	}
	public void setCantaAutor(boolean cantaAutor) {
		this.cantaAutor = cantaAutor;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	
}
