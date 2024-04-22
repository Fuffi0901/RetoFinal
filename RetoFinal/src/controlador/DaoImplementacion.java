package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelo.Album;
import modelo.Cancion;
import modelo.Playlist;
import modelo.Usuario;

public class DaoImplementacion implements Dao{
	
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	//consultas usuarios
	private final String CONSULTA_PLAYLIST = "select * from playlist where codPlayList=?";
	private final String NUMERO_ALBUM = "select count(*) from album";
	private final String NUMERO_PLAYLIST = "select count(*) from playlist";
	private final String CONSULTA_USUARIO = "select * from Usuario where  Contraseina=? and NombreUsuario=?";
	private final String CONSULTA_NUMALBUM = "select count(codAlbum) from album";
	private final String INSERT_PERSONA =  "insert into persona(Dni,nombrePersona,apellidoPersona,pais,edad) values (?,?,?,?,?)";
	private final String INSERT_USUARIO =  "insert into usuario(Dni,contraseina,NombreUsuario) values (?,?,?)";
	private final String INSERT_ALBUM = "insert into album(codAlbum,nombreAlbum,fotoAlbum,fechaLan) values (?,?,?,?)";
	
	//consultas canciones
	private final String SACAR_CANCIONES = "select * from Cancion";
	private final String CANCIONES_PLAYLIST = "select c.* from cancion c, pertenece p where c.codCancion=p.codCancion and p.codPlayList = ?";
	private final String TODAS_PLAYLIST = "select * from playlist where codPlayList = ?";
	private final String TODAS_ALBUM = "select * from album where codAlbum = ?";
	private final String SACAR_FOTO = "select fotoAlbum from Album where codAlbum = (select codAlbum from Cancion where codAlbum=?)";
	
	
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

	@Override
	public ArrayList<Cancion> sacarCanciones(String nombre, String contraseña) {
		// TODO Auto-generated method stub
		return null;
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
		return num;
		
	}
	
	@Override
	public void crearAlbum(int codAlbum, String fecha, String foto, String nombre) {
		// TODO Auto-generated method stub
		this.openConnection();

		try {

			stmt = con.prepareStatement(INSERT_ALBUM);
			stmt.setInt(1, codAlbum);
			stmt.setString(2, nombre);
			stmt.setString(3, foto);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date fechaDate = null;
				try {
					fechaDate = (Date) sdf.parse(fecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // Establecer la fecha en el PreparedStatement
	            stmt.setDate(4, new java.sql.Date(fechaDate.getTime()));

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
	            play.setCodPlaylist(rs.getInt("codPlayL,fist")); // Suponiendo que "cod_playlist" es el nombre de la columna en la tabla
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
	
	public Album sacarAlbum(int cod) {

		Album album = null;

		this.openConnection();

		try {
			stmt = con.prepareStatement(TODAS_ALBUM);
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				album = new Album();
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
	public List<Playlist> getAllPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
