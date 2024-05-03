package controlador;

import vista.Inicio_Sesion;

public class Main {

	public static void main(String[] args) {
			/**
			 * Launch the application.
			 */
			Dao dao = new DaoImplementacion();
			
			Inicio_Sesion ven = new Inicio_Sesion(dao);
			ven.setVisible(true);
		}
}
