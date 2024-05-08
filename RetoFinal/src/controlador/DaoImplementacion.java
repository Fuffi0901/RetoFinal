package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.Estilo;
import modelo.Persona;
import modelo.Playlist;
import modelo.Usuario;

public class DaoImplementacion implements Dao{
	
	
	private Connection con;
	private PreparedStatement stmt;
	@SuppressWarnings("unused")
	private ResultSet rs;
	
	
	//consultas usuarios
	private final String CONSULTA_USUARIO = "select * from Usuario where  Contraseina=? and NombreUsuario=?";
	private final String INSERT_PERSONA =  "insert into persona(Dni,nombrePersona,apellidoPersona,pais,edad) values (?,?,?,?,?)";
	private final String INSERT_USUARIO =  "insert into usuario(Dni,contraseina,NombreUsuario) values (?,?,?)";
	

	
	//consultas canciones
	private final String SACAR_CANCIONES = "select * from Cancion";
	private final String BORRAR_CANCIONES = "delete from Cancion where codCancion=?";
	private final String CODIGO_CANCION = "select crearCodigoCancion() AS cod";
	private final String MODIFICAR_CANCIONES = "update cancion set  Duracion = ? , nombreCancion = ?, Audio = ?,codAlbum = ? where codCancion=?";
	private final String INSERT_CANCIONES =  "insert into cancion(codCancion,Duracion,nombreCancion, Audio,codAlbum) values (?,?,?,?,?)";
	private final String INSERT_CANTA =  "insert into canta(dni,codCancion) values (?,?)";
	private final String BORRAR_CANTA =  "delete from canta where codCancion=?";
	private final String CANCIONES_PLAYLIST = "select c.* from cancion c, pertenece p where c.codCancion=p.codCancion and p.codPlayList = ?";
	
	
	private final String SACAR_FOTO = "select fotoAlbum from Album where codAlbum = (select codAlbum from Cancion where codAlbum=?)";
	
	
	//Consulta Playlist
	private final String INSERT_PLAYLIST = "insert into PlayList(codPlayList,nombrePlayList,fotoPlayList,dni) values(?,?,?,?)";
	private final String CONSULTA_PLAYLIST = "select * from playlist where codPlayList=? and dni=?";
	private final String MODIFICAR_PLAYLIST = "update PlayList set nombrePlayList=?, fotoPlayList=? where codPlayList=?";
	private final String ELIMINAR_PLAYLIST = "delete from Playlist where codPlayList=?";
	private final String TODAS_PLAYLIST = "select * from playlist where codPlayList = ?";
	private final String INSERT_PERTENECE = "insert into Pertenece(codPlayList,codCancion) values(?,?)";
	private final String ELIMINAR_PERTENECE = "delete from Pertenece where codPlayList=?";
	private final String NUMERO_PLAYLIST = "select count(*) from playlist";
	private final String CANCIONES_POR_PLAYLIST = "select c.* from cancion c, pertenece p where c.codCancion=p.codCancion and codPlaylist=?";


	//CONSULTA ALBUM
	private final String NUMERO_ALBUM = "select count(*) from album";
	private final String CONSULTA_NUMALBUM = "select count(codAlbum) from album";
	private final String SACAR_ALBUM = "select * from album where codAlbum = ?";
	private final String SACAR_ALBUMES = "select * from album ";
	private final String BORRAR_ALBUM = "delete from album where codAlbum=?";
	private final String INSERT_ALBUM = "insert into album(codAlbum,nombreAlbum,fotoAlbum,fechaLan) values(?,?,?,?)";
	private final String MODIFICAR_ALBUM = "update album set  codAlbum = ? , nombreAlbum = ?, fotoAlbum = ?,fechaLan = ? where codAlbum=?;";
	private final String CODIGO_ALBUM = "select crearCodigoCancion() AS cod";
	private final String CANCIONES_POR_ALBUM = "select * from cancion where codAlbum=?";

	
	//consultas artista
	private final String SACAR_ARTISTA_CANCION = "select a.* from Artista a , Canta c where a.dni=c.dni and codCancion=?";
	private final String SACAR_ARTISTAS = "select * from Artista ";
	private final String SACAR_PERSONAS_ARTISTAS = "select * from Persona where dni in (select dni from artista) ";
	private final String MODIFICAR_PERSONA = "update persona set  nombrePersona = ? , apellidoPersona = ?, pais = ?, edad = ?, dni = ? where dni=?";
	private final String FUNCION_ART_CA = "select artistaPorCancion(?) AS result";
	private final String INSERT_ARTISTA =  "insert into artista(Dni,nombreArtistico,CantaAutor,estilo) values (?,?,?,?)";
	private final String BORRAR_ARTISTA = "delete from persona where dni=?";
	private final String MODIFICAR_ARTISTA = "update artista set  nombreArtistico = ? , CantaAutor = ?, Estilo = ?, dni = ? where dni=?";

	//abrir la conexion con la base de datos
	private void openConnection() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/beatdam?serverTimezone=Europe/Madrid&useSSL=false", "root",
					"abcd*1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Este metodo cierra la conexion con la base de datos
	 * 
	 * @throws SQLException
	 */
	private void closeConnection() throws SQLException {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int crearCodigoCancion() {
		int cod = 0;
		
		this.openConnection();
		try {

			stmt = con.prepareStatement(CODIGO_CANCION);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cod=rs.getInt("cod");
			}else {
				cod=-1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return cod;
	}
	
	
	public int crearCodigoAlbum() {
		int cod = 0;
		
		this.openConnection();
		try {

			stmt = con.prepareStatement(CODIGO_ALBUM);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cod=rs.getInt("cod");
			}else {
				cod=-1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return cod;
	}
	
	public int crearCodigoPlaylist() {
		int cod = 0;
		
		this.openConnection();
		try {

			stmt = con.prepareStatement(CODIGO_ALBUM);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cod=rs.getInt("cod");
			}else {
				cod=-1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return cod;
	}
	
	public String funcionArtistas(int cod) {
		String mensaje="";
		
		this.openConnection();
		try {

			stmt = con.prepareStatement(FUNCION_ART_CA);
			
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				mensaje=rs.getString("result");
			}else {
				mensaje="";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return mensaje;
	}
	
	
	
	public Usuario comprobarUsuario(String nombre, String contraseña) {
		
		Usuario usu = new Usuario();
		this.openConnection();
		try {

			stmt = con.prepareStatement(CONSULTA_USUARIO);

			
			stmt.setString(1, contraseña);
			stmt.setString(2, nombre);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				
				usu.setNombreUsuario(rs.getString("NombreUsuario"));
				usu.setConstraseña("contraseina");
				
			}else {
				usu=null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return usu;
	}
	
	
	public void registrarPersona(String dni, String nombreP, String apellido, String pais, int edad) {

		this.openConnection();

		try {

			stmt = con.prepareStatement(INSERT_PERSONA);
			stmt.setString(1, dni);
			stmt.setString(2, nombreP);
			stmt.setString(3, apellido);
			stmt.setString(4, pais);
			stmt.setInt(5, edad);

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//Añadir nuevos usuarios a la base de datos
	public void registrarUsuario(String dni,String nombreU, String contraseña ){
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(INSERT_USUARIO);
			stmt.setString(1, dni);
			stmt.setString(2, contraseña);
			stmt.setString(3, nombreU);
			
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	
	//Añadir nuevos canciones la base de datos
	public void añadirCancion(int cod, int duracion, String nombreCancion, String audio, int codAlbum){
		this.openConnection();
		try {
			stmt = con.prepareStatement(INSERT_CANCIONES);
			stmt.setInt(1, cod);
			stmt.setInt(2, duracion);
			stmt.setString(3, nombreCancion);
			stmt.setString(4, audio);
			stmt.setInt(5, codAlbum);					
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}


	//Amodificar los datos de una cancion la base de datos
	public void modificarCancion(int cod,int duracion, String nombreCancion, String audio,int codAlbum ){
					
		this.openConnection();
					
		try {

			stmt = con.prepareStatement(MODIFICAR_CANCIONES);
						
			stmt.setInt(1, duracion);
			stmt.setString(2, nombreCancion);
			stmt.setString(3, audio);
			stmt.setInt(4, codAlbum);
			stmt.setInt(5, cod);
						
						
			stmt.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public void borrarCancion(int cod){		
		this.openConnection();		
		try {
			stmt = con.prepareStatement(BORRAR_CANCIONES);
			stmt.setInt(1, cod);	
			stmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
			

//Añadir nuevos usuarios a la base de datos
	public void insertarCanta(String dni,int codCancion){
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(INSERT_CANTA);
			stmt.setString(1, dni);
			stmt.setInt(2, codCancion);		
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
					
	}
				
				
	//Añadir nuevos usuarios a la base de datos
	public void eliminarCanta(int codCancion){
		this.openConnection();			
		try {
			stmt = con.prepareStatement(BORRAR_CANTA);
			stmt.setInt(1, codCancion);					
			stmt.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
					
	}
		
	@Override
	public void borrarAlbum(int codAlbum) {
		// TODO Auto-generated method stub

		this.openConnection();
		
		try {

			stmt = con.prepareStatement(BORRAR_ALBUM);
			stmt.setInt(1, codAlbum);
			
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	

	@Override
	public void modificarAlbum(String cod, String nombre, String foto, String fecha) {
		// TODO Auto-generated method stub
		
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(MODIFICAR_ALBUM);
			
			stmt.setInt(1, Integer.valueOf(cod));
			stmt.setString(2, nombre);
			stmt.setString(3, foto);
			stmt.setDate(4, Date.valueOf(fecha));
			stmt.setInt(5, Integer.valueOf(cod));
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}		
	
	// saca las canciones de una playlist
	public ArrayList<Cancion> sacarCancionesPlaylist(int codPlaylist) {

		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Cancion ca ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(CANCIONES_PLAYLIST);
			stmt.setInt(1, codPlaylist);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ca = new Cancion();
				ca.setCodCancion(rs.getInt("codCancion"));
				ca.setNombreCancion(rs.getString("nombreCancion"));
				ca.setDuracion(rs.getInt("Duracion"));
				ca.setAudio(rs.getString("Audio"));
				canciones.add(ca);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}

	// sacar la foto de una cancion
	public String sacarFotoCancion(int cod) {

		String foto = null;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_FOTO);
			stmt.setInt(1, cod);

			ResultSet rs = stmt.executeQuery();
			foto = rs.getString("fotoAlbum");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return foto;
	}
	
	public ArrayList<Artista> artistasPorCancion(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = new  ArrayList<>();
		Artista art ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ARTISTA_CANCION);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				art = new Artista();
				
				art.setDni(rs.getString("dni"));
				art.setNombreArtistico(rs.getString("NombreArtistico"));
				art.setCantaAutor(rs.getBoolean("CantaAutor"));
				art.setEstilo(Estilo.valueOf(rs.getString("Estilo")));
				artistas.add(art);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artistas;
	}
	
	@Override
	public ArrayList<Artista> sacarartistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = new  ArrayList<>();
		Artista art ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ARTISTAS);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				art = new Artista();
				art.setDni(rs.getString("Dni"));
				art.setNombreArtistico(rs.getString("NombreArtistico"));
				art.setCantaAutor(rs.getBoolean("CantaAutor"));
				art.setEstilo(Estilo.valueOf(rs.getString("Estilo")));
				artistas.add(art);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return artistas;
	}
	

	@Override
	public ArrayList<Cancion> sacarCanciones() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Cancion ca ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_CANCIONES);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ca = new Cancion();
				ca.setCodCancion(rs.getInt("codCancion"));
				ca.setNombreCancion(rs.getString("nombreCancion"));
				ca.setDuracion(rs.getInt("Duracion"));
				ca.setAudio(rs.getString("Audio"));
				ca.setCodAlbum(rs.getInt("codAlbum"));
				
				canciones.add(ca);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}
	
	@Override
	public ArrayList<Cancion> sacarCancionesAlbum(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Cancion ca ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_CANCIONES);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ca = new Cancion();
				ca.setCodCancion(rs.getInt("codCancion"));
				ca.setNombreCancion(rs.getString("nombreCancion"));
				ca.setDuracion(rs.getInt("Duracion"));
				ca.setAudio(rs.getString("Audio"));
				ca.setCodAlbum(rs.getInt("codAlbum"));
				
				canciones.add(ca);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}
	
	
	@Override
	public void meterAlbum(int codAlbum, String nombre, String foto, String fecha) {
	    this.openConnection();

	    try {
	        stmt = con.prepareStatement(INSERT_ALBUM);
	        stmt.setInt(1, codAlbum);
	        stmt.setString(2, nombre);
	        stmt.setString(3, foto);
	        
	        // Parsear la fecha de String a Date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date fechaDate = sdf.parse(fecha);
	        
	        // Establecer la fecha en el PreparedStatement
	        stmt.setDate(4, new java.sql.Date(fechaDate.getTime()));

	        stmt.executeUpdate();

	    } catch (SQLException | ParseException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public int consultarNumAlbum() {

		int NumAlbum = 0;
		this.openConnection();
		try {

			stmt = con.prepareStatement(CONSULTA_NUMALBUM);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				NumAlbum=rs.getInt("count(codAlbum)");
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return NumAlbum;
	}
	
	public int sacarNumeroDePlayList() {
		int num = 0;
		try {
			this.openConnection();
			stmt = con.prepareStatement(NUMERO_PLAYLIST);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            num = rs.getInt(1); // Utiliza el índice de la columna en lugar del nombre
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public int sacarNumeroDeAlbum() {
		int num = 0;
		try {
			this.openConnection();
			stmt = con.prepareStatement(NUMERO_ALBUM);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            num = rs.getInt(1); // Utiliza el índice de la columna en lugar del nombre
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return num;
		
	}
	public Playlist consultaPlaylist(int cod) {
	    Playlist play = null; // Inicializamos la playlist como null
	    
	    try {
	        this.openConnection();
	        stmt = con.prepareStatement(CONSULTA_PLAYLIST); // CORREGIDO: Se asume que CONSULTA_PLAYLIST es la consulta correcta
	        stmt.setInt(1, cod);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            play = new Playlist(); // Creamos una nueva instancia de Playlist solo si encontramos resultados
	            play.setCodPlaylist(rs.getInt("codPlayList")); // Suponiendo que "cod_playlist" es el nombre de la columna en la tabla
	            // Aquí puedes añadir más asignaciones de propiedades de la playlist si es necesario
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejamos la excepción imprimiendo el rastreo de la pila, pero podrías querer un manejo más sofisticado dependiendo del contexto
	    } finally { // Añadimos un bloque finally para asegurarnos de cerrar la conexión incluso si hay una excepción
	        try {
	            closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return play; // Devolvemos la playlist, que puede ser null si no se encontraron resultados
	}

	public Playlist sacarPlaylist(int cod) {

		Playlist play = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(TODAS_PLAYLIST);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				play = new Playlist();
				play.setCodPlaylist(rs.getInt("codPlayList"));
				play.setNombrePlaylist(rs.getString("nombrePlayList"));
				play.setFotoPlaylist(rs.getString("fotoPlayList"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return play;
	}
	
	@Override
	public Album sacarAlbum(int cod) {
		// TODO Auto-generated method stub
		Album album = new Album();
		@SuppressWarnings("unused")
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ALBUM);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				album.setCodAlbum(rs.getInt("codAlbum"));
				album.setNombreAlbum(rs.getString("nombreAlbum"));
				album.setFotoAlbum(rs.getString("fotoAlbum"));
				album.setFechaLan(rs.getDate("fechaLan"));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return album;
	}
	
	
	@Override
	public ArrayList<Album> sacarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = new ArrayList<>();
		Album album ;
		@SuppressWarnings("unused")
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ALBUMES);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				album = new Album();
				album.setCodAlbum(rs.getInt("codAlbum"));
				album.setNombreAlbum(rs.getString("nombreAlbum"));
				album.setFotoAlbum(rs.getString("fotoAlbum"));
				album.setFechaLan(rs.getDate("fechaLan"));
				albumes.add(album);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return albumes;
	}

	public void insertarPlaylist(int cod,String nombre,String foto,String dni){
		
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERT_PLAYLIST);
			stmt.setInt(1, cod);
			stmt.setString(2, nombre);
			stmt.setString(3, foto);
			stmt.setString(4, dni);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	

	public ArrayList<Playlist> sacararPlaylists() {
		// TODO Auto-generated method stub
		ArrayList<Playlist> plays = new  ArrayList<>();
		Playlist play ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(TODAS_PLAYLIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				play = new Playlist();
				play.setNombrePlaylist(rs.getString("nombrePlayList"));
				play.setFotoPlaylist(rs.getString("fotoPlayLis"));
				play.setDni(rs.getString("dni"));
				plays.add(play);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return plays;
	}
	//Añadir canciones a una playlist 
	public void insertarPertenece(int cod,int cod2){
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(INSERT_PERTENECE);
			stmt.setInt(1, cod);
			stmt.setInt(2, cod2);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	
	public void borrarArtista(String dni) {
		// TODO Auto-generated method stub
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(BORRAR_ARTISTA);
			stmt.setString(1, dni);
			
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}

	public void registrarArtista(String dni, String nombreArtistico, boolean cantaAutor, String estilo) {
		// TODO Auto-generated method stub
		this.openConnection();

		try {

			stmt = con.prepareStatement(INSERT_ARTISTA);
			stmt.setString(1, dni);
			stmt.setString(2, nombreArtistico);
			stmt.setBoolean(3, cantaAutor);
			stmt.setString(4, estilo);

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
	public ArrayList<Artista> sacarArtista(){
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = new ArrayList<>();
		Artista artista ;
		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ARTISTAS);
			ResultSet rs = stmt.executeQuery();
					
			while (rs.next()) {
				artista = new Artista();
				artista.setDni(rs.getString("Dni"));
				artista.setNombreArtistico(rs.getString("nombreArtistico"));
				artista.setCantaAutor(rs.getBoolean("CantaAutor"));
				for(Estilo e: Estilo.values()) 
					if (e.toString().equalsIgnoreCase(rs.getString("Estilo"))) 
						artista.setEstilo(e);
				artistas.add(artista);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artistas;
	}

	@Override
	public ArrayList<Persona> sacarPersonas() {
		// TODO Auto-generated method stub
				ArrayList<Persona> personas = new ArrayList<>();
				Persona persona ;
				this.openConnection();

				try {

					stmt = con.prepareStatement(SACAR_PERSONAS_ARTISTAS);
					ResultSet rs = stmt.executeQuery();
							
					while (rs.next()) {
						persona = new Persona();
						persona.setDni(rs.getString("Dni"));
						persona.setNombrePersona(rs.getString("nombrePersona"));
						persona.setApellidoPersona(rs.getString("apellidoPersona"));
						persona.setPais(rs.getString("pais"));
						persona.setEdad(rs.getInt("Edad"));
						personas.add(persona);
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return personas;
	}

	@Override
	public void modificarPersona(String dni, String nombre, String apellido, String pais, int edad) {
		// TODO Auto-generated method stub
		
		
		this.openConnection();
				
		try {
			stmt = con.prepareStatement(MODIFICAR_PERSONA);
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, pais);
			stmt.setInt(4, Integer.valueOf(edad));
			stmt.setString(5, dni);
			stmt.setString(6, dni);
			stmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void modificarArtista(String dni, String nombre, String estilo, boolean cantaAutor) {
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(MODIFICAR_ARTISTA);
			stmt.setString(1, nombre);
			stmt.setBoolean(2, cantaAutor);
			stmt.setString(3, estilo);
			stmt.setString(4, dni);
			stmt.setString(5, dni);

			stmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	public Playlist sacarPlaylist(int cod, String dni) {

		Playlist play = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(CONSULTA_PLAYLIST);
			stmt.setInt(1, cod);
			stmt.setString(2, dni);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				play = new Playlist();
				play.setCodPlaylist(rs.getInt("codPlayList"));
				play.setNombrePlaylist(rs.getString("nombrePlayList"));
				play.setFotoPlaylist(rs.getString("fotoPlayList"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return play;
	}
	
	public void modificarPlaylist(int cod,String nombre,String foto){
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(MODIFICAR_PLAYLIST);
			stmt.setString(1, nombre);
			stmt.setString(2, foto);
			stmt.setInt(3, cod);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	
	public void eliminarplaylist(int cod){
		
		this.openConnection();
		
		try {

			stmt = con.prepareStatement(ELIMINAR_PLAYLIST);
			stmt.setInt(1, cod);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	public void eliminarPertenece(int cod){
		
		this.openConnection();
		
		try {
	
			stmt = con.prepareStatement(ELIMINAR_PERTENECE);
			stmt.setInt(1, cod);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}
	
	public ArrayList<Cancion> CancionesDePlaylist(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Cancion ca ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(CANCIONES_POR_PLAYLIST);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ca = new Cancion();
				ca.setCodCancion(rs.getInt("codCancion"));
				ca.setNombreCancion(rs.getString("nombreCancion"));
				ca.setDuracion(rs.getInt("Duracion"));
				ca.setAudio(rs.getString("Audio"));
				ca.setCodAlbum(rs.getInt("codAlbum"));
				
				canciones.add(ca);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}

	@Override
	public ArrayList<Playlist> sacarPlaylists() {
		// TODO Auto-generated method stub
		ArrayList<Playlist> plays = new  ArrayList<>();
		Playlist play ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SACAR_ARTISTAS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				play = new Playlist();
				play.setNombrePlaylist(rs.getString("nombrePlayList"));
				play.setFotoPlaylist(rs.getString("fotoPlayLis"));
				play.setDni(rs.getString("dni"));
				plays.add(play);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return plays;
	}

	public ArrayList<Cancion> CancionesDeAlbum(int cod) {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		Cancion ca ;

		this.openConnection();

		try {

			stmt = con.prepareStatement(CANCIONES_POR_ALBUM);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ca = new Cancion();
				ca.setCodCancion(rs.getInt("codCancion"));
				ca.setNombreCancion(rs.getString("nombreCancion"));
				ca.setDuracion(rs.getInt("Duracion"));
				ca.setAudio(rs.getString("Audio"));
				ca.setCodAlbum(rs.getInt("codAlbum"));
				
				canciones.add(ca);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return canciones;
	}

}