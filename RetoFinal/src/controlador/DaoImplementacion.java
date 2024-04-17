package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cancion;
import modelo.Usuario;

public class DaoImplementacion implements Dao{
	
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	//consultas usuarios
	
	private final String CONSULTA_USUARIO = "select * from Usuario where  Contraseina=? and NombreUsuario=?";
	
	private final String INSERT_PERSONA =  "insert into persona(Dni,nombrePersona,apellidoPersona,pais,edad) values (?,?,?,?,?)";
	private final String INSERT_USUARIO =  "insert into usuario(Dni,contraseina,NombreUsuario) values (?,?,?)";
	
	//consultas canciones
	private final String SACAR_CANCIONES = "select * from Cancion";
	private final String CANCIONES_PLAYLIST = "select c.* from cancion c, pertenece p where c.codCancion=p.codCancion and p.codPlayList = ?";
	
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
	
	

}
