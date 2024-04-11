package controlador;

import vista.Inicio_Sesion;
import controlador.Dao;
import controlador.DaoImplementacion;

public class Main {

	public static void main(String[] args) {
		/**
		 * Launch the application.
		 */
		Dao dao = new DaoImplementacion();
		
		Inicio_Sesion ven = new Inicio_Sesion(null, true,dao);
		ven.setVisible(true);
	}

}
