package inne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import widok.WidokWzorzec;

public class Polaczenia {

	final static String JDBC_DRIVER = "org.postgresql.Driver";
	final static String DB_URL = "jdbc:postgresql://localhost:5433/cwiczenie2";
	final static String USER = "postgres";
	final static String PASS = "adminLWW";
	public static java.sql.Connection lacznosc = null;
	public static Connection OtworzPolaczenie() {

		try {
			lacznosc = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		return lacznosc;
	}

	public static void ZaladujSterownik() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
	}

	public static void ZamknijPolaczenie() {
		try {
			lacznosc.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}
	}
}
